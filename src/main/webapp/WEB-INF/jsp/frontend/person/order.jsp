<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../tag/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv=“X-UA-Compatible” content=“IE=EmulateIE7″>
<meta http-equiv=“X-UA-Compatible” content=“IE=8″>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


    <title>我的订单</title>

    <link href="/frontend/css/wddd.css" rel="stylesheet" type="text/css" />

    <link href="/frontend/css/base.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="/frontend/js/jquery1.42.min.js"></script>
</head>
<style type="text/css">


    .demo-right{width:80%; float:right; background:#fff;}
    .tb-title{ height:50px;}
    .tb-title1{ margin:0 auto;border-collapse:collapse; background:#FFF;}
    .tb-title1 td{border:1px solid #ccc; }
    .tb-title2{  line-height:40px; text-indent:20px; border-top:1px solid #ccc; border-left:1px solid #ccc;border-right:1px solid #ccc;width:100%; background:#f4f4f4; box-sizing:border-box;}
    .tb-title2 span{ float:right; position:absolute; right:10px; position:relative;}
    .demo-right table{ float:right;  }
    .demo table tr td{text-align:center; padding:20px 0;}
    .tb-min{width:780px; margin:0 auto; margin-top:10px;}
</style>
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
    <div class="search-box"></div>
    <div class="demo">

        <div class="demo-left">
            <div class="demo-left-top">
                <p><img src="/frontend/images/dingdan_03.jpg" /></p>
                <h3>${user.username}</h3>
            </div>
            <ul>
                <a href="/frontend/person/index"><li class="title2">我的资料</li></a>
                <a href="/frontend/person/order"><li class="title2" style="background: #fff;">我的订单</li></a>
            </ul>
        </div>
        <%--右边--%>
        <div class="demo-right">
            <div style="width:780px; margin:0 auto; padding-top:10px;">
                <table width="100%" border="0"  class="tb-title" style=" border-collapse:collapse;" >
                    <tr style=" background:#fef7ea; border:1px solid #fed89a;">
                        <th scope="col" width="50%">主题</th>
                        <th scope="col" width="15%">数量</th>
                        <th scope="col" width="20%" >实付款（元）</th>
                        <th scope="col" width="15%">交易状态</th>
                    </tr>
                </table>
            </div>
            <div style="clear:both;"></div>


<c:forEach items="${orders}" var="order">
            <div  class="tb-min" >
                <p class="tb-title2"><fmt:formatDate value="${order.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <span>订单号：${order.id}</span></p>

                <table width="100%" border="1"class="tb-title1" >

                    <tr >
                        <td class="ddc" width="50%"><a>${order.goodsName}</a></td>
                        <td class="ddc" width="15%" >${order.number}</td>
                        <td class="ddc" width="20%" ><fmt:formatNumber value="${order.payment}" maxFractionDigits="1"/></td>
                        <td class="ddc" width="15%">
                            <p>${order.state.info}</p>
                            <p><a href="/frontend/person/order/${order.id}/detail">查看详情</a></p>
                            <p>
                                <c:if test="${order.state eq 'paid'}"><a class="chargeBackBtn" href="#" data-id="${order.id}">申请退款</a></c:if>
                                <c:if test="${order.state eq 'charge_back'}"><a class="cancelBtn" href="#" data-id="${order.id}">取消</a></c:if>
                            </p>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="clear:both;"></div>
</c:forEach>




        </div>
        <div style="clear:both;"></div>
    </div>

</div>

</div>

</body>
<script>
    $(function () {
        $(".chargeBackBtn").click(function() {

            if (confirm("确定要申请退单吗？")) {
                var id = $(this).attr("data-id");
                var url = "/frontend/person/" + id + "/chargeBack";

                window.location.href = url;

            }
        });
        $(".cancelBtn").click(function() {

            if (confirm("确定要取消吗？")) {

                var id = $(this).attr("data-id");
                var url = "/frontend/person/" + id + "/cancel";

                window.location.href = url;

            }
        });
    });
</script>
</html>


