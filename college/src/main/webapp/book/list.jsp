<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="doPage" uri="http://java.sun.com/jsp/doPage/doPage" %>
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
    <script>
        function del(){
            return confirm("确认删除？");
        }

        $(function () {
            $("[name=all]").click(function () {
                var singles = $("[name=single]");
                for(var i=0; i<singles.length; i++) {
                    singles[i].checked = $(this)[0].checked;
                }
            });
        })

        $(function () {
            $("[name=num]").change(function () {
                var size = $(this).val();
                location.href = "/book/findAllBooks?size=" + size;
            });
        })

        function toExcel() {
            var singles = $("[name=single]");
            var k = -1;
            for(var i=0; i<singles.length; i++) {
                if(singles[i].checked) {
                    k = 1;
                    $("[name=form1]").attr("action", "/book/toExcel");
                    document.forms[0].submit();
                    break;
                }
            }
            if(k == -1) {
                alert("请选择要导出的书籍");
            }
        }

        function checkBid() {
            var singles = $("[name=single]");
            var k = -1;
            for(var i=0; i<singles.length; i++) {
                if(singles[i].checked) {
                    k = 1;
                    $("[name=form1]").attr("action", "/book/deleteBooks");
                    document.forms[0].submit();
                    break;
                }
            }
            if(k == -1) {
                alert("请选择要删除的书籍");
            }
        }

    </script>
</head>
<body>

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：书籍管理-》书籍管理</span> <span
                style="float:right;margin-right: 8px;font-weight: bold">
            <a style="text-decoration: none;" onclick="toExcel()" href="#">【导出excel】</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" onclick="checkBid()" href="#">【批量删除】</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a style="text-decoration: none;" href="/book/addBook.jsp">【新增书籍】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
</div>

<div class="morebt">

</div>

<div class="cztable">
<form action="" name="form1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody>
        <tr style="height: 25px" align="center">
            <th><input name="all" type="checkbox"/></th>
            <th scope="col">
                编号
            </th>

            <th scope="col">
                书籍名称
            </th>
            <th scope="col">
                状态
            </th>
            <th scope="col">
                操作
            </th>
        </tr>

        <c:forEach items="${pageInfo.list}" var="books" varStatus="sta">
            <tr align="center">
                <th><input name="single" value="${books.bookid}" type="checkbox"/></th>
                <td>
                    ${sta.count}
                </td>
                <td>
                        ${books.bookname}
                </td>
                <td>&nbsp;
                        ${books.bookstate}
                </td>

                <td>&nbsp;
                    <a href="/book/changeState?bookid=${books.bookid}&bookstate=${books.bookstate}">
                        <c:if test="${books.bookstate=='启用'}">禁用</c:if>
                        <c:if test="${books.bookstate=='禁用'}">启用</c:if>
                    </a>
                    <a href="/book/findBookById?bookid=${books.bookid}">详情</a>
                    <a href="/book/findBookToUpdate?bookid=${books.bookid}">修改</a>
                    <a href="/book/deleteBook?bookid=${books.bookid}" onclick="return del()" class="tablelink"> 删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="20" style="text-align: center;">
                <doPage:fy url="/book/findAllBooks" pageInfo="${pageInfo}"></doPage:fy>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</div>

</body>
</html>
