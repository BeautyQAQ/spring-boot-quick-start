package com.quick.start.code.snippets.controller;

import com.quick.start.code.snippets.DO.UserDO;
import com.quick.start.code.snippets.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public UserDO get(@RequestParam("id") Integer id) {
        // 查询并返回用户
        return userService.get(id);
    }
}
