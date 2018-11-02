package com.service;

import com.bean.Menu;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface MenuService {

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
    PageInfo findMenu(int pageIndex, int size, List<Integer> menuidList);
    //查询上级菜单
    Menu findTopMenu(int upmenuid);
    //查询所有顶级菜单
    List<Menu> findAllTopMenu();
    //批量删除
    int deleteMenus(String[] single);
}
