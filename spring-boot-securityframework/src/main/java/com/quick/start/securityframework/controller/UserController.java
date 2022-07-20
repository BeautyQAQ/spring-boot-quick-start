package com.quick.start.securityframework.controller;

import com.quick.start.securityframework.common.util.PageTableRequest;
import com.quick.start.securityframework.common.util.Result;
import com.quick.start.securityframework.entity.MyUser;
import com.quick.start.securityframework.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Controller
@RequestMapping("/api/user")
@Api(tags = "系统：用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('user:list')")
    public String index() {
        return "system/user/user";
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "用户列表")
    public Result<MyUser> userList(PageTableRequest pageTableRequest, MyUser myUser) {
        pageTableRequest.countOffset();
        return userService.getAllUsersByPage(pageTableRequest.getOffset(), pageTableRequest.getLimit(), myUser);
    }
}
