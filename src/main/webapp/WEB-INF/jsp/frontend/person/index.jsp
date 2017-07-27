<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../tag/tag.jsp"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>个人主页</title>
    <link href="/frontend/css/ddxq.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/frontend/js/public.js"></script>
    <link href="/frontend/css/base.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/frontend/js/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/frontend/js/popup_layer.js"></script>
    <script type="text/javascript" src="/frontend/js/blk.js"></script>
    </script>
    </head>

    <body style="background:#f9f9f9;">

            <div class="logBox">
            <div class="login">
            <div class="siderNav">
            <ul class="topmenu" id="jq_topmenu">
            <li class="first"><a href="/">首页</a>|<a>关于我们</a></li>

            </ul>
            </div>
            </div>
            </div>
            <div class="main">
            <div class="search-box">
            <div class="c"></div>
            </div>
            <div class="demo">

            <div class="demo-left">
            <div class="demo-left-top">
            <p><Image src="/frontend/images/dingdan_03.jpg"/></p>
            <h3>${user.username}</h3>
            </div>
            <ul>
            <a href="/frontend/person/index"><li class="title2-on">我的资料</li></a>
            </a><li class=""><a href="/frontend/person/order">我的订单</li></a>
            </ul>
            </div>
            <div class="demo-right">
            <h2>个人资料</h2>
            <table width="400" border="0" class="demo-table">
            <tr>
            <td style="text-align:right">用户名</td>
            <td>${user.username}</td>
            </tr>
            <tr>
            <td style="text-align:right">密码</td>
            <td>******</td>
            <td><a class="ele7">[修改]</a></td>
            </tr>
            <tr>
            <td style="text-align:right">手机号</td>
            <td>${user.phone}</td>
            </tr>
            <tr>
            <td style="text-align:right">余额</td>
            <td><fmt:formatNumber value="${money}" maxFractionDigits="1"/></td>
            </tr>
    </table>
    <div class="tsgl-top" id="blk7" style="display:none; margin-top: -67px;">
            <a id="close7" class="closeBtn br ">×</a>

                <div class="tsgl-top-p1"><label><span class="p1-text">手机号</span><input id="phone" name="phone" type="text" placeholder="请输入手机号" style= "width: 278px;"/></label>
                 <a  id ="getcode" class="tijiao br" style="    width: 131px;margin-left: 10px;position: initial;">获取验证码</a></div>
                <div class="tsgl-top-p1"><label><span class="p1-text">验证码</span><input id="valcode" name="valcode" type="text" placeholder="请输入验证码"/></label></div>
                <div class="tsgl-top-p1"><label><span class="p1-text">新密码</span><input  id="nPassword" name="nPassword" type="password" placeholder="请输入新密码" /></label></div>
                <div class="c"></div>
                <a class="tijiao br" id="changePassword">提交</a>
            </div>
            </div>
            <div style="clear:both;"></div>
            </div>

            </div>
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
