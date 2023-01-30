package com.quick.start.middleware;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@SpringBootTest
public class ScriptTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test01() throws IOException {
        // <1.1> 读取 /resources/lua/compareAndSet.lua 脚本 。注意，需要引入下 commons-io 依赖。
        String  scriptContents = IoUtil.read(getClass().getResourceAsStream("/lua/compareAndSet.lua"), StandardCharsets.UTF_8);
        // String  scriptContents = IOUtils.toString(getClass().getResourceAsStream("/lua/compareAndSet.lua"), "UTF-8");
        // <1.2> 创建 RedisScript 对象
        RedisScript<Long> script = new DefaultRedisScript<>(scriptContents, Long.class);
        // <2> 执行 LUA 脚本
        Long result = stringRedisTemplate.execute(script, Collections.singletonList("ScriptTest:1"), "haha", "hehe");
        System.out.println(result);
    }
}
