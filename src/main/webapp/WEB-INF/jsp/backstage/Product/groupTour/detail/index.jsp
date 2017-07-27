<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../../tag/tag.jsp"%>
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
<shiro:hasPermission name="product:create">
<div style="margin-left: 10px ;margin-top: 20px;">
    <button type="button" class="btn btn-success" id="addnew" data-id="${groupTourId}">添加子产品</button>
</div>
</shiro:hasPermission>
<hr/>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>开始日期</th>
        <th>结束日期</th>
        <th>价格</th>
        <th>库存</th>
        <th>状态</th>
        <th>最近修改时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:if test="${not empty groupTourDetails}">
    <c:forEach items="${groupTourDetails}" var="groupTourDetail">
        <tr>
            <td><fmt:formatDate value="${groupTourDetail.departureTime}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${groupTourDetail.endTime}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatNumber value="${groupTourDetail.price}" maxFractionDigits="1"/></td>
            <td>${groupTourDetail.stock}</td>
            <td><c:choose><c:when test="${groupTourDetail.available == false}">已下架</c:when><c:otherwise>已上架</c:otherwise></c:choose></td>
            <td><fmt:formatDate value="${groupTourDetail.gmtModified}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <td>
                <shiro:hasPermission name="product:update">
                <c:choose>
                    <c:when test="${groupTourDetail.available == true}">
                        <a href="/backstage/Product/groupTour/${groupTourId}/detail/${groupTourDetail.id}/update">下架</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/backstage/Product/groupTour/${groupTourId}/detail/${groupTourDetail.id}/update">上架</a>
                    </c:otherwise>
                </c:choose>
                </shiro:hasPermission>
                <%--<a class="deleteBtn" href="#" data-id="${groupTourDetail.id}" data-groupTourId = "${groupTourId}">删除</a>这个按钮会导致一些设计上的bug--%>
            </td>
        </tr>
    </c:forEach>
    </c:if>
</table>
</body>
</html>
<script>
    $(function () {
        $('#addnew').click(function(){
            window.location.href="/backstage/Product/groupTour/"+$(this).data("id")+"/detail/add";
        });
    });

    $(".deleteBtn").click(function() {
        if(confirm("确定要删除吗？"))
        {
            var groupTourId = $(this).attr("data-groupTourId");
            var url = "/backstage/Product/groupTour/"+groupTourId+"/detail/"+$(this).data("id")+"/delete";
            window.location.href=url;
        }
    });
</script>

