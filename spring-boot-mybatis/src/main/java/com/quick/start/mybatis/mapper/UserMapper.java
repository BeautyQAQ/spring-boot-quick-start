package com.quick.start.mybatis.mapper;

import com.quick.start.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * '@Repository' 注解，用于标记是数据访问 Bean 对象。在 MyBatis 的接口，实际非必须，只是为了避免在 Service 中，@Autowired 注入时无需报警
 */
@Repository
public interface UserMapper {

    int insert(UserDO user);

    int updateById(UserDO user);

    /**
     * '@Param' 注解，声明变量名。
     * 在方法为单参数时，非必须。
     * 在方法为多参数时，必须。禁止使用 Map 作为查询参数，因为无法通过方法的定义，很直观的看懂具体的用途
     * 生产使用标记删除。
     * @param id 参数id
     * @return 删除数量
     */
    int deleteById(@Param("id") Integer id);

    /**
     * @param id 参数id
     * @return UserDO
     */
    UserDO selectById(@Param("id") Integer id);

    UserDO selectByUsername(@Param("username") String username);

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

}
