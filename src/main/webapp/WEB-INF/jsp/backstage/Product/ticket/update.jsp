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
    <link href="/backstage/Css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
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
<form action="/backstage/Product/ticket/update" method="post" class="definewidth m20">
    <input type="hidden" name="id" value="${ticket.id}"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">标题</td>
            <td><input type="text" name="title" value="${ticket.title}"/></td>
        </tr>
        <tr>
            <td class="tableleft">标签</td>
            <td><input type="text" name="label" value="<c:forEach items="${ticket.label}" var="lab">${lab},</c:forEach>"/></td>
        </tr>
        <tr>
            <td class="tableleft">地点</td>
            <td><input type="text" name="location" value="${ticket.location}"/></td>
        </tr>
        <tr>
            <td class="tableleft">介绍</td>
            <td><textarea type="text" name="introduce">${ticket.introduce}</textarea></td>
        </tr>
        <tr>
            <td class="tableleft">行程</td>
            <td><textarea type="text" name="travel">${ticket.travel}</textarea></td>
        </tr>
        <tr>
            <td class="tableleft">是否上架</td>
            <td>
                <input type="radio" name="available" value="1" checked/> 是
                <input type="radio" name="available" value="0"/> 否
            </td>
        </tr>
        <tr>
            <td class="tableleft">优惠</td>
            <td>
                <c:forEach items="${discounts}" var="discount">
                    <label class="checkbox inline"> <input type="checkbox" name="discountIds" value="${discount.id}" />${discount.percent * 10}折</label>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td class="tableleft">图片</td>
            <td>
                <c:if test="${not empty pictures}">
                    <c:forEach var="picture" items="${pictures}">
                        <div class="file-preview-frame file-preview-success" data-fileindex="0" data-template="image">
                            <div class="kv-file-content">
                                <img src="/backstage/Product/image/cut_image/${picture}" class="kv-preview-data file-preview-image" title="${picture}" alt="${picture}" style="width:260px;height:160px;">
                            </div>
                            <div class="file-thumbnail-footer">
                                <div class="file-actions">
                                    <div class="file-footer-buttons">
                                        <label class="checkbox inline"> <input type="checkbox" name="picture" value="${picture}"
                                                <c:forEach items="${ticket.picture}" var="pic">
                                                    <c:if test="${pic ==picture }">
                                                        checked
                                                    </c:if>
                                                </c:forEach>
                                        /></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
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
            window.location.href="/backstage/Product/ticket/index";
        });

    });
</script>

