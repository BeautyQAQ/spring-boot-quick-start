package com.quick.start.mybatis;

import cn.hutool.json.JSONObject;
import com.quick.start.mybatis.dataobject.UserDO;
import com.quick.start.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 使用真实的数据库，这里我们使用H2内存数据库
// @Rollback(value = false)    // 默认为true，数据库操作会回滚。改为false后，不会回滚
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO();
        updateUser.setId(4);
        updateUser.setPassword("wobucai");
        userMapper.updateById(updateUser);
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteById(5);
    }

    @Test
    public void testSelectById() {
        UserDO userDO = userMapper.selectById(4);
        JSONObject jsonObject = new JSONObject(userDO);
        System.out.println(jsonObject);
    }

    @Test
    public void testSelectByUsername() {
        UserDO userDO = userMapper.selectByUsername("786805f5-8d33-488d-a7bd-0a6ce19972f4");
        JSONObject jsonObject = new JSONObject(userDO);
        System.out.println(jsonObject);
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userMapper.selectByIds(Arrays.asList(4, 6));
        System.out.println("users：" + users.size());
    }
}
