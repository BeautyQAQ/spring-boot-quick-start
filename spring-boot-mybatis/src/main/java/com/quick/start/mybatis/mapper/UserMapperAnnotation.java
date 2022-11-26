package com.quick.start.mybatis.mapper;

import com.quick.start.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserMapperAnnotation {

    @Insert("INSERT INTO users(username, password, create_time) VALUES(#{username}, #{password}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(UserDO user);

    @Update(value = {"""
            <script>
            UPDATE users
            <set>
            <if test='username != null'>, username = #{username}</if>
            <if test='password != null'>, password = #{password}</if>
            </set>
            WHERE id = #{id}
            </script>
    """})
    int updateById(UserDO user);

    @Insert("DELETE FROM users WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Select("SELECT username, password, create_time FROM users WHERE id = #{id}")
    UserDO selectById(@Param("id") Integer id);

    @Select("SELECT username, password, create_time FROM users WHERE username = #{username}")
    UserDO selectByUsername(@Param("username") String username);

    @Select(value = {"""
            <script>
            SELECT username, password, create_time FROM users
            WHERE id IN
            <foreach item='id' collection='ids' separator=',' open='(' close=')' index=''>
            #{id}
            </foreach>
            </script>
    """})
    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

}
