<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <script>

    </script>
</head>
<body>

<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》班级管理-》修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
</div>
</div>
<div class="cztable">
    <form action="/Educational/class/updateClassInfo" method="post">
        <input type="hidden" name="classid" value="${class1.classid}" />
        <table border="1" width="100%" class="table_a">

            <tr>
                <td  width="120px;">班级编号：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="classnum" value="${class1.classnum}" />
                </td>
            </tr>
            <tr>
                <td>学院<span style="color:red">*</span>：</td>
                <td>
                    <select name="deptid">
                        <option value="1" <c:if test="${class1.deptid == 1}">selected</c:if>>矿冶工程学院</option>
                        <option value="2" <c:if test="${class1.deptid == 2}">selected</c:if>>政治学院</option>
                        <option value="3" <c:if test="${class1.deptid == 3}">selected</c:if>>计算机学院</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>专业<span style="color:red">*</span>：</td>
                <td>
                    <select name="majorid">
                    </select>
                </td>
            </tr>

            <tr>
                <td>班级名称：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="classname" value="${class1.classname}" /></td>
            </tr>

            <tr>
                <td>开班时间：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="classbegin" value="<fmt:formatDate value="${class1.classbegin}" pattern="yyyy/MM/dd"></fmt:formatDate>" /></td>
            </tr>
            <tr>
                <td>毕业时间：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="classend" value="<fmt:formatDate value="${class1.classend}" pattern="yyyy/MM/dd"></fmt:formatDate>"/></td>
            </tr>
            <tr>
                <td>简介<span style="color:red">*</span>：</td>
                <td>
                    <textarea name="classcontent">${class1.classcontent}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="提交">
                    <input type="button" value="返回" onclick="history.back();">
                </td>
            </tr>
        </table>
    </form>
</div>

</div>
</div>
</div>

<script>
    var departid = $("[name=deptid]").val();
    if($("[name=deptid]").attr("value") > 0) {
        $.ajax({
            url:"/Educational/class/findMajorByDeptid",
            data:"departid=" + departid,
            type:"post",
            dataType:"json",
            success:function (majorList) {
                $("[name=majorid]")[0].length = 0;
                for (var i = 0; i < majorList.length; i++) {
                    var op = new Option(majorList[i].majorname, majorList[i].majorid);
                    $("[name=majorid]")[0].add(op);
                    if(${class1.majorid} == op.value) {
                        op.selected = "selected";
                    }
                }
            }
        });
    }
    $(function () {
        $("[name=deptid]").change(function () {
            var departid = $(this).val();
            $.ajax({
                url:"/Educational/class/findMajorByDeptid",
                data:"departid=" + departid,
                type:"post",
                dataType:"json",
                success:function (majorList) {
                    $("[name=majorid]")[0].length = 0;
                    for (var i = 0; i < majorList.length; i++) {
                        var op = new Option(majorList[i].majorname, majorList[i].majorid);
                        //alert(op.value);
                        $("[name=majorid]")[0].add(op);
                        if(${class1.majorid} == op.value) {
                            op.selected = "selected";
                        }
                    }
                }
            });
        })
    });
</script>

</body>
</html>
