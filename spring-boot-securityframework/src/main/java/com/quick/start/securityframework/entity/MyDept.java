package com.quick.start.securityframework.entity;

import java.io.Serial;

public class MyDept extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 8925514045582235633L;

    private Integer deptId;

    private Integer parentId;

    private String ancestors;

    private String deptName;

    private Integer sort;

    private Integer status;

    public MyDept() {}

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return (
            "MyDept{" +
            "deptId=" +
            deptId +
            ", parentId=" +
            parentId +
            ", ancestors='" +
            ancestors +
            '\'' +
            ", deptName='" +
            deptName +
            '\'' +
            ", sort=" +
            sort +
            ", status=" +
            status +
            '}'
        );
    }
}
