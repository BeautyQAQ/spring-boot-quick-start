package com.quick.start.securityframework.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMenuDao {

    /**
     * 通过id删除roleMenu
     * 
     * @param roleId roleId
     * @return int
     */
    @Delete("delete from my_role_menu where role_id = #{roleId}")
    int deleteRoleMenu(Integer roleId);

    /**
     * 新建角色与menu的联系
     * 
     * @param roleId  roleId
     * @param menuIds menuIds
     */
    @Insert("""
            <script>
            insert into my_role_menu(role_id, menu_id) values
                    <foreach collection="menuIds" item="menuId" separator=",">
                        (#{roleId}, #{menuId})
                    </foreach>
            </script>
            """)
    void save(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    /**
     * 通过menu_id计算权限数量
     * 
     * @param menuId
     * @return 权限数量
     */
    @Select("select count(*) from my_role_menu t where t.menu_id = #{menuId}")
    Integer countRoleMenuByRoleId(Integer menuId);
}
