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
    <button type="button" class="btn btn-success" id="addnew">新增角色</button>
</div>
<hr/>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
        <th>角色名称</th>
        <th>角色描述</th>
        <th>最后修改时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${roleList}" var="role">
        <tr>
            <td>${role.role}</td>
            <td>${role.description}</td>
            <td><fmt:formatDate value="${role.gmtModified}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
            <td>
                <shiro:hasPermission name="role:update">
                    <a href="/backstage/Role/${role.id}/update">编辑</a>
                </shiro:hasPermission>

                <shiro:hasPermission name="role:delete">
                    <a class="deleteBtn" href="#" data-id="${role.id}">删除</a>
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

            window.location.href="/backstage/Role/add";
        });


    });

    $(".deleteBtn").click(function() {

        if(confirm("确定要删除吗？"))
        {

            var url = "/backstage/Role/"+$(this).data("id")+"/delete";

            window.location.href=url;

        }
    });
</script>
