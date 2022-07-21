package com.quick.start.securityframework.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class MyUserJob implements Serializable {

    @Serial
    private static final long serialVersionUID = 8925514045582235321L;

    private Integer userId;

    private Integer jobId;
}
