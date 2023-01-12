package com.quick.start.middleware;

import com.quick.start.middleware.dataobject.ProductDO;
import com.quick.start.middleware.repository.MongoProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MongoProductRepositoryTest {

    @Resource
    private MongoProductRepository mongoProductRepository;

    @Test
    public void testInsert(){
        ProductDO productDO = new ProductDO();
        productDO.setName("测试");
        mongoProductRepository.insert(productDO);
        System.out.println(productDO.getId());
    }
}
