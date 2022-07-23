package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.dto.RoleDto;
import com.quick.start.securityframework.entity.MyRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {

    /**
     * 分页模糊查询角色
     * @param role
     * @return
     */
    @Select("""
            <script>
            select distinct r.role_id,r.role_name,r.data_scope,r.description,r.create_time,r.update_time
                    from my_role r
            	        left join my_role_user ru on ru.role_id = r.role_id
            	        left join my_user u on u.user_id = ru.user_id
            	        left join my_dept d on u.dept_id = d.dept_id
            <where>
                        <if test="roleName != null and roleName != ''">
                             r.role_name like CONCAT('%', #{roleName}, '%')
                        </if>
                        <!-- 数据范围过滤 -->
                        ${params.dataScope}
            </where>
            </script>
            """)
    List<MyRole> getFuzzyRolesByPage(MyRole role);

    /**
     * 通过id查询角色
     * @param roleId
     * @return
     */
    @Select("select r.role_id,r.role_name,r.data_scope,r.description,r.create_time,r.update_time from my_role r where r.role_id = #{roleId}")
    MyRole getRoleById(Integer roleId);

    /**
     * 更新角色
     * @param roleDto
     * @return
     */
    @Update("""
            <script>
            update my_role r
                    <set>
                        <if test="roleName != null">
                            `role_name` = #{roleName},
                        </if>
                        <if test="dataScope != null">
                            data_scope = #{dataScope},
                        </if>
                        <if test="description != null">
                            description = #{description},
                        </if>
                        update_time = #{updateTime}
                    </set>
                    where r.role_id = #{roleId}
            </script>
            """)
    int update(RoleDto roleDto);

    /**
     * 新建角色
     * @param roleDto
     * @return
     */
    @Insert("""
            insert into my_role(role_name, description, create_time, update_time)
            		values
            		(#{roleName}, #{description}, now(), now())
            """)
    int saveRole(RoleDto roleDto);

    /**
     * 通过id删除角色
     * @param roleId
     * @return
     */
    @Delete("delete from my_role where role_id = #{roleId}")
    int delete(Integer roleId);

    /**
     * 返回所有角色
     * @return
     */
    @Select("select r.role_id,r.role_name,r.description from my_role r")
    List<MyRole> getAllRoles();
}
