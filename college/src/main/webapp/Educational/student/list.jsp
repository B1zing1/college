<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="doPage" uri="http://java.sun.com/jsp/doPage/doPage" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
<link href="../../css/mine.css" type="text/css" rel="stylesheet">
<script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../../Script/Common.js" type="text/javascript"></script>
<script src="../../Script/Data.js" type="text/javascript"></script>

	<script>
        /*function del() {
            confirm("确认删除？");
        }*/

        $(function () {
            $("[name=num]").change(function () {
                var size = $(this).val();
                location.href = "/Educational/student/findAllStudent?size=" + size;
            });
        })

	</script>

</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span>
                <span style="float: left;">当前位置是：教务中心-》学生管理</span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="/Educational/student/findAllDepartment">【新增学生】</a>&emsp;&emsp;&emsp;&emsp;
                </span>
            </span>
	</div>
	<div class="cztable">
		<div>
				  <form action="/Educational/student/findAllStudent" method="post">
                    学生名称: 
					<input type="text" name="stuname" value="${stuname}" />
                     学生学号: 
					<input type="text" name="studentno" value="${studentno}" />
					性别: 
					<select name="stusex">
							<option value="-1">--请选择--</option>
						<option value="1" <c:if test="${stusex == 1}">selected</c:if>>男</option>
							<option value="0" <c:if test="${stusex == 0}">selected</c:if>>女</option>
						</select>
					<input type="submit" value="查询" />
                </form>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
                        <th >学号</th>
						<th width="">姓名</th>
						<th width="">性别</th>
                        <th width="15%">联系电话</th>						
                        <th width="15%">专业</th>
						<th width="15%">登记时间</th>
						<th>操作</th>
                    </tr>

				<c:forEach items="${pageInfo.list}" var="student">
					<tr id="product1">
						<td align="center">${student.studentno}</td>
						<td align="center">${student.stuname}</td>
						<c:if test="${student.stusex == 1}">
							<td align="center">男</td>
						</c:if>
						<c:if test="${student.stusex == 0}">
							<td align="center">女</td>
						</c:if>
						<td align="center">${student.phone}</td>
						<td align="center">${student.major.majorname}</td>
						<td align="center">
							<fmt:formatDate value="${student.regdate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
						</td>
						<td align="center">
							<a href="/Educational/student/findStudentById?studentid=${student.studentid}">详细</a>
							<a href="/Educational/student/findStudentToUpdate?studentid=${student.studentid}">修改</a>
							<a href="/Educational/student/deleteStudent?studentid=${student.studentid}">退学</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="20" style="text-align: center;">

						<doPage:fy url="/Educational/student/findAllStudent?stuname=${stuname}&studentno=${studentno}&stusex=${stusex}" pageInfo="${pageInfo}"></doPage:fy>

						<%--<a style="text-decoration: none;" href="#">
							<a href="/Educational/student/findAllStudent?stuname=${stuname}&pageindex=${pageInfo.pageNum}&size=${size}">首页 </a>
							<a href="/Educational/student/findAllStudent?stuname=${stuname}&pageindex=${pageInfo.prePage}&size=${size}">上一页</a>
							<c:forEach var="i" begin="1" end="${pageInfo.pages}">
								<a href="/Educational/student/findAllStudent?stuname=${stuname}&pageindex=${i}&size=${size}">${i}</a>
							</c:forEach>
							<a href="/Educational/student/findAllStudent?stuname=${stuname}&pageindex=${pageInfo.nextPage}&size=${size}">下一页</a>
							<a href="/Educational/student/findAllStudent?stuname=${stuname}&pageindex=${pageInfo.pages}&size=${size}">尾页</a>
							共${pageInfo.total}条
							每页显示
							<select name="num">
								<option value="3" <c:if test="${size==3}">selected</c:if>>3</option>
								<option value="5" <c:if test="${size==5}">selected</c:if>>5</option>
								<option value="7" <c:if test="${size==7}">selected</c:if>>7</option>
							</select> ${pageInfo.pageNum}/${pageInfo.pages} </a>--%>
					</td>
				</tr>
			</tbody>
            </table>
	</div>

	</div>
	</div>

	</div>
</body>
</html>
