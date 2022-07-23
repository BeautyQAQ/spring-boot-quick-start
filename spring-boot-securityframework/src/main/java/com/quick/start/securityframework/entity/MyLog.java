package com.quick.start.securityframework.entity;

import java.io.Serializable;
import java.util.Date;

public class MyLog implements Serializable {

    private Long logId;

    /** 操作用户 */
    private String userName;

    /** 描述 */
    private String description;

    /** 方法名 */
    private String method;

    /** 请求ip */
    private String ip;

    /** 异常详细  */
    private String exceptionDetail;

    /** 异常类型 */
    private String type;

    // /** 地址 */
    // private String ipAddress;

    /** 参数*/
    private String params;

    /** 浏览器  */
    private String browser;

    /** 请求耗时 */
    private Long time;

    /** 创建日期 */
    private Date createTime = new Date();

    public MyLog( String type,Long time) {
        this.type = type;
        this.time = time;
    }

    public MyLog() {
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
