<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <style>
        table, td, th {
            border: 1px solid black;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th {
            height: 70px;
        }
        .cc {
            width: 400px;
            margin-top: 5px;
            margin-left: 5px;
        }
    </style>
    <script type="text/javascript">
        function upload(){
            $.ajax({
                url : form.attr('action'),
                type : 'POST',
                cache : false,
                data : new FormData($('#form')[0]),
                processData : false,
                contentType : false,
                success : function(result) {
                    // do something
                }
            });
        }
        function qr(){
            $.ajax({
                url : $('#formQr').attr('action'),
                type : 'POST',
                cache : false,
                data : new FormData($('#formQr')[0]),
                processData : false,
                contentType : false,
                success : function(result) {
                    // do something
                }
            });
        }

        function init(){
            $.getJSON('/init',{},function (data){
                alert(data);
            });
        }
    </script>

</head>
<body>


<form id="form" autocomplete="off" class="form-horizontal" role="form"  action="upload" enctype="multipart/form-data">
导入数据： <input type="file" name="file"><input type="button" value="提交" onclick="upload()"/>
</form>
<form id="formQr" autocomplete="off" class="form-horizontal" role="form"  action="qr" enctype="multipart/form-data">
    qr： <input type="file" name="file"><input type="button" value="提交" onclick="qr()"/>
</form>
<button type="button" onclick="init()">初始化用户</button>
</body>
</html>