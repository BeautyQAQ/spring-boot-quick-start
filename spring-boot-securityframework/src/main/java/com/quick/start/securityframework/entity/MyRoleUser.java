package com.quick.start.securityframework.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class MyRoleUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 8545514045582235838L;

    private Integer userId;

    private Integer roleId;
}
