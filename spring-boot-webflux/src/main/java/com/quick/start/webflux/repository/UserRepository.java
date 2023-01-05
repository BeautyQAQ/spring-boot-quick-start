package com.quick.start.webflux.repository;

import com.quick.start.webflux.dataobject.UserDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDO, Integer> {
}
