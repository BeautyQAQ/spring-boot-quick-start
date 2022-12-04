package com.quick.start.code.snippets.controller;

import cn.hutool.json.JSONUtil;
import com.quick.start.code.snippets.dataobject.UserDo;
import com.quick.start.code.snippets.dataobject.UserDo01;
import com.quick.start.code.snippets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
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
    public UserDo get(@RequestParam("id") Integer id) {
        // 查询并返回用户
        return userService.get(id);
    }

    @GetMapping("/test")
    public String test() {
        UserDo01 userDo01 = new UserDo01();
        userDo01.setUsername("123455");
        log.info(JSONUtil.toJsonPrettyStr(userDo01));
        // 查询并返回用户
        return "test_hotSwap_test";
    }
}
