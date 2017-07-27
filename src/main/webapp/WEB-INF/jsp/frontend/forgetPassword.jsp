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
        <form  method="post" class="zcform br" action="">
            <h2 class="title2">忘记密码</h2>
            <p class="clearfix">
                <label>手机号：</label><input id="phone" name="phone" name="username" />
                <span  style="padding-left:10px; line-height:22px;"  id="getcode">获取验证码</span>
            </p>
            <p class="clearfix">
                <label>验证码：</label><input id="valcode"  type="text" class="" name="username" />
            </p>
            <p class="clearfix">
                <label>新密码：</label><input id="nPassword"  type="password" class="" name="username" />
            </p>
            <p class="clearfix" id="changePassword"><input  class="submit br" type="button" value="修改密码"/></p>
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

        $('#getcode').click(function(){

            var phone = $("#phone").val().trim();
            var mobile = /(^(13|14|15|18)\d{9}$)/;
            if(phone==""||!mobile.test(phone)){
                alert("填写格式错误!");
            }else{
                $.post("/valcode",{phone : phone},function(data){
                    if(data==null){
                        alert("服务器异常!");
                        return ;
                    }
                    if(data==200){
                        alert("获取验证码成功,请在手机上查收");
                        return ;
                    }
                    if(data!=200){
                        alert("获取验证码失败!");
                        return ;
                    }
                },"json");
            }
        }),
                $('#changePassword').click(function(){

                    var phone = $("#phone").val().trim();
                    var valcode = $("#valcode").val().trim();
                    var nPassword = $("#nPassword").val().trim();
                    var info = {
                        phone : phone,
                        valcode : valcode,
                        nPassword : nPassword
                    }
                    var mobile = /(^(13|14|15|18)\d{9}$)/;
                    if(phone==""||!mobile.test(phone) || nPassword.length <6){
                        alert("填写格式错误!");
                    }else{


                        var data = JSON.stringify({"phone": phone, "valcode": valcode, "nPassword": nPassword});
                        $.ajax({
                            url: "/frontend/person/changePassword",
                            type: "POST",
                            cache: false,
                            contentType: "application/json; charset=utf-8",
                            data: JSON.stringify(info),
                            dataType: 'json',
                            success: function(data) {
                                if(data==null){
                                    alert("服务器异常!");
                                    return ;
                                }
                                if(data==1){
                                    alert("修改密码成功");
                                    window.location.href="/login";
                                    return ;
                                }
                                if(data==2){
                                    alert("密码格式错误!");
                                    return ;
                                }
                                if(data==3){
                                    alert("验证码错误!");
                                    return ;
                                }
                            }
                        });



                    }
                });
    });
</script>