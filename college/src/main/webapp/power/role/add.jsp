<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
    学生信息管理平台
</title>
    <link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
    <link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
    <link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
    <link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>

    <script src="/Script/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="/Script/jquery.validate.min.js" type="text/javascript"></script>

    <script>

        $(function () {
            $("#form1").validate({
                debug:false, //false表示验证通过后不要自动提交表单
                onkeyup:false, //表示关闭按键松开时候监听验证
                rules:{
                    rolename:{
                        required:true,
                        remote:{
                            type:"POST",
                            url:"/power/role/checkRoleName",
                            date:{ rolename:function () { return $("[id='rolename']").val(); }
                            }
                        }
                    }
                },
                messages:{
                    rolename:{
                        required:"角色名必填",
                        remote:"该角色名已存在"
                    }
                }
            })
        })

        function checkChoice() {
            var classes = $("[class=topMenu]");
            var b = false;
            for(var i=0; i<classes.length; i++) {
                if(classes[i].checked == true) {
                    b = true;
                    break;
                }
            }
            if(b == false) {
                alert("请选择权限");
            }
            return b;
        }
        function checkSecond(mid) {
            var c1 = "a" + mid;
            var classes = $("[class="+c1+"]");
            var menu = $("#m" + mid)[0];
            for(var i=0; i<classes.length; i++) {
                classes[i].checked = menu.checked;
            }
        }
        function getTop(mid) {
            var c1 = "a" + mid;
            var classes = $("[class="+c1+"]");
            var k = -1;
            for(var i=0;i<classes.length;i++){
                if(classes[i].checked==true){
                    document.getElementById("m"+mid).checked=true;
                    k=1;
                    break;
                }
            }
            if(k==-1){
                document.getElementById("m"+mid).checked=false;
            }
        }
    </script>

</head>
<body>
<div class="div_head">
            <span>
                <span style="float:left">当前位置是：权限管理-》角色管理-》修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="javascript:history.back();">【返回】</a>
                </span>
            </span>
</div>
</div>
<div class="cztable">
    <form id="form1" action="/power/role/addRole" method="post" >
        <table border="1" width="100%" class="table_a">
            <tr  width="120px;">
                <td width="120px">角色名：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" id="rolename" name="rolename"/>
                </td>
            </tr>
            <tr  width="120px;">
                <td>菜单资源<span style="color:red">*</span>：</td>
                <td>
                    <ul>
                        <c:forEach items="${menuList}" var="menu1">
                            <li>
                                <input id="m${menu1.menuid}" class="topMenu" onclick="checkSecond(${menu1.menuid})" type="checkbox" name="menus" value="${menu1.menuid}" />${menu1.menuname}
                                <ul>
                                    <c:forEach items="${menu1.secondMenuList}" var="menu2">
                                        <li>&nbsp;&nbsp;&nbsp;&nbsp;<input class="a${menu1.menuid}" onclick="getTop(${menu1.menuid})" type="checkbox" name="menus" value="${menu2.menuid}" />${menu2.menuname}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>

                </td>
            </tr>
            <tr>
                <td>启用状态<span style="color:red">*</span>：</td>
                <td>
                    <input type="radio" name="rolestate" checked value="1" />启用
                    <input type="radio" name="rolestate" value="0"/>禁用
                </td>
            </tr>
            <tr width="120px">
                <td colspan="2" align="center">
                    <input type="submit" value="添加" onclick="return checkChoice()" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
