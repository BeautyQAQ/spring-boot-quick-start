package com.quick.start.webflux.config;

import com.quick.start.webflux.dataobject.UserCacheObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {

    /**
     * #commonRedisTemplate() 方法，创建了通用的 ReactiveRedisTemplate Bean 对象，名字为 "commonRedisTemplate" 。
     * 为什么说它是通用的呢？因为其 value 的序列化使用的是通用的 GenericJackson2JsonRedisSerializer 。
     * @param factory factory
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> commonRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, Object> serializationContext =
                RedisSerializationContext.<String, Object>newSerializationContext(RedisSerializer.string())
                        .value(RedisSerializer.json()) // 创建通用的 GenericJackson2JsonRedisSerializer 作为序列化
                        .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    /**
     * #userRedisTemplate() 方法，创建专属 UserCacheObject 的 ReactiveRedisTemplate Bean 对象，名字为 "userRedisTemplate"
     * 。为什么说它是专用的呢？因为其 value 的序列化使用的是专属 UserCacheObject 的 Jackson2JsonRedisSerializer 。
     * @param factory factory
     * @return ReactiveRedisTemplate
     */
    @Bean
    public ReactiveRedisTemplate<String, UserCacheObject> userRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, UserCacheObject> serializationContext =
                RedisSerializationContext.<String, UserCacheObject>newSerializationContext(RedisSerializer.string())
                        .value(new Jackson2JsonRedisSerializer<>(UserCacheObject.class)) // 创建专属 UserCacheObject 的 Jackson2JsonRedisSerializer 作为序列化
                        .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

}
