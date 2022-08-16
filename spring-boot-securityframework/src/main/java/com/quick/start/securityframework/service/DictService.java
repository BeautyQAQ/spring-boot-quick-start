package com.quick.start.securityframework.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.common.ResultCode;
import com.quick.start.securityframework.common.UserConstants;
import com.quick.start.securityframework.dao.DictDao;
import com.quick.start.securityframework.dao.DictDetailDao;
import com.quick.start.securityframework.entity.MyDict;
import com.quick.start.securityframework.common.exption.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DictService {
    @Autowired
    private DictDao dictDao;

    @Autowired
    private DictDetailDao dictDetailDao;

    public Result<MyDict> getDictPage(Integer offectPosition, Integer limit, MyDict myDict) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyDict> fuzzyDictByPage = dictDao.getFuzzyDictByPage(myDict);
        return Result.ok().count(page.getTotal()).data(fuzzyDictByPage).code(ResultCode.TABLE_SUCCESS);
    }

    public MyDict getDictByName(String dictName) {
        return dictDao.getDictByName(dictName);
    }

    public String checkDictNameUnique(MyDict myDict) {
        MyDict info = dictDao.getDictByName(myDict.getDictName());
        if (ObjectUtil.isNotEmpty(info) && !info.getDictId().equals (myDict.getDictId())){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    public int insertDict(MyDict myDict) {
        return dictDao.insertDict(myDict);
    }


    public MyDict getDictById(Integer dictId) {
        return dictDao.getDictById(dictId);
    }

    public int updateDict(MyDict myDict) {
        return dictDao.updateDict(myDict);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteDictByIds(String ids)  throws MyException {
        Integer[] dictIds = Convert.toIntArray(ids);
        for (Integer dictId : dictIds){
            dictDetailDao.deleteDictDetailByDictId(dictId);
        }
        return dictDao.deleteDictByIds(dictIds);
    }
}
