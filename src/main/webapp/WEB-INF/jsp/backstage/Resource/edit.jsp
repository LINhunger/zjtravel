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
<form action="/backstage/Resource/${resource.id}/update" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover m10">
        <input type="hidden" name="id" value="${resource.id}"/>
        <input type="hidden" name="parentId" value="${resource.parentId}"/>
        <input type="hidden" name="parentIds" value="${resource.parentIds}"/>
        <tr>
            <td width="10%" class="tableleft">上级</td>
            <td>
                ${parentName}
            </td>
        </tr>
        <tr>
            <td class="tableleft">名称</td>
            <td><input type="text" name="name" value="${resource.name}"/></td>
        </tr>
        <tr>
            <td class="tableleft">类型</td>
            <td>
                <input type="radio" name="type" value="menu" checked/> 菜单
                <input type="radio" name="type" value="button"/> 按钮
            </td>
        </tr>
        <tr>
            <td class="tableleft">URL路径</td>
            <td><input type="text" name="url" value="${resource.url}"/></td>
        </tr>
        <tr>
            <td class="tableleft">权限字符串</td>
            <td><input type="text" name="permission" value="${resource.permission}"/></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="available" value="0" checked/> 启用
                <input type="radio" name="available" value="1"/> 禁用
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
            window.location.href="/backstage/Resource/index";
        });

    });
</script>