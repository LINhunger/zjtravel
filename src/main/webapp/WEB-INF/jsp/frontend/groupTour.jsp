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

        <link rel="stylesheet" href="/frontend/css/qzhd.css" type="text/css" />
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
            <li class="wbg"><a href="/groupTour">跟团游</a></li>
            <li><a href="/ticket">票务</a></li>
            <li><a>更多</a></li>
            <li><a>更多</a></li>
            <li><a>关于我们</a></li>
        </ul>
    </div>

    <div class="main">
        <div style=" clear:both;"></div>
        <div class="qzhd">
            <div class="qzhd-back"><a href="/">粤西游</a>&gt;<a href="/groupTour">跟团游</a></div>
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
                    <span style="float: right">共有<i style="color: red" id="num">${fn:length(groupTourVOList)}</i>条产品信息&nbsp;&nbsp;</span>
                </div>
            </div>


            <ul class="qzhd-main" >


                <c:if test="${not empty groupTourVOList}">
                    <c:forEach items="${groupTourVOList}" var="groupTourVO">
                        <c:if test="${not empty groupTourVO.detailList}">
                            <li style="margin-left: 12px;margin-right: 12px;">
                                <a href="/groupTour/${groupTourVO.groupTourPO.id}/detail"><img src="/backstage/Product/image/${groupTourVO.groupTourPO.picture[0]}" width="305"  height="242"/></a>
                                <div class="qzhd-li-t">
                                    <p>${groupTourVO.groupTourPO.title}</p>
                                    <span>
                                        <i><img src="/frontend/images/time_19.jpg" /></i>
                                        <fmt:formatDate value="${groupTourVO.detailList[0].departureTime}" pattern="yyyy年MM月dd日"/>-
                                        <fmt:formatDate value="${groupTourVO.detailList[0].endTime}" pattern="yyyy年MM月dd日"/>
                                    </span>
                                    <c:choose>
                                        <c:when test="${not empty groupTourVO.groupTourPO.discountIds}">
                                            <c:forEach items="${discounts}" var="discount">
                                                <c:if test="${discount.id == groupTourVO.groupTourPO.discountIds[0]}">
                                                    <a><b>￥</b><fmt:formatNumber value="${groupTourVO.detailList[0].price * discount.percent}" maxFractionDigits="1"/> </a>
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
            </ul>
        </div>
    </div>


</body>
</html>



<script>
    $('.search-button,.qzhd-all-d').click(function(){

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
                url: "/groupTour",
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
                        $(".qzhd-main").find("li").remove();
                        var groupTourVOlist = data.data;
                        $("#num").text(groupTourVOlist.length);
                        for(var i=0; i<groupTourVOlist.length; i++){
                            var groupTour = groupTourVOlist[i].groupTourPO;
                            var groupDetail = groupTourVOlist[i].detailList[0];
                            var discount = groupTourVOlist[i].discountPO;
                            var id = groupTour.id;
                            var title = groupTour.title;
                            var picture = groupTour.picture[0];
                            var departure = new Date(groupDetail.departureTime);
                            var end = new Date(groupDetail.endTime);
                            var departureTime = departure.getFullYear()+"年"+(departure.getMonth()+1)+"月"+departure.getDate()+"日";
                            var endTime = end.getFullYear()+"年"+(end.getMonth()+1)+"月"+end.getDate()+"日";
                            var price = groupDetail.price.toFixed(1);
                            if (discount != null) {
                                price = (price * discount.percent).toFixed(1);
                            }
                            searchGroupTour(id, title, picture, departureTime, endTime, price, discount);
                        }
                        return;
                    }
                }
            });



        }
    });

    function searchGroupTour(id, title, picture, departureTime, endTime, price, discount) {
        var $li = $(
                '<li style="margin-left: 12px;margin-right: 12px;">'+
                '<a href="/groupTour/'+id+'/detail"><img src="/backstage/Product/image/'+picture+'" width="305"  height="242"/></a>'+
                '<div class="qzhd-li-t">'+
                '<p>'+title+'</p>'+
                '<span>'+
                '<i><img src="/frontend/images/time_19.jpg" /></i>'+
                departureTime+'-'+endTime+
                '</span>'+
                '<a><b>￥</b>'+price+' </a>'+
                '</div>'+
                '</li>'
        );
        if (discount != null) {
            var $div =$('<div class="jinxin">优惠中</div>');
            $div.appendTo($li);
        }
        var $ul = $(".qzhd-main");
        $li.appendTo($ul);
    }

    
</script>



