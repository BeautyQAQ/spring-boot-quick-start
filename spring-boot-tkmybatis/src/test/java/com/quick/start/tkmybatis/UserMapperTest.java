package com.quick.start.tkmybatis;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quick.start.tkmybatis.dataobject.UserDO;
import com.quick.start.tkmybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 使用真实的数据库，这里我们使用H2内存数据库
@Rollback(value = false)    // 默认为true，数据库操作会回滚。改为false后，不会回滚
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
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateById() {
        UserDO updateUser = new UserDO();
        updateUser.setId(4);
        updateUser.setPassword("wobucai");
        userMapper.updateByPrimaryKey(updateUser);
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteByPrimaryKey(4);
    }

    @Test
    public void testSelectById() {
        UserDO userDO = userMapper.selectByPrimaryKey(5);
        JSONObject jsonObject = new JSONObject(userDO);
        System.out.println(jsonObject);
    }

    @Test
    public void testSelectByUsername() {
        UserDO userDO = userMapper.selectByUsername("357cedcd-0974-4d04-83a4-b3ccac931cd8");
        JSONObject jsonObject = new JSONObject(userDO);
        System.out.println(jsonObject);
    }

    @Test
    public void testSelectByIds() {
        List<UserDO> users = userMapper.selectByIds(Arrays.asList(5, 6));
        System.out.println("users：" + users.size());
    }

    @Test // 更多使用，可以参考 https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
    public void testSelectPageByCreateTime() {
        // 设置分页
        PageHelper.startPage(1, 3);
        Date createTime = new Date(2018 - 1990, Calendar.FEBRUARY, 24); // 临时 Demo ，实际不建议这么写
        // 执行列表查询 PageHelper 会自动发起分页的数量查询，设置到 PageHelper 中
        List<UserDO> users = userMapper.selectListByCreateTime(createTime); // 实际返回的是 com.github.pagehelper.Page 代理对象
        // 转换成 PageInfo 对象，并输出分页
        PageInfo<UserDO> page = new PageInfo<>(users);
        System.out.println(page.getList().size());
    }

}
