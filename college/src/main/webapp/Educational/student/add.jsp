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

            $('#form1').validate({
                debug:false, //false表示验证通过后不要自动提交表单
                onkeyup:false, //表示关闭按键松开时候监听验证

                rules:{
                    studentno:{
                        required:true,
                        remote:{
                            type:"POST",
                            url:"/Educational/student/checkStudentno",
                            data:{
                                studentno:function(){return $("[id='studentno']").val();}
                            }
                        }
                    }
                },
                messages:{
                    studentno:{
                        required:"学生编号必填",
                        remote:"学生编号已存在"
                    }
                }
            });


            $("[name=deptid]").change(function () {
                var departid = $(this).val();
                if(departid == -1) {
                    alert("请选择学院");
                    $("[name=majorid]")[0].length = 0;
                    $("[name=majorid]")[0].add(new Option("请选择", -1));
                    $("[name=classtid]")[0].length = 0;
                    $("[name=classid]")[0].add(new Option("请选择", -1));
                } else {
                    $.ajax({
                        url:"/Educational/class/findMajorByDeptid",
                        data:"departid=" + departid,
                        type:"post",
                        dataType:"json",
                        success:function (majorList) {
                            $("[name=majorid]")[0].length = 0;
                            $("[name=majorid]")[0].add(new Option("请选择", -1));
                            $("[name=classid]")[0].length = 0;
                            $("[name=classid]")[0].add(new Option("请选择", -1));
                            for(var i=0; i<majorList.length; i++) {
                                $("[name=majorid]")[0].add(new Option(majorList[i].majorname, majorList[i].majorid));
                            }
                        }
                    });
                }
            })
            $("[name=majorid]").change(function () {
                var departid = $("[name=deptid]").val();
                var majorid = $(this).val();
                if(majorid == -1) {
                    alert("请先选择学院");
                } else {
                    $.ajax({
                        url:"/Educational/class/findClassByMajorid",
                        data:"majorid=" + majorid,
                        type:"post",
                        dataType:"json",
                        success:function (classList) {
                            $("[name=classid]")[0].length = 0;
                            for(var i=0; i<classList.length; i++) {
                                $("[name=classid]")[0].add(new Option(classList[i].classname, classList[i].classid));
                            }
                        }
                    });
                }
            })
        });
    </script>

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》学生管理-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form name="form1" id="form1" action="/Educational/student/addStudent" method="post">
	<table border="1" width="100%" class="table_a">
                <tr  width="120px;">
                    <td width="10%">学号：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" id="studentno" name="studentno" />
					</td>
                </tr>

                <tr  width="120px;">
                    <td>姓名<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="stuname" />
					</td>
                </tr>
               <tr>
                    <td>学院<span style="color:red">*</span>：</td>
                    <td>
                        <select name="deptid">
                            <option value="-1">请选择</option>
                            <c:forEach items="${departmentList}" var="dept">
                                <option value="${dept.departid}">${dept.departname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>专业<span style="color:red">*</span>：</td>
                    <td>
                        <select name="majorid">
                            <option value="-1">请选择</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>班级<span style="color:red">*</span>：</td>
                    <td>
                        <select name="classid">
                            <option value="-1">请选择</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>性别<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" name="stusex" checked value="1" />男
                        <input type="radio" name="stusex" value="0"/>女
                    </td>
                </tr>
				<tr>
                    <td>EMAIL：</td>
                    <td>
                        <input type="text" name="email" />
                    </td>                
                </tr>

				<tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" name="phone" />
                    </td>                
                </tr>

				<tr>
                    <td>户口所在地：</td>
                    <td>
                        <input type="text" name="registered" />
                    </td>                
                </tr>

				<tr>
                    <td>住址：</td>
                    <td>
                        <input type="text" name="address" />
                    </td>                
                </tr>
				<tr>
                    <td>政治面貌：</td>
                    <td>
                        <input type="text" name="politics"/>
                    </td>                
                </tr>
				<tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="cardid" />
                    </td>                
                </tr>
				<tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name="stucontent"></textarea>
                    </td>
                </tr>
				<tr>
					<td colspan=2 align="center">
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
