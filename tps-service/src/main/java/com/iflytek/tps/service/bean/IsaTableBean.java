package com.iflytek.tps.service.bean;


public class IsaTableBean {
    private Integer id;//自增ID
    private String fileName;//文件名称
    private String fileDate;//文件日期 -年月日时分秒
    private String fileDateDay;//文件日期-年月日
    private String ip;//ip地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDateDay() {
        return fileDateDay;
    }

    public void setFileDateDay(String fileDateDay) {
        this.fileDateDay = fileDateDay;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
