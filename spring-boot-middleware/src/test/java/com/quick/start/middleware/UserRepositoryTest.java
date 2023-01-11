package com.quick.start.middleware;

import com.quick.start.middleware.dataobject.UserDO;
import com.quick.start.middleware.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    /**
     * 插入一条记录
     */
    @Test
    public void testInsert() {
        // 创建 UserDO 对象
        UserDO user = new UserDO();
        // 这里先临时写死一个 ID 编号，后面演示自增 ID 的时候，在修改这块
        user.setId(5);
        user.setUsername("test");
        user.setPassword("test");
        user.setCreateTime(new Date());
        // 创建 Profile 对象
        UserDO.Profile profile = new UserDO.Profile();
        profile.setNickname("test");
        profile.setGender(1);
        user.setProfile(profile);
        // 存储到 DB
        userRepository.insert(user);
    }

    /**
     * 更新一条记录
     * 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
     * 所以，这里仅仅是作为一个示例。
     */
    @Test
    public void testUpdate() {
        // 查询用户
        Optional<UserDO> userResult = userRepository.findById(1);
        Assert.isTrue(userResult.isPresent(), "用户一定要存在");
        // 更新
        UserDO updateUser = userResult.get();
        updateUser.setUsername("update");
        userRepository.save(updateUser);
    }

    /**
     * 根据 ID 编号，查询一条记录
     */
    @Test
    public void testSelectById() {
        Optional<UserDO> userDO = userRepository.findById(1);
        System.out.println(userDO.isPresent());
    }

    /**
     * 根据 ID 编号数组，查询多条记录
     */
    @Test
    public void testSelectByIds() {
        Iterable<UserDO> users = userRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }

    /**
     * 根据 ID 编号，删除一条记录
     */
    @Test
    public void testDelete() {
        userRepository.deleteById(5);
    }

    @Test
    public void testFindByName(){
        UserDO user = userRepository.findByUsername("test");
        System.out.println(user);
    }

    /**
     * 使用 username 模糊查询，分页返回结果
     */
    @Test
    public void testFindByNameLike(){
        // 创建排序条件, ID倒序
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        PageRequest pageable = PageRequest.of(0, 10, sort);
        Page<UserDO> page = userRepository.findByUsernameLike("up", pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    @Test
    public void testFindByName01(){
        UserDO user = userRepository.findByUsername01("test");
        System.out.println(user);
    }

    @Test
    public void testFindByNameLike01(){
        UserDO user = userRepository.findByUsernameLike01("up");
        System.out.println(user);
    }
}
