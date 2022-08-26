package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.dto.ErrorLogDto;
import com.quick.start.securityframework.dto.LogDto;
import com.quick.start.securityframework.dto.LogQuery;
import com.quick.start.securityframework.entity.MyLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogDao {

    /**
     * 保存日志
     * 
     * @param log MyLog
     */
    @Insert("insert into my_log(user_name,ip,description,params,type,exception_detail,browser,method,time,create_time)values(#{userName},#{ip},#{description},#{params},#{type},#{exceptionDetail},#{browser},#{method},#{time},now())")
    void save(MyLog log);

    /**
     * 分页返回所有用户日志
     * 
     * @param logQuery 查询条件
     * @return List<LogDto>
     */
    @Select("""
            <script>
            SELECT l.user_name,l.ip,l.params,l.description,l.browser,l.time,l.method,l.create_time
                    FROM my_log l
                    <where>
                        <if test="logQuery.logType != null and logQuery.logType  != ''">
                            AND l.type = #{logQuery.logType}
                        </if>
                        <if test="logQuery.userName != null and logQuery.userName != ''">
                            AND l.user_name like CONCAT('%', #{logQuery.userName}, '%')
                        </if>
                    </where>
                    ORDER BY l.create_time desc
            </script>
            """)
    List<LogDto> getFuzzyLogByPage(@Param("logQuery") LogQuery logQuery);


    /**
     * 分页返回所有错误日志
     * 
     * @param logQuery 查询条件
     * @return List<ErrorLogDto>
     */
    @Select("""
            <script>
            SELECT l.user_name,l.ip,l.params,l.description,l.browser,l.exception_detail,l.method,l.create_time
                    FROM my_log l
                    <where>
                        <if test="logQuery.logType != null and logQuery.logType  != ''">
                            AND l.type = #{logQuery.logType}
                        </if>
                        <if test="logQuery.userName != null and logQuery.userName != ''">
                            AND l.user_name like CONCAT('%', #{logQuery.userName}, '%')
                        </if>
                    </where>
                    ORDER BY l.create_time desc
            </script>
            """)
    List<ErrorLogDto> getFuzzyErrorLogByPage(@Param("logQuery") LogQuery logQuery);


    /**
     * 删除所有日志
     * 
     * @param type 日志类型
     */
    @Delete("delete from my_log where type = #{type}")
    void delAllByInfo(String type);
}
