<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>图片上传</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/backstage/Css/default.css">
    <link href="/backstage/Css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="/backstage/Js/jquery-1.11.0.min.js"><\/script>')</script>
    <script src="/backstage/Js/fileinput.js" type="text/javascript"></script>

</head>
<body>
<div class="htmleaf-container">

    <div class="container kv-main">

        <form enctype="multipart/form-data">
            <hr>
            <div class="form-group">
                <input id="file-5" class="file" type="file" name="file"  multiple data-preview-file-type="any" data-upload-url="#"/>
            </div>
        </form>


    </div>
</div>


</body>
<script>
    $("#file-5").fileinput({
        uploadUrl: '/backstage/Product/upload', // you must set a valid URL here else you will get an error
        allowedFileExtensions : ['jpg', 'png','gif'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 10,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });
    $(document).ready(function() {
        $("#test-upload").fileinput({
            'showPreview' : false,
            'allowedFileExtensions' : ['jpg', 'png','gif'],
            'elErrorContainer': '#errorBlock'
        });
    });

</script>


</html>
