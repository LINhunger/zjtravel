<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../tag/tag.jsp" %>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv=“X-UA-Compatible” content=“IE=EmulateIE7″>
    <meta http-equiv=“X-UA-Compatible” content=“IE=8″>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

        <title>粤西古村旅游网站开发与实现</title>
        <link rel="stylesheet" href="/frontend/css/style.css" type="text/css"/>
        <script type="text/javascript" src="/frontend/js/common.min.js"></script>
        <script type="text/javascript" src="/frontend/js/jquery1.42.min.js"></script>
        <script type="text/javascript" src="/frontend/js/jquery.SuperSlide.2.1.1.js"></script>
        <script type="text/javascript" src="/frontend/js/public.js"></script>
    </head>

<body>

<div id="web">

    <div class="topall">
        <div class="top-line">
            <div class="top-line-box">
                <c:choose>
                    <c:when test="${not empty username}">
                    <shiro:hasPermission name="page:upload">
                    <span style="float: right; margin-left: 30px"><a style="clear:both;" href="/backstage/index">[管理中心]</a>
                    </shiro:hasPermission>
                        <span style="float: right; margin-left: 30px"><a style="clear:both;"
                                                                         href="/frontend/person/index">[个人中心]</a>欢迎您，<span
                                class="dl-log-user">${username}</span><a href="/logout" title="退出系统"
                                                                         class="dl-log-quit">[退出]</a></span>
                    </c:when>
                    <c:otherwise>
                        <a href="/login">登录</a>
                        <a class="top-line-a-on" href="/register">注册</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div style="clear:both;"></div>
        <div class="top">
            <img src="/frontend/images/denglu_02.jpg"/>
            <img src="/frontend/images/index_03.jpg" style="float:right;"/>
        </div>
    </div>

    <div class="banner">
        <div class="bd">
            <ul class="fixed">
                <c:forEach items="${pictures}" var="picture" begin="0" end="3">
                    <li style=" background:url(/backstage/Page/image/cut_image/${picture}) 50% 0 no-repeat"><a href="cn/#"></a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="hd">
            <ul class="fixed">
                <li class="on"></li>
                <li class=""></li>
                <li class=""></li>
            </ul>
        </div>
    </div>
    <script type="text/javascript">
        jQuery(".banner").slide({
            titCell: ".hd ul",
            mainCell: ".bd ul",
            effect: "left",
            autoPage: "<li></li>",
            autoPlay: true,
            effect: "fold"
        })
    </script>
    <!--banner end-->


    <div class="main">
        <div class="container-tit">
            <div class="container-tit-p1"><img src="/frontend/images/index_05.jpg"/>
                <p><a href="/groupTour">跟团游</a></p></div>
            <div class="container-tit-p2"><img src="/frontend/images/index_05.jpg" style="padding-left:100px;"/>
                <p><a href="/ticket">景点门票</a></p></div>
            <div class="container-tit-p3"><img src="/frontend/images/index_05.jpg"/>
                <p><a href="#">更多</a></p></div>
        </div>
        <div style=" clear:both;"></div>
        <div class="qzhd">
            <div class="title">
                <p><i>G</i><b>跟团游</b><span>group_tour</span></p>
                <a href="/groupTour"><span class="more">more</span></a>
            </div>


            <ul class="qzhd-main">


                <c:if test="${not empty groupTourVOList}">
                <c:forEach items="${groupTourVOList}" var="groupTourVO">
                <c:if test="${not empty groupTourVO.detailList}">
                <li style="margin-left: 12px;margin-right: 12px;">
                    <a href="/groupTour/${groupTourVO.groupTourPO.id}/detail"><img
                            src="/backstage/Product/image/${groupTourVO.groupTourPO.picture[0]}" width="305"
                            height="242"/></a>
                    <div class="qzhd-li-t">
                        <p>${groupTourVO.groupTourPO.title}</p>
                        <span>
                                        <i><img src="/frontend/images/time_19.jpg"/></i>
                                        <fmt:formatDate value="${groupTourVO.detailList[0].departureTime}"
                                                        pattern="yyyy年MM月dd日"/>-
                                        <fmt:formatDate value="${groupTourVO.detailList[0].endTime}"
                                                        pattern="yyyy年MM月dd日"/>
                                    </span>
                        <c:choose>
                        <c:when test="${not empty groupTourVO.groupTourPO.discountIds}">
                        <c:forEach items="${discounts}" var="discount">
                            <c:if test="${discount.id == groupTourVO.groupTourPO.discountIds[0]}">
                                <a><b>￥</b><fmt:formatNumber
                                        value="${groupTourVO.detailList[0].price * discount.percent}"
                                        maxFractionDigits="1"/> </a>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="jinxin">优惠中</div>
                    </c:when>
                    <c:otherwise>
                    <a><b>￥</b><fmt:formatNumber value="${groupTourVO.detailList[0].price}" maxFractionDigits="1"/> </a>
        </div>
        </c:otherwise>
        </c:choose>
        </li>
        </c:if>
        </c:forEach>
        </c:if>


        <div style="clear:both;"></div>
        </ul>
    </div>


    <div class="pw">
        <div class="title">
            <p><i>T</i><b>票务</b><span>ticket</span></p>
            <a href="/ticket"><span class="more">more</span></a>
        </div>
        <ul class="pw-main">

            <c:if test="${not empty ticketVOList}">
            <c:forEach items="${ticketVOList}" var="ticketVO">
            <c:if test="${not empty ticketVO.detailList}">
            <li style="margin-left: 2.5px;margin-right: 2.5px;">
                <a href="/ticket/${ticketVO.ticketPO.id}/detail"><img src="/backstage/Product/image/${ticketVO.ticketPO.picture[0]}" width="326" height="247"/></a>
                <div class="pw-li-t">
                    <p>${ticketVO.ticketPO.title}</p>
                    <c:choose>
                    <c:when test="${not empty ticketVO.ticketPO.discountIds}">
                    <c:forEach items="${discounts}" var="discount">
                        <c:if test="${discount.id == ticketVO.ticketPO.discountIds[0]}">
                            <a><b>￥</b><fmt:formatNumber value="${ticketVO.detailList[0].price * discount.percent}" maxFractionDigits="1"/> </a>
                            <span>门店价<b><fmt:formatNumber value="${ticketVO.detailList[0].price}" maxFractionDigits="1"/> </b></span>
                            <a href="/ticket/${ticketVO.ticketPO.id}/detail"><div class="pw-li-t-d">立即购买&gt;&gt;</div></a>
                        </c:if>
                    </c:forEach>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <a><b>￥</b><fmt:formatNumber value="${ticketVO.detailList[0].price}" maxFractionDigits="1"/> </a>
                        <span>门店价<b><fmt:formatNumber value="${ticketVO.detailList[0].price}" maxFractionDigits="1"/> </b></span>
                        <a href="/ticket/${ticketVO.ticketPO.id}/detail"><div class="pw-li-t-d">立即购买&gt;&gt;</div></a>
                    </div>
                    </c:otherwise>
                    </c:choose>
            </li>
            </c:if>
            </c:forEach>
            </c:if>



    <div style="clear:both;"></div>
    </ul>
</div>

</div>
<!--main end-->

<div style="height: 69px;"></div>


</div>
<script id="jsID" type="text/javascript">
    var ary = location.href.split("\\\&");
    jQuery(".slideBox").slide({
        mainCell: ".bd ul",
        effect: ary[1],
        autoPlay: ary[2],
        trigger: ary[3],
        easing: ary[4],
        delayTime: ary[5],
        mouseOverStop: ary[6],
        pnLoop: ary[7]
    });
</script>


</body>
</html>
