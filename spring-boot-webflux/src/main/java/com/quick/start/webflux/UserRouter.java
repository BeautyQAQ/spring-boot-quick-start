package com.quick.start.webflux;

import cn.hutool.core.util.StrUtil;
import com.quick.start.webflux.vo.UserVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * 基于函数式编程方式的Reactor
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userListRouterFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/users2/list"), new HandlerFunction<ServerResponse>(){

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
    public RouterFunction<ServerResponse> userGetRouterFunction(){
        return RouterFunctions.route(RequestPredicates.GET("user2/get"), new HandlerFunction<ServerResponse>() {
            @Override
            public Mono<ServerResponse> handle(ServerRequest request) {
                // 获取编号
                Integer id = request.queryParam("id").map(s -> StrUtil.isEmpty(s) ? null : Integer.valueOf(s)).get();
                UserVO userVO = new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
                return ServerResponse.ok().bodyValue(userVO);
            }
        });
    }

    @Bean
    public RouterFunction<ServerResponse> demoRouterFunction(){
        return route(GET("/users2/demo"), request -> ok().bodyValue("demo"));
    }
}
