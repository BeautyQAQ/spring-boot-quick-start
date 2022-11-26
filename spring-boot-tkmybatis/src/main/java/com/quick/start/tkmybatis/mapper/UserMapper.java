package com.quick.start.tkmybatis.mapper;

import com.quick.start.tkmybatis.dataobject.UserDO;
import com.quick.start.tkmybatis.util.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 继承的是，我们定义的 BaseMapper 接口
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {
    default UserDO selectByUsername(@Param("username") String username){
        Example example = new Example(UserDO.class);
        // 创建 Criteria 对象，设置 username 查询条件
        example.createCriteria().andEqualTo("username", username);
        return selectOneByExample(example);
    }

    default List<UserDO> selectListByCreateTime(@Param("createTime") Date createTime){
        Example example = new Example(UserDO.class);
        // 创建 Criteria 对象，设置 username 查询条件
        example.createCriteria().andGreaterThan("createTime", createTime);
        return selectByExample(example);
    }

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);
}
