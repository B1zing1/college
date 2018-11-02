<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv=content-type content="text/html; charset=utf-8" />
        <link href="css/admin.css" type="text/css" rel="stylesheet" />

        <script src="/Script/jquery-1.8.0.min.js" type="text/javascript"></script>

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
                var h=now.getHours();
                var m=now.getMinutes();
                var s=now.getSeconds();
                m=checkTime(m)
                s=checkTime(s)

                var weekday=new Array(7)
                weekday[0]="星期日"
                weekday[1]="星期一"
                weekday[2]="星期二"
                weekday[3]="星期三"
                weekday[4]="星期四"
                weekday[5]="星期五"
                weekday[6]="星期六"
                var w=weekday[now.getDay()]; //js获取的星期是0~6,0是星期天；
                document.getElementById("show").innerHTML=""+year+"年"+month+"月"+day+"日 "+w+"  "+h+":"+m+":"+s;
                t=setTimeout('showTime()',500)
            }

            /*function checkP () {
                var num = $("[name=phone]").val();
                $.ajax({
                    type:"POST",
                    url:"/checkPhone",
                    dataType:"text",
                    data:{
                        phoneNum:num
                    },
                    success:function (rs) {
                        $('span').html(rs);
                    }
                })*/
                function checkW () {
                    var num = $("[name=weather]").val();
                    $.ajax({
                        type: "POST",
                        url: "/checkWeather",
                        dataType: "text",
                        data: {
                            cityName: num
                        },
                        success: function (rs) {
                            $('#span2').html(rs);
                        }
                    })

            }
        </script>
    </head>
    <body>
        <table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
            <tr height=28>
                <td background=./img/title_bg1.jpg>当前位置: </td></tr>
            <tr>
                <td bgcolor=#b1ceef height=1></td></tr>
            <tr height=20>
                <td background=./img/shadow_bg.jpg></td></tr></table>
        <table cellspacing=0 cellpadding=0 width="90%" align=center border=0>
            <tr height=100>
                <td align=middle width=100>
					<img height=100 src="./img/admin_p.gif"  width=90>
				</td>
                <td width=60>&nbsp;</td>
                <td>
                    <table height=100 cellspacing=0 cellpadding=0 width="100%" border=0>

                        <tr>
                         <td id="show"></td>
						</tr>
                        <tr>
                            <td style="font-weight: bold; font-size: 16px">${userTb1.userName}</td>
						</tr>
                        <tr>
                            <td>欢迎进入网站管理中心！</td></tr></table></td>
						</tr>
            <tr>
         <td colspan=3 height=10></td></tr></table>
        <table cellspacing=0 cellpadding=0 width="95%" align=center border=0>
            <tr height=20>
                <td></td></tr>
            <tr height=22>
                <td style="padding-left: 20px; font-weight: bold; color: #ffffff" 
                    align=middle background=./img/title_bg2.jpg>个人信息</td></tr>
            <tr bgcolor=#ecf4fc height=12>
                <td></td></tr>
            <tr height=20>
                <td></td></tr></table>
        <table cellspacing=0 cellpadding=2 width="95%" align=center border=0>
            <tr>
                <td align=right width=100>登陆帐号：</td>
                <td style="color: #880000">${userTb1.userName}</td></tr>
            <tr>
                <td align=right>真实姓名：</td>
                <td style="color: #880000">${userTb1.userRealname}</td></tr>
            <tr>
                <td align=right>注册时间：</td>
                <td style="color: #880000"><fmt:formatDate value="${userTb1.regdate}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td></tr>
            <tr>
                <td align=right>登陆次数：</td>
                <td style="color: #880000">${userTb1.logincount}</td></tr>
            <tr>
                <td align=right>上线时间：</td>
                <td style="color: #880000"><fmt:formatDate value="${logintime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
            <tr>
                <td align=right>手机归属地：</td>
                <td style="color: #880000">
                    <input name="phone" id="weather" onblur="checkP ()"/><span id="span"></span>
                </td>
            </tr>
            <tr>
                <td align=right>天气查询：</td>
                <td style="color: #880000">
                    <input name="weather" id="phone" onblur="checkW()"/><span id="span2"></span>
                </td>
            </tr>

        </table>		
<div style="text-align:center;">
<p>维护信息：<a href="http://www.zparkedu.com" target="_blank">湖南管理学院</a></p>
<p>联系电话：<a href="http://www.zparkedu.com" target="_blank">17110771077</a></p>
</div>
    </body>
</html>