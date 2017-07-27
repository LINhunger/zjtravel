<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../tag/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv=“X-UA-Compatible” content=“IE=EmulateIE7″>
    <meta http-equiv=“X-UA-Compatible” content=“IE=8″>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>粤西古村旅游网站开发与实现</title>

        <link rel="stylesheet" href="/frontend/css/qzhd-1.css" type="text/css" />

        <script type="text/javascript" src="/frontend/js/jquery1.42.min.js"></script>
        <script type="text/javascript" src="/frontend/js/jquery.SuperSlide.2.1.1.js"></script>
        <script type="text/javascript" src="/frontend/js/public.js"></script>
        <script type="text/javascript" src="/frontend/js/jquery.Spinner.js"></script>
        <script type="text/javascript" src="/frontend/js/popup_layer.js"></script>
        <script type="text/javascript" src="/frontend/js/blk.js"></script>
    </head>

<body>

<div id="web">

    <div class="topall">
        <div class="top-line">
            <div class="top-line-box">
                <c:choose>
                    <c:when test="${not empty username}">
                        <span style="float: right; margin-left: 30px"><a  style="clear:both;" href="/frontend/person/index">[个人中心]</a>欢迎您，<span class="dl-log-user">${username}</span> <a href="/logout" title="退出系统" class="dl-log-quit">[退出]</a></span>
                    </c:when>
                    <c:otherwise>
                        <a href="/login">登录</a>
                        <a class="top-line-a-on" href="/register">注册</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div  style="clear:both;"></div>
        <div class="top">
            <img src="/frontend/images/denglu_02.jpg" />
            <img src="/frontend/images/index_03.jpg" style="float:right;" />
        </div>
    </div>
    <div class="nav">
        <ul>
            <li ><a href="/">首页</a></li>
            <li class="wbg"><a href="/groupTour">跟团游</a></li>
            <li><a href="/ticket">票务</a></li>
            <li><a>更多</a></li>
            <li><a>更多</a></li>
            <li><a>关于我们</a></li>
        </ul>
    </div>
    <div class="main">

        <div class="main-top" >
            <h2>${groupTourVO.groupTourPO.title}</h2>
            <div id="demo1" class="picBtnTop">
                <div class="hd">
                    <ul>
                        <c:forEach items="${groupTourVO.groupTourPO.picture}" var="picture" begin="0" end="3">
                            <li><img src="/backstage/Product/image/${picture}"></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="bd">
                    <ul>
                        <c:forEach items="${groupTourVO.groupTourPO.picture}" var="picture" begin="0" end="3">
                            <li>
                                <div class="pic"><img src="/backstage/Product/image/${picture}"></div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="table-right">
                <c:choose>
                    <c:when test="${not empty groupTourVO.discountPO}">
                        <h1 id="nPrice"><fmt:formatNumber value="${groupTourVO.detailList[0].price * groupTourVO.discountPO.percent}" maxFractionDigits="1"/></h1>
                        <span>原价：<b>￥</b><b style="text-decoration:line-through;" id="oPrice"><fmt:formatNumber value="${groupTourVO.detailList[0].price}" maxFractionDigits="1"/></b></span>
                    </c:when>
                    <c:otherwise>
                        <h1 id="nPrice"><fmt:formatNumber value="${groupTourVO.detailList[0].price}" maxFractionDigits="1"/></h1>
                        <span>原价：<b>￥</b><b style="text-decoration:line-through;" id="oPrice"><fmt:formatNumber value="${groupTourVO.detailList[0].price}" maxFractionDigits="1"/></b></span>
                    </c:otherwise>
                </c:choose>
                <span><img src="/frontend/images/hd-n_06.jpg" />${groupTourVO.groupTourPO.location},<i style="font-style:normal; padding-left:50px;"><img src="/frontend/images/hd-n_03_03.jpg" style="padding:5px 5px 0px 0px;" />跟团游</i></span>
                <div class="table-right-box">
                    <div class="table-right-b-t1">
                        <p>出行日期：</p>
                        <c:forEach items="${groupTourVO.detailList}" var="detail">
                            <span  id="${detail.id}" class="time">
                                <fmt:formatDate value="${detail.departureTime}" pattern="MM月dd日"/>-
                                            <fmt:formatDate value="${detail.endTime}" pattern="MM月dd日"/>
                            </span>
                        </c:forEach>
                        <div style="clear:both;"></div>
                    </div>
                    <div style="clear:both;"></div>
                    <div class="table-right-b-t2">
                        <p>数量：</p>
                        <div class="table-right-b-t2-box">
                            <div class="table-right-b-t2-m">
                                <div id="d" class="Spinner"></div>
                                <div style="clear:both;"></div>
                            </div>
                            <div style="clear:both;"></div>
                        </div>
                    </div>
                    <div style="clear:both;"></div>
                    <span>标签：<b>${groupTourVO.groupTourPO.label}</b></span>
                    <c:if test="${not empty groupTourVO.discountPO}">
                         <span>优惠：<b>${groupTourVO.discountPO.title} </b>&nbsp;&nbsp;折扣：<b>${groupTourVO.discountPO.percent  * 10}折</b></span>
                    </c:if>
                </div>
                <a class="goumai ele7">立刻购买</a>
            </div>
        </div>



        <div class="qzly">

            <div class="qzly-main">
                <div class="content2-main">
                    <div class="hd">
                        <ul>
                            <li>活动介绍</li>
                            <li>旅游路线</li>
                        </ul>
                    </div>
                    <div class="content2-main-r"></div>
                    <div class="bd">
                        <ul>
                            <p style="height: 300px;">${groupTourVO.groupTourPO.introduce}</p>
                        </ul>
                        <ul>
                            <p style="height: 450px;">${groupTourVO.groupTourPO.travel}</p>
                        </ul>
                    </div>
                </div>
                <div style="clear:both;"></div>
            </div>
        </div>



    </div>
    <!--main end-->
