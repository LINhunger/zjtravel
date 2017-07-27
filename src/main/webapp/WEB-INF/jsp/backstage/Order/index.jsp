<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../tag/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/backstage/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/backstage/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/backstage/Css/style.css" />
    <script type="text/javascript" src="/backstage/Js/jquery.js"></script>
    <script type="text/javascript" src="/backstage/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/backstage/Js/ckform.js"></script>
    <script type="text/javascript" src="/backstage/Js/common.js"></script>



    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>

<hr/>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>订单号</th>
        <th>用户名</th>
        <th>产品信息</th>
        <th>数量</th>
        <th>总额</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.username}</td>
            <td>${order.goodsName}</td>
            <td>${order.number}</td>
            <td><fmt:formatNumber value="${order.payment}" maxFractionDigits="1"/></td>
            <td>${order.state.info}</td>
            <td><fmt:formatDate value="${order.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <td>
                <shiro:hasPermission name="order:update">
                <c:if test="${order.state eq 'charge_back'}">
                    <a class="approveBtn" href="#" data-id="${order.id}">同意</a>
                </c:if>
                </shiro:hasPermission>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<script>

    $(".approveBtn").click(function() {

        if(confirm("确定同意退单吗？"))
        {

            var url = "/backstage/Order/"+$(this).data("id")+"/approve";

            window.location.href=url;

        }
    });
</script>
