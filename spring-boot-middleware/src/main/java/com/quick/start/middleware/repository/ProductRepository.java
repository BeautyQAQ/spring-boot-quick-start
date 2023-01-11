package com.quick.start.middleware.repository;

import com.quick.start.middleware.dataobject.ProductDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDO, Integer> {
}
