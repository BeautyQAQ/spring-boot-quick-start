package com.quick.start.securityframework.dao;

import com.quick.start.securityframework.entity.MyDictDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface DictDetailDao {

    @Select("""
            select did.id,did.dict_id,did.label,did.value,did.sort
            from my_dict_detail did where did.dict_id = #{dictId}
            """)
    List<MyDictDetail> getDictDetail(Integer dictId);
    /**
     * 插入字典详情
     * @param myDictDetail
     * @return
     */
    @Insert("INSERT INTO my_dict_detail(dict_id,label,value, sort,create_time, update_time)values(#{dictId},#{label},#{value},#{sort}, now(), now())")
    int insertDictDetail(MyDictDetail myDictDetail);

    /**
     * 通过id获得字典详情信息
     * @param id
     * @return
     */
    @Select("select did.id,did.dict_id,did.label,did.value,did.sort,did.create_time,did.update_time from my_dict_detail did  where did.id = #{id}")
    MyDictDetail getDictDetailById(Integer id);

    /**
     * 修改保存字典详情信息
     *
     * @param myDictDetail 岗位信息
     * @return 结果
     */
    @Update("""
            update my_dict_detail
                    <set>
                        <if test="label != null and label != ''">label = #{label},</if>
                        <if test="value != null">value = #{value},</if>
                        <if test="sort != null and sort != ''">sort = #{sort},</if>
                        update_time = #{updateTime}
                    </set>
                    where id = #{id}
            """)
    int updateDictDetail(MyDictDetail myDictDetail);

    /**
     * 批量删除字典详情
     *
     * @param dictDetailIds 需要删除的数据ID
     * @return 结果
     */
    @Delete("""
            delete from my_dict_detail where id in
                    <foreach collection="array" item="id" open="(" separator="," close=")">
                        #{id}
                    </foreach>
            """)
    int deleteDictDetailByIds(Integer[] dictDetailIds);

    /**
     *
     * 根据字典id删除字典详情
     * @param dictId
     * @return
     */
    @Delete("DELETE from my_dict_detail where dict_id = #{dictId}")
    int deleteDictDetailByDictId(Integer dictId);
}
