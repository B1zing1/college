<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</script>
</head>
<body>
		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：书籍管理-》资料上传</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="../right.jsp" method="post">
	<table border="1" width="100%" class="table_a">
                <tr>
                    <td width="120px;">编号：<span style="color:red">*</span>：</td>
                    <td>
                        ${information.informationid}
                    </td>
                </tr>
                <tr>
                    <td>资料名称 ：<span style="color:red">*</span>：</td>
                    <td>
                       ${information.informationname}
                    </td>
                </tr>
               
                <tr>
                    <td>资料类型 ：<span style="color:red">*</span>：</td>
                    <td>
                        ${information.infotype.infotype}
                    </td>
                </tr>
                <tr>
                    <td>更新时间：<span style="color:red">*</span>：</td>
                    <td>
                        <fmt:formatDate value="${information.date}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
                    </td>
                </tr>
                <tr>
                    <td>格式：<span style="color:red">*</span>：</td>
                    <td>
                        ${information.filetype}
                    </td>
                </tr>
                <tr>
                    <td>上传人：<span style="color:red">*</span>：</td>
                    <td>
						${information.uploader}
                </tr>
                <tr>
                    <td colspan="2" align="center">
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
