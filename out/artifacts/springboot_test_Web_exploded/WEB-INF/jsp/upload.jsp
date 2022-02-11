<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <style>



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
                url : $('#form').attr('action'),
                type : 'POST',
                cache : false,
                data : new FormData($('#form')[0]),
                processData : false,
                contentType : false,
                success : function(data) {
                    for(var i in data){
                        $("#imdiv").append(JSON.stringify(data[i])).append('</br>');
                    }
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
        function addOrder(){
            $.ajax({
                url : $('#addOrder').attr('action'),
                type : 'POST',
                cache : false,
                data : new FormData($('#addOrder')[0]),
                processData : false,
                contentType : false,
                success : function(result) {
                    $('#addOrderDiv').append(JSON.stringify(result));
                }
            });
        }

        function init(){
            $.getJSON('/init',{},function (data){
                alert(data);
            });
        }
    </script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">

</head>
<body>
<div id="app">
    <template>
        <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 100%"  :fit='true'>
            <el-table-column type="index" width="50">
            </el-table-column>
            <el-table-column prop="date" label="表名" width="180">
            <el-table-column :prop="index" :label="item" sortable show-overflow-tooltip v-for="(item, index) in tableHeader"
                             :key="index">

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
                            @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
</div>
<form id="form" autocomplete="off" class="form-horizontal" role="form"  action="upload" enctype="multipart/form-data">
导入数据： <input type="file" name="file"><input type="button" value="提交" onclick="upload()"/>
</form>
<div> 导入结果
    <ul id ="imdiv">

    </ul>
</div>

<hr />

<div>
    手动
    <form id="addOrder" autocomplete="off" class="form-horizontal" role="form"  action="addOrder" enctype="application/x-www-form-urlencoded">
        no:<input type="text" name="no"><br>
        userId:<input type="text" name="userId"><br>
        amount:<input type="text" name="amount"><br>
        <input type="button" value="提交" onclick="addOrder()"/>
        <div> 导入结果
            <ul id ="addOrderDiv">

            </ul>
        </div>
    </form>
</div>

<hr />
<form id="formQr" autocomplete="off" class="form-horizontal" role="form"  action="qr" enctype="multipart/form-data">
    qr： <input type="file" name="file"><input type="button" value="提交" onclick="qr()"/>
</form>

<hr />
<button type="button" onclick="init()">初始化用户</button>
</body>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    var Main = {
        data() {
            return {
                tableHeader: {
                    date: "表名"
                },
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄'
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄'
                }]
            }
        }
    }
    var De = {
        data() {
            return {
                tableHeader: {
                    date: "表名",
                    name: "姓名",
                    address: "地址",
                },
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄'
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄'
                }]
            }
        }
    }
    var Ctor = Vue.extend(Main)
    new Ctor().$mount('#app')
</script>
</html>