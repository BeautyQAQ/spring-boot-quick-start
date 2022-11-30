package com.quick.start.code.snippets.controller;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CodeSnippetsController {

    private final static Logger logger = LoggerFactory.getLogger(CodeSnippetsController.class);

    @Resource
    private HttpServletRequest request;

    @GetMapping("/request1")
    public void test1(HttpServletRequest request) {
        logger.info("第一种获取HttpServletRequest的方式, 从方法入参中获取,request = {}", request);
    }

    @GetMapping("/request2")
    public void test2() {
        // 从请求上下文里获取Request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest contextRequest = requestAttributes.getRequest();
        logger.info("第二种获取HttpServletRequest的方式, 从请求上下文里获取Request对象,request = {}", contextRequest);
    }

    @GetMapping("/request3")
    public void test3() {
        logger.info("第三种获取HttpServletRequest的方式, 依赖注入,request = {}", request);
    }

    /**
     * Collectors.toMap 当value为空时，报空指针
     */
    @GetMapping("/request4")
    public void test4() {
        logger.info("stream中toMap时空指针");
        List<User> list = new ArrayList<>();
        list.add(new User().setName("a").setSex("男"));
        list.add(new User().setName("b").setSex("女"));
        list.add(new User().setName("c").setSex(null));

        // 若value为空，设置默认值""
        Map<String, String> map1 = list.stream().collect(
                Collectors.toMap(User::getName, i-> Optional.ofNullable(i.getSex()).orElse(""), (v1, v2) -> v1));

        // 调用hashMap putAll方法， 注意key相同时，value会覆盖。
        Map<String, String> map2 = list.stream().collect(
                HashMap::new, (m, i) -> m.put(i.getName(), i.getSex()), HashMap::putAll);

        logger.info("map1={}",JSONUtil.toJsonPrettyStr(map1));
        logger.info("map2={}",JSONUtil.toJsonPrettyStr(map2));
    }

    private class User{
        private String name;
        private String sex;

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public String getSex() {
            return sex;
        }

        public User setSex(String sex) {
            this.sex = sex;
            return this;
        }
    }
}
