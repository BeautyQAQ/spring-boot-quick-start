package com.quick.start.code.snippets.controller;

import com.quick.start.code.snippets.DO.UserDO;
import com.quick.start.code.snippets.service.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Resource
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGet() throws Exception {
        // Mock UserService 的 get 方法
        Mockito.when(userService.get(any())).thenReturn(new UserDO().setId(1).setUsername("username:1").setPassword("password:1"));
        // mock查询用户
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/user/get?id=1"));
        // 校验相应状态码
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // 校验相应内容方式一: 全部匹配
        resultActions.andExpect(MockMvcResultMatchers.content().json("""
                {
                    "id": 1,
                    "username": "username:1",
                    "password": "password:1"
                }""", true));
        // 校验响应内容方式二：逐个字段匹配
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("id", IsEqual.equalTo(1)));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("username", IsEqual.equalTo("username:1")));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("password", IsEqual.equalTo("password:1")));
    }
}
