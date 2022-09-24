<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/8
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%--管理员页面--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>经理界面</title>
</head>
<div class="layui-container">
    <%--    导航区--%>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-demo-nav">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">公告管理</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">房间设置</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">营业额</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">待定</a>
                </li>
            </ul>
        </div>
    </div>
    <%--    内容区--%>
        <%--    内容区--%>
        <%--    内容区--%>
        <div class="layui-row" style="height: 100%; margin-left: 15%;margin-top: 10%">
            <div class="layui-col-md12">
                <jsp:include page="../common/header.jsp"></jsp:include>
            </div>
            <div class="layui-col-md12">
                <jsp:include page=""></jsp:include>
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
