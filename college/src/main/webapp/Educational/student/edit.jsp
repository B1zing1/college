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

</head>
<body>

<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》学生管理-》修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
</div>
</div>
<div class="cztable">
    <form action="/Educational/student/updateStudentById" method="post">
        <input type="hidden" name="studentid" value="${student1.studentid}">
        <table border="1" width="100%" class="table_a">
            <tr  width="120px;">
                <td width="10%">学号：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text"  name="studentno" value="${student1.studentno}" />
                </td>
            </tr>

            <tr  width="120px;">
                <td>姓名<span style="color:red">*</span>：</td>
                <td>
                    <input type="text"  name="stuname" value="${student1.stuname}" />
                </td>
            </tr>
            <tr>
                <td>学院<span style="color:red">*</span>：</td>

                <td>
                    <select name="deptid">
                        <option value="1" <c:if test="${student1.deptid == 1}">selected</c:if>>矿冶工程学院</option>
                        <option value="2" <c:if test="${student1.deptid == 2}">selected</c:if>>政治学院</option>
                        <option value="3" <c:if test="${student1.deptid == 3}">selected</c:if>>计算机学院</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>专业<span style="color:red">*</span>：</td>
                <td>
                    <select name="majorid" >
                    </select>
                </td>
            </tr>
            <tr>
                <td>班级<span style="color:red">*</span>：</td>
                <td>
                    <select name="classid">
                    </select>
                </td>
            </tr>
            <tr>
                <td>性别<span style="color:red">*</span>：</td>
                <td>
                    <input type="radio" name="stusex" <c:if test="${student1.stusex == 1}">checked</c:if> value="1" />男
                    <input type="radio" name="stusex" <c:if test="${student1.stusex == 0}">checked</c:if> value="0"/>女
                </td>
            </tr>

            <tr>
                <td>EMAIL：</td>
                <td>
                    <input type="text" name="email" value="${student1.email}" />
                </td>
            </tr>

            <tr>
                <td>联系电话：</td>
                <td>
                    <input type="text" name="phone" value="${student1.phone}" />
                </td>
            </tr>

            <tr>
                <td>户口所在地：</td>
                <td>
                    <input type="text" name="registered" value="${student1.registered}"  />
                </td>
            </tr>

            <tr>
                <td>住址：</td>
                <td>
                    <input type="text" name="address" value="${student1.address}" />
                </td>
            </tr>
            <tr>
                <td>政治面貌：</td>
                <td>
                    <input type="text" name="politics" value="${student1.politics}" />
                </td>
            </tr>
            <tr>
                <td>身份证号：</td>
                <td>
                    <input type="text" name="cardid" value="${student1.cardid}" />
                </td>
            </tr>
            <tr>
                <td>简介<span style="color:red">*</span>：</td>
                <td>
                    <textarea name="stucontent">${student1.stucontent}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan=2 align="center">
                    <input type="submit" value="提交" />
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
                    if(${student1.majorid} == op.value) {
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
                                    if(${student1.classid} == op.value) {
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
                        if(${student1.majorid} == op.value) {
                            op.selected = "selected";
                        }
                    }
                }
            });
        })
        $("[name=majorid]").change(function () {
            var majorid = $(this).val();
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
                        if(${student1.classid} == op.value) {
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

