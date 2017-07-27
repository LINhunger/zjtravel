<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form action="/backstage/User/${user.id}/update" method="post" class="definewidth m20">
    <input type="hidden" name="username" value="${user.username}"/>
    <input type="hidden" name="phone" value="${user.phone}"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">用户名</td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td class="tableleft">电话</td>
            <td>${user.phone}</td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="locked" value="0" checked/> 启用
                <input type="radio" name="locked" value="1"/> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft">角色</td>
            <td>
                <c:forEach items="${roleList}" var="role">
                    <label class="checkbox inline"> <input type="checkbox" name="roleIds" value="${role.id}"
                            <c:forEach items="${user.roleIds}" var="roleId">
                                <c:if test="${roleId ==role.id }">
                                    checked
                                </c:if>
                            </c:forEach>
                    />${role.role}</label>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {
        $('#backid').click(function(){
            window.location.href="/backstage/User/index";
        });

    });
</script>

