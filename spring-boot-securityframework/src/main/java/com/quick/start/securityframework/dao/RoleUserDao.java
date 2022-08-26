package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.entity.MyRoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoleUserDao {

    /**
     * 通过角色id返回所有用户
     *
     * @param id 角色id
     * @return List MyRoleUser
     */
    @Select("select * from my_role_user ru where ru.role_id = #{roleId}")
    List<MyRoleUser> listAllRoleUserByRoleId(Integer id);

    /**
     * 通过用户id查询权限id
     *
     * @param userId 用户id
     * @return List MyRoleUser
     */
    @Select("select * from my_role_user ru where ru.user_id = #{userId}")
    List<MyRoleUser> getMyRoleUserByUserId(Integer userId);

    /**
     * 通过用户id返回角色
     *
     * @param userId 用户id
     * @return MyRoleUser
     */
    @Select("select * from my_role_user ru where ru.user_id = #{userId}")
    MyRoleUser getRoleUserByUserId(int userId);

    /**
     * 更新
     *
     * @param myRoleUser myRoleUser
     * @return int
     */
    @Update("""
            <script>
            update my_role_user ru
                    <set>
                        <if test="roleId != null">
                            role_id = #{roleId},
                        </if>
                    </set>
                    where ru.user_id = #{userId}
                    </script>
            """)
    int updateMyRoleUser(MyRoleUser myRoleUser);

    /**
     * 新建
     *
     * @param myRoleUser myRoleUser
     * @return int
     */
    @Insert("insert into my_role_user(user_id, role_id) values(#{userId}, #{roleId})")
    int save(MyRoleUser myRoleUser);

    /**
     * 删除
     *
     * @param id id
     * @return int
     */
    @Delete("delete from my_role_user where user_id = #{userId}")
    int deleteRoleUserByUserId(Integer id);
}
