package com.quick.start.webflux.controller;

import com.quick.start.webflux.dto.UserAddDTO;
import com.quick.start.webflux.dto.UserUpdateDTO;
import com.quick.start.webflux.service.UserService;
import com.quick.start.webflux.vo.UserVO;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * 基于注解的Reactor
 */
@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true") // 允许所有来源，允许发送 Cookie
@RequestMapping("/users")
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @CrossOrigin(allowCredentials = "false") // 允许所有来源，不允许发送 Cookie
    @GetMapping("/list")
    public Flux<UserVO> list() {
        ArrayList<UserVO> result = new ArrayList<>();
        result.add(new UserVO().setId(1).setUsername("a"));
        result.add(new UserVO().setId(2).setUsername("b"));
        result.add(new UserVO().setId(3).setUsername("c"));
        return Flux.fromIterable(result);
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public Mono<UserVO> get(@RequestParam("id") Integer id) {
        UserVO userVO = new UserVO().setId(id).setUsername("username:" + id);
        return Mono.just(userVO);
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/v2/get")
    public Mono<UserVO> get2(@RequestParam("id") Integer id) {
        UserVO userVO = userService.get(id);
        return Mono.just(userVO);
    }

    /**
     * 添加用户
     * 从 request 的 Body 中读取参数。注意，此时提交参数需要使用 "application/json" 的 Content-Type 内容类型。
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("/add")
    public Mono<Integer> add(@RequestBody Publisher<UserAddDTO> addDTO){
        // 插入用户记录，返回编号
        Integer returnId = 1;
        return Mono.just(returnId);
    }

    /**
     * 添加用户
     * 使用 application/x-www-form-urlencoded 或 multipart/form-data 这两个 Content-Type 内容类型，
     * 通过 request 的 Form Data 或 Multipart Data 传递参数
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("add2")
    public Mono<Integer> add(Mono<UserAddDTO> addDTO) {
        // 插入用户记录，返回编号
        Integer returnId = 1;
        // 返回用户编号
        return Mono.just(returnId);
    }

    /**
     * 更新指定用户编号的用户
     *
     * @param updateDTO 更新用户信息 DTO
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public Mono<Boolean> update(@RequestBody Publisher<UserUpdateDTO> updateDTO){
        Boolean success = true;
        return Mono.just(success);
    }

    /**
     * 删除指定用户编号的用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @PostMapping("/delete") // URL 修改成 /delete ，RequestMethod 改成 DELETE
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        log.info("入参id={}", id);
        // 删除用户记录
        Boolean success = true;
        // 返回是否更新成功
        return Mono.just(success);
    }
}
