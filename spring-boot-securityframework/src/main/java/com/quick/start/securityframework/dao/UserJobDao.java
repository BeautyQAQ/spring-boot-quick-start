package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.entity.MyUserJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserJobDao {

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param jobId 岗位ID
     * @return 结果
     */
    @Select("select count(1) from my_user_job where job_id=#{jobId}")
    int countUserJobtById(Integer jobId);

    /**
     * 批量新增用户岗位信息
     *
     * @param userJobList 用户角色列表
     * @return 结果
     */
    @Insert("""
            <script>
            insert into my_user_job(user_id, job_id) values
                    <foreach item="item" index="index" collection="list" separator=",">
                        (#{item.userId},#{item.jobId})
                    </foreach>
            </script>
            """)
    int batchUserJob(List<MyUserJob> userJobList);

    /**
     * 通过用户ID删除用户和岗位关联
     *
     * @param id 用户ID
     * @return 结果
     */
    @Delete("delete from my_user_job where user_id=#{id}")
    int deleteUserJobByUserId(Integer id);
}
