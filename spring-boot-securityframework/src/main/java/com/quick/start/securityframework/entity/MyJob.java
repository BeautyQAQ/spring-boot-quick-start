package com.quick.start.securityframework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyJob extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 8925514045582234222L;

    private Integer jobId;

    private String jobName;

    private Integer status;

    private Integer sort;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    private boolean flag = false;
}
