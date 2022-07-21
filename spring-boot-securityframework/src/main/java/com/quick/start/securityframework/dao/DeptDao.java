package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.dto.DeptDto;
import com.quick.start.securityframework.entity.MyDept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptDao {
    /**
     * 模糊查询部门
     *
     * @param myDept 查询的名称
     * @return
     */
    @Select("""
            select d.dept_id,d.parent_id,d.dept_name,d.sort,d.status,d.create_time,d.update_time from my_dept d
                    <where>
                                <if test="deptName != null and deptName != ''">
                                    AND d.dept_name like CONCAT('%', #{deptName}, '%')
                                </if>
                                <if test="status != null and status != ''">
                                    AND d.status = #{status}
                                </if>
                                <!-- 数据范围过滤 -->
                                ${params.dataScope}
                    </where>
                    order by d.sort
            """)
    List<MyDept> getFuzzyDept(MyDept myDept);


    /**
     * 部门树
     * @param deptDto
     * @return
     */
    @Select("""
            select d.dept_id as id,d.parent_id,d.dept_name as title
                from my_dept d
            <where>
                <!-- 数据范围过滤 -->
                ${params.dataScope}
            </where>
            """)
    List<DeptDto> buildAll(DeptDto deptDto);

    /**
     * 校验部门名称
     * @param deptName 岗位名称
     * @param parentId
     * @return
     */
    @Select("""
            select d.dept_id,d.parent_id,d.dept_name,d.sort,d.status,d.create_time,d.update_time
            from my_dept d
            where dept_name= #{deptName} and parent_id = #{parentId} limit 1
            """)
    MyDept checkDeptNameUnique(@Param("deptName")String deptName,@Param("parentId") Integer parentId);


    /**
     * 新增部门信息
     * @param dept 岗位信息
     * @return 结果
     */
    @Insert("INSERT INTO my_dept(parent_id,ancestors,dept_name,sort,status, create_time, update_time) values(#{parentId},#{ancestors},#{deptName},#{sort},#{status}, now(), now())")
    int insertDept(MyDept dept);
    /**
     * 根据部门ID查询信息
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Select("""
            select d.dept_id, d.parent_id, d.ancestors, d.dept_name, d.status,
            			(select s.dept_name from my_dept s where s.dept_id = d.parent_id) parent_name
            		from my_dept d
            		where d.dept_id = #{deptId}
            """)
    MyDept selectDeptById(Integer deptId);

    /**
     * 通过id查询部门信息
     * @param deptId
     * @return
     */
    @Select("select d.dept_id,d.parent_id,d.ancestors,d.dept_name,d.sort,d.status,d.create_time,d.update_time from my_dept d where d.dept_id = #{deptId}")
    MyDept getDeptById(Integer deptId);


    /**
     * 根据ID查询所有子部门
     *
     * @param id 部门ID
     * @return 部门列表
     */
    @Select("select * from my_dept where find_in_set(#{id}, ancestors)")
     List<MyDept> selectChildrenDeptById(Integer id);


    /**
     * 根据角色ID查询部门
     *
     * @param id 角色ID
     * @return 部门列表
     */
    @Select("""
            select d.dept_id as id,d.parent_id,d.dept_name as title
            from my_dept d
            	left join my_role_dept rd on d.dept_id = rd.dept_id
            where rd.role_id = #{id}
            order by d.parent_id
            """)
     List<DeptDto> selectRoleDeptTree(Integer id);
    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    @Update("""
                    update my_dept set ancestors =
                    <foreach collection="depts" item="item" index="index"
                             separator=" " open="case dept_id" close="end">
                        when #{item.id} then #{item.ancestors}
                    </foreach>
                    where id in
                    <foreach collection="depts" item="item" index="index"
                             separator="," open="(" close=")">
                        #{item.id}
                    </foreach>
            """)
    int updateDeptChildren(@Param("depts")List<MyDept> depts);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Update("""
                    update my_dept d
                    <set>
                        <if test="parentId != null and parentId != 0">parent_id = #{parentId},</if>
                        <if test="deptName != null and deptName != ''"> `dept_name` = #{deptName},</if>
                        <if test="ancestors != null and ancestors != ''">ancestors = #{ancestors},</if>
                        <if test="sort != null">sort = #{sort},</if>
                        <if test="status != null">status = #{status},</if>
                        update_time = #{updateTime}
                    </set>
                    where d.dept_id = #{deptId}
            """)
    int updateDept(MyDept dept);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
    @Update("""
            update my_dept
            <set>
                <if test="status != null and status != ''">status = #{status},</if>
                update_time = #{updateTime}
            </set>
            where dept_id in (${ancestors})
            """)
     void updateDeptStatus(MyDept dept);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Select("select count(*) from my_dept where status = 1 and find_in_set(#{dept_id}, ancestors)")
    int selectNormalChildrenDeptById(Integer deptId);
    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Select("""
            select count(1) from my_dept
            <where>
                <if test="deptId != null and deptId != 0"> and dept_id = #{deptId} </if>
                <if test="parentId != null and parentId != 0"> and parent_id = #{parentId} </if>
            </where>
            """)
    int selectDeptCount(MyDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Select("select count(1) from my_user where dept_id = #{deptId}")
    int checkDeptExistUser(Integer deptId);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Delete("delete from my_dept where dept_id =#{deptId}")
    int deleteDeptById(Integer deptId);
}
