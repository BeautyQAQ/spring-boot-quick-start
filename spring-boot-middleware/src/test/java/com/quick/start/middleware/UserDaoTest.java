package com.quick.start.middleware;

import com.quick.start.middleware.dao.UserDao;
import com.quick.start.middleware.dataobject.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    /**
     * 插入一条记录
     */
    @Test
    public void testInsert() {
        // 创建 UserDO 对象
        UserDO user = new UserDO();
        // 这里先临时写死一个 ID 编号，后面演示自增 ID 的时候，在修改这块
        user.setId(10);
        user.setUsername("didi");
        user.setPassword("buzhidao");
        user.setCreateTime(new Date());
        // 创建 Profile 对象
        UserDO.Profile profile = new UserDO.Profile();
        profile.setNickname("测试");
        profile.setGender(1);
        user.setProfile(profile);
        // 存储到 DB
        userDao.insert(user);
    }

    /**
     * 更新一条记录
     * 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
     * 所以，这里仅仅是作为一个示例。
     */
    @Test
    public void testUpdate() {
        // 创建 UserDO 对象
        UserDO updateUser = new UserDO();
        updateUser.setId(1);
        updateUser.setUsername("nicai");

        // 执行更新
        userDao.updateById(updateUser);
    }

    /**
     * 根据 ID 编号，查询一条记录
     */
    @Test
    public void testSelectById() {
        UserDO userDO = userDao.findById(10);
        System.out.println(userDO);
    }

    /**
     * 根据 ID 编号数组，查询多条记录
     */
    @Test
    public void testSelectByIds() {
        List<UserDO> users = userDao.findAllById(Arrays.asList(10, 4));
        users.forEach(System.out::println);
    }

    /**
     * 根据 ID 编号，删除一条记录
     */
    @Test
    public void testDelete() {
        userDao.deleteById(10);
    }
}
