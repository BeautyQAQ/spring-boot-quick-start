package com.quick.start.webflux;

import cn.hutool.core.lang.Assert;
import com.quick.start.webflux.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootTest
@AutoConfigureWebFlux
@AutoConfigureWebTestClient
public class UserControllerTest {

    @Resource
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void testGet(){
        webTestClient.get().uri("/users/get?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody().json("""
                        {
                            "id": 1,
                            "username": "username:1"
                        }""");
    }

    @Test
    public void testGet2(){
        webTestClient.get().uri("/users2/get?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody().json("""
                        {
                            "id": 1,
                            "username": "username:1"
                        }""");
    }

    @Test
    public void testAdd(){
        Map<String, Object> params = new HashMap<>();
        params.put("username", "a");
        params.put("password", "123");
        webTestClient.post().uri("/users/add")
                .bodyValue(params)
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody().json("1");
    }

    /**
     * 发送文件的测试
     */
    @Test
    public void testAdd2(){
        // Form Data 数据，需要这么拼凑
        BodyInserters.FormInserter<String> formData = BodyInserters.fromFormData("username", "a").with("password", "123");
        webTestClient.post().uri("/users/add2")
                .body(formData)
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody().json("1");
    }

    @Test
    public void testUpdate(){
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("username", "a");
        // 修改用户
        webTestClient.post().uri("/users/update")
                .bodyValue(params)
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody(Boolean.class) // 期望返回值类型是 Boolean
                .consumeWith((Consumer<EntityExchangeResult<Boolean>>) result -> // 通过消费结果，判断符合是 true 。
                        Assert.isTrue(Boolean.TRUE.equals(result.getResponseBody()), "返回结果需要为true"));
    }

    @Test
    public void testDelete(){
        // 删除用户
        webTestClient.post().uri("/users/delete?id=1")
                .exchange() // 执行请求
                .expectStatus().isOk() // 判断状态码 200
                .expectBody(Boolean.class) // 期望返回值类型是 Boolean
                .isEqualTo(true); // 这样更加简洁一些
//                .consumeWith((Consumer<EntityExchangeResult<Boolean>>) result -> // 通过消费结果，判断符合是 true 。
//                        Assert.isTrue(Boolean.TRUE.equals(result.getResponseBody()), "返回结果需要为true"));
    }
}
