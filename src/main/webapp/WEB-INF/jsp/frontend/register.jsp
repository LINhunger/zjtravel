<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>粤西古村旅游网站开发与实现</title>

    <script type="text/javascript" src="/frontend/js/jquery1.42.min.js"></script>
    <script language="javascript" type="text/javascript" src="/frontend/js/jquery.validate.js"></script>
    <script language="javascript" type="text/javascript" src="/frontend/js/validate_expand.js"></script>
    <script language="javascript" type="text/javascript" src="/frontend/js/validate.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            initValidator();
        });
    </script>
    <link href="/frontend/css/regist.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="content">
    <div class="demo">
        <div class="title"><img src="/frontend/images/demo_03.jpg" /><p>账号注册</p>
            <div class="c"></div>
        </div>
        <form id="thisForm" method="post" action="">
            <table width="50%">
                <tr>
                    <td class="tdcon"><span style="color:#F00;">*</span>用户名：</td>
                    <td>
                        <input class="stext" type='text' name="username" id="userName" value="" />
                        <div class="tipinfo"></div>
                    </td>
                </tr>
                <tr>
                    <td class="tdcon"><span style="color:#F00;">*</span>密码：</td>
                    <td>
                        <input class="stext" type="password" name="password" id="passWord" value="" />
                        <div class="tipinfo"></div>
                    </td>
                </tr>
                <tr>
                    <td class="tdcon"><span style="color:#F00;">*</span>确认密码：</td>
                    <td>
                        <input class="stext" type='password' name="passwordAgain" id="passWordAgain" value="" />
                        <div class="tipinfo"></div>
                    </td>
                </tr>

                <tr>
                    <td class="tdcon"><span style="color:#F00;">*</span>手机号码：</td>
                    <td>
                        <input class="stext" type='text' name="phone" id="sms" value="" />
                        <div class="tipinfo"></div>
                    </td>
                </tr>

                <tr>
                    <td class="tdcon"><span style="color:#F00;">*</span>验证码：</td>
                    <td>
                        <input class="stext" type='text' name="valcode" id="valcode" value="" />
                        <input  id="getcode" type='button' value="获取验证码" />
                    </td>
                </tr>

                <tr>
                    <td style="width:50px;"></td>
                    <td id="submit"><input type="button" value="立即注册" class="Button br" id="register" ></td>
                </tr>
            </table>
        </form>

        <div class="erweima">
            <p>已有阿拉亲子论坛账号？ 立即登录</p>
            <img src="/frontend/images/demo_07.jpg" width="170" height="170"/>
            <p>「最新资讯 请关注我们的微信号」</p>
        </div>
    </div>

</div>
</body>

</html>
<script>
    $(function () {

        $('#getcode').click(function(){

            var phone = $("#sms").val().trim();
            var mobile = /(^(13|14|15|18)\d{9}$)/;
            if(phone==""||!mobile.test(phone)){
                alert("填写格式错误!");
            }else{
                $.post("/valcode",{phone : phone},function(state){
                    if(state==null){
                        alert("服务器异常!");
                        return ;
                    }
                    if(state==200){
                        alert("获取验证码成功,请在手机上查收");
                        return ;
                    }
                    if(state==2){
                        alert("请填写正确的手机号!");
                        return ;
                    }
                    if(state!=2 && state !=200){
                        alert("获取验证码失败!");
                        return ;
                    }
                },"json");
            }
        });


        $('#register').click(function(){

            var username = $("#userName").val().trim();
            var password = $("#passWord").val().trim();
            var phone = $("#sms").val().trim();
            var valcode = $("#valcode").val().trim();
            var info = {
                username : username,
                password : password,
                phone : phone,
                valcode : valcode
            }
            var mobile = /(^(13|14|15|18)\d{9}$)/;
            if(phone==""||!mobile.test(phone)){
                alert("填写格式错误!");
            }else{


                $.ajax({
                    url: "/register",
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
                            alert("注册成功，确认跳转到登录页面");
                            window.location.href="/login";
                            return ;
                        }
                        else if(data==2){
                            alert("信息格式错误!");
                            return ;
                        }
                        else if(data==4){
                            alert("已存在的用户名!");
                            return ;
                        }
                        else {
                            alert("注册失败!");
                            return ;
                        }
                    }
                });



            }
        });


    });

</script>
