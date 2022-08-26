package com.quick.start.securityframework.controller;

import com.quick.start.securityframework.annotation.MyLog;
import com.quick.start.securityframework.common.PageTableRequest;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.entity.MyDictDetail;
import com.quick.start.securityframework.entity.MyJob;
import com.quick.start.securityframework.common.exption.MyException;
import com.quick.start.securityframework.service.DictDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/dictDetail")
@Api(tags = "系统：字典详情管理")
public class DictDetailController {
    @Autowired
    private DictDetailService detailService;

    @ApiOperation("查询字典详情")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('dict:list')")
    @ResponseBody
    public Result getDictDetailMaps(PageTableRequest pageTableRequest, String dictName) {
        pageTableRequest.countOffset();
        return detailService.getDictByName(pageTableRequest.getOffset(), pageTableRequest.getLimit(), dictName);
    }

    @GetMapping("/add")
    @ApiOperation(value = "添加字典详情页面")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String addDictDetail(Model model, MyDictDetail myDictDetail) {
        model.addAttribute("MyDictDetail", myDictDetail);
        return "/system/dict/dict-detail-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加字典详情")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    @MyLog("添加字典详情")
    public Result saveDictDetail(@RequestBody MyDictDetail myDictDetail) {
        return Result.judge(detailService.insertDictDetail(myDictDetail), "添加字典详情");
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改字典详情页面")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String editDictDetail(Model model, MyDictDetail myDictDetail) {
        model.addAttribute("MyDictDetail", detailService.getDictDetailById(myDictDetail.getId()));
        return "system/dict/dict-detail-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改字典详情")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    @MyLog("修改字典详情")
    public Result updateDictDetail(@RequestBody MyDictDetail myDictDetail) {
        return Result.judge(detailService.updateDictDetail(myDictDetail), "修改字典详情");
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除字典详情")
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<MyJob> deleteDict(String ids) {
        try {
            detailService.deleteDictDetailByIds(ids);
            return Result.ok().message("删除成功");
        } catch (MyException e) {
            return Result.error().message(e.getMsg()).code(e.getCode());
        }
    }
}
