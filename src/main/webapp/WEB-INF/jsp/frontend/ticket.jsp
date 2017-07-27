<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../tag/tag.jsp"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv=“X-UA-Compatible” content=“IE=EmulateIE7″>
    <meta http-equiv=“X-UA-Compatible” content=“IE=8″>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <title>粤西古村旅游网站开发与实现</title>

        <link rel="stylesheet" href="/frontend/css/pw.css" type="text/css" />
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
                        <span style="float: right; margin-left: 30px"><a  style="clear:both;" href="/frontend/person/index">[个人中心]</a>欢迎您，<span class="dl-log-user">${username}</span><a href="/logout" title="退出系统" class="dl-log-quit">[退出]</a></span>
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
            <li><a href="/groupTour">跟团游</a></li>
            <li class="wbg"><a href="/ticket">票务</a></li>
            <li><a>更多</a></li>
            <li><a>更多</a></li>
            <li><a>关于我们</a></li>
        </ul>
    </div>

    <div class="main">
        <div style=" clear:both;"></div>
        <div class="qzhd">
            <div class="qzhd-back"><a href="/">粤西游</a>&gt;<a href="/ticket">票务</a></div>
            <div class="qzhd-all">


                <div class="qzhd-all-d" id="location"><p>区域：</p><a class="all-p-a" name="">全部</a><a>湛江</a><a>吴川</a><a>雷州</a><a>廉江</a><a>遂溪</a><a>徐闻</a></div>
                <div  class="qzhd-all-d" id="time"><p>时间：</p><a class="all-p-a">全部</a><a>最近一周</a><a>往期活动</a></div>
                <div class="qzhd-all-d" ><p>价格：</p>
                    <input type="text" id="minPrice" name="minPrice"  style="width: 50px;"/>￥-
                    <input type="text" id="maxPrice" name="maxPrice" style="width: 50px;"/>￥</div>
                <div class="qzhd-all-d" >
                    <p>标题：</p>
                    <input type="text" class="search-text"  id="title" autocomplete="off" placeholder="请输入搜索关键字" style="margin-top: 14px;"/>
                    <input type="button" class="search-button" value="" style="margin-top: 13px;"/>
                    <span style="float: right">共有<i style="color: red" id="num">${fn:length(ticketVOList)}</i>条产品信息&nbsp;&nbsp;</span>
                </div>
            </div>


            <ul class="pw-main">

                <c:if test="${not empty ticketVOList}">
                <c:forEach items="${ticketVOList}" var="ticketVO">
                <c:if test="${not empty ticketVO.detailList}">
                <li style="margin-left: 2.5px;margin-right: 2.5px;">
                    <a href="/ticket/${ticketVO.ticketPO.id}/detail"><img src="/backstage/Product/image/${ticketVO.ticketPO.picture[0]}" style="width: 322px;height: 247px;"/></a>
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


</body>
</html>



<script>
    $('.search-button, .qzhd-all-d').click(function(){

        var title = $("#title").val().trim();
        var location = $("#location").find(".all-p-a").text();
        var time = $("#time").find(".all-p-a").text();
        var minPrice = $("#minPrice").val().trim();
        var maxPrice = $("#maxPrice").val().trim();
        var info = {
            title : title,
            location : location,
            time : time,
            minPrice : minPrice,
            maxPrice : maxPrice
        }
        if(title=="ABCDEFG"){
            alert("请填写搜索信息!");
        }else{

            $.ajax({
                url: "/ticket",
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
                    else if (data.data.length == 0){
                        alert("尚无该类商品");
                        return ;
                    }
                    else {
                        $(".pw-main").find("li").remove();
                        var ticketVOlist = data.data;
                        $("#num").text(ticketVOlist.length);
                        for(var i=0; i<ticketVOlist.length; i++){
                            var ticket = ticketVOlist[i].ticketPO;
                            var groupDetail = ticketVOlist[i].detailList[0];
                            var discount = ticketVOlist[i].discountPO;
                            var id = ticket.id;
                            var title = ticket.title;
                            var picture = ticket.picture[0];
                            var oPrice = groupDetail.price.toFixed(1);
                            var nPrice = oPrice;
                            if (discount != null) {
                                nPrice = (oPrice * discount.percent).toFixed(1);
                            }
                            searchTicket(id, title, picture, oPrice, nPrice);
                        }
                        return;
                    }
                }
            });



        }
    });

    function searchTicket(id, title, picture, oPrice, nPrice) {
        var $li = $(
                '<li style="margin-left: 2.5px;margin-right: 2.5px;">'+
                '<a href="/ticket/'+id+'/detail"><img src="/backstage/Product/image/'+picture+'" width="322px"  height="247px"/></a>'+
                '<div class="pw-li-t">'+
                '<p>'+title+'</p>'+
                '<a><b>￥</b>'+nPrice+' </a>'+
                '<span>门店价<b>￥'+oPrice+'</b></span>'+
                '<a href="/ticket/'+id+'/detail"><div class="pw-li-t-d">立即购买&gt;&gt;</div></a>'+
                '</div>'+
                '</li>'
        );
        var $ul = $(".pw-main");
        $li.appendTo($ul);
    }


</script>



