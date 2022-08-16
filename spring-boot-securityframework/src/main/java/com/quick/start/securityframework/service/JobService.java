package com.quick.start.securityframework.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.common.ResultCode;
import com.quick.start.securityframework.common.UserConstants;
import com.quick.start.securityframework.dao.JobDao;
import com.quick.start.securityframework.dao.UserJobDao;
import com.quick.start.securityframework.dto.JobQueryDto;
import com.quick.start.securityframework.entity.MyJob;
import com.quick.start.securityframework.common.exption.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    
    @Autowired
    private JobDao jobDao;

    @Autowired
    private UserJobDao userJobDao;

    public Result<MyJob> getJobAll(Integer offectPosition, Integer limit, JobQueryDto jobQueryDto) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyJob> fuzzyJob = jobDao.getFuzzyJob(jobQueryDto.getQueryName(), jobQueryDto.getQueryStatus());
        return Result.ok().count(page.getTotal()).data(fuzzyJob).code(ResultCode.TABLE_SUCCESS);
    }

    public int insertJob(MyJob job) {
        return jobDao.insertDept(job);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param job 岗位信息
     * @return 结果
     */

    public String checkJobNameUnique(MyJob job) {
        MyJob info = jobDao.checkJobNameUnique(job.getJobName());
        if (ObjectUtil.isNotEmpty(info) && !info.getJobId().equals (job.getJobId())){
            return UserConstants.JOB_NAME_NOT_UNIQUE;
        }
        return UserConstants.JOB_NAME_UNIQUE;
    }

    public MyJob getJobById(Integer jobId) {
        return jobDao.getJobById(jobId);
    }

    public int deleteJobByIds(String ids) throws MyException {
        Integer[] jobIds = Convert.toIntArray(ids);
        for (Integer jobid:jobIds) {
            MyJob job = getJobById(jobid);
            if (countUserJobtById(jobid)>0) {
                throw new MyException(ResultCode.ERROR,job.getJobName()+ "已分配,不能删除");
            }
        }
        return jobDao.deleteJobByIds(jobIds);
    }

    public int countUserJobtById(Integer jobId) {
        return userJobDao.countUserJobtById(jobId);
    }

    public List<MyJob> selectJobAll() {
        return jobDao.selectJobAll();
    }

    public List<MyJob> selectJobsByUserId(Integer userId) {
        List<MyJob> userJobs = jobDao.selectJobsByUserId(userId);
        List<MyJob>jobs =jobDao.selectJobAll();
        for (MyJob job : jobs)
        {
            for (MyJob userJob : userJobs)
            {
                if (job.getJobId().equals(userJob.getJobId()))
                {
                    job.setFlag(true);
                    break;
                }
            }
        }
        return jobs;
    }

    public int updateJob(MyJob myJob) {
        return jobDao.updateJob(myJob);
    }

    public int changeStatus(MyJob myJob) {
        return jobDao.updateJob(myJob);
    }
}
