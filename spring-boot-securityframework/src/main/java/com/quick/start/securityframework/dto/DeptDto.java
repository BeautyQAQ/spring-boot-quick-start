package com.quick.start.securityframework.dto;

import com.quick.start.securityframework.entity.BaseEntity;

public class DeptDto extends BaseEntity {

    private Integer id;

    private Integer parentId;

    private String checkArr = "0";

    private String title;

    public DeptDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
