package com.iflytek.tps.service;

import com.iflytek.tps.beans.IpsProperties;
import com.iflytek.tps.config.AtsConfig;
import com.iflytek.tps.config.IsaConfig;
import com.iflytek.tps.foun.dto.AppResponse;
import com.iflytek.tps.foun.util.SFTPUtil;
import com.iflytek.tps.service.bean.AtsTableBean;
import com.iflytek.tps.service.bean.IsaTableBean;
import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

@Service
public class LogOperService {

    @Autowired
    private AtsConfig atsConfig;

    @Autowired
    private IsaConfig isaConfig;

    private static Logger logger = LoggerFactory.getLogger(LogOperService.class);

    private List<IpsProperties> pros;

    private static final String SUCCESS = "\"ls\":true";

    @Autowired
    private JdbcService jdbcService;



    public AppResponse<String> readLog(String type){
        if(type.equals("ats")){
            pros = atsConfig.getData();
        }else{
            pros = isaConfig.getData();
        }

        for(IpsProperties p :pros){
            try {
                SFTPUtil util = new SFTPUtil(p.getUsername(),p.getPassword(),p.getIp(),22);
                util.login();
                listDirectory(util,p.getLogAddress(),type,p.getIp());
                util.logout();
                logger.info("服务器ip为{}的日志读取完成",p.getIp());
            }catch (Exception e){
               logger.info("当前发生错误，错误信息 {}",e.getMessage());
            }
        }
        return AppResponse.success("读取日志已完成");

    }

    /**
     * 循环获取文件夹下文件
     * @param util
     * @param path
     * @param type
     * @throws Exception
     */
    private  void listDirectory(SFTPUtil util,String path,String type,String ip) throws Exception{

        Vector<ChannelSftp.LsEntry> listFiles =  (Vector<ChannelSftp.LsEntry>)util.listFiles(path);
        for(ChannelSftp.LsEntry entry : listFiles){
            if(!entry.getAttrs().isDir()){
                //如果是isa的日志,插入isa表中
                if(type.equals("isa")){
                   this.doSaveIsa(jdbcService,path,entry.getFilename(),ip);
                }else{
                    //如果是ats的日志，读取文件并插入数据库
                    InputStream inputStream = util.getSftp().get( path + "/" + entry.getFilename());
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String s;
                    boolean flag = false;
                    //查看日志内容中是否有转写成功结束的标识
                    while ((s = br.readLine()).length() !=0){
                        if(s.contains(SUCCESS)){
                            System.out.println("当前行中含有转写正常结束标识。。。。");
                            flag = true;
                            break;
                        }
                    }
                    String status = flag == true ? "0" : "1";
                    this.doSaveAts(jdbcService,path,entry.getFilename(),ip,status);
                }

            }else{
                if(!entry.getFilename().startsWith(".")){
                    listDirectory(util,path+ "/"+entry.getFilename(),type,ip);
                }
            }
        }

    }

    private int doSaveAts(JdbcService jdbcService,String path,String fileName,String ip,String status){
        AtsTableBean atsTableBean = new AtsTableBean();
        atsTableBean.setFileDate(getDateFromPath("yyyy-MM-dd HH:mm:ss",path));
        atsTableBean.setFileDateDay(getDateFromPath("yyyy-MM-dd",path));
        atsTableBean.setFileName(fileName);
        atsTableBean.setIp(ip);
        atsTableBean.setStatus(status);
        return jdbcService.insertAts(atsTableBean);
    }

    private int doSaveIsa(JdbcService jdbcService,String path,String fileName,String ip){
        IsaTableBean isaTableBean = new IsaTableBean();
        isaTableBean.setFileDate(getDateFromPath("yyyy-MM-dd HH:mm:ss",path));
        isaTableBean.setFileDateDay(getDateFromPath("yyyy-MM-dd",path));
        isaTableBean.setFileName(fileName);
        isaTableBean.setIp(ip);
        return jdbcService.insertIsa(isaTableBean);
    }

    private String getDateFromPath(String type,String path){
        String [] ads = path.split("/");
        String date="";
        if(type.equals("yyyy-MM-dd")){
            date = ads[ads.length-6]+"-"+ads[ads.length-5].substring(0,2)+"-"+ads[ads.length-4];
        }else if(type.equals("yyyy-MM-dd HH:mm:ss")){
            date = ads[ads.length-6]+"-"+ads[ads.length-5].substring(0,2)+"-"+ads[ads.length-4] +" "+ads[ads.length-3]+":"+ads[ads.length-2]+":"+ads[ads.length-1];
        }
        return date;

    }
}
