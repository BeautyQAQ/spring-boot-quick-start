package com.quick.start.securityframework.dto;


public class LogQuery {

    private String userName;

    private String logType;
    // private List<Timestamp> createTime;


    public LogQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
