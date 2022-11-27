package com.quick.start.springmvc;

import com.quick.start.springmvc.controller.UserController;
import com.quick.start.springmvc.service.UserService;
import com.quick.start.springmvc.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

/**
 * ‘@WebMvcTest’ 注解，是包含了 @AutoConfigureMockMvc 的组合注解，所以它会自动化配置我们稍后注入的 MockMvc Bean 对象 mvc 。
 * 在后续的测试中，都是通过 mvc 调用后端 API 接口。但是！每一次调用后端 API 接口，并不会执行真正的后端逻辑，而是走的 Mock 逻辑。
 * 也就是说，整个逻辑，走的是单元测试，只会启动一个 Mock 的 Spring 环境。
 */
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @Resource
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGet2() throws Exception {
        // Mock UserService 的 get 方法
        System.out.println("before mock:" + userService.get(1)); // <1.1>
        Mockito.when(userService.get(1)).thenReturn(
                new UserVO().setId(1).setUsername("username:1")); // <1.2>
        System.out.println("after mock:" + userService.get(1)); // <1.3>

        // 查询用户列表
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/users/v2/1"));
        // 校验结果
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()); // 响应状态码 200
        resultActions.andExpect(MockMvcResultMatchers.content().json("""
                {
                    "id": 1,
                    "username": "username:1"
                }""")); // 响应结果
    }

}
