package com.quick.start.webflux.repository;

import com.quick.start.webflux.dataobject.UserDO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveCrudRepository<UserDO, Long> {

    /**
     * 注意，这里的 @Query 注解是 Spring Data R2DBC 自定义的，而不是 JPA 规范中的。
     * 如果我们注释掉 @Query 注解，启动项目会报 "Query derivation not yet supported!" 异常提示。
     * 目前看下来，Spring Data R2DBC 暂时不支持【基于方法名查询】。
     * @param username username
     * @return UserDO
     */
    @Query("SELECT id, username, password, create_time FROM users WHERE username = :username")
    Mono<UserDO> findByUsername(String username);
}
