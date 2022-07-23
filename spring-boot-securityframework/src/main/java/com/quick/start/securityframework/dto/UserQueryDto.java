package com.quick.start.securityframework.dto;


import java.io.Serializable;

public class UserQueryDto implements Serializable {

    private String nickName;

    private String userName;

    private Integer deptId;

    public UserQueryDto() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
