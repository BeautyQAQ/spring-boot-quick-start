package com.quick.start.securityframework.entity;

import java.io.Serial;
import java.io.Serializable;

public class MyRoleDept implements Serializable {

    @Serial
    private static final long serialVersionUID = 8925514042332235875L;

    private Integer roleId;

    private Integer deptId;

    public MyRoleDept() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
