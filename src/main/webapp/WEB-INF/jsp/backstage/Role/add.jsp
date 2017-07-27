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
<form action="/backstage/Role/add" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">角色名称</td>
            <td><input type="text" name="role"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">描述</td>
            <td><input type="text" name="description"/></td>
        </tr>

        <tr>
            <td class="tableleft">权限</td>
            <td>
                <ul>
                    <c:forEach items="${resourceList}" var="aGroup">
                        <li>
                            <label class="checkbox inline"> <input type="checkbox" name="resourceIds" value="${aGroup.menu.id}" />${aGroup.menu.name}</label>
                            <ul>
                                <c:forEach items="${aGroup.buttons}" var="button">
                                     <li><label class="checkbox inline"> <input type="checkbox" name="resourceIds" value="${button.id}" />${button.name}</label> </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
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
        $(':checkbox[name="resourceIds"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

        $('#backid').click(function(){
            window.location.href="/backstage/Role/index";
        });
    });
</script>