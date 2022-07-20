package com.quick.start.securityframework.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quick.start.securityframework.common.util.Result;
import com.quick.start.securityframework.common.util.ResultCode;
import com.quick.start.securityframework.dao.UserDao;
import com.quick.start.securityframework.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Result getAllUsersByPage(Integer offectPosition, Integer limit, MyUser myUser) {
        Page page = PageHelper.offsetPage(offectPosition, limit);
        List<MyUser> fuzzyUserByPage = userDao.getFuzzyUserByPage(myUser);
        return Result.ok().count(page.getTotal()).data(fuzzyUserByPage).code(ResultCode.TABLE_SUCCESS);
    }

    public MyUser getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
