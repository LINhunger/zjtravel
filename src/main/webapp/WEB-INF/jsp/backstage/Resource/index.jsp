<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../tag/tag.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="/backstage/Css/bootstrap.css" />
    <link rel="stylesheet" href="/backstage/jquery-treetable/stylesheets/jquery.treetable.css">
    <link rel="stylesheet" href="/backstage/jquery-treetable/stylesheets/jquery.treetable.theme.default.css">
    <style>
        #table th, #table td {
            font-size: 14px;
            padding : 8px;
        }
    </style>
</head>
<body>

<table id="table">
    <thead>
    <tr>
        <th>名称</th>
        <th>类型</th>
        <th>URL路径</th>
        <th>权限字符串</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resourceList}" var="resource">
        <tr data-tt-id='${resource.id}' <c:if test="${not resource.rootNode}">data-tt-parent-id='${resource.parentId}'</c:if>>
            <td>${resource.name}</td>
            <td>${resource.type.info}</td>
            <td>${resource.url}</td>
            <td>${resource.permission}</td>
            <td>
                <shiro:hasPermission name="resource:create">
                    <c:if test="${resource.type ne 'button'}">
                        <a href="/backstage/Resource/${resource.id}/appendChild">添加子节点</a>
                    </c:if>
                </shiro:hasPermission>

                    <shiro:hasPermission name="resource:update">
                        <a href="/backstage/Resource/${resource.id}/update">修改</a>
                    </shiro:hasPermission>
                <c:if test="${not resource.rootNode}">

                    <shiro:hasPermission name="resource:delete">
                        <a class="deleteBtn" href="#" data-id="${resource.id}">删除</a>
                    </shiro:hasPermission>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="/backstage/Js/jquery.js"></script>
<script src="/backstage/jquery-treetable/javascripts/src/jquery.treetable.js"></script>
<script>
    $(function() {
        $("#table").treetable({ expandable: true }).treetable("expandNode", 1);
        $(".deleteBtn").click(function() {
            if(confirm("确认删除吗?")) {
                location.href = "/backstage/Resource/"+$(this).data("id")+"/delete";
            }
        });
    });
</script>
</body>
</html>