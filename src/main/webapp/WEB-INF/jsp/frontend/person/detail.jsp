<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../tag/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv=“X-UA-Compatible” content=“IE=EmulateIE7″>
<meta http-equiv=“X-UA-Compatible” content=“IE=8″>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


    <title>粤西古村旅游网站开发与实现</title>

    <meta name="description" content="" />

    <meta name="keywords" />

    <link href="/frontend/css/ddxq.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/frontend/js/public.js"></script>
    <link href="/frontend/css/base.css" rel="stylesheet" type="text/css" />

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
    <div class="demo">

        <div class="demo-left">
            <div class="demo-left-top">
                <p><img src="/frontend/images/dingdan_03.jpg" /></p>
                <h3>${user.username}</h3>
            </div>
            <ul>
                <li class=""><a href="/frontend/person/index">我的资料</a></li>
                <li class="title2-on"><a href="/frontend/person/order">我的订单</a></li>
            </ul>
        </div>
        <div class="demo-right">
            <h2>订单详情</h2>
            <div class="demo-right-tb">
                <div class="demo-right-th">
                    <p>${orderVO.goods.title}<span><img src="/frontend/images/sc_03.jpg" /></span></p>
                </div>
                <div class="demo-right-tr">
                    <p class="demo-right-s1">目的地：${orderVO.goods.location}</p>
                    <p class="demo-right-s2">详细信息:${orderVO.orderPO.goodsType== 1? "跟团游":"票务"}</p>
                    <div style="clear:both;"></div>
                </div>
                <div style="clear:both;"></div>
                <div class="demo-right-tr">
                    <p class="demo-right-s1">订单时间：<fmt:formatDate value="${orderVO.orderPO.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                    <p class="demo-right-s2">出行时间：<fmt:formatDate value="${orderVO.goodsDetail.departureTime}" pattern="yyyy-MM-dd"/></p>
                    <div style="clear:both;"></div>
                </div>
                <div style="clear:both;"></div>
                <div class="demo-right-tr">
                    <p class="demo-right-s1">订购数量：${orderVO.orderPO.number}</p>
                    <c:if test="${orderVO.orderPO.goodsType== 1}">
                        <p class="demo-right-s2">结束时间：<fmt:formatDate value="${orderVO.goodsDetail.endTime}" pattern="yyyy-MM-dd"/></p>
                    </c:if>
                    <div style="clear:both;"></div>
                </div>
                <div style="clear:both;"></div>
                <div class="demo-right-tr">
                    <p class="demo-right-s1 demo-right-last1">
                        <span>实际付款：￥<b><fmt:formatNumber value="${orderVO.orderPO.payment}" maxFractionDigits="1"/></b></span>
                    </p>
                    <p class="demo-right-s2 demo-right-last2"></p>
                    <div style="clear:both;"></div>
                </div>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>

</div>

</div>



</body>

</html>


