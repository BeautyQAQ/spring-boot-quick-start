package com.quick.start.securityframework.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quick.start.securityframework.common.util.Result;
import com.quick.start.securityframework.common.util.ResultCode;
import com.quick.start.securityframework.common.util.UserConstants;
import com.quick.start.securityframework.dao.RoleUserDao;
import com.quick.start.securityframework.dao.UserDao;
import com.quick.start.securityframework.dao.UserJobDao;
import com.quick.start.securityframework.entity.MyRoleUser;
import com.quick.start.securityframework.entity.MyUser;
import com.quick.start.securityframework.entity.MyUserJob;
import com.quick.start.securityframework.handler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Autowired
    private UserJobDao userJobDao;


//    @DataPermission(deptAlias = "d", userAlias = "u")
    public Result<MyUser> getAllUsersByPage(Integer offectPosition, Integer limit, MyUser myUser) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyUser> fuzzyUserByPage = userDao.getFuzzyUserByPage(myUser);
        return Result.ok().count(page.getTotal()).data(fuzzyUserByPage).code(ResultCode.TABLE_SUCCESS);
    }


    public MyUser getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }


    public void checkUserAllowed(MyUser user) {
        if (!StringUtils.isEmpty(user.getUserId()) && user.isAdmin())
        {
            throw new MyException(ResultCode.ERROR,"不允许操作超级管理员用户");
        }
    }


    public String checkPhoneUnique(MyUser myUser) {
        Integer userId = ObjectUtil.isEmpty(myUser.getUserId()) ? -1: myUser.getUserId();
        MyUser info = userDao.checkPhoneUnique(myUser.getPhone());
        if (ObjectUtil.isNotEmpty(info) && !info.getUserId().equals(userId))
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }


    public String checkUserNameUnique(MyUser myUser) {
        Integer userId = ObjectUtil.isEmpty(myUser.getUserId()) ? -1: myUser.getUserId();
        MyUser info = userDao.checkUsernameUnique(myUser.getUserName());
        if (ObjectUtil.isNotEmpty(info) && !info.getUserId().equals(userId))
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }


    public Result<MyUser> updateUser(MyUser myUser, Integer roleId) {
        if (roleId!=null){
            userDao.updateUser(myUser);
            MyRoleUser myRoleUser = new MyRoleUser();
            myRoleUser.setUserId(myUser.getUserId());
            myRoleUser.setRoleId(roleId);
            if(roleUserDao.getRoleUserByUserId(myUser.getUserId())!=null){
                roleUserDao.updateMyRoleUser(myRoleUser);
            }else {
                roleUserDao.save(myRoleUser);
            }
            userJobDao.deleteUserJobByUserId(myUser.getUserId());
            insertUserPost(myUser);
            return Result.ok().message("更新成功");
        }else {
            return Result.error().message("更新失败");
        }
    }


    public int changeStatus(MyUser user) {
        return userDao.updateUser(user);
    }


    public Result<MyUser> save(MyUser myUser, Integer roleId) {
        if(roleId!= null){
            userDao.save(myUser);
            MyRoleUser myRoleUser = new MyRoleUser();
            myRoleUser.setRoleId(roleId);
            myRoleUser.setUserId(myUser.getUserId().intValue());
            roleUserDao.save(myRoleUser);
            insertUserPost(myUser);
            return Result.ok().message("添加成功，初始密码123456");
        }

        return Result.error().message("添加失败");
    }


    public int deleteUser(Integer userId) {
        checkUserAllowed(new MyUser(userId));
        roleUserDao.deleteRoleUserByUserId(userId);
        userJobDao.deleteUserJobByUserId(userId);
        return userDao.deleteUserById(userId);
    }


    public MyUser getUserByName(String userName) {
        return userDao.getUser(userName);
    }


    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(MyUser user)
    {
        Integer[] jobs = user.getJobIds();

        if (ArrayUtil.isNotEmpty(jobs))
        {
            // 新增用户与岗位管理
            List<MyUserJob> list = new ArrayList<MyUserJob>();
            for (Integer jobId : jobs)
            {
                MyUserJob up = new MyUserJob();
                up.setUserId(user.getUserId());
                up.setJobId(jobId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                userJobDao.batchUserJob(list);
            }
        }
        return;
    }
}
