package com.quick.start.middleware.dao;

import com.quick.start.middleware.dataobject.MongoUserDO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

@Repository
public class MongoUserDao {
    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(MongoUserDO entity){
        mongoTemplate.insert(entity);
    }

    public void updateById(MongoUserDO entity){
        // 生成 Update 条件
        final Update update = new Update();
        // 反射遍历 entity 对象，将非空字段设置到 Update 中
        ReflectionUtils.doWithFields(entity.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                /* 排除指定条件
                排除 id 字段，因为作为查询主键
                排除 @Transient 注解的字段，因为非存储字段
                排除静态字段 */
                if ("id".equals(field.getName())||field.getAnnotation(Transient.class)!=null|| Modifier.isStatic(field.getModifiers())){
                    return;
                }
                // 设置字段可反射
                if (!field.canAccess(entity)){
                    field.setAccessible(true);
                }
                // 排除字段为空的情况
                if (field.get(entity) == null){
                    return;
                }
                // 设置更新条件
                update.set(field.getName(), field.get(entity));
            }
        });
        // 防御，避免有业务传递空的 Update 对象
        if (update.getUpdateObject().isEmpty()){
            return;
        }
        // 执行更新
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(entity.getId())), update, MongoUserDO.class);
    }

    public void deleteById(Integer id){
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), MongoUserDO.class);
    }

    public MongoUserDO findById(Integer id){
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), MongoUserDO.class);
    }

    public MongoUserDO findByUsername(String username) {
        return mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), MongoUserDO.class);
    }

    public List<MongoUserDO> findAllById(List<Integer> ids){
        return mongoTemplate.find(new Query(Criteria.where("_id").in(ids)), MongoUserDO.class);
    }
}
