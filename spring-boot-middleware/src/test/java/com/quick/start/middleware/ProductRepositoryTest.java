package com.quick.start.middleware;

import com.quick.start.middleware.dataobject.ProductDO;
import com.quick.start.middleware.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ProductRepositoryTest {

    @Resource
    private ProductRepository productRepository;

    @Test
    public void testInsert(){
        ProductDO productDO = new ProductDO();
        productDO.setName("测试");
        productRepository.insert(productDO);
        System.out.println(productDO.getId());
    }
}
