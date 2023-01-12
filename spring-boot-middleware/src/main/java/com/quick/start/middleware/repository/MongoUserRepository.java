package com.quick.start.middleware.repository;

import com.quick.start.middleware.dataobject.MongoUserDO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<MongoUserDO, Integer> {
    MongoUserDO findByUsername(String username);

    /**
     * 对于分页操作，需要使用到 Pageable 参数，需要作为方法的最后一个参数。
     * 基于方法名查询，不支持内嵌对象的属性。
     *
     * @param username username
     * @param pageable pageable
     * @return page
     */
    Page<MongoUserDO> findByUsernameLike(String username, Pageable pageable);

    /**
     * 使用username精确匹配
     * @param username username
     * @return UserDO
     */
    default MongoUserDO findByUsername01(String username){
        // 创建 Example 对象，使用 username 查询
        MongoUserDO probe = new MongoUserDO();
        probe.setUsername(username);
        // 精准匹配 username 查询
        Example<MongoUserDO> example = Example.of(probe);
        return findOne(example).orElse(null);
    }

    /**
     * 使用 username 模糊匹配
     */
    default MongoUserDO findByUsernameLike01(String username){
        MongoUserDO probe = new MongoUserDO();
        probe.setUsername(username);
        // 模糊匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MongoUserDO> example = Example.of(probe, exampleMatcher);
        return findOne(example).orElse(null);
    }
}
