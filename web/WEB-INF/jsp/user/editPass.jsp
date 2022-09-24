<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/><html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css" />
    <title>修改个人信息</title>
    <script src="${ctxPath}/static/layui/layui.js"></script>
</head>
<body>
<form class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="text" name="frmNo" id="frmPass"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">再次输入密码</label>
            <div class="layui-input-inline">
                <input type="text" name="frmNo" id="frmPasse"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</form>
</body>
</html>