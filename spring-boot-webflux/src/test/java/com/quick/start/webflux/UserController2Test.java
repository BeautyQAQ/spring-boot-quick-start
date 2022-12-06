package com.quick.start.webflux;

import com.quick.start.webflux.controller.UserController;
import com.quick.start.webflux.service.UserService;
import com.quick.start.webflux.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;

/**
 * 在类上添加 @WebFluxTest 注解，并且传入的是 UserController 类，表示我们要对 UserController 进行单元测试。
 * 同时，@WebFluxTest 注解，是包含了 @UserController 的组合注解，
 * 所以它会自动化配置我们稍后注入的 WebTestClient Bean 对象 mvc 。
 * 在后续的测试中，我们会看到都是通过 webClient 调用后端 API 接口。
 * 但是！每一次调用后端 API 接口，并不会执行真正的后端逻辑，而是走的 Mock 逻辑。
 * 也就是说，整个逻辑，走的是单元测试，只会启动一个 Mock 的 Spring 环境。
 */
@WebFluxTest(UserController.class)
public class UserController2Test {

    public static final Logger log = LoggerFactory.getLogger(UserController2Test.class);

    @Resource
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void testGet3(){
        // Mock UserService的get方法 此时的 userService 是通过 Mockito 来 Mock 出来的对象，其所有调用它的方法，返回的都是空。
        log.info("Before mock: " + userService.get(1));
        Mockito.when(userService.get(any())).thenReturn(new UserVO().setId(1).setUsername("username:1"));
        log.info("After mock: " + userService.get(1));
        webTestClient.get().uri("/users/v2/get?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody().json("""
                        {
                            "id": 1,
                            "username": "username:1"
                        }""");
    }
}
