<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查询学生信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        input[type="text"] {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 50%;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-transform: uppercase;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        #resultArea {
            text-align: center;
            margin-top: 30px;
        }
        p {
            line-height: 1.5;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>请输入学生学号查询信息:</h2>
    <form action="/second/StuInfoServlet" method="post">
        学号：<input type="text" name="studentId"><br><br>
        <input type="submit" value="查询">
    </form>

    <%-- 用于显示查询结果 --%>
    <div id="resultArea">
        <% if(request.getAttribute("studentName")!=null) { %>
        <h3>学生信息：</h3>
        <p>姓名：<%= request.getAttribute("studentName") %></p>
        <p>学号：<%= request.getAttribute("studentId") %></p>
        <p>班级：<%= request.getAttribute("studentClassName") %></p>
        <!-- 可以添加更多学生信息展示 -->
        <% } %>
        <% if(request.getAttribute("errorMessage")!=null) { %>
        <h3>学生信息：</h3>
        <p>错误：<%= request.getAttribute("errorMessage") %></p>
        <% } %>
    </div>
</div>
</body>
</html>