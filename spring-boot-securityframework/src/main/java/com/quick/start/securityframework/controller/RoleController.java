package com.quick.start.securityframework.controller;

import com.quick.start.securityframework.annotation.MyLog;
import com.quick.start.securityframework.common.PageTableRequest;
import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.dto.RoleDto;
import com.quick.start.securityframework.entity.MyRole;
import com.quick.start.securityframework.service.RoleService;
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
@RequestMapping("/api/role")
@Api(tags = "系统：角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('role:list')")
    public String index() {
        return "system/role/role";
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "分页返回角色列表")
    @PreAuthorize("hasAnyAuthority('role:list')")
    @MyLog("查询角色")
    public Result roleList(PageTableRequest request, MyRole myRole) {
        request.countOffset();
        return roleService.getFuzzyRolesByPage(request.getOffset(), request.getLimit(), myRole);
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改角色页面")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public String editRole(Model model, MyRole role) {
        model.addAttribute("MyRole", roleService.getRoleById(role.getRoleId()));
        return "system/role/role-edit";
    }

    @GetMapping(value = "/edit/dataScope")
    @ApiOperation(value = "修改角色页面")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public String editRoleDataScope(Model model, MyRole role) {
        model.addAttribute("MyRole", roleService.getRoleById(role.getRoleId()));
        return "system/role/role-dataScope";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改角色")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    @MyLog("修改角色")
    public Result updateRole(@RequestBody RoleDto roleDto) {

        return roleService.update(roleDto);
    }

    @PutMapping(value = "/authDataScope")
    @ResponseBody
    @ApiOperation(value = "修改角色数据权限")
    public Result updateauthDataScope(@RequestBody RoleDto roleDto) {

        return roleService.authDataScope(roleDto);
    }

    @GetMapping(value = "/add")
    @ApiOperation(value = "添加角色页面")
    @PreAuthorize("hasAnyAuthority('role:add')")
    public String addRole(Model model) {
        model.addAttribute("MyRole", new MyRole());
        return "/system/role/role-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加角色")
    @PreAuthorize("hasAnyAuthority('role:add')")
    @MyLog("添加角色")
    public Result saveRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除角色")
    @PreAuthorize("hasAnyAuthority('role:del')")
    @MyLog("删除角色")
    public Result<MyRole> deleteRole(RoleDto roleDto) {
        return roleService.delete(roleDto.getRoleId());
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation(value = "角色列表")
    @PreAuthorize("hasAnyAuthority('user:list')")
    public Result<MyRole> getAll() {
        return roleService.getAllRoles();
    }
}
