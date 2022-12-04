package com.quick.start.code.snippets.dao;

import cn.hutool.core.lang.Assert;
import com.quick.start.code.snippets.dataobject.UserDo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;

/**
 * 可以去看下 Apollo 开源项目，由携程开源的分布式配置中心。
 * 在 Apollo 项目中，会有比较多的配置管理的业务逻辑，开发者对这些逻辑写了蛮多单元测试，我们可以进行借鉴学习。
 */
@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    /**
     * 针对 Dao 的测试，我们并不会使用 Mockito 进行 mock 的方式，而是使用内存数据库，进行对应的数据库操作。例如说，这里我们采用 H2 内存数据库。
     * 第一条，在单元测试方法执行之前，执行 /sql/create_tables.sql 脚本
     * 第二条，在单元测试方法执行之前，执行在 statements 属性定义的 SQL 操作，插入一条 id=1 的数据
     * 第三条，在单元测试方法执行之后，/sql/clean.sql 脚本，清空数据。毕竟，多个单元测试是共享一个 H2 内存数据库，所以需要进行清理。
     */
    @Sql(scripts = "/sql/create_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `t_user`(`id`, `username`, `password`) VALUES (1, 'username:1', 'password:1');",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void testSelectById() {
        // 查询用户
        UserDo user = userDao.selectById(1);
        // 校验结果
        Assert.equals(1, user.getId(), "编号不匹配");
        Assert.equals("password:1", user.getPassword(), "密码不匹配");
        Assert.equals("username:1", user.getUsername(), "用户名不匹配");
    }
}
