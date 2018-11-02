<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

</head>
<body>
		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：书籍管理-》修改资料</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/book/toupload" method="post" enctype="multipart/form-data" >
	    <table border="1" width="100%" class="table_a">
                <tr>
                    <td width="120px;">编号：<span style="color:red">*</span>：</td>
                    <td><input type="text" readonly="readonly" name="informationid" value="${information.informationid}" /></td>
                </tr>
                <tr>
                    <td>资料名称 ：<span style="color:red">*</span>：</td>
                    <td>
                       <input type="text" name="informationname" value="${information.informationname}" />
                    </td>
                </tr>
               
                <tr>
                    <td>资料类型 ：<span style="color:red">*</span>：</td>
                    <td>
					<select name="typeid">
                        <option value="3" <c:if test="${information.infotype.infotype=='复习资料'}">selected</c:if>>复习资料</option>
                    <option value="2" <c:if test="${information.infotype.infotype=='练习题'}">selected</c:if>>练习题</option>
                    <option value="1" <c:if test="${information.infotype.infotype=='内部资料'}">selected</c:if>>内部资料</option>
                    <option value="4" <c:if test="${information.infotype.infotype=='真题'}">selected</c:if>>真题</option>
                    </select>	
                    </td>
                </tr>
                <tr>
                    <td>更新时间：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="date" value="<fmt:formatDate value="${information.date}" pattern="yyyy/MM/dd"></fmt:formatDate>" /></td>
                </tr>

                <tr>
                    <td>上传人：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="uploader" value="${information.uploader}" /></td>
                </tr>
			<<tr>
                    <td>上传：<span style="color:red">*</span>：</td>
                    <td>
                        <a href="${information.filelocation}"></a>
                        <input type="file" name="myfile">
                    </td> 
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="提交">
                        <input type="submit" value="返回" onclick="history.back();">
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
