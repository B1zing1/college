<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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

    <script src="../Script/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.validate.min.js" type="text/javascript"></script>

    <script>
        $(function () {
            $('#form1').validate({
                debug:false, //false表示验证通过后不要自动提交表单
                onkeyup:false, //表示关闭按键松开时候监听验证

                rules:{
                    bookid:{
                        remote:{
                            type:"POST",
                            url:"/book/checkBookId",
                            data:{
                                bookid:function(){return $("[id='bookid']").val();}
                            }
                        }
                    },
                    bookname:{
                        maxlength:"15"
                    },
                    bookremark:{
                        maxlength:"20"
                    }
                },
                messages:{
                    bookid:{
                        remote:"该书籍编号已存在"
                    },
                    bookname:{
                        maxlength:"书籍名称长度不得超过15"
                    },
                    bookremark:{
                        maxlength:"备注长度不得超过20"
                    }
                }
            });
        });

    </script>
    
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：资料管理-》书记管理-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="javascript:history.back();">【返回】</a>
                </span>
            </span>
        </div>
</div>
<div class="cztable">
	<form id="form1" action="/book/addBook" method="post">
        <table border="1" width="100%" class="table_a">
                <tr  width="120px;">
                    <td>书籍编号：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" id="bookid" name="bookid" />
					</td>
                </tr>

				<tr  width="120px;">
                    <td>书籍名称：<span style="color:red">*</span>：</td>
                    <td>
                    	<input type="text" name="bookname" />
					</td>
                </tr>

                <tr>
                    <td>启用状态<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" name="bookstate" checked value="启用" />启用
                        <input type="radio" name="bookstate" value="禁用"/>禁用
                    </td>
                </tr>
				
                <tr  width="120px;">
                    <td>备注<span style="color:red">*</span>：</td>
                    <td>
						<textarea rows="5" cols="20" name="bookremark"></textarea>
					</td>
                </tr>
				
				<tr width="120px">
					<td colspan="2" align="center">
						<input type="submit" value="添加" /> 
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
