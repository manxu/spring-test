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
        $(function (){
            var xx = {};
            $.getJSON('/collections',{},function (data){
                for(var i in data.collections){
                    $("#col").append('<li class="datas">'+ data.collections[i].name +'</li>');
                }
                $(".datas").click(function (){
                    var table = $(this).text();
                    var skip = $("#skip").val();
                    $.ajax({type:'POST',url:'datas',dataType:'json',data:{'table':table,'para':"{}", 'skip': skip},
                    success:function (res){
                        $('#list').html("");
                        $('#detail').html("");
                        for(var i in res.data){
                            var x = JSON.parse(res.data[i]);
                            $("#list").append('<p id="'+x._id+'">' + i + ': ' + x._id +'</p>');
                            xx[x._id] = res.data[i];
                        }
                        $('#list>p').click(function (){
                            var id  = $(this).attr("id");
                            var x = JSON.parse(xx[id]);
                            $('#detail').html("");
                            for(var s in x){
                                var type = typeof x[s] == 'number' ? 1 : 0;
                                var value = ((typeof x[s] == 'object' ? JSON.stringify(x[s]) : x[s]));
                                var str = '<span>'+ s + ':</span> <input class="cc" is_number="'+type+'" name="'+ s +'"  data_table="'+ table +'"  data_id="'+ id +'" value=\'' + value + '\'> </input>';
                                str += '<input type="button" value="修改" class="modify"/></br>';
                                $('#detail').append(str);
                            }
                            $(".modify").click(function (){
                                var inp = $(this).prev();
                                var n = inp.attr('is_number')
                                $.ajax({
                                    type:'POST',
                                    url:'update',
                                    dataType:'json',
                                    data:{'table':inp.attr('data_table'),'para':'{"_id":"'+ inp.attr('data_id')+'", "'+ inp.attr('name') +'":' + (n==1?'':'"')   + inp.val() + (n==1?'':'"') +'}'},
                                    success:function(res){
                                        alert(res.errcode + ":" + res.errmsg)
                                    }
                                });
                            });
                        });
                    }});
                });
            })



        });
    </script>

</head>
<body>
skip <input type="text" value="0" id="skip">
<div style="display: flex; flex-direction: row">
    <div style="width: 20%; border: 1px solid gray">
        表空间
        <ul id="col" style="width:200px;">

        </ul>
    </div>
    <div style="width: 20%; border: 1px solid gray" id="list">

    </div>
    <div style="width: 60%; border: 1px solid gray" id="detail">

    </div>
</div>




</body>
</html>