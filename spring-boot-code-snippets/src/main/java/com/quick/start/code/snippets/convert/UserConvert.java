package com.quick.start.code.snippets.convert;

import com.quick.start.code.snippets.businessobject.UserBo;
import com.quick.start.code.snippets.dataobject.UserDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * Mappings 注解用于字段不同的状况
     * Mappings 还支持多个对象转换成一个对象
     * @param userDo source
     * @return target
     */
     @Mappings(@Mapping(source = "id", target = "userId"))
     UserBo convert(UserDo userDo);
}
