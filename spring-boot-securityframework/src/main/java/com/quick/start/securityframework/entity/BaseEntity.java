package com.quick.start.securityframework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类.
 */
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 8925514045582235838L;
    private ID id;
    private Date createTime = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updateTime = new Date();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
