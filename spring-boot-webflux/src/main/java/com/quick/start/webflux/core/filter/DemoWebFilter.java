package com.quick.start.webflux.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
@Order(1)
public class DemoWebFilter implements WebFilter {

    public static final Logger log = LoggerFactory.getLogger(DemoWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 继续执行请求
        return chain.filter(exchange).doOnSuccess(new Consumer<Void>() { // 执行成功后回调
            @Override
            public void accept(Void unused) {
                log.info("[accept][执行成功]");
            }
        });
    }
}
