package com.quick.start.code.snippets.dao;

import com.quick.start.code.snippets.dataobject.UserDo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {

    @Resource
    private JdbcTemplate template;

    public UserDo selectById(Integer id) {
        return template.queryForObject("SELECT id, username, password FROM t_user WHERE id = ?",
                new BeanPropertyRowMapper<>(UserDo.class), // 结果转换成对应的对象
                id);
    }
}
