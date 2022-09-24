<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/><html>
<html>
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
<div class="layui-container">
    <%--    导航区--%>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-demo-nav">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">房间信息</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">住房信息</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">当前业务信息</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">营业额</a>
                </li>
            </ul>
        </div>
    </div>
    <%--    内容区--%>
        <%--    内容区--%>
        <div class="layui-row" style="height: 100%; margin-left: 15%;margin-top: 10%">
            <div class="layui-col-md12">
                <jsp:include page="../common/header.jsp"></jsp:include>
            </div>
            <div class="layui-col-md12">
                <jsp:include page="roomList.jsp"></jsp:include>
            </div>
            <div class="layui-col-md12">
                <jsp:include page="../common/footer.jsp"></jsp:include>
            </div>
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
