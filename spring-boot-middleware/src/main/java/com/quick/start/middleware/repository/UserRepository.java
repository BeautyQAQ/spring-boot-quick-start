package com.quick.start.middleware.repository;

import com.quick.start.middleware.dao.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

}
