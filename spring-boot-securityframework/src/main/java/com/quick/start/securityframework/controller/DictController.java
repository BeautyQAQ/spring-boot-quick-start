package com.quick.start.securityframework.controller;

import com.quick.start.securityframework.annotation.MyLog;
import com.quick.start.securityframework.common.PageTableRequest;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.common.UserConstants;
import com.quick.start.securityframework.entity.MyDict;
import com.quick.start.securityframework.entity.MyJob;
import com.quick.start.securityframework.common.exption.MyException;
import com.quick.start.securityframework.service.DictService;
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
@RequestMapping("/api/dict")
@Api(tags = "系统：字典管理")
public class DictController {
    @Autowired
    private DictService dictService;


    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('dict:list')")
    public String index(){
        return "system/dict/dict";
    }


    @GetMapping
    @ResponseBody
    @ApiOperation(value = "字典列表")
    @PreAuthorize("hasAnyAuthority('dict:list')")
    @MyLog("查询字典列表")
    public Result getDictAll(PageTableRequest pageTableRequest, MyDict myDict){
        pageTableRequest.countOffset();
        return dictService.getDictPage(pageTableRequest.getOffset(),pageTableRequest.getLimit(),myDict);
    }


    @GetMapping("/add")
    @ApiOperation(value = "添加字典页面")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String addDict(Model model){
        model.addAttribute("MyDict",new MyDict());
        return "/system/dict/dict-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加字典")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    @MyLog("添加字典")
    public Result saveDict(@RequestBody MyDict myDict){
        if (UserConstants.NOT_UNIQUE.equals(dictService.checkDictNameUnique(myDict))) {
            return Result.error().message("新增字典'" + myDict.getDictName() + "'失败，字典名称已存在");
        }
        return Result.judge(dictService.insertDict(myDict),"添加字典");
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改字典页面")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String editDict(Model model, MyDict myDict) {
        model.addAttribute("MyDict",dictService.getDictById(myDict.getDictId()));
        return "system/dict/dict-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改字典")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    @MyLog("修改字典")
    public Result updateDict(@RequestBody MyDict myDict){
        if (UserConstants.NOT_UNIQUE.equals(dictService.checkDictNameUnique(myDict))) {
            return Result.error().message("修改字典'" + myDict.getDictName() + "'失败，字典名称已存在");
        }
        return Result.judge(dictService.updateDict(myDict),"修改字典");
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除字典")
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<MyJob> deleteDict(String ids) {
        try {
            dictService.deleteDictByIds(ids);
            return Result.ok().message("删除成功");
        }catch (MyException e){
            return Result.error().message(e.getMsg()).code(e.getCode());
        }
    }
}
