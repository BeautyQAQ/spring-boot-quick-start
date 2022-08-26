package com.quick.start.securityframework.service;

import com.quick.start.securityframework.dao.RoleUserDao;
import com.quick.start.securityframework.entity.MyRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;

    public List<MyRoleUser> getMyRoleUserByUserId(Integer userId) {
        return roleUserDao.getMyRoleUserByUserId(userId);
    }
}
