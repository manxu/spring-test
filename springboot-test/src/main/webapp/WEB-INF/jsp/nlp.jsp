<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <style>

    </style>

    <!-- 引入样式 -->
</head>
<body style="margin-left: 50px;">
<br><br><br>
<select id="type">
    <option value="1">情感</option>
    <option value="2">duihua</option>
</select>
<br>
<textarea rows="10" cols="100" id ="content" name="content"></textarea>
<input type="button" value="tixxx" onclick="cc()">
<br>
<div>情感极性分类结果: <span id="sentiment"></span></div>
<div>分类的置信度: <span id="confidence"></span></div>
<div>积极类别的概率: <span id="positive_prob"></span></div>
<div>消极类别的概率: <span id="negative_prob"></span></div>
<div class="progress" style="width: 30%;">
    <div id="ji" class="progress-bar progress-bar-success" style=" "></div>
    <div id="fu" class="progress-bar progress-bar-danger" style=""></div>
</div>
duihua
<div class="progress" style="width: 30%;">
    <div id="ji1" class="progress-bar progress-bar-success" style=" "></div>
    <div id="zh1" class="progress-bar progress-bar-info" style=" "></div>
    <div id="fu1" class="progress-bar progress-bar-danger" style=""></div>
</div>

<div>
    <pre id = "pre" style="width: 50%">

    </pre>

</div>

<div style="width: 50%">
    语言：<textarea rows="10" cols="100" id ="yu" name="yu"></textarea>
    语速<input type="range" class="form-range" id="spd" name="spd" min="0" max="9">
    音调<input type="range" class="form-range" id="pit" name="pit" min="0" max="9">
    音量<input type="range" class="form-range" id="vol" name="vol" min="0" max="15" value="5">
    发音人<input type="radio" name="per" value="0" checked>度小美
    <input type="radio" name="per" value="1"> 度小宇
    <input type="radio" name="per" value="2">xxx
    <input type="radio" name="per" value="3">度逍遥
    <input type="radio" name="per" value="4">度丫丫
    <input type="radio" name="per" value="5">度小娇
    <input type="radio" name="per" value="5003">度逍遥
    <input type="radio" name="per" value="5118">度小鹿
    <input type="radio" name="per" value="106">度博文
    <input type="radio" name="per" value="110">度小童
    <input type="radio" name="per" value="111">度小萌
    <input type="radio" name="per" value="103">度米朵


    <input type="button" value="tixxx" onclick="yu()">

</div>



<div style="width: 50%">
    <form id="form" autocomplete="off" enctype="multipart/form-data">
    上传： <input type="file" name="file" id = "file">
        <input type="button" value="tixxx" onclick="up()">
    </form>

</div>

</body>
<script>

    function cc(){
        var type = $('#type').val();
        var content = $('#content').val();
        $.getJSON('/fx',{type:type, content:content},function (data){
            $('#pre').html(JSON.stringify(data).replaceAll(',',',<br>'));

            if(type ==1){
                data = data.items[0];
                $('#sentiment').text(data.sentiment == 0?'负向':(data.sentiment == 1?'中性':'正向'));
                $('#sentiment').css('color',data.sentiment == 0?'red':(data.sentiment == 1?'gray':'green'));
                $('#confidence').text(data.confidence);
                $('#positive_prob').text(data.positive_prob);
                $('#negative_prob').text(data.negative_prob);

                $('#ji').css('width', data.positive_prob*100 + '%');
                $('#fu').css('width', data.negative_prob*100 + '%');
            }else{
                data = data.items;
                for (d in data){
                    var x = data[d];
                    if(x.label == 'optimistic'){
                        $('#ji1').css('width', data[d].prob*100 + '%');
                    } else if(x.label == 'neutral'){
                        $('#zh1').css('width', data[d].prob*100 + '%');
                    } else{
                        $('#fu1').css('width', data[d].prob*100 + '%');
                    }
                }



            }

        });
    }
    function yu(){
        var content = $('#yu').val();
        var spd = $('#spd').val();
        var pit = $('#pit').val();
        var vol = $('#vol').val();
        var per = $("[name='per']:checked").val();


        $.getJSON('/yu',{spd:spd, pit:pit, vol:vol, per:per , content:content},function (data){
            alert(data);
        });
    }

    function up(){
        $.ajax({
            url : '/upload',
            type : 'POST',
            cache : false,
            data : new FormData($('#form')[0]),
            processData : false,
            contentType : false,
            success : function(result) {
                alert(result)
            }
        });
    }
</script>
</html>