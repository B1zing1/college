package com.service.impl;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.dao.RoleMapper;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuService menuService;
    @Resource
    private UserTbMapper userTbMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer roleid) {

        int count = roleMapper.findUserByRoleId(roleid);
        if(count > 0) {
            return 0;
        }
        int count1 = roleMapper.deleteMiddleByRoleId(roleid);
        if(count1 > 0) {
            int count2 = roleMapper.deleteByPrimaryKey(roleid);
            return count2;
        }
        return 0;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleid) {
        return null;
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }

    @Override
    public PageInfo findAllRole(int pageindex, int size) {

        PageHelper.startPage(pageindex, size);
        List<Role> roleList = roleMapper.findAllRole();
        PageInfo pageInfo = new PageInfo(roleList);
        return pageInfo;
    }

    @Override
    @Transactional
    public int changeState(Role role) {

        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role findRoleById(int roleid) {

        Role role = roleMapper.selectByPrimaryKey(roleid);
        List<Menu> allMenuList = menuService.findMenuByRoleId(roleid);
        //将菜单进行分类
        List menuList = new ArrayList(); //存放分类后的菜单
        for (Menu menu : allMenuList) {
            if (menu.getUpmenuid() == -1) { //先找出一级菜单用于下面判断菜单是否是二级菜单
                List<Menu> secondMenuList = new ArrayList(); //存放一级菜单包含的二级菜单
                for (Menu menu2 : allMenuList) {
                    if (menu2.getUpmenuid() == menu.getMenuid()) {//根据一级菜单id和二级菜单的上级菜单id找出二级菜单存入二级菜单集合
                        secondMenuList.add(menu2);
                    }
                }
                //将二级菜单集合赋给一级菜单
                menu.setSecondMenuList(secondMenuList);
                //将一级菜单对象添加进存放所有菜单的集合
                menuList.add(menu);
            }
        }
        //将存放分类后的菜单的集合赋给角色
        role.setMenuList(menuList);
        return role;
    }

    @Override
    public Role findRoleToUpdate(int roleid) {

        Role role = roleMapper.selectByPrimaryKey(roleid);
        List<Menu> allMenuList = menuService.findAllMenu();
        //将存放分类后的菜单的集合赋给角色
        role.setMenuList(allMenuList);
        return role;
    }

    @Override
    @Transactional
    public int updateMiddle(int roleid, int[] menus) {
        Map map = new HashMap();
        map.put("roleid", roleid);
        map.put("menus", menus);
        return roleMapper.updateMiddle(map);
    }

    @Override
    @Transactional
    public int deleteMiddleByRoleId(int roleid) {
        return roleMapper.deleteMiddleByRoleId(roleid);
    }

    @Override
    public List findAllMenu() {

        return menuService.findAllMenu();
    }

    @Override
    @Transactional
    public int addRole(Role role, int[] menus) {

        int count = roleMapper.insert(role);
        Map map = new HashMap();
        map.put("roleid", role.getRoleid());
        map.put("menus", menus);
        int count2 = roleMapper.updateMiddle(map);
        return count2;
    }

}
