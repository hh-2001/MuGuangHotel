<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/7
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css" />
    <link rel="stylesheet" href="${ctxPath}/static/css/login.css" />
    <title>用户登录</title>
    <script src="${ctxPath}/static/layui/layui.js"></script>
    <script src="${ctxPath}/static/js/login/login.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title"
          style="margin-top: 20px;">
    <legend>用户登录页</legend>
</fieldset>
<div class="layui-container layui-row">
    <div class="layui-carousel layui-col-md9" id="change" style="margin: 0">
        <div carousel-item>
            <div><img src="${ctxPath}/static/image/index/1.jpg" alt="img"/></div>
            <div><img src="${ctxPath}/static/image/index/2.jpg" alt="img"/></div>
            <div><img src="${ctxPath}/static/image/index/3.jpg" alt="img"/></div>
            <div><img src="${ctxPath}/static/image/index/4.jpg" alt="img"/></div>
            <div><img src="${ctxPath}/static/image/index/5.jpg" alt="img"/></div>
        </div>
    </div>
    <div class="layui-form layui-col-md3">
        <form action="${ctxPath}/Common/login">
            <div class="layui-form-item">
                <label class="layui-form-label">用户帐号</label>
                <div class="layui-input-block" style="width:450px;">
                    <input type="text" id="account"
                           name="account" autocomplete="off"
                           placeholder="请输入帐号"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户密码</label>
                <div class="layui-input-block" style="width:450px;">
                    <input type="text" id="password"
                           name="password" autocomplete="off"
                           placeholder="请输入密码"
                           class="layui-input">
                </div>
            </div>
            <!-- {ps}新增加的内容(3 项)。。 -->
            <div class="layui-form-item">
                <div class="layui-input-block" style="width:120px;">
                    <input type="text" id="code"
                           name="code" autocomplete="off"
                           placeholder="请输入验证码"
                           class="layui-input" />
                </div>
                <label><img src="${ctxPath}/Common/GetCode" onclick="this.src='${ctxPath}/Common/GetCode?' + Math.random()" /></label>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%
    String err = request.getParameter("error");
    err = (err == null) ? "" : err;

%>
<script>

    window.onload = function () {
        var err = '<%=err%>';
        if (err) {
            layer.msg(err);
        }
    }
</script>
</body>
</html>
