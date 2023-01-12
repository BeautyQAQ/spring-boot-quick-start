package com.quick.start.webflux.controller;

import com.quick.start.webflux.dataobject.EsUserDO;
import com.quick.start.webflux.dto.UserAddDTO;
import com.quick.start.webflux.dto.UserUpdateDTO;
import com.quick.start.webflux.repository.EsUserRepository;
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
@RequestMapping("/es/users")
public class EsUserController {
    private static final EsUserDO USER_NULL = new EsUserDO();

    @Resource
    private EsUserRepository esUserRepository;

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public Flux<UserVO> list() {
        // 返回列表
        return esUserRepository.findAll()
                .map(userDO -> new UserVO().setId(userDO.getId()).setUsername(userDO.getUsername()));
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public Mono<UserVO> get(@RequestParam("id") Integer id) {
        // 返回
        return esUserRepository.findById(id)
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
        Mono<EsUserDO> user = esUserRepository.findByUsername(addDTO.getUsername());

        // 执行插入
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<EsUserDO, Mono<Integer>>() {

                    @Override
                    public Mono<Integer> apply(EsUserDO userDO) {
                        if (userDO != USER_NULL) {
                            // 返回 -1 表示插入失败。
                            // 实际上，一般是抛出 ServiceException 异常。因为这个示例项目里暂时没做全局异常的定义，所以暂时返回 -1 啦
                            return Mono.just(-1);
                        }
                        // 将 addDTO 转成 UserDO
                        userDO = new EsUserDO().setId((int) (System.currentTimeMillis() / 1000)) // 使用当前时间戳的描述，作为 ID 。
                                .setUsername(addDTO.getUsername())
                                .setPassword(addDTO.getPassword())
                                .setCreateTime(new Date());
                        // 插入数据库
                        return esUserRepository.save(userDO).map(EsUserDO::getId);
                    }

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
        Mono<EsUserDO> user = esUserRepository.findById(updateDTO.getId());

        // 执行更新
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<EsUserDO, Mono<Boolean>>() {

                    @Override
                    public Mono<Boolean> apply(EsUserDO userDO) {
                        // 如果不存在该用户，则直接返回 false 失败
                        if (userDO == USER_NULL) {
                            return Mono.just(false);
                        }
                        // 查询用户是否存在
                        return esUserRepository.findByUsername(updateDTO.getUsername())
                                .defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                                .flatMap(new Function<EsUserDO, Mono<? extends Boolean>>() {

                                    @Override
                                    public Mono<? extends Boolean> apply(EsUserDO usernameUserDO) {
                                        // 如果用户名已经使用（该用户名对应的 id 不是自己，说明就已经被使用了）
                                        if (usernameUserDO != USER_NULL && !Objects.equals(updateDTO.getId(), usernameUserDO.getId())) {
                                            return Mono.just(false);
                                        }
                                        // 执行更新
                                        userDO.setUsername(updateDTO.getUsername());
                                        userDO.setPassword(updateDTO.getPassword());
                                        return esUserRepository.save(userDO).map(userDO -> true); // 返回 true 成功
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
    @PostMapping("/delete") // URL 修改成 /delete ，RequestMethod 改成 DELETE
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        // 查询用户
        Mono<EsUserDO> user = esUserRepository.findById(id);

        // 执行删除。这里仅仅是示例，项目中不要物理删除，而是标记删除
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<EsUserDO, Mono<Boolean>>() {

                    @Override
                    public Mono<Boolean> apply(EsUserDO userDO) {
                        // 如果不存在该用户，则直接返回 false 失败
                        if (userDO == USER_NULL) {
                            return Mono.just(false);
                        }
                        // 执行删除
                        return esUserRepository.deleteById(id).map(aVoid -> true); // 返回 true 成功
                    }

                });
    }

}
