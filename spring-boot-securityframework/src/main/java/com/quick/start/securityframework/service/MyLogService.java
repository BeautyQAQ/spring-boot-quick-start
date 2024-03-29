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
import java.lang.reflect.Method;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyLogService {

    @Autowired
    private LogDao logDao;

    public Result<LogDto> getFuzzyInfoLogByPage(Integer offsetPosition, Integer limit, LogQuery logQuery) {
        Page page = PageHelper.offsetPage(offsetPosition, limit);
        List<LogDto> fuzzyLogByPage = logDao.getFuzzyLogByPage(logQuery);
        return Result.ok().count(page.getTotal()).data(fuzzyLogByPage).code(ResultCode.TABLE_SUCCESS);
    }

    public Result<ErrorLogDto> getFuzzyErrorLogByPage(Integer offsetPosition, Integer limit, LogQuery logQuery) {
        Page page = PageHelper.offsetPage(offsetPosition, limit);
        List<ErrorLogDto> fuzzyErrorLogByPage = logDao.getFuzzyErrorLogByPage(logQuery);
        return Result.ok().count(page.getTotal()).data(fuzzyErrorLogByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(String userName, String browser, String ip, ProceedingJoinPoint joinPoint, MyLog log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.quick.start.securityframework.annotation.MyLog myLog = method
                .getAnnotation(com.quick.start.securityframework.annotation.MyLog.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        log.setMethod(methodName);
        StringBuilder params = new StringBuilder("{");
        // 参数值
        Object[] argValues = joinPoint.getArgs();
        // 参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        log.setDescription(myLog.value());
        log.setIp(ip);
        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
                userName = new JSONObject(argValues[0]).get("userName").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
