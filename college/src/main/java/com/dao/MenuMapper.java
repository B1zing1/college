package com.dao;

import com.bean.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    //根据角色i的查询菜单集合
    List<Menu> findMenuByRoleId(int roleid);
    //查询全部菜单
    List<Menu> findAllMenu();
    //分页查询全部菜单
    List<Menu> findMenu(Map map);
    //查询上级菜单
    Menu findTopMenu(int upmenuid);
    //查询所有顶级菜单
    List<Menu> findAllTopMenu();
    //查询顶级菜单下是否还有二级菜单
    int findSecondMenu(Map map);
    //批量删除
    int deleteMenus(Map map);
    //删除
    int delete(Integer menuid);
}