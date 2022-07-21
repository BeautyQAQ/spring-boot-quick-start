package com.quick.start.securityframework.dao;


import com.quick.start.securityframework.entity.MyDict;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 */
@Mapper
public interface DictDao {

    /**
     * 模糊查询字典
     * @param myDict 状态查询
     * @return
     */
    @Select("""
            select di.dict_id,di.dict_name,di.description,di.sort,di.create_by,di.update_by,di.create_time,di.update_time
                    from my_dict di
                    <where>
                        <if test="dictName != null and dictName != ''">
                            AND di.dict_name like CONCAT('%', #{dictName}, '%')
                        </if>
                    </where>
            """)
    List<MyDict> getFuzzyDictByPage(MyDict myDict);

    /**
     * 通过字典名称获取字典信息
     * @param dictName
     * @return
     */
    @Select("""
            select di.dict_id,di.dict_name,di.description,di.sort,di.create_by,di.update_by,di.create_time,di.update_time
                    from my_dict di where di.dict_name = #{dictName}
            """)
    MyDict getDictByName(String dictName);

    /**
     * 插入字典
     * @param myDict
     * @return
     */
    @Insert("INSERT INTO my_dict(dict_id,dict_name,description, sort,create_time, update_time)values(#{dictId},#{dictName},#{description},#{sort}, now(), now())")
    int insertDict(MyDict myDict);

    /**
     * 通过id获得字典信息
     * @param dictId
     * @return
     */
    @Select("select di.dict_id,di.dict_name,di.description,di.sort,di.create_time,di.update_time from my_dict di  where di.dict_id = #{dictId}")
    MyDict getDictById(Integer dictId);

    /**
     * 修改保存字典信息
     *
     * @param myDict 岗位信息
     * @return 结果
     */
    @Select("""
                    update my_dict
                    <set>
                        <if test="dictName != null and dictName != ''">dict_name = #{dictName},</if>
                        <if test="description != null">description = #{description},</if>
                        <if test="sort != null and sort != ''">sort = #{sort},</if>
                        update_time = #{updateTime}
                    </set>
                    where dict_id = #{dictId}
            """)
    int updateDict(MyDict myDict);

    /**
     * 批量删除岗位信息
     *
     * @param dictIds 需要删除的数据ID
     * @return 结果
     */
    @Delete("""
            delete from my_dict where dict_id in
                    <foreach collection="array" item="dictId" open="(" separator="," close=")">
                        #{dictId}
                    </foreach>
            """)
    int deleteDictByIds(Integer[] dictIds);
}
