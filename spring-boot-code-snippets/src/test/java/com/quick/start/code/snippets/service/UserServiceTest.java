package com.quick.start.code.snippets.service;

import cn.hutool.core.lang.Assert;
import com.quick.start.code.snippets.dataobject.UserDo;
import com.quick.start.code.snippets.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserDao userDao;

    @Resource
    private UserService userService;

    @Test
    public void testGet() {
        // Mock UserDao 的 selectById 方法
        Mockito.when(userDao.selectById(any())).thenReturn(new UserDo().setId(1).setUsername("username:1").setPassword("password:1"));
        UserDo user = userService.get(1);
        // 校验结果
        Assert.equals(1, user.getId(), "编号不匹配");
        Assert.equals("username:1", user.getUsername(), "用户名不匹配");
        Assert.equals("password:1", user.getPassword(), "密码不匹配");
    }

    @Test
    public void addUser() throws Exception {
        Assert.equals(1, userService.addUser("test"));
        Assert.equals(0, userService.addUser(null));
    }
}
