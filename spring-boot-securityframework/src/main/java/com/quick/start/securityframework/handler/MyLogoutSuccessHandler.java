package com.quick.start.securityframework.handler;

import com.alibaba.fastjson.JSON;
import com.quick.start.securityframework.common.Result;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
        throws IOException, ServletException {
        //修改编码格式
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");
        Result r = Result.ok().message("退出成功");
        httpServletResponse.getWriter().write(JSON.toJSONString(r));
    }
}
