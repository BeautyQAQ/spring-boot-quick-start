## Mybatis-Plus

- 继承了 com.baomidou.mybatisplus.core.mapper.BaseMapper<T> 接口，这样常规的 CRUD 操作，MyBatis-Plus 就可以替我们自动生成。一般来说，开发 CRUD 业务的时候，最枯燥的就是要写 CRUD 的常用 SQL ，完全跟不上艿艿的思绪哈。
因为继承了 BaseMapper 接口，更多 BaseMapper 已经提供好的接口方法，可以看看 [《MyBatis-Plus 文档 —— CRUD 接口》](https://baomidou.com/pages/49cc81/) 。
- 对于 #selectByUsername(@Param("username") String username) 方法，使用了 com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<T> 构造相对灵活的条件，这样一些动态 SQL 就无需在 XML 中编写。 更多 QueryWrapper 已经提供好的拼接方法，可以看看 [《MyBatis-Plus 文档 —— 条件构造器》](https://baomidou.com/pages/10c804/) 。
- 建议 1 ：不要在 Service 中，使用 QueryWrapper 拼接动态条件。因为 BaseMapper 提供了 #selectList(Wrapper<T> queryWrapper) 等方法，促使我们能够在 Service 层的逻辑中，使用 QueryWrapper 拼接动态条件，这样会导致逻辑里遍布了各种查询，使我们无法对实际有哪些查询条件做统一的管理。碰到这种情况，建议封装到对应的 Mapper 中，这样会更加简洁干净可管理。
- 建议 2 ：因为 QueryWrapper 暂时不支持一些类似 <if /> 等 MyBatis 的 OGNL 表达式
- 对于 #selectByIds(@Param("ids") 方法，实际也可以使用 MyBatis-Plus 的 QueryWrapper 很方便的实现，这里仅仅是为了演示在 MyBatis-Plus 混合使用 XML 。
- 对于 #selectPageByCreateTime(IPage<UserDO> page, @Param("createTime") Date createTime) 方法，用于演示 MyBatis-Plus 提供的分页插件。 更多 IPage 的内容，可以看看 [《MyBatis-Plus 文档 —— 分页插件》](https://baomidou.com/pages/97710a/) 。