</div>

<!--弹出框-->
<div class="xd-blk" id="blk7" style="display:none;">
    <h3>购买信息</h3>
    <div class="content-line xd-blk1" >
        <p>商品信息：第一件跟团游</p>
        <span>时间：<b id="shijian"></b></span>
        <span>数量：<b id="shuliang"></b></span>
        <span>实付款：<b id="fukuang"></b></span>
        <a id="tijiao" style="margin-top: 30px;margin-left: 160px;
        padding: 10px 20px;color: #fff;background: #ffa400;border: 1px solid #ff6a00;font-size: 14px;position: absolute;">提交订单</a>
    </div>
    <h1 id="tips" style="margin-left: 164px;margin-top: 50px;">请选择时间</h1>
    <a class="closebut" id="close7"></a>
</div>

<script type="text/javascript">

    jQuery(".content2-main").slide({trigger:"click"});

</script>
<script type="text/javascript">

    jQuery("#demo1").slide({ mainCell:".bd ul",effect:"top",autoPlay:true,triggerTime:0 });

</script>
<script type="text/javascript">
    $(function(){
        $("#d").Spinner();
    });

    $('.time').click(function(){

        $.ajax({
            url: "/getPrice?id="+this.id,
            type: "GET",
            cache: false,
            dataType: 'json',
            success: function(data) {
                if(data==null){
                    alert("服务器异常!");
                    return ;
                }
                else if (data.oPrice == 0 || data.nPrice == 0){
                    alert("获取数据异常");
                    return ;
                }
                else {
                    var oPrice = data.oPrice.toFixed(1);
                    var nPrice = data.nPrice.toFixed(1);
                    $('#nPrice').text(nPrice);
                    $('#oPrice').text(oPrice);
                    return;
                }
            }
        });

        });

    $('.goumai').click(function(){
        if ($('.table-span').attr("id") == null) {
            $('.xd-blk1').css("display","none");
            $('#tips').css("display", "block");
            return;
        }
        $('.xd-blk1').css("display","block");
        $('#tips').css("display", "none");
        var shijian = $('.table-span').text();
        var shuliang = $('.Amount').val();
        var danjia = $('#nPrice').text().replace(/[^0-9.]/g,"");
        var fukuang = (parseFloat(danjia) * parseFloat(shuliang)).toFixed(1);
        var id = $('.table-span').attr("id");
        $('#shijian').text(shijian);
        $('#shuliang').text(shuliang);
        $('#fukuang').text(fukuang);
    });

    $('#tijiao').click(function(){
        var num = parseInt($('.Amount').val());
        var type = 1;
        var id = parseInt($('.table-span').attr("id"));
        var info = JSON.stringify({"id": id, "num": num, "type": type});
        $.ajax({
            url: "/groupTour/order",
            type: "POST",
            cache: false,
            contentType: "application/json; charset=utf-8",
            data: info,
            dataType: 'json',
            success: function(data) {
                if(data==null){
                    alert("服务器异常!");
                    return ;
                }
                else if (data.state == 1){
                    alert("提交订单成功");
                    window.location.href="/frontend/person/order";
                    return ;
                }
                else {
                    alert(data.stateInfo);
                    return;
                }
            }
        });


    });


</script>
</body></html>
