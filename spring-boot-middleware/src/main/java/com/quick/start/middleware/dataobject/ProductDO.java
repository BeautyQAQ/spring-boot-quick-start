package com.quick.start.middleware.dataobject;

import com.quick.start.middleware.mongo.IncIdEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class ProductDO extends IncIdEntity<Integer> {

    /**
     * 商品名
     */
    private String name;

    public String getName() {
        return name;
    }

    public ProductDO setName(String name) {
        this.name = name;
        return this;
    }

}
