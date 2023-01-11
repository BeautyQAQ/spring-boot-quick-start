package com.quick.start.webflux.repository;

import com.quick.start.webflux.dataobject.UserDO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserDO, Integer> {
    Mono<UserDO> findByUsername(String username);
}
