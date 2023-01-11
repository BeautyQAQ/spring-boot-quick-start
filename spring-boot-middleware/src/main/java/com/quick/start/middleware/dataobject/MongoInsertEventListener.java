package com.quick.start.middleware.dataobject;

import com.quick.start.middleware.mongo.IncIdEntity;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * MongoDB 实体对象在插入之前，会发布 BeforeConvertEvent 事件。
 * 所以，我们可以通过创建 MongoInsertEventListener 监听器，监听该事件，
 * 生成自增主键 ID 主键，设置到实体对象中。
 * 如果想要使用集合名作为 "sequence" 集合的 "id" ，
 * 可以使用 BeforeConvertEvent.collectionName 属性。
 */
@Component
public class MongoInsertEventListener extends AbstractMongoEventListener<IncIdEntity> {
    /**
     * sequence - 集合名
     */
    private static final String SEQUENCE_COLLECTION_NAME = "sequence";
    /**
     * sequence - 自增值的字段名
     */
    private static final String SEQUENCE_FIELD_VALUE = "value";

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<IncIdEntity> event){
        IncIdEntity entity = event.getSource();
        // 判断id为空
        if (entity.getId() == null){
            Number id = this.getNextId(entity);
            // 设置到实体中
            entity.setId(id);
        }
    }

    /**
     * 获得实体的下一个主键 ID 编号
     *
     * @param entity 实体对象
     * @return ID 编号
     */
    private Number getNextId(IncIdEntity entity) {
        // 使用实体名的简单类名，作为 ID 编号
        String id = entity.getClass().getSimpleName();
        Query query = new Query(Criteria.where("_id").is(id));
        // 创建update对象
        Update update = new Update();
        // 自增值
        update.inc(SEQUENCE_FIELD_VALUE, 1);
        // 创建FindAndModifyOptions对象
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        // 如果不存在时, 则进行插入
        findAndModifyOptions.upsert(true);
        // 返回新值
        findAndModifyOptions.returnNew(true);
        // 执行操作
        HashMap<String, Object> result = mongoTemplate.findAndModify(query, update, findAndModifyOptions, HashMap.class, SEQUENCE_COLLECTION_NAME);
        // 返回主键
        return (Number) result.get(SEQUENCE_FIELD_VALUE);
    }
}
