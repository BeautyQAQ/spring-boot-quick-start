package com.quick.start.middleware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class TransactionTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
//    @Transactional
    public void test01() {
        // 这里是偷懒，没在 RedisConfiguration 配置类中，设置 stringRedisTemplate 开启事务。
        stringRedisTemplate.setEnableTransactionSupport(true);

        // 执行想要的操作
        stringRedisTemplate.opsForValue().set("tes1:1", "a");
        stringRedisTemplate.opsForValue().set("test:2", "b");
    }
}
