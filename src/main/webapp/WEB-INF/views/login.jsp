<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        a{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div>
    请输入你的名字：<input type="text" name="name" id="name"/>
    <button type="button" onclick="save()">提交</button>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var totalNumber = Number(${totalPageNumber});
        var pageSize = Number(${pageSize});
        var pageCount = totalNumber/pageSize;
        var html = "";
        for(var i = 0;i<pageCount;i++){
            var link_Url = "<li><a href=\"list?pageNumber="+(i+1)+"\">"+(i+1)+"</a></li>";
            html += link_Url;
        }
        var fenyeDiv = document.getElementById("link");
        fenyeDiv.innerHTML=html;
    });
</script>
<div class="table-responsive">
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td><a onclick="deleteUser(${item.id})" class="glyphicon glyphicon-trash"></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="pagination pagination-lg" id="link">

    </ul><br>
</div>
</body>
<script>
    function save(){
        $.ajax({
            url:"add",
            type:'post',
            dataType:'json',
            data:{
                name:$("#name").val()
            },
            success:function(data){
                console.info(data);
                location.reload();
            }
        });
    }

    function deleteUser(id){
        $.ajax({
            url:'deleteUser',
            type:'POST',
            dataType:'json',
            data:{
                id:id
            },
            success:function (data) {
                if(data.state=="success"){
                    alert("删除成功");
                    location.reload();
                }else{
                    alert("删除失败，请稍后重新尝试。。。");
                }
            }
        });
    }
</script>
</html>
