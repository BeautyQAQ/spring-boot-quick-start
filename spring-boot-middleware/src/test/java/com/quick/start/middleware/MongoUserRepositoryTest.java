package com.quick.start.middleware;

import com.quick.start.middleware.dataobject.MongoUserDO;
import com.quick.start.middleware.repository.MongoUserRepository;
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
public class MongoUserRepositoryTest {
    @Resource
    private MongoUserRepository mongoUserRepository;

    /**
     * 插入一条记录
     */
    @Test
    public void testInsert() {
        // 创建 UserDO 对象
        MongoUserDO user = new MongoUserDO();
        // 这里先临时写死一个 ID 编号，后面演示自增 ID 的时候，在修改这块
        user.setId(5);
        user.setUsername("test");
        user.setPassword("test");
        user.setCreateTime(new Date());
        // 创建 Profile 对象
        MongoUserDO.Profile profile = new MongoUserDO.Profile();
        profile.setNickname("test");
        profile.setGender(1);
        user.setProfile(profile);
        // 存储到 DB
        mongoUserRepository.insert(user);
    }

    /**
     * 更新一条记录
     * 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
     * 所以，这里仅仅是作为一个示例。
     */
    @Test
    public void testUpdate() {
        // 查询用户
        Optional<MongoUserDO> userResult = mongoUserRepository.findById(1);
        Assert.isTrue(userResult.isPresent(), "用户一定要存在");
        // 更新
        MongoUserDO updateUser = userResult.get();
        updateUser.setUsername("update");
        mongoUserRepository.save(updateUser);
    }

    /**
     * 根据 ID 编号，查询一条记录
     */
    @Test
    public void testSelectById() {
        Optional<MongoUserDO> userDO = mongoUserRepository.findById(1);
        System.out.println(userDO.isPresent());
    }

    /**
     * 根据 ID 编号数组，查询多条记录
     */
    @Test
    public void testSelectByIds() {
        Iterable<MongoUserDO> users = mongoUserRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }

    /**
     * 根据 ID 编号，删除一条记录
     */
    @Test
    public void testDelete() {
        mongoUserRepository.deleteById(5);
    }

    @Test
    public void testFindByName(){
        MongoUserDO user = mongoUserRepository.findByUsername("test");
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
        Page<MongoUserDO> page = mongoUserRepository.findByUsernameLike("up", pageable);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    @Test
    public void testFindByName01(){
        MongoUserDO user = mongoUserRepository.findByUsername01("test");
        System.out.println(user);
    }

    @Test
    public void testFindByNameLike01(){
        MongoUserDO user = mongoUserRepository.findByUsernameLike01("up");
        System.out.println(user);
    }
}
