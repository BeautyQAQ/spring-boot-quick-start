package com.quick.start.webflux;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureWebFlux
@AutoConfigureWebTestClient
public class UserControllerTest {
    @Resource
    private WebTestClient webTestClient;
}
