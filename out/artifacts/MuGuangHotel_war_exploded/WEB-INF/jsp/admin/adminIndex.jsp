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
    <title>管理员界面</title>
</head>
<div class="layui-container">
    <%--    导航区--%>
    <div class="layui-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree site-demo-nav">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">员工信息列表</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">角色设置</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">角色权限设置</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="javascript:;" href="javascript:;">菜单管理</a>
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
                <jsp:include page="list.jsp"></jsp:include>
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
