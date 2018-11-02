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
        /*$(function () {
            $("#form1").validate({
                debug:false, //false表示验证通过后不要自动提交表单
                onkeyup:false, //表示关闭按键松开时候监听验证
                rules:{
                    userName:{
                        required:true,
                        remote:{
                            type:"POST",
                            url:"/power/user/checkUserName",
                            data:{
                                userName:function () { return $("[id='userName']").val();}
                            }
                        }
                    },
                    userPs:{
                        required:true
                    }
                },
                messages:{
                    userName:{
                        required:"用户名必填",
                        remote:"该角色名已存在"
                    },
                    userPs:{
                        required:"密码必填"
                    }
                }
            })
        })*/
    </script>
    
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：权限管理-》用户管理-》修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="javascript:history.back();">【返回】</a>
                </span>
            </span>
        </div>
</div>
<div class="cztable">
	<form id="form1" action="/power/user/updateUser" method="post">
        <input type="hidden" name="userId" value="${user.userId}"/>
        <table border="1" width="100%" class="table_a">
                <tr  width="120px;">
                    <td width="120px">用户名：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" id="userName" name="userName" value="${user.userName}" />
					</td>
                </tr>

				<tr  width="120px;">
                    <td>密码：<span style="color:red">*</span>：</td>
                    <td>
						<input type="password" id="userPs" name="userPs" value="${user.userPs}" />
					</td>
                </tr>

                <tr  width="120px;">
                    <td>姓名<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="userRealname" value="${user.userRealname}" />
					</td>
                </tr>
                <tr>
                    <td>性别<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" name="gender" <c:if test="${user.userSex=='男'}">checked</c:if> value="男" />男
                        <input type="radio" name="gender" <c:if test="${user.userSex=='女'}">checked</c:if> value="女"/>女
                    </td>
                </tr>

				<tr>
                    <td>角色：<span style="color:red">*</span>：</td>
                    <td>
                        <select name="roleid">
							<c:forEach items="${roleList}" var="role">
                            <option <c:if test="${user.role.roleid==role.roleid}">selected</c:if> value="${role.roleid}">${role.rolename}</option>
                            </c:forEach>
						</select>
                    </td>
                </tr>

				<tr>
                    <td>EMAIL：</td>
                    <td>
                        <input type="text" name="userEmail" value="${user.userEmail}" />
                    </td>                
                </tr>

				<tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" name="userPhone" value="${user.userPhone}" />
                    </td>                
                </tr>

				<tr>
                    <td>住址：</td>
                    <td>
                        <input type="text" name="userAddress" value="${user.userAddress}" />
                    </td>                
                </tr>
				              
				<tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="userIdcard" value="${user.userIdcard}" />
                    </td>                
                </tr>
				
				
				<tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name="userContent">${user.userContent}</textarea>
                    </td>
                </tr>
				<tr>
					<td colspan=2 align="center">
						<input type="submit" value="提交" />
					</td> 
				</tr>
			</table>
	</form>
</div>

</body>
</html>
