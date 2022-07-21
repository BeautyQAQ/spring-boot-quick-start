package com.quick.start.securityframework.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class MyRoleDept implements Serializable {

    @Serial
    private static final long serialVersionUID = 8925514042332235875L;

    private Integer roleId;

    private Integer deptId;
}
