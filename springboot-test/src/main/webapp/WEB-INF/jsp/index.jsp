<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <style>
        /*table, td, th {*/
        /*    border: 1px solid black;*/
        /*}*/

        /*table {*/
        /*    border-collapse: collapse;*/
        /*    width: 100%;*/
        /*}*/

        /*th {*/
        /*    height: 70px;*/
        /*}*/
        .cc {
            width: 400px;
            margin-top: 5px;
            margin-left: 5px;
        }
    </style>
    <script type="text/javascript">
        // $(function (){
        //     var xx = {};
        //     $.getJSON('/collections',{},function (data){
        //         for(var i in data.collections){
        //             $("#col").append('<li class="datas">'+ data.collections[i].name +'</li>');
        //         }
        //         $(".datas").click(function (){
        //             var table = $(this).text();
        //             var skip = $("#skip").val();
        //             $.ajax({type:'POST',url:'datas',dataType:'json',data:{'table':table,'para':"{}", 'skip': skip},
        //             success:function (res){
        //                 $('#list').html("");
        //                 $('#detail').html("");
        //                 for(var i in res.data){
        //                     var x = JSON.parse(res.data[i]);
        //                     $("#list").append('<p id="'+x._id+'">' + i + ': ' + x._id +'</p>');
        //                     xx[x._id] = res.data[i];
        //                 }
        //                 $('#list>p').click(function (){
        //                     var id  = $(this).attr("id");
        //                     var x = JSON.parse(xx[id]);
        //                     $('#detail').html("");
        //                     $('#detail').append('<input type="button" value="删除" onclick="del(this)">');
        //                     for(var s in x){
        //                         var type = typeof x[s] == 'number' ? 1 : 0;
        //                         var value = ((typeof x[s] == 'object' ? JSON.stringify(x[s]) : x[s]));
        //                         var str = '<span>'+ s + ':</span> <input class="cc" is_number="'+type+'" name="'+ s +'"  data_table="'+ table +'"  data_id="'+ id +'" value=\'' + value + '\'> </input>';
        //                         str += '<input type="button" value="修改" class="modify"/></br>';
        //                         $('#detail').append(str);
        //                     }
        //                     $(".modify").click(function (){
        //                         var inp = $(this).prev();
        //                         var n = inp.attr('is_number')
        //                         $.ajax({
        //                             type:'POST',
        //                             url:'update',
        //                             dataType:'json',
        //                             data:{'table':inp.attr('data_table'),'para':'{"_id":"'+ inp.attr('data_id')+'", "'+ inp.attr('name') +'":' + (n==1?'':'"')   + inp.val() + (n==1?'':'"') +'}'},
        //                             success:function(res){
        //                                 alert(res.errcode + ":" + res.errmsg)
        //                             }
        //                         });
        //                     });
        //                 });
        //             }});
        //         });
        //     })
        //
        //
        //
        // });
        // function del(i){
        //     var inp = $(i).next().next();
        //     $.ajax({
        //         type:'POST',
        //         url:'delete',
        //         dataType:'json',
        //         data:{'table':inp.attr('data_table'),'para':'{"_id":"'+ inp.attr('data_id') +'"}'},
        //         success:function(res){
        //             alert(res.errcode + ":" + res.errmsg)
        //         }
        //     });
        //
        // }
    </script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
skip <input type="text" value="0" id="skip">
<div id="main">
    <template>
        <el-table :data="tableData" border style="width: 100%"  :fit='true' @row-click="clickTr">
            <el-table-column type="index" width="50">
            </el-table-column>
            <el-table-column prop="name" label="表名" width="180"></el-table-column>
        </el-table>
    </template>
</div>

<div id="de">
    <template>
        <el-table :data="tableData" border style="width: 100%"  :fit='true' @cell-click="clickCr">
            <el-table-column type="index" width="50"></el-table-column>

            <el-table-column  width="200"  :prop="index" :label="item" sortable  v-for="(item, index) in tableHeader"
                             :key="index" >
                <template slot-scope="scope">
                    <input type="text" v-model="scope.row[item]" />
                    <button>修改</button>
                </template>
            </el-table-column>
            <el-table-column
                    align="right">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="输入关键字搜索"/>
                </template>
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
</div>


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
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    va
</script>
<script>
    var xx = {};
    $.getJSON('/collections',{},function (data){

        var mainData = {};
        mainData.tableHeader = {
            name: "表名"
        };
        mainData.tableData= [];
        for(var i in data.collections){
            mainData.tableData.push({name:data.collections[i].name});
        }
        var Main = {
            data() {
                return mainData;
            },
            methods: {
                clickTr(row){
                    clickTr(row);
                }
            }
        }
        var Ctor = Vue.extend(Main)
        new Ctor().$mount('#main')
    });

    //行点击

    function clickTr(row){
        var table = row.name;
        var skip = $("#skip").val();
        $.ajax({type:'POST',url:'datas',dataType:'json',data:{'table':table,'para':"{}", 'skip': skip},
            success:function (res){
                var deData = {};
                deData.tableHeader = {
                    tablename: 'tablename'
                };
                deData.tableData= [];

                for(var i in res.data){
                    var x = JSON.parse(res.data[i]);
                    for(var s in x) {

                        deData.tableHeader[s] = s;
                        var type = typeof x[s] == 'number' ? 1 : 0;
                        var k = s + "is_number";
                        x[k] = type;

                    }

                    x.tablename = table;
                    deData.tableData.push(x);


                }
                var De = {
                    data() {
                        return deData;
                    },
                    methods: {
                        clickCr(row, column, cell, event) {
                            if(event.target.nodeName!='BUTTON') return;
                            var k = column.property + "is_number";
                            var n = row[k];
                            $.ajax({
                                type:'POST',
                                url:'update',
                                dataType:'json',
                                data:{'table':row.tablename,'para':'{"_id":"'+ row._id +'", "'+ column.property +'":' + (n==1?'':'"')   + row[column.property] + (n==1?'':'"') +'}'},
                                success:function(res){
                                    alert(res.errcode + ":" + res.errmsg)
                                }
                            });
                        },
                        handleDelete(index, row) {
                            var inp = row._id;
                            $.ajax({
                                type:'POST',
                                url:'delete',
                                dataType:'json',
                                data:{'table':inp.attr('data_table'),'para':'{"_id":"'+ inp.attr('data_id') +'"}'},
                                success:function(res){
                                    alert(res.errcode + ":" + res.errmsg)
                                }
                            });
                        }
                    },
                }
                var Ctor = Vue.extend(De)
                new Ctor().$mount('#de')
            }});
    }

</script>
</html>