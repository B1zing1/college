<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="doPage" uri="http://java.sun.com/jsp/doPage/doPage" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>学生信息管理平台</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css"/>
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css"/>
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css"/>
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
	<script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
	<script src="../../Script/Common.js" type="text/javascript"></script>
	<script src="../../Script/Data.js" type="text/javascript"></script>

	<script>
        function del(){
            if (confirm("确认删除该班级？")) {
                return true;
            } else {
                return false;
            }
        }

        $(function () {
            $("[name=num]").change(function () {
                var size = $(this).val();
                location.href = "/Educational/class/findAllClass?size=" + size;
            });
        })

        $(function () {
            $("[name=all]").click(function () {
                var singles = $("[name=single]");
                for(var i=0; i<singles.length; i++) {
                    singles[i].checked = $(this)[0].checked;
				}
            });
        })

		function toExcel() {
            var singles = $("[name=single]");
            var k = -1;
            for(var i=0; i<singles.length; i++) {
                if(singles[i].checked) {
                    k = 1;
                    document.forms[1].submit();
                    break;
                }
            }
            if(k == -1) {
                alert("请选择要导出的班级");
            }
        }
	</script>

</head>
<body>

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》班级管理</span> <span
				style="float: right; margin-right: 8px; font-weight: lighter">
            <span id="sp" onclick="toExcel()" style="color: #0b5b98; cursor: pointer">【导出excel】</span>&nbsp;&nbsp;
            <a style="text-decoration: blink" href="/Educational/class/findAllDepartment">【新增班级】&emsp;&emsp;&emsp;&emsp;</a>
		</span>
		</span>
</div>

<div class="cztable">
	<div>

		<ul class="seachform1">
			<form action="/Educational/class/findAllClass" method="post">
				<li>
					<label>班级名称:</label>
					<input name="classname" type="text" class="scinput1" value="${classname}"/>&nbsp;&nbsp;
					<input type="submit" class="scbtn" value="查询"/>&nbsp;
				</li>
			</form>

			<form action="/Educational/class/toExcel" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
					<tr style="font-weight: bold;">
						<th width="8%">
							<input name="all" type="checkbox"/>
						</th>
						<th>院系</th>
						<th width="">班级编号</th>
						<th width="">班级名称</th>
						<th width="15%">班主任老师</th>
						<th width="15%">人数</th>
						<th width="15%">班级状态</th>
						<th width="20%">操作</th>
					</tr>
					<c:forEach items="${pageInfo.list}" var="class1">
						<tr id="product1">
							<td width="8%" align="center">
								<input name="single" value="${class1.classid}" type="checkbox"/>
							</td>
							<td align="center">${class1.department.departname}</td>
							<td align="center">${class1.classnum}</td>
							<td align="center">${class1.classname}</td>
							<td align="center">${class1.classteacher}</td>
							<td align="center">${class1.peoplecount}</td>
							<td align="center">${class1.classstate}</td>

							<td align="center">
								<c:if test="${class1.classstate == '未审核' || class1.classstate == '审核未通过'}">
									<a href="/Educational/class/findClassById?classid=${class1.classid}">详情</a>&nbsp;
									<a href="/Educational/class/findClassToUpdate?classid=${class1.classid}">修改</a>&nbsp;
									<a href="/Educational/class/updateClassState?classid=${class1.classid}&auditcount=${class1.auditcount}">提交审核</a>
									&nbsp;<a href="/Educational/class/deleteClassById?classid=${class1.classid}" onclick="return del()">删除</a>
								</c:if>
								<c:if test="${class1.classstate == '审核中' || class1.classstate == '已毕业'}">
									&nbsp;<a href="/Educational/class/findClassById?classid=${class1.classid}">详情</a>
								</c:if>
								<c:if test="${class1.classstate == '审核通过'}">
									&nbsp;发书
									&nbsp;<a href="/Educational/class/findClassById?classid=${class1.classid}">详情</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="20" style="text-align: center;">

							<doPage:fy url="/Educational/class/findAllClass?classname=${classname}" pageInfo="${pageInfo}"></doPage:fy>

							<%--<a style="text-decoration: none;" href="#">
								<a href="/Educational/class/findAllClass?classname=${classname}&pageindex=${pageInfo.pageNum}&size=${size}">首页 </a>
								<a href="/Educational/class/findAllClass?classname=${classname}&pageindex=${pageInfo.prePage}&size=${size}">上一页</a>
								<c:forEach var="i" begin="1" end="${pageInfo.pages}">
									<a href="/Educational/class/findAllClass?classname=${classname}&pageindex=${i}&size=${size}">${i}</a>
								</c:forEach>
								<a href="/Educational/class/findAllClass?classname=${classname}&pageindex=${pageInfo.nextPage}&size=${size}">下一页</a>
								<a href="/Educational/class/findAllClass?classname=${classname}&pageindex=${pageInfo.pages}&size=${size}">尾页</a>
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
			</form>
	</div>
</div>
</body>
</html>


