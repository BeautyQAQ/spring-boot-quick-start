package com.quick.start.mybatis;

import cn.hutool.json.JSONObject;
import com.quick.start.mybatis.dataobject.UserDO;
import com.quick.start.mybatis.mapper.UserMapperAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserMapperAnnotationTest {
    @Resource
    private UserMapperAnnotation userMapperAnnotation;

    @Test
    public void testInsert() {
        UserDO user = new UserDO();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("nicai");
        user.setCreateTime(new Date());
        userMapperAnnotation.insert(user);
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO();
        updateUser.setId(7);
        updateUser.setPassword("nicai");
        userMapperAnnotation.updateById(updateUser);
    }

    @Test
    public void testDeleteById() {
        userMapperAnnotation.deleteById(7);
    }

    @Test
    public void testSelectById() {
        UserDO userDO = userMapperAnnotation.selectById(4);
        JSONObject jsonObject = new JSONObject(userDO);
        System.out.println(jsonObject);
    }

    @Test
    public void testSelectByUsername() {
        UserDO userDO = userMapperAnnotation.selectByUsername("786805f5-8d33-488d-a7bd-0a6ce19972f4");
        JSONObject jsonObject = new JSONObject(userDO);
        System.out.println(jsonObject);
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userMapperAnnotation.selectByIds(Arrays.asList(4, 6));
        System.out.println("users：" + users.size());
    }
}
