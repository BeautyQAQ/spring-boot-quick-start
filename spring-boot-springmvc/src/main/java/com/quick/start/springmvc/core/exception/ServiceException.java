package com.quick.start.springmvc.core.exception;

import com.quick.start.springmvc.core.constants.ServiceExceptionEnum;

public final class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        // 使用父类的 message 字段
        super(serviceExceptionEnum.getMessage());
        // 设置错误码
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
