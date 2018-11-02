<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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

    <script src="/Script/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script src="/Script/jquery.validate.min.js" type="text/javascript"></script>

    <script>

        window.onload = function(){
            showTime();     //网页一加载就调用showTime()函数；
        }
        function checkTime(i){  //补位处理
            if(i < 10)
            {
                i="0"+i;     //当秒分小于10时，在左边补0；
            }
            return i;
        }
        function showTime(){
            var now=new Date();
            var year=now.getFullYear();
            var month=now.getMonth()+1; //js获取的月份是从0开始；
            var day=now.getDate();

            document.getElementById("date").value=""+year+"/"+month+"/"+day;
            t=setTimeout('showTime()',500)
        }

       $(function () {
           $('#form1').validate({
               debug:false, //false表示验证通过后不要自动提交表单
               onkeyup:false, //表示关闭按键松开时候监听验证

               rules:{
                   informationid:{
                       remote:{
                           type:"POST",
                           url:"/book/checkInfoId",
                           data:{
                               informationid:function(){return $("[id='informationid']").val();}
                           }
                       }
                   },
                   informationname:{
                       maxlength:"15"
                   }
               },
               messages:{
                   informationid:{
                       remote:"资料编号已存在"
                   },
                   informationname:{
                       maxlength:"资料名不得超过15"
                   }
               }
           });
       })



        function checkFileSize(maxSize, fileId) {
            if(!maxSize) {
                maxSize = 10*1024*1024;
            }
            var myfile = document.getElementById(fileId);
            var fileInfo = myfile.files[0];
            if(!fileInfo) {
                alert("请选择文件");
                return false;
            }
            var isUpdate = true;
            if(fileInfo) {
                if(fileInfo.size > maxSize) {
                    isUpdate = false;
                    var size = maxSize/1024/1024;
                    alert("上传文件必须小于" + size + "M");
                } else if(fileInfo.size == 0) {
                    isUpdate = false;
                }
            }
            return isUpdate;
        }
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
    <form id="form1" name="form1" action="/book/addInfo" method="post" enctype="multipart/form-data">
	<table border="1" width="100%" class="table_a">
                <tr>
                    <td width="120px;">编号：<span style="color:red">*</span>：</td>
                    <td><input type="text" id="informationid" name="informationid" /></td>
                </tr>
                <tr>
                    <td>资料名称 ：<span style="color:red">*</span>：</td>
                    <td>
                       <input type="text" name="informationname" value="" />
                    </td>
                </tr>
               
                <tr>
                    <td>资料类型 ：<span style="color:red">*</span>：</td>
                    <td>
					<select name="typeid">
                    <option value="3">复习资料</option>
                    <option value="2">练习题</option>
                    <option value="1">内部资料</option>
                    <option value="4">真题</option>
                    </select>	
                    </td>
                </tr>
                <tr>
                    <td>更新时间：<span style="color:red">*</span>：</td>
                    <td>
						<input readonly="readonly" type="text" name="date" id="date" value="" /></td>
                </tr>
                <tr>
                    <td>上传人：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" readonly="readonly" name="uploader" value="${userTb1.userRealname}" /></td>
                </tr>
				<tr>
                    <td>上传：<span style="color:red">*</span>：</td>
                    <td>
                        <input type="file" name="myfile" id="myfile"/>
                    </td> 
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="提交" onclick="return checkFileSize(10*1024*1024,'myfile')">
                        <input type="submit" value="返回" onclick="history.back();">
                    </td>
                </tr>  
            </table>
	</form>
</div>

</body>
</html>
