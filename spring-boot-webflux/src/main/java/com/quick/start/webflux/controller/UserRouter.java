package com.quick.start.webflux.controller;

import cn.hutool.core.util.StrUtil;
import com.quick.start.webflux.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * 基于函数式编程方式的Reactor
 */
@Configuration
public class UserRouter {

    public static final Logger log = LoggerFactory.getLogger(UserRouter.class);

    @Bean
    public RouterFunction<ServerResponse> userListRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/list"), new HandlerFunction<ServerResponse>() {

            @Override
            public Mono<ServerResponse> handle(ServerRequest request) {
                // 查询列表
                List<UserVO> result = new ArrayList<>();
                result.add(new UserVO().setId(1).setUsername("c"));
                result.add(new UserVO().setId(2).setUsername("d"));
                result.add(new UserVO().setId(3).setUsername("e"));
                return ServerResponse.ok().bodyValue(result);
            }
        });
    }

    @Bean
    public RouterFunction<ServerResponse> userGetRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("users2/get"), new HandlerFunction<ServerResponse>() {
            @Override
            public Mono<ServerResponse> handle(ServerRequest request) {
                // 获取编号
                Integer id = request.queryParam("id").map(s -> StrUtil.isEmpty(s) ? null : Integer.valueOf(s)).get();
                UserVO userVO = new UserVO().setId(id).setUsername("username:1");
                return ServerResponse.ok().bodyValue(userVO);
            }
        });
    }

    @Bean
    public RouterFunction<ServerResponse> demoRouterFunction() {
        return route(GET("/users2/demo"), request -> ok().bodyValue("demo"));
    }

    /**
     * 在基于函数式编程方式中，可以使用如下的方式，实现对每个路由的过滤处理
     * @return RouterFunction ServerResponse
     */
    @Bean
    public RouterFunction<ServerResponse> domo2RouterFunction() {
        return route(GET("/user2/demo2"), request -> ok().bodyValue("demo")).filter(new HandlerFilterFunction<ServerResponse, ServerResponse>() {
            @Override
            public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
                return next.handle(request).doOnSuccess(new Consumer<ServerResponse>() { // 执行成功后回调

                    @Override
                    public void accept(ServerResponse serverResponse) {
                        log.info("[accept][执行成功]");
                    }
                });
            }
        });

    }
}
