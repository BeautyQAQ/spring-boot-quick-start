package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.entity.MyUser;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

    /**
     * 分页返回所有用户
     * 
     * @param myUser 查询条件
     * @return List<MyUser>
     */
    @Select("""
            <script>
            SELECT u.user_id,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_time,u.update_time
                    FROM my_user u
                    left join my_dept d on u.dept_id = d.dept_id
                    <where>
                        <if test="nickName != null and nickName != ''">
                            AND u.nick_name like CONCAT('%', #{nickName}, '%')
                        </if>
                        <if test="userName != null and userName != ''">
                            AND u.user_name like CONCAT('%', #{userName}, '%')
                        </if>
                        <if test="deptId != null and deptId != ''">
                            AND (u.dept_id = #{deptId} OR u.dept_id IN ( SELECT e.dept_id FROM my_dept e WHERE FIND_IN_SET(#{deptId},ancestors) ))
                        </if>
                        <!-- 数据范围过滤 -->
                        ${params.dataScope}
                    </where>
                    ORDER BY u.user_id
            </script>
            """)
    List<MyUser> getFuzzyUserByPage(MyUser myUser);

    /**
     * 计算所有用户数量
     * 
     * @return 所有用户数量
     */
    @Select("select count(*) from my_user")
    Long countAllUser();

    /**
     *
     * 通过id返回用户
     * 
     * @param userId userId
     * @return 用户
     */
    @Select("select u.user_id,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_time,u.update_time from my_user u where u.user_id = #{userId}")
    MyUser getUserById(Integer userId);

    /**
     * 通过手机返回用户
     * 
     * @param phone 手机
     * @return 用户
     */
    @Select("select u.user_id,u.phone from my_user u where u.phone = #{phone} limit 1")
    MyUser checkPhoneUnique(String phone);

    /**
     * 通过用户名返回用户
     * 
     * @param userName 用户名
     * @return 用户
     */
    @Select("select u.user_id,u.user_name from my_user u where u.user_name = #{userName} limit 1")
    MyUser checkUsernameUnique(String userName);

    /**
     * 更新用户
     * 
     * @param myUser 用户
     * @return int
     */
    @Update("""
            <script>
            update my_user u
                    <set>
                        <if test="deptId != null">
                            dept_id = #{deptId},
                        </if>
                        <if test="userName != null">
                            user_name = #{userName},
                        </if>
                        <if test="nickName != null">
                            nick_name = #{nickName},
                        </if>
                        <if test="phone != null">
                            phone = #{phone},
                        </if>
                        <if test="email != null">
                            email = #{email},
                        </if>
                        <if test="status != null">
                            status = #{status},
                        </if>
                        update_time = #{updateTime}
                    </set>
                    where u.user_id = #{userId}
            </script>
            """)
    int updateUser(MyUser myUser);

    /**
     * 插入用户
     * 
     * @param myUser 用户
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into my_user(dept_id,user_name, password, nick_name, phone, email, status, create_time, update_time) values(#{deptId},#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())")
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
     * @param userName 用户名
     * @return MyUser
     */
    @Select("select * from my_user t where t.user_name = #{userName}")
    MyUser getUser(String userName);

}
