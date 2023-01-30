package com.quick.start.middleware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
public class PipelineTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test01() {
        List<Object> results = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                // set 写入
                for (int i = 0; i < 3; i++) {
                    connection.set(String.format("test:%d", i).getBytes(), "aaa".getBytes());
                }

                // get
                for (int i = 0; i < 3; i++) {
                    connection.get(String.format("test:%d", i).getBytes());
                }

                // 返回 null 即可
                return null;
            }
        });

        // 打印结果
        System.out.println(results);
    }
}
