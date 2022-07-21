package com.quick.start.securityframework.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryDto implements Serializable {

    private String nickName;

    private String userName;

    private Integer deptId;
}
