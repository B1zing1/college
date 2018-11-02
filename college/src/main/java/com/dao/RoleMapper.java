package com.dao;

import com.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    //查询全部角色
    public List<Role> findAllRole();
    //更新中间表
    int updateMiddle(Map map);
    //根据角色id删除中间表信息
    int deleteMiddleByRoleId(int roleid);
    //查询有角色id的用户数
    int findUserByRoleId(int roleid);
}