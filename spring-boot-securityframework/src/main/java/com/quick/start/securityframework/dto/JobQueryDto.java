package com.quick.start.securityframework.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobQueryDto implements Serializable {
    private String queryName;

    private Integer queryStatus;
}
