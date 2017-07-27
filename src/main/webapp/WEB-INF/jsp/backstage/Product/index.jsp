<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../tag/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>图片预览</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/backstage/Css/default.css">
    <link href="/backstage/Css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="/backstage/Js/jquery-1.11.0.min.js"><\/script>')</script>
</head>
<body>
<shiro:hasPermission name="product:upload">
<div style="margin-left: 10px ;margin-top: 20px;">
    <button type="button" class="btn btn-success" id="addnew">上传图片</button>
</div>
</shiro:hasPermission>
<hr/>
<c:if test="${not empty pictures}">
    <c:forEach var="picture" items="${pictures}">
        <div class="file-preview-frame file-preview-success" id="${picture}" data-fileindex="0" data-template="image">
            <div class="kv-file-content">
                <img src="/backstage/Product/image/cut_image/${picture}" class="kv-preview-data file-preview-image" title="${picture}" alt="${picture}" style="width: 291px;height:160px;">
            </div>
            <div class="file-thumbnail-footer">
                <div class="file-actions">
                    <div class="file-footer-buttons">
                        <shiro:hasPermission name="product:delete">
                        <button type="button" class="kv-file-remove btn btn-xs btn-default" title="Remove file" data-id="${picture}"><i class="glyphicon glyphicon-trash text-danger"></i></button>
                        </shiro:hasPermission>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>

</body>

<script>

    $(function () {
        $('#addnew').click(function(){
            window.location.href="/backstage/Product/upload";
        });
    });

    $(".kv-file-remove").click(function() {

        if(confirm("确定要删除吗？"))
        {
            var url = "/backstage/Product/delete?picture=" + $(this).data("id");
            window.location.href=url;
        }
    });
</script>

</html>
