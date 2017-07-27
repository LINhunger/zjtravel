<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv=“X-UA-Compatible” content=“IE=EmulateIE7″>
<meta http-equiv=“X-UA-Compatible” content=“IE=8″>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>粤西古村旅游网站开发与实现</title>
<link href="/frontend/css/base.css" rel="stylesheet" type="text/css" />
<link href="/frontend/css/denglu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/frontend/js/jquery1.42.min.js"></script>
</head>
<body>
<div class="logBox">
		<div class="login">
			<div class="siderNav">
				<ul class="topmenu" id="jq_topmenu">
                <li class="first"><a href="/">首页</a>|<a>关于我们</a></li>
              
				</ul>
			</div>
		</div>
</div>
<div class="main-content">
	<div class="wrapper">
    <form id="signupForm" method="post" class="zcform br" action="">
    	<h2 class="title2">登录<a>${error}</a></h2>
        <p class="clearfix">
       <label>用户名：</label><input id="telphone" name="username" class="required"  /><a  style="padding-left:10px; line-height:22px;" href="/register">注册</a>
        </p>
         <p class="clearfix">
           <label>密码：</label><input id="password" name="password" type="password" class=""  /><a  style="padding-left:10px; line-height:22px;" href="/forget">忘记密码</a>
        </p>
        <p class="clearfix agreement">
        	<input type="checkbox" name="rememberMe" id="rememberMe"/>
            <b class="left" style="font-weight:normal;">自动登录</b>
        </p>
       	<p class="clearfix"><input  class="submit br" type="submit" value="登录"/></p>
    </form>
</div>


</div>
<div id="footer">
  <div style="clear:both;"></div>
  </div>

</body>
</html>
<script>
    $(function () {

        $('#login').click(function(){
            var username = $("#telphone").val().trim();
            var password = $("#password").val().trim();
            var rememberMe = $("#rememberMe").val().trim();
            if(username == "" || password == ""){
                alert("请输入用户名和密码");
            }else{
                $.post("/login", {
                   username :  $("#telphone").val().trim(),
                    password : $("#password").val().trim(),
                    rememberMe : $("#rememberMe").val().trim()
                }, function(data){
                    var state = data.state;
                    console.log(data);
                    if(state==null){
                        alert("服务器异常!");
                        return ;
                    }
                    if(state=="121"){
                        alert("登录成功");
//                        window.location.href="/login";
                        return ;
                    }
                    if(state !="121"){
                        alert("登录失败!");
                        return ;
                    }
                },"json");
            }
        });
    });

</script>