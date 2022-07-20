package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.entity.MyUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 分页返回所有用户
     *
     * @param myUser MyUser
     * @return MyUsers
     */
    List<MyUser> getFuzzyUserByPage(MyUser myUser);

    /**
     * 通过id返回用户
     *
     * @param userId userId
     * @return MyUser
     */
    @Select("""
            select u.user_id,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_time,u.update_time
             from my_user u where u.user_id = #{userId}
            """)
    MyUser getUserById(Integer userId);

    /**
     * 通过手机返回用户
     *
     * @param phone phone
     * @return MyUser
     */
    MyUser checkPhoneUnique(String phone);

    /**
     * 通过用户名返回用户
     *
     * @param userName userName
     * @return MyUser
     */
    MyUser checkUsernameUnique(String userName);

    /**
     * 更新用户
     *
     * @param myUser myUser
     * @return int
     */
    int updateUser(MyUser myUser);

    /**
     * 插入用户
     *
     * @param myUser myUser
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("""
            insert into my_user(dept_id,user_name, password, nick_name, phone, email, status, create_time, update_time)
             values(#{deptId},#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())
            """)
    int save(MyUser myUser);

    /**
     * 通过id删除用户
     *
     * @param userId userId
     * @return int
     */
    @Delete("delete from my_user where user_id = #{userId}")
    int deleteUserById(Integer userId);

    /**
     * 根据用户名查询用户
     *
     * @param userName userName
     * @return MyUser
     */
    @Select("select * from my_user t where t.user_name = #{userName}")
    MyUser getUser(String userName);
}
