package com.quick.start.webflux.repository;

import com.quick.start.webflux.dataobject.MongoUserDO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoUserRepository extends ReactiveMongoRepository<MongoUserDO, Integer> {
    Mono<MongoUserDO> findByUsername(String username);
}
