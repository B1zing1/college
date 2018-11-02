<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="/Script/Data.js" type="text/javascript"></script>

</head>
<body>

<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》考试-》修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="/Educational/exam/findAllExam">【返回】</a>
                </span>
            </span>
</div>
</div>
<div class="cztable">
    <form action="/Educational/exam/updateExam" method="post">

        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="80">编号：</td>
                <td>
                    <input name="examid" readonly="readonly" value="${exam.examid}" type="text" />
                </td>
            </tr>

            <tr>
                <td align="right">考试编号：</td>
                <td>
                    <input name="examnum" value="${exam.examnum}" type="text" />
                </td>
            </tr>

            <tr>
                <td align="right">考试科目：</td>
                <td>
                    <input name="examsubject" value="${exam.examsubject}" type="text" />
                </td>
            </tr>

            <tr>
                <td align="right">考试时间：</td>
                <td>
                    <input name="examtime" value="<fmt:formatDate value="${exam.examtime}" pattern="yyyy/MM/dd"></fmt:formatDate>"/>
                </td>
            </tr>

            <tr>
                <td align="right">考试班级：</td>
                <td>
                    <select name="deptid">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${exam.deptid == 1}">selected</c:if>>矿冶工程学院</option>
                        <option value="2" <c:if test="${exam.deptid == 2}">selected</c:if>>政治学院</option>
                        <option value="3" <c:if test="${exam.deptid == 3}">selected</c:if>>计算机学院</option>
                    </select>
                    <select name="majorid">
                        <option value="-1">请选择</option>
                    </select>
                    <select name="classid">
                        <option value="-1">请选择</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td align="right">考试人数：</td>
                <td>
                    <input name="examcount" value="${exam.examcount}" type="text" />
                </td>
            </tr>

            <tr>
                <td align="right">考试状态：</td>
                <td>
                    <input name="examstate" value="${exam.examstate}" type="text" />
                </td>
            </tr>

            <tr align="center">
                <td colspan="5" height="40">
                    <div align="center">

                        <input type="submit" id="button2" value="提交"/>
                    </div>
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
                    if("${student1.majorid}" == op.value) {
                        op.selected = "selected";
                    }
                    var majorid = $("[name=majorid]").val();
                    if($("[name=majorid]").attr("value") > 0) {
                        $.ajax({
                            url:"/Educational/class/findClassByMajorid",
                            data:"majorid=" + majorid,
                            type:"post",
                            dataType:"json",
                            success:function (classList) {
                                $("[name=classid]")[0].length = 0;
                                for (var i = 0; i < classList.length; i++) {
                                    var op = new Option(classList[i].classname, classList[i].classid);
                                    $("[name=classid]")[0].add(op);
                                    if("${student1.classid}" == op.value) {
                                        op.selected = "selected";
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }
    $(function () {
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
                        $("[name=classid]")[0].add(new Option("请选择", -1));
                        for(var i=0; i<classList.length; i++) {
                            $("[name=classid]")[0].add(new Option(classList[i].classname, classList[i].classid));
                        }
                    }
                });
            }
        })
    });
</script>
</body>
</html>
