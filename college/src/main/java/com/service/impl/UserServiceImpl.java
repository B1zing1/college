package com.service.impl;

import com.bean.Class;
import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MenuService menuService;
    @Resource
    private UserTbMapper userTbMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {

        return userTbMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserTb record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(UserTb record) {

        return userTbMapper.insertSelective(record);
    }

    //主键查询
    @Override
    public UserTb selectByPrimaryKey(Integer userId) {
        UserTb userTb = userTbMapper.selectByPrimaryKey(userId);
        return userTb;
    }

    //更新数据
    @Override
    @Transactional
    public int updateByPrimaryKeySelective(UserTb record) {

        int count = userTbMapper.updateByPrimaryKeySelective(record);
        return count;
    }

    @Override
    public int updateByPrimaryKey(UserTb record) {
        return 0;
    }

    //登陆
    public UserTb login(UserTb userTb) {

        UserTb userTb1 = userTbMapper.login(userTb.getUserName());
        //判断用户名和密码
        if(userTb1 != null && userTb.getUserPs().equals(userTb1.getUserPs())) {
            //判断用户权限是否禁用
            if(userTb1.getRole().getRolestate() == 1) {
                Role role = userTb1.getRole();
                //根据角色id查询所对应的菜单集合
                List<Menu> menuList = menuService.findMenuByRoleId(role.getRoleid());
                //将菜单进行分类
                List allMenuList = new ArrayList(); //存放分类后的菜单
                for(Menu menu : menuList) {
                    if(menu.getUpmenuid() == -1 && menu.getMenustate() == 1) { //先找出一级菜单用于下面判断菜单是否是二级菜单
                        List secondMenuList = new ArrayList(); //存放一级菜单包含的二级菜单
                        for(Menu menu2 : menuList) {
                            if(menu2.getUpmenuid() == menu.getMenuid() && menu2.getMenustate() == 1) {//根据一级菜单id和二级菜单的上级菜单id找出二级菜单存入二级菜单集合
                                secondMenuList.add(menu2);
                            }
                        }
                        //将二级菜单集合赋给一级菜单
                        menu.setSecondMenuList(secondMenuList);
                        //将一级菜单对象添加进存放所有菜单的集合
                        allMenuList.add(menu);
                    }
                }
                //将存放分类后的菜单的集合赋给角色
                role.setMenuList(allMenuList);
                //将角色赋给用户
                userTb1.setRole(role);
                //修改登录次数
                userTb1.setLogincount(userTb1.getLogincount() + 1);
                userTbMapper.updateByPrimaryKey(userTb1);
                UserTb manager = userTbMapper.selectByPrimaryKey(userTb1.getManagerid());
                userTb1.setManager(manager);
                return userTb1;
            } else {
                return null;
            }
        }
        return null;
    }

    //查询班级信息
    @Override
    public Class findClassInfo(int studentId) {

        Class class1 = userTbMapper.findClassInfo(studentId);
        if (class1 != null) {
            return class1;
        }
        return null;
    }

    @Override
    public List findAll(int departid, int majorid, String rolename) {
        Map map = new HashMap();
        map.put("departid", departid);
        map.put("majorid", majorid);
        map.put("rolename", rolename);
        return userTbMapper.findAll(map);
    }

    @Override
    public PageInfo findAllUser(int pageindex, int size, int[] single) {
        //显示页码和显示条数
        PageHelper.startPage(pageindex, size);
        Map map = new HashMap();
        map.put("userIds", single);
        List list = userTbMapper.findAllUser(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public int deleteUsers(int[] single) {
        Map map = new HashMap();
        map.put("userIds", single);
        int count = userTbMapper.deleteUsers(map);
        return count;
    }

}















