package com.quick.start.securityframework.entity;

import java.io.Serial;
import java.io.Serializable;

public class MyUserJob implements Serializable {

    @Serial
    private static final long serialVersionUID = 8925514045582235321L;

    private Integer userId;

    private Integer jobId;

    public MyUserJob() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
