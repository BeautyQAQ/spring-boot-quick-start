package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.entity.MyJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface JobDao {
    /**
     * 模糊查询岗位
     * @param queryName 查询的名称
     * @param queryStatus 状态查询
     * @return
     */
    @Select("""
            <script>
            select j.job_id,j.job_name,j.sort,j.status,j.create_time,j.update_time
            from my_job j
            <where>
                <if test="queryName != null and queryName != ''">
                    AND j.job_name like CONCAT('%', #{queryName}, '%')
                </if>
                <if test="queryStatus!= null and queryStatus != ''">
                    AND j.status = #{queryStatus}
                </if>
            </where>
            order by j.sort
            </script>
            """)
    List<MyJob> getFuzzyJob(String queryName, Integer queryStatus);

    /**
     * 新增岗位信息
     * @param job 岗位信息
     * @return 结果
     */
    @Insert("INSERT INTO my_job(job_name,status,sort, create_time, update_time) values(#{jobName},#{status},#{sort}, now(), now())")
    int insertDept(MyJob job);


    /**
     * 校验岗位名称
     * @param name 岗位名称
     * @return 结果
     */
    @Select("""
            select j.job_id,j.job_name,j.sort,j.status,j.create_time,j.update_time
                    from my_job j where j.job_name= #{jobName} limit 1
            """)
    MyJob checkJobNameUnique(String name);
    /**
     * 通过id查询岗位信息
     * @param jobId
     * @return
     */
    @Select("select j.job_id,j.job_name,j.status,j.sort,j.create_time,j.update_time from my_job j where j.job_id = #{jobId}")
    MyJob getJobById(Integer jobId);

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Delete("""
            <script>
            delete from my_job where job_id in
                    <foreach collection="array" item="jobId" open="(" separator="," close=")">
                        #{jobId}
                    </foreach>
            </script>
            """)
    int deleteJobByIds(Integer[] ids);

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    @Select("""
            SELECT j.job_id, j.job_name,j.status
            		FROM my_user u
            			 LEFT JOIN my_user_job uj ON u.user_id = uj.user_id
            			 LEFT JOIN my_job j ON uj.job_id = j.job_id
            		WHERE uj.user_id = #{userId}
            """)
    List<MyJob> selectJobsByUserId(Integer userId);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Select("""
            select j.job_id,j.job_name,j.sort,j.status,j.create_time,j.update_time
                    from my_job j
            """)
    List<MyJob> selectJobAll();

    /**
     * 修改岗位信息
     *
     * @param myJob 岗位信息
     * @return 结果
     */
    @Update("""
            <script>
            update my_job
                    <set>
                        <if test="jobName != null and jobName != ''">job_name = #{jobName},</if>
                        <if test="status != null">status = #{status},</if>
                        <if test="sort != null and sort != ''">sort = #{sort},</if>
                        update_time = #{updateTime}
                    </set>
                    where job_id = #{jobId}
            </script>
            """)
    int updateJob(MyJob myJob);
}
