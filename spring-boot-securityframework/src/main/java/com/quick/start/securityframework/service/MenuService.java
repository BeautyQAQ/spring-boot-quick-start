package com.quick.start.securityframework.service;

import com.quick.start.securityframework.common.Result;
import com.quick.start.securityframework.common.util.TreeUtil;
import com.quick.start.securityframework.dao.MenuDao;
import com.quick.start.securityframework.dao.RoleMenuDao;
import com.quick.start.securityframework.dto.MenuDto;
import com.quick.start.securityframework.dto.MenuIndexDto;
import com.quick.start.securityframework.entity.MyMenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    public List<MyMenu> getMenuAll(String queryName, Integer queryType) {
        return menuDao.getFuzzyMenu(queryName, queryType);
    }

    public MyMenu getMenuById(Integer id) {
        return menuDao.getMenuById(id);
    }

    public List<MenuDto> buildMenuAll() {
        return menuDao.buildAll();
    }

    public Result updateMenu(MyMenu menu) {
        menu.setIcon("layui-icon " + menu.getIcon());
        return (menuDao.update(menu) > 0) ? Result.ok().message("修改成功") : Result.error().message("修改失败");
    }

    public Result<MyMenu> save(MyMenu menu) {
        menu.setIcon("layui-icon " + menu.getIcon());
        return (menuDao.save(menu) > 0) ? Result.ok().message("添加成功") : Result.error().message("添加失败");
    }

    /**
     * 如果这里删除了菜单树的父节点，把它的子节点一并删除
     *
     * @param id 父节点ID
     * @return 删除结果
     */
    public Result delete(Integer id) {
        Integer count = roleMenuDao.countRoleMenuByRoleId(id);
        if (count == 0) {
            menuDao.deleteById(id);
            List<Integer> list = menuDao.selectByParentId(id);
            if (list.size() > 0) {
                for (Integer parentId : list) {
                    menuDao.deleteByParentId(parentId);
                }
                menuDao.deleteByParentId(id);
            }
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("已经有角色分配该菜单，无法删除");
        }
    }

    public List<MenuDto> buildMenuAllByRoleId(Integer roleId) {
        List<MenuDto> listByRoleId = menuDao.listByRoleId(roleId);
        List<MenuDto> permissionDtos = menuDao.buildAll();
        List<MenuDto> tree = TreeUtil.menutree(listByRoleId, permissionDtos);
        return tree;
    }

    public List<MenuIndexDto> getMenu(Integer userId) {
        List<MenuIndexDto> list = menuDao.listByUserId(userId);
        List<MenuIndexDto> result = TreeUtil.parseMenuTree(list);
        return result;
    }
}
