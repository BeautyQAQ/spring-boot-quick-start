package com.quick.start.securityframework.service;

import cn.hutool.json.JSONObject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.common.ResultCode;
import com.quick.start.securityframework.dao.LogDao;
import com.quick.start.securityframework.dto.ErrorLogDto;
import com.quick.start.securityframework.dto.LogDto;
import com.quick.start.securityframework.dto.LogQuery;
import com.quick.start.securityframework.entity.MyLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class MyLogService {
    @Autowired
    private LogDao logDao;

    public Result<LogDto> getFuzzyInfoLogByPage(Integer offectPosition, Integer limit, LogQuery logQuery) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<LogDto> fuzzyLogByPage = logDao.getFuzzyLogByPage(logQuery);
        return Result.ok().count(page.getTotal()).data(fuzzyLogByPage).code(ResultCode.TABLE_SUCCESS);
    }

    public Result<ErrorLogDto> getFuzzyErrorLogByPage(Integer offectPosition, Integer limit, LogQuery logQuery) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<ErrorLogDto> fuzzyErrorLogByPage = logDao.getFuzzyErrorLogByPage(logQuery);
        return Result.ok().count(page.getTotal()).data(fuzzyErrorLogByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(String userName, String browser, String ip, ProceedingJoinPoint joinPoint, MyLog log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.quick.start.securityframework.annotation.MyLog myLog = method.getAnnotation(com.quick.start.securityframework.annotation.MyLog.class);
        // ????????????
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        StringBuilder params = new StringBuilder("{");
        //?????????
        Object[] argValues = joinPoint.getArgs();
        //????????????
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // ??????
        if (log != null) {
            log.setDescription(myLog.value());
        }
        assert log != null;
        log.setIp(ip);
        String loginPath = "login";
        if(loginPath.equals(signature.getName())){
            try {
                assert argValues != null;
                userName = new JSONObject(argValues[0]).get("userName").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setMethod(methodName);
        log.setUserName(userName);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        logDao.save(log);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        logDao.delAllByInfo("ERROR");
    }

    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        logDao.delAllByInfo("INFO");
    }
}
