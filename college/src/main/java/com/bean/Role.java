package com.bean;

import java.util.List;

public class Role {
    private Integer roleid;

    private String rolename;

    private Integer rolestate;
    //角色对应的菜单集合
    private List<Menu> menuList;
    //角色对应的用户集合
    private List<UserTb> userTbList;

    @Override
    public String toString() {
        return "Role{" + "roleid=" + roleid + ", rolename='" + rolename + '\'' + ", rolestate=" + rolestate + ", menuList=" + menuList + ", userTbList=" + userTbList + '}';
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<UserTb> getUserTbList() {
        return userTbList;
    }

    public void setUserTbList(List<UserTb> userTbList) {
        this.userTbList = userTbList;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getRolestate() {
        return rolestate;
    }

    public void setRolestate(Integer rolestate) {
        this.rolestate = rolestate;
    }
}