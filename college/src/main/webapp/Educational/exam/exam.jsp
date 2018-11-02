<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    $(function () {
        $("[name=num]").change(function () {
            var size = $(this).val();
            location.href = "/Educational/exam/findAllExam?size=" + size;
        });
    })

    function doDel() {
        if (confirm('确定要删除吗？')) {
            return true;
        } else {
            return false;
        }
    }

</script>

</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》考试</span> <span
			style="float:right;margin-right: 8px;font-weight: bold"> <a
				style="text-decoration: none" href="/Educational/exam/findAllDepartment">【新增考试】</a>
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<ul class="seachform1">
				<form action="/Educational/exam/findAllExam" method="post">
					<li>
						<label>考试科目</label>
						<input name="examsubject" value="${examsubject}" type="text" class="scinput1" />&nbsp;&nbsp;
						<input  type="submit" class="scbtn" value="查询"/>&nbsp;
					</li>
						
				</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
					<th scope="col">编号</th>
					<th scope="col">考试编号</th>
					<th scope="col">考试科目</th>
					<th scope="col">考试时间</th>
					<th scope="col">考试班级</th>
					<th scope="col">考试状态</th>
					<th scope="col">操作</th>
				</tr>
				<c:forEach items="${pageInfo.list}" var="exam">
					<tr id="product1">
						<td align="center">${exam.examid}</td>
						<td align="center">${exam.examnum}</td>
						<td align="center">${exam.examsubject}</td>
						<td align="center">
							<fmt:formatDate value="${exam.examtime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
						</td>
						<td align="center">${exam.class1.classname}</td>
						<td align="center">${exam.examstate}</td>
						<td align="center">
							<c:if test="${exam.examstate == '已结束'}">
								<a href="/Educational/exam/findExamById?examid=${exam.examid}">详细</a>
								<a href="/Educational/exam/deleteExamById?examid=${exam.examid}" onclick="return doExit()" class="tablelink"> 删除</a>
							</c:if>
							<c:if test="${exam.examstate == '准备中'}">
								<a href="/Educational/exam/findExamToUpdate?examid=${exam.examid}">修改</a>
								<a href="/Educational/exam/deleteExamById?examid=${exam.examid}" onclick="return doDel()" class="tablelink"> 删除</a>
								<a href="/Educational/exam/findExamById?examid=${exam.examid}">详细</a>
							</c:if>
							<c:if test="${exam.examstate == '进行中'}">
								<a href="/Educational/exam/findExamById?examid=${exam.examid}">详细</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="20" style="text-align: center;">


						<doPage:fy url="/Educational/exam/findAllExam?examid=${examid}" pageInfo="${pageInfo}"></doPage:fy>

						<%--<a style="text-decoration: none;" href="#">
							<a href="/Educational/exam/findAllExam?examid=${examid}&pageindex=${pageInfo.pageNum}&size=${size}">首页 </a>
							<a href="/Educational/exam/findAllExam?examid=${examid}&pageindex=${pageInfo.prePage}&size=${size}">上一页</a>
							<c:forEach var="i" begin="1" end="${pageInfo.pages}">
								<a href="/Educational/exam/findAllExam?examid=${examid}&pageindex=${i}&size=${size}">${i}</a>
							</c:forEach>
							<a href="/Educational/exam/findAllExam?examid=${examid}&pageindex=${pageInfo.nextPage}&size=${size}">下一页</a>
							<a href="/Educational/exam/findAllExam?examid=${examid}&pageindex=${pageInfo.pages}&size=${size}">尾页</a>
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
</body>
</html>
