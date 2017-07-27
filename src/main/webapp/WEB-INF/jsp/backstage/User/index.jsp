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
<div style="margin-left: 10px ;margin-top: 20px;">
    <button type="button" class="btn btn-success" id="addnew">新增用户</button>
</div>
<hr/>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>用户名</th>
        <th>用户电话</th>
        <th>角色</th>
        <th>状态</th>
        <th>最近修改时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.userPO.username}</td>
            <td>${user.userPO.phone}</td>
            <td>${user.userRoles}</td>
            <td><c:choose><c:when test="${user.userPO.locked == true}">禁用</c:when><c:otherwise>启用</c:otherwise></c:choose></td>
            <td><fmt:formatDate value="${user.userPO.gmtModified}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <td>
                <shiro:hasPermission name="user:update">
                    <a href="/backstage/User/${user.userPO.id}/update">编辑</a>
                </shiro:hasPermission>

                <shiro:hasPermission name="user:delete">
                    <a class="deleteBtn" href="#" data-id="${user.userPO.id}">删除</a>
                </shiro:hasPermission>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<script>
    $(function () {

        $('#addnew').click(function(){

            window.location.href="/backstage/User/add";
        });


    });

    $(".deleteBtn").click(function() {

        if (confirm("确定要删除吗？")) {

            var url = "/backstage/User/" + $(this).data("id") + "/delete";

            window.location.href = url;

        }
    });
</script>
