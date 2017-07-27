<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../tag/tag.jsp"%>
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
    <button type="button" class="btn btn-success" id="addnew">新增产品</button>
</div>
</shiro:hasPermission>
<hr/>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>标题</th>
        <th>标签</th>
        <th>地点</th>
        <th>状态</th>
        <th>最近修改时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${tickets}" var="ticket">
        <tr>
            <td>${ticket.title}</td>
            <td>${ticket.label}</td>
            <td>${ticket.location}</td>
            <td><c:choose><c:when test="${ticket.available == false}">已下架</c:when><c:otherwise>已上架</c:otherwise></c:choose></td>
            <td><fmt:formatDate value="${ticket.gmtModified}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <td>
                    <a href="/backstage/Product/ticket/${ticket.id}/detail">子产品</a>
                    <shiro:hasPermission name="product:update">
                        <a href="/backstage/Product/ticket/${ticket.id}/update">编辑</a>
                    </shiro:hasPermission>
                    <%--<a class="deleteBtn" href="#" data-id="${ticket.id}">删除</a>这个按钮会导致一些设计上的bug--%>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<script>
    $(function () {
        $('#addnew').click(function(){
            window.location.href="/backstage/Product/ticket/add";
        });
    });

    $(".deleteBtn").click(function() {
        if(confirm("确定要删除吗？"))
        {
            var url = "/backstage/Product/ticket/"+$(this).data("id")+"/delete";
            window.location.href=url;
        }
    });
</script>
