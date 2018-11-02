<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../Script/Common.js" type="text/javascript"></script>
    <script src="../Script/Data.js" type="text/javascript"></script>

    <script src="/Script/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="/Script/jquery.validate.min.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function(){
            $('#form1').validate({
                debug:false, //false表示验证通过后不要自动提交表单
                onkeyup:false, //表示关闭按键松开时候监听验证

                rules:{
                    userContent:{
                        maxlength:20
                    },
                    userEmail:{
                        email:true
                    }
                },
                messages:{
                    userContent: {
                        maxlength:"简介字数不得超过20"
                    },
                    userEmail:"邮箱格式不正确"
                }
            });
        });
    </script>

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：个人中心-》我的信息</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form id="form1" name="form1" action="/user/updateUser" method="post">
        <input type="hidden" name="userId" value="${userTb1.userId}"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr  width="120px;">
                    <td width="120px">用户名：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="userName" value="${userTb1.userName}" />
					</td>
                </tr>

                <tr  width="120px;">
                    <td>姓名<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="userRealname" value="${userTb1.userRealname}" />
					</td>
                </tr>
                <tr>
                    <td>性别<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" name="userSex"  value="男" <c:if test="${userTb1.userSex=='男'}">checked</c:if> />男
                        <input type="radio" name="userSex" value="女" <c:if test="${userTb1.userSex=='女'}">checked</c:if>/>女
                    </td>
                </tr>

				<tr>
                    <td>EMAIL：</td>
                    <td>
                        <input type="text" name="userEmail" value="${userTb1.userEmail}" />
                    </td>                
                </tr>

				<tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" name="userPhone" value="${userTb1.userPhone}" />
                    </td>                
                </tr>

				<tr>
                    <td>住址：</td>
                    <td>
                        <input type="text" name="userAddress" value="${userTb1.userAddress}" />
                    </td>                
                </tr>
				              
				<tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="userIdcard" value="${userTb1.userIdcard}" />
                    </td>                
                </tr>
				
				
				<tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name="userContent">${userTb1.userContent}</textarea>
                    </td>
                </tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="保存">
			</td>
		</tr>
        
    </table>
	</form>
</div>
            </div>
        </div>
    </div>
</body>
</html>
