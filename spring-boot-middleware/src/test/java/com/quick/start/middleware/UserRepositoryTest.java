package com.quick.start.middleware;

import com.quick.start.middleware.dao.UserDO;
import com.quick.start.middleware.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test // 插入一条记录
    public void testSave() {
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date());
        userRepository.save(user);
    }

    @Test // 更新一条记录
    public void testUpdate() {
        // 先查询一条记录
        Optional<UserDO> userDO = userRepository.findById(14);
        Assert.isTrue(userDO.isPresent(), "记录不能为空");
        // 更新一条记录
        UserDO updateUser = userDO.get();
        updateUser.setPassword("dddd");
        userRepository.save(updateUser);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        userRepository.deleteById(14);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<UserDO> userDO = userRepository.findById(14);
        System.out.println(userDO.get());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<UserDO> users = userRepository.findAllById(Arrays.asList(14, 13));
        users.forEach(System.out::println);
    }
}
