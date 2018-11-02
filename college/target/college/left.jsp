<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv=content-type content="text/html; charset=utf-8" />
        <link href="css/admin.css" type="text/css" rel="stylesheet" />
        <script language=javascript>
            function expand(el)
            {
                childobj = document.getElementById("child" + el);

                if (childobj.style.display == 'none')
                {
                    childobj.style.display = 'block';
                }
                else
                {
                    childobj.style.display = 'none';
                }
                return;
            }
        </script>
    </head>
    <body background=img/menu_bg.jpg >
        <table height="100%" cellspacing=0 cellpadding=0 width=170   background=./img/menu_bg.jpg border=0>
            <tr>
                <td valign=top align=middle>
                    <table cellspacing=0 cellpadding=0 width="100%" border=0>
						<tr>
                            <td height=10></td>
						</tr>
					</table>

					<c:forEach items="${userTb1.role.menuList}" var="menu1" varStatus="sta">
						<!--一级菜单-->
						<table cellspacing=0 cellpadding=0 width=150 border=0>
							<tr height=22>
								<td style="padding-left: 30px" background=./img/menu_bt.jpg>
									<a     class=menuparent onclick=expand(${sta.count})
										   href="javascript:void(0);">${menu1.menuname}</a>
								</td>
							</tr>
							<tr height=4>
								<td></td>
							</tr>
						</table>
						<!--二级菜单-->
						<table id=child${sta.count} style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
							<c:forEach items="${menu1.secondMenuList}" var="menu2">
								<tr height=20>
									<td align=middle width=30>
										<img height=9 src="./img/menu_icon.gif" width=9>
									</td>
									<td>
										<a class=menuchild href="${menu2.menupath}" target="right">${menu2.menuname}</a>
									</td>
								</tr>
							</c:forEach>
							<tr height=4>
								<td colspan=2></td>
							</tr>
						</table>
					</c:forEach>



                    <%--<table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(1)  href="javascript:void(0);">个人中心</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>
                    <table id=child1 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                        
						<tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild href="user/MyUser.jsp" target="right">我的资料</a>
							</td>
						</tr>
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9   src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild href="user/class.jsp" target="right">班级信息</a>
							</td>
						</tr>
						<tr height=20>
                            <td align=middle width=30>
								<img height=9   src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="user/password.jsp"  target="right">修改密码</a>
							</td>
						</tr>
						<!--<tr height=20>
                            <td align=middle width=30>
								<img height=9   src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="user/systemMsge.html"  target="right">学院通知</a>
							</td>
						</tr>-->
                       
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>
                    
					<table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(2)    href="javascript:void(0);">教务中心</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>		
							
					<table id=child2 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                        
						<tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild href="/Educational/class/findAllClass" target="right">班级管理</a>
							</td>
						</tr>
						
						<tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="/Educational/student/findAllStudent"   target="right">学生管理</a>
							</td>
						</tr>
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="/Educational/exam/findAllExam"  target="right">组织考试</a>
							</td>
						</tr>

						 <tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="/Educational/findNotClass"  target="right">班级审核</a>
							</td>
						</tr>
						
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>
					
					<table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(3)    href="javascript:void(0);">学员中心</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>		
							
					<table id=child3 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                        
						<tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="user/msg.html"  target="right">学员信息</a>
							</td>
						</tr>
						
						<tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="Educational/list.html"   target="right">我的成绩</a>
							</td>
						</tr>
						 <tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="Educational/Book.html"   target="right">我的书籍</a>
							</td>
						</tr>
						
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>	
					
					<table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(4)    href="javascript:void(0);">学习中心</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>		
							
					<table id=child4 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="study/StudentMaterial.html"   target="right">资料下载</a>
							</td>
						</tr>
                       
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>	

					
					<table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(5)    href="javascript:void(0);">资料管理</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>		
							
					<table id=child5 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild href="/book/findAllInfo" target=right >资料上传</a>
							</td>
						</tr>
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="book/list.html"   target=right >书籍管理</a>
							</td>
						</tr>
                     </table>
					
                    
					<table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(7)    href="javascript:void(0);">权限管理</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>		
							
					<table id=child7 style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                    	<tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="power/user/list.html"  target="right">用户管理</a>
							</td>
						</tr>
                        
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="power/role/list.html"  target="right">角色管理</a>
							</td>
						</tr>
                        
                        <tr height=20>
                            <td align=middle width=30>
								<img height=9  src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild  href="power/menu/list.html"   target="right">菜单管理</a>
							</td>
						</tr>				
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>		--%>
				</td>
                <td width=1 bgcolor=#d1e6f7></td>
            </tr>
        </table>
    </body>
</html>