package com.quick.start.securityframework.entity;

import java.io.Serial;

public class MyRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -6525908145032868837L;

    private Integer roleId;

    private String roleName;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限）
     */
    private String dataScope;

    private String description;

    /**
     * 判断是否为admin用户
     *
     * @return boolean
     */
    public boolean isAdmin() {
        return isAdmin(this.getRoleId());
    }

    public static boolean isAdmin(Integer roleId) {
        return roleId != null && 1L == roleId;
    }

    public MyRole(Integer roleId) {
        this.setRoleId(roleId);
    }

    public MyRole() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
