package com.quick.start.webflux.controller;

import com.quick.start.webflux.dataobject.UserCacheObject;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis/users")
public class RedisUserController {

    /**
     * ========== 使用通用的 ReactiveRedisTemplate 的方式 ==========
     */
    @Resource
    private ReactiveRedisTemplate<String, Object> commonRedisTemplate;

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public Mono<UserCacheObject> get(@RequestParam("id") Integer id) {
        String key = genKey(id);
        return commonRedisTemplate.opsForValue().get(key).map(o->(UserCacheObject)o);
    }

    /**
     * 设置指定用户的信息
     *
     * @param user 用户
     * @return 是否成功
     */
    @PostMapping("/set")
    public Mono<Boolean> set(UserCacheObject user) {
        String key = genKey(user.getId());
        return commonRedisTemplate.opsForValue().set(key, user);
    }

    private static String genKey(Integer id) {
        return "user::" + id;
    }

    /**
     * ========== 使用专属的 ReactiveRedisTemplate 的方式 =========
     */
    @Resource
    private ReactiveRedisTemplate<String, UserCacheObject> userRedisTemplate;

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/v2/get")
    public Mono<UserCacheObject> getV2(@RequestParam("id") Integer id) {
        String key = genKeyV2(id);
        return userRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置指定用户的信息
     *
     * @param user 用户
     * @return 是否成功
     */
    @PostMapping("/v2/set")
    public Mono<Boolean> setV2(UserCacheObject user) {
        String key = genKeyV2(user.getId());
        return userRedisTemplate.opsForValue().set(key, user);
    }

    private static String genKeyV2(Integer id) {
        return "user::v2::" + id;
    }
}
