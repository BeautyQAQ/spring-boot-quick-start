package com.quick.start.securityframework.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDeptDao {

    /**
     * 通过id删除与部门关联
     *
     * @param roleId roleId
     * @return int
     */
    @Delete("delete from my_role_dept where role_id = #{roleId}")
    int deleteRoleDept(Integer roleId);

    /**
     * 新建角色与部门的联系
     *
     * @param id      roleId
     * @param deptIds deptIds
     */
    @Insert("""
            <script>
            insert into my_role_dept(role_id, dept_id) values
                    <foreach collection="deptIds" item="deptId"
                             separator=",">
                        (#{roleId}, #{deptId})
                    </foreach>
            </script>
            """)
    void save(@Param("roleId") Integer id, @Param("deptIds") List<Integer> deptIds);
}
