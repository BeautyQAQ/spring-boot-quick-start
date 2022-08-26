package com.quick.start.securityframework.entity;

import java.io.Serial;

public class MyJob extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 8925514045582234222L;

    private Integer jobId;

    private String jobName;

    private Integer status;

    private Integer sort;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    private boolean flag = false;

    public MyJob() {}

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
