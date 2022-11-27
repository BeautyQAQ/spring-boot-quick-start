package com.quick.start.springmvc.core.web;

import com.quick.start.springmvc.core.vo.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 只拦截 Controller 所在包，避免其它类似 swagger 提供的 API 被切面拦截
 */
@ControllerAdvice(basePackages = "com.quick.start.springmvc.core.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    /**
     * 实现 #supports(MethodParameter returnType, Class converterType) 方法，返回 true 。表示拦截 Controller 所有 API 接口的返回结果
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return true
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 实现 #beforeBodyWrite(...) 方法，当返回的结果不是 CommonResult 类型时，则包装成 CommonResult 类型。这里有两点要注意：
     * 第一点，可能 API 接口的返回结果已经是 CommonResult 类型，就无需做二次包装了。
     * 第二点，API 接口既然返回结果，被 GlobalResponseBodyHandler 拦截到，约定就是成功返回，所以使用 CommonResult#success(T data) 方法，进行包装成成功的 CommonResult 返回。
     * 那么，如果我们希望 API 接口是失败的返回呢？我们约定在 Controller 抛出异常。
     * @param body the body to be written
     * @param returnType the return type of the controller method
     * @param selectedContentType the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request the current request
     * @param response the current response
     * @return Object
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 如果已经是 CommonResult 类型，则直接返回
        if (body instanceof CommonResult) {
            return body;
        }
        // 如果不是，则包装成 CommonResult 类型
        return CommonResult.success(body);
    }

}
