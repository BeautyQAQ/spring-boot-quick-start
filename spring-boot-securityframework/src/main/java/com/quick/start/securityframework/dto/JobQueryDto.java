package com.quick.start.securityframework.dto;

import java.io.Serializable;

public class JobQueryDto implements Serializable {
    private String queryName;

    private Integer queryStatus;

    public JobQueryDto() {
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Integer getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(Integer queryStatus) {
        this.queryStatus = queryStatus;
    }
}
