<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="jquery.validate.js是一个强大的表单验证插件，这里将来个快速入门范例，展示它的快速易用性。" />

    <title>
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
    <script src="../Script/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.validate.min.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function(){
            $.validator.addMethod("checkPs",function(value,element,params){
                    var checkPs = /^\w{6,16}$/;
                    return this.optional(element)||(checkPs.test(value));
                }, "用户密码只允许6-16位英文字母、数字和下划线"
            );
            $('#form1').validate({
                debug:false, //false表示验证通过后不要自动提交表单
                onkeyup:false, //表示关闭按键松开时候监听验证

                rules:{
                    password1:{
                        required:true,
                        remote:{
                            type:"POST",
                            url:"/user/checkPass",
                            data:{
                                pass:function(){return $("[id='password1']").val();}
                            }
                        }
                    },
                    userPs:{
                        required:true,
                        checkPs:true
                    },
                    password3:{
                        required:true,
                        equalTo:"#userPs"
                    }
                },
                messages:{
                    password1:{
                        required:"原密码必填",
                        remote:"原密码错误"
                    },
                    userPs:{
                        required:"新密码必填"
                    },
                    password3:{
                        required:"重复密码必填",
                        equalTo:"两次密码要一致"
                    }
                }
            });
        });
    </script>
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：个人中心-》密码修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form id="form1" name="form1" onsubmit="true" action="/user/updatePs" method="post" >
        <input type="hidden" name="userId" value="${userTb1.userId}">
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="80">原密码：</td>
                <td><input type="password" id="password1" name="password1" /></td>
            </tr>
            <tr>
                <td align="right" width="90">新密码：</td>
                <td><input type="password" id="userPs" name="userPs" /></td>
            </tr>
            <tr>
                <td align="right" width="90">密码确认：</td>
                <td><input type="password" id="password3" name="password3" /></td>
            </tr>

            <tr align="center">
                <td colspan="5" height="40">
                    <div align="center">
                        <input type="submit" id="submit" value="确认" />
                    </div>
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
