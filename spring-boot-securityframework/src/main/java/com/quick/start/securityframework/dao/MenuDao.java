package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.dto.MenuDto;
import com.quick.start.securityframework.dto.MenuIndexDto;
import com.quick.start.securityframework.entity.MyMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@SuppressWarnings("ALL")
@Mapper
public interface MenuDao {
    /**
     * 模糊查询菜单
     * @param queryName 查询的表题
     * @param queryType 查询类型
     * @return
     */
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    @Select("""
            <script>
            select m.menu_id,m.parent_id,m.menu_name,m.icon,m.url,m.permission,m.sort,m.type,m.create_time,m.update_time
                    from my_menu m
                    <where>
                        <if test="queryName != null and queryName != ''">
                            AND m.menu_name like CONCAT('%', #{queryName}, '%')
                        </if>
                        <if test="queryType != null and queryType != ''">
                            AND m.type = #{queryType}
                        </if>
                    </where>
                    order by m.sort
            </script>
            """)
    List<MyMenu> getFuzzyMenu(String queryName, Integer queryType);

    /**
     * 通过id查询菜单
     * @param menuId
     * @return
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name,m.icon,m.url,m.permission,m.sort,m.type,m.create_time,m.update_time from my_menu m where m.menu_id = #{menuId}")
    MyMenu getMenuById(Integer menuId);

    /**
     * 菜单树
     * @return
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name from my_menu m")
    @Result(property = "title",column = "menu_name")
    @Result(property = "id",column = "menu_id")
    List<MenuDto> buildAll();

    /**
     * 更新菜单
     * 
     * @param menu
     * @return
     */
    @Select("""
            <script>
            update my_menu m
                    <set>
                        <if test="parentId != null">
                            parent_id = #{parentId},
                        </if>
                        <if test="menuName != null">
                            `menu_name` = #{menuName},
                        </if>
                        <if test="icon != null">
                            `icon` = #{icon},
                        </if>
                        <if test="url != null">
                            url = #{url},
                        </if>
                        <if test="permission != null">
                            permission = #{permission},
                        </if>
                        <if test="sort != null">
                            sort = #{sort},
                        </if>
                        <if test="type != null">
                            type = #{type},
                        </if>
                        update_time = #{updateTime}
                    </set>
                    where m.menu_id = #{menuId}
            </script>
            """)
    int update(MyMenu menu);

    /**
     * 新建菜单
     * 
     * @param menu
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "menuId")
    @Insert("insert into my_menu(parent_id, menu_name, icon, url, permission, sort, type, create_time, update_time)values(#{parentId}, #{menuName}, #{icon}, #{url}, #{permission}, #{sort}, #{type}, now(), now())")
    int save(MyMenu menu);

    /**
     * 通过id删除菜单
     * 
     * @param menuId
     * @return
     */
    @Delete("delete from my_menu where menu_id = #{menuId}")
    int deleteById(Integer menuId);

    /**
     * 通过父节点删除子菜单
     * 
     * @param parentId
     * @return
     */
    @Delete("delete from my_menu where parent_id = #{parentId}")
    int deleteByParentId(Integer parentId);

    /**
     * 通过父节点返回字节点
     * 
     * @param parentId
     * @return
     */
    @Select("select m.menu_id from my_menu m where parent_id = #{parentId}")
    List<Integer> selectByParentId(Integer parentId);

    /**
     * 通过角色id返回菜单
     * 
     * @param roleId
     * @return
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name from my_menu m inner join my_role_menu rm on m.menu_id = rm.menu_id where rm.role_id = #{roleId}")
    @Result(property = "title",column = "menu_name")
    @Result(property = "id",column = "menu_id")
    List<MenuDto> listByRoleId(Integer roleId);

    /**
     * 通过用户id返回菜单
     * 
     * @param userId
     * @return
     */
    @Select("""
            <script>SELECT DISTINCT m.menu_id as id,m.parent_id,m.menu_name as title,m.icon,m.url as href,m.type,m.permission,m.sort
                        FROM my_role_user ru
                    INNER JOIN my_role_menu rm ON rm.role_id = ru.role_id
                    LEFT JOIN my_menu m ON rm.menu_id = m.menu_id
                    <where>
                        <if test="userId != null">
                            ru.user_id = #{userId}
                        </if>
                    </where>
                    ORDER BY ifnull(m.sort,0)</script>
            """)
    List<MenuIndexDto> listByUserId(@Param("userId")Integer userId);
}
