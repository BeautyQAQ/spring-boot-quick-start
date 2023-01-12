package com.quick.start.webflux.repository;

import com.quick.start.webflux.dataobject.EsUserDO;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Mono;

public interface EsUserRepository extends ReactiveElasticsearchRepository<EsUserDO, Integer> {
    Mono<EsUserDO> findByUsername(String username);
}
