package com.quick.start.securityframework.entity;

import java.io.Serial;
import java.io.Serializable;

public class MyRoleMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = 8925514045582235875L;

    private Integer roleId;

    private Integer permissionId;

    public MyRoleMenu() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
