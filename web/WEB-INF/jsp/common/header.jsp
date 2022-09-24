<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/8
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css" />
    <title>header</title>
    <script src="${ctxPath}/static/layui/layui.js"></script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header header-demo">
        <div class="layui-fluid">
            <a class="logo" href="${ctxPath}/static/image/common/tou.jpg"></a>
            <div class="layui-form layui-hide-xs component" lay-filter="LAY-site-header-component"></div>
            <div class="layui-hide-xs site-notice"></div>
            <ul class="layui-nav" id="LAY_NAV_TOP" style="float: right">
                <li class="layui-nav-item" data-dir="docs"><a href="/docs/">文档</a></li>
                <li class="layui-nav-item" data-dir="demo"><a href="/demo/">示例</a></li>
                <li class="layui-nav-item">
                    <a href=""><img src="${ctxPath}/static/image/common/tou.jpg" style="width: 50px;height: 50px;" alt="layui"></a>
                    <hr>
                    <dl class="layui-nav-child layui-nav-child-c">
                        <dd lay-unselect><a href="${ctxPath}/User/E" target="_blank" lay-unselect>个人设置</a></dd>
                        <hr>
                        <dd lay-unselect><a href="${ctxPath}/Common/loginOut" target="_blank">登出</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
