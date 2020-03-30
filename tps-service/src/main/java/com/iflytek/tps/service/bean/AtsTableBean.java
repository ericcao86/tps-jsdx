package com.iflytek.tps.service.bean;

public class AtsTableBean {

    private Integer id;//自增Id
    private String fileName;//文件名称
    private String fileDate;//文件日期-年月日时分秒
    private String status;//转写正确标识
    private String ip;//服务器地址
    private String fileDateDay;//文件日期-年月日

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFileDateDay() {
        return fileDateDay;
    }

    public void setFileDateDay(String fileDateDay) {
        this.fileDateDay = fileDateDay;
    }
}
