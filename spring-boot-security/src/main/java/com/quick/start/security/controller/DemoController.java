package com.quick.start.security.controller;

import javax.annotation.security.PermitAll;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Security 注解测试Controller.
 * ·@EnableGlobalMethodSecurity(prePostEnabled = true)·
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @PermitAll
    @GetMapping("/echo")
    public String demo() {
        return "示例返回";
    }

    @GetMapping("/home")
    public String home() {
        return "我是首页";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "我是管理员";
    }

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    @GetMapping("/normal")
    public String normal() {
        return "我是普通用户";
    }
}
