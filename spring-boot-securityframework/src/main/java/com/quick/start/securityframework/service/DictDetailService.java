package com.quick.start.securityframework.service;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.common.ResultCode;
import com.quick.start.securityframework.dao.DictDetailDao;
import com.quick.start.securityframework.entity.MyDict;
import com.quick.start.securityframework.entity.MyDictDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDetailService {
    @Autowired
    private DictService dictService;

    @Autowired
    private DictDetailDao dictDetailDao;

    public Result<MyDictDetail> getDictByName(Integer offsetPosition, Integer limit, String dictName) {
        MyDict dictByName = dictService.getDictByName(dictName);
        Integer dictId = dictByName.getDictId();
        Page page = PageHelper.offsetPage(offsetPosition, limit);
        List<MyDictDetail> fuzzyDictDetailByPage = getDictDetail(dictId);
        return Result.ok().count(page.getTotal()).data(fuzzyDictDetailByPage).code(ResultCode.TABLE_SUCCESS);
    }

    public List<MyDictDetail> getDictDetail(Integer dictId) {
        return dictDetailDao.getDictDetail(dictId);
    }

    public int insertDictDetail(MyDictDetail myDictDetail) {
        return dictDetailDao.insertDictDetail(myDictDetail);
    }

    public MyDictDetail getDictDetailById(Integer id) {
        return dictDetailDao.getDictDetailById(id);
    }

    public int updateDictDetail(MyDictDetail myDictDetail) {
        return dictDetailDao.updateDictDetail(myDictDetail);
    }

    public int deleteDictDetailByIds(String ids) {
        Integer[] dictDetailIds = Convert.toIntArray(ids);
        return dictDetailDao.deleteDictDetailByIds(dictDetailIds);
    }

    public int deleteDictDetailByDictId(Integer dictId) {
        return dictDetailDao.deleteDictDetailByDictId(dictId);
    }
}
