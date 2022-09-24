<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/11
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css" />
    <title>修改用户信息</title>
    <script src="${ctxPath}/static/layui/layui.js"></script>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item" style="margin-bottom:5px;">
        <div class="layui-inline">
            <label class="layui-form-label">用户昵称</label>
            <div class="layui-input-inline">
                <input type="text" name="frmNickName" id="frmNickName"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">工号</label>
            <div class="layui-input-inline">
                <input type="text" name="frmNo" id="frmNo"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">用户账号</label>
            <div class="layui-input-inline">
                <input type="text" name="frmAccount"
                       id="frmAccount" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">

        <label class="layui-form-label">用户角色</label>
        <div class="layui-input-inline">
            <select name="frmRoleId" id="frmRoleId">
                <option value="">请选择角色</option>
                <option value="1">酒店经理</option>
                <option value="2">酒店前台</option>
                <option value="3">保洁人员</option>
                <option value="4">维修人员</option>
                <option value="5">后勤人员</option>
                <option value="6">财务人员</option>
            </select>
        </div>

        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <select id="frmSex" name="frmSex" lay-filter="frmSex">
                <option value="">请选择性别</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
    </div>
</form>
</body>
</html>
