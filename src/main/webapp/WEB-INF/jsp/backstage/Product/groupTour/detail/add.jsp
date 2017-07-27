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
<form action="/backstage/Product/groupTour/${groupTourId}/detail/add" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>

            <td width="10%" class="tableleft">出发时间</td>
            <td><input size="16" type="date" name="dTime" ></td>
        </tr>
        <tr>
            <td class="tableleft">结束时间</td>
            <td><input size="16" type="date" name="eTime"></td>
        </tr>
        <tr>
            <td class="tableleft">价格</td>
            <td><input type="text" name="price"/></td>
        </tr>
        <tr>
            <td class="tableleft">库存</td>
            <td><input type="text" name="stock"/></td>
        </tr>
        <tr>
            <td class="tableleft">是否上架</td>
            <td>
                <input type="radio" name="available" value="1" /> 是
                <input type="radio" name="available" value="0" checked/> 否
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid" data-groupTourId="${groupTourId}">返回列表</button>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
<script>
    $(function () {
        $('#backid').click(function(){
            var groupTourId = $(this).attr("data-groupTourId");
            window.location.href="/backstage/Product/groupTour/"+groupTourId+"/detail";
        });
    });
</script>

