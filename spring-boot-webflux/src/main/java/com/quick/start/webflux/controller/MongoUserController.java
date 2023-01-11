package com.quick.start.webflux.controller;

import com.quick.start.webflux.constants.ServiceExceptionEnum;
import com.quick.start.webflux.dataobject.UserDO;
import com.quick.start.webflux.dto.UserAddDTO;
import com.quick.start.webflux.dto.UserUpdateDTO;
import com.quick.start.webflux.exception.ServiceException;
import com.quick.start.webflux.repository.UserRepository;
import com.quick.start.webflux.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@RestController
@RequestMapping("/mongo/users")
public class MongoUserController {
    public static final UserDO USER_NULL = new UserDO();

    @Resource
    private UserRepository userRepository;

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public Flux<UserVO> list(){
        return userRepository.findAll().map(userDO -> new UserVO().setId(userDO.getId()).setUsername(userDO.getUsername()));
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public Mono<UserVO> get(@RequestParam("id") Integer id) {
        return userRepository.findById(id)
                .map(userDO -> new UserVO().setId(userDO.getId()).setUsername(userDO.getUsername()));
    }

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("add")
    public Mono<Integer> add(UserAddDTO addDTO) {
        // 查询用户
        Mono<UserDO> user = userRepository.findByUsername(addDTO.getUsername());
        // 执行插入
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(userDO -> {
                    if (userDO != USER_NULL){
                        return Mono.error(new ServiceException(ServiceExceptionEnum.SYS_ERROR));
                    }
                    // 将 addDTO 转成 UserDO
                    userDO = new UserDO().setId((int) (System.currentTimeMillis() / 1000)) // 使用当前时间戳的描述，作为 ID 。
                            .setUsername(addDTO.getUsername())
                            .setPassword(addDTO.getPassword())
                            .setCreateTime(new Date());
                    // 插入数据库
                    return userRepository.insert(userDO).map(UserDO::getId);
                });
    }

    /**
     * 更新指定用户编号的用户
     *
     * @param updateDTO 更新用户信息 DTO
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public Mono<Boolean> update(UserUpdateDTO updateDTO) {
        // 查询用户
        Mono<UserDO> user = userRepository.findById(updateDTO.getId());
        // 执行更新
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<UserDO, Mono<? extends Boolean>>() {
                    @Override
                    public Mono<? extends Boolean> apply(UserDO userDO) {
                        if (userDO == USER_NULL){
                            return Mono.just(false);
                        }
                        // 查询用户是否存在
                        return userRepository.findByUsername(updateDTO.getUsername()).defaultIfEmpty(USER_NULL).flatMap(new Function<UserDO, Mono<? extends Boolean>>() {
                            @Override
                            public Mono<? extends Boolean> apply(UserDO usernameUserDO) {
                                // 如果用户名已经使用（该用户名对应的 id 不是自己，说明就已经被使用了）
                                if (usernameUserDO != USER_NULL && !Objects.equals(updateDTO.getId(), usernameUserDO.getId())){
                                    return Mono.just(false);
                                }
                                // 执行更新
                                userDO.setUsername(updateDTO.getUsername());
                                userDO.setPassword(updateDTO.getPassword());
                                // 返回 true 成功
                                return userRepository.save(userDO).map(userDO1 -> true);
                            }
                        });
                    }
                });
    }

    /**
     * 删除指定用户编号的用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @PostMapping("/delete")
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        // 查询用户
        Mono<UserDO> user = userRepository.findById(id);
        // 执行删除。这里仅仅是示例，项目中不要物理删除，而是标记删除
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(userDO -> {
                    // 如果不存在该用户，则直接返回 false 失败
                    if (userDO == USER_NULL){
                        return Mono.just(false);
                    }
                    // 执行删除 返回 true 成功
                    return userRepository.deleteById(id).map(aVoid -> true);
                });
    }
}
