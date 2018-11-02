package com.service.impl;

import com.bean.Menu;
import com.dao.MenuMapper;
import com.dao.MiddleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MiddleMapper middleMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer menuid) {

        Map map = new HashMap();
        map.put("upmenuid", menuid);
        int count = menuMapper.findSecondMenu(map);
        if(count > 0) {
            return 0;
        } else {
            List<Integer> menuidList = new ArrayList<>();
            menuidList.add(menuid);
            Map map1 = new HashMap();
            map1.put("menuids", menuidList);
            int count2 = middleMapper.deleteMiddle(map1);
            return menuMapper.deleteByPrimaryKey(menuid);
        }
    }

    @Override
    public int insert(Menu record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Menu record) {

        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuid) {

        return menuMapper.selectByPrimaryKey(menuid);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Menu record) {

        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return 0;
    }

    @Override
    public List<Menu> findMenuByRoleId(int roleid) {

        return menuMapper.findMenuByRoleId(roleid);
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenuList = menuMapper.findAllMenu();
        //将菜单进行分类
        List<Menu> menuList = new ArrayList(); //存放分类后的菜单
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
        return menuList;
    }

    @Override
    public PageInfo findMenu(int pageIndex, int size, List<Integer> menuidList) {

        PageHelper.startPage(pageIndex, size);
        Map map = new HashMap();
        map.put("menuids", menuidList);
        List<Menu> menuList = menuMapper.findMenu(map);
        PageInfo pageInfo = new PageInfo(menuList);
        return pageInfo;
    }

    @Override
    public Menu findTopMenu(int upmenuid) {
        return menuMapper.findTopMenu(upmenuid);
    }

    @Override
    public List<Menu> findAllTopMenu() {
        return menuMapper.findAllTopMenu();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int deleteMenus(String[] single) {

        //1.先将被删除的菜单分类
        List<Integer> firstMenuList = new ArrayList<>(); //保存一级菜单
        List<Integer> secondMenuList = new ArrayList<>(); //保存二级菜单
        for(String id : single) {
            int index = id.indexOf("-");
            int upmenuid = Integer.parseInt(id.substring(index + 1));
            int menuid = Integer.parseInt(id.substring(0, index));
            if(upmenuid == -1) {
                firstMenuList.add(menuid);
            } else {
                secondMenuList.add(menuid);
            }
        }
        //2.先删除二级菜单，在判断一级菜单下是否有二级菜单
        if(firstMenuList.size() == single.length) { //全是一级菜单
            //一级菜单下没有二级菜单则可以删除
            for (Integer id : firstMenuList) {
                try {
                    Map map = new HashMap();
                    map.put("upmenuid", id);
                    int secondMenuCount = menuMapper.findSecondMenu(map);
                    if(secondMenuCount == 0) { //该一级菜单下没有二级菜单，可以删除
                        int ii = menuMapper.delete(id);
                        Map map1 = new HashMap();
                        map1.put("menuids", firstMenuList);
                        int i = middleMapper.deleteMiddle(map1);
                    } else { //该一级菜单下有二级菜单，不能删除
                        int i = 1/0;
                    }
                } catch (Exception e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return 0;
                }
            }
        } else {
            if(secondMenuList.size() == single.length) { //全是二级菜单,可以直接删除
                Map map = new HashMap();
                map.put("menuids", secondMenuList);
                menuMapper.deleteMenus(map);
                middleMapper.deleteMiddle(map);
            } else {
                try {
                    //先删除二级菜单
                    Map mapp = new HashMap();
                    mapp.put("menuids", secondMenuList);
                    menuMapper.deleteMenus(mapp);
                    middleMapper.deleteMiddle(mapp);
                    //再判断一级菜单下是否还有二级菜单
                    for (Integer menuid : firstMenuList) {
                        Map map1 = new HashMap();
                        map1.put("upmenuid", menuid);
                        int count = menuMapper.findSecondMenu(map1);
                        if (count > 0) { //一级菜单下还有子菜单，不能删除
                            //int i = 1 / 0;
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return 0;
                        } else {
                            Map map2 = new HashMap();
                            map2.put("menuids", firstMenuList);
                            int i = menuMapper.delete(menuid);
                            int i2 = middleMapper.deleteMiddle(map2);
                        }
                    }
                } catch (Exception e) {
                    /*TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return 0;*/
                }
            }
        }
        return 1;
    }
}
