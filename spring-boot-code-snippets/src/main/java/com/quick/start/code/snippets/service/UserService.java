package com.quick.start.code.snippets.service;

import com.quick.start.code.snippets.DO.UserDO;
import com.quick.start.code.snippets.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public UserDO get(Integer id) {
        return userDao.selectById(id);
    }

    public Integer addUser(String username){
        System.out.println("user dao adduser [username="+username+"]");
        if(username == null){
            return 0;
        }
        return 1;
    }
}
