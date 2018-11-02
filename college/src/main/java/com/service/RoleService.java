package com.service;

import com.bean.Menu;
import com.bean.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface RoleService {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    //查询全部角色
    public PageInfo findAllRole(int pageindex, int size);
    //更改角色状态
    int changeState(Role role);
    //主键查询角色信息
    Role findRoleById(int roleid);
    //主键查询用于修改
    Role findRoleToUpdate(int roleid);
    //更新中间表
    int updateMiddle(int roleid, int[] menus);
    //根据角色id删除中间表信息
    int deleteMiddleByRoleId(int roleid);
    //查询所有菜单
    List findAllMenu();
    //添加角色
    public int addRole(Role role, int[] menus);
}