<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/9/11
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
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
    <title>房间预览</title>
    <script src="${ctxPath}/static/layui/layui.js"></script>
    <script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<!-- {1}放置搜索栏 【018行】 -->

<!-- {2}放置动态表格 -->
<table id="demo" lay-filter="test"></table>

<!-- {3}表格工具栏 -->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit" href="${ctxPath}/">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    //{1}为了更新 js 缓存，可忽略
    layui.config({
        version: '1554901098009'
    });

    //{2}声明 LayUI 使用哪些组件。
    layui.use(
        [ 'laypage', 'layer', 'table', 'element' ],
        function(){
            var laypage = layui.laypage     //分页
                ,layer = layui.layer       //弹层
                ,table = layui.table       //表格
                ,element = layui.element;  //元素操作

            //{A}动态表格
            var ran = Math.random();
            table.render({
                elem: '#demo'
                ,url: '${ctxPath}/User/viewList?page=1&limit=3&ran='+ ran   //数据接口
                ,title: '房间表'
                ,page: true     //{ps} 开启分页
                ,id: 'userTbl'
                //{ps} 开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                //toolbar: 'default'
                ,totalRow: false   //{ps} 开启合计行
                ,cols: [[          //{ps} 表头
                    {type: 'checkbox', fixed: 'left'},
                    /*
                      [改动]
                                用户角色不显示:
                      1. User 类中要包含 roleName 属性。
                      2. sql 语句中要包含 roleName 属性。
                    */
                    {field: 'roomNo', title: '房间号', width:100,  fixed: 'left'},
                    {field: 'floor', title: '层数', width:100, sort: true, fixed: 'left'},
                    {field: 'status', title: '入住状态', width:100, fixed: 'left'},
                    {field: 'typeName', title: '房间类型', width:100, fixed: 'left'},
                    {field: 'dayPrice', title: '日价格', width:100, fixed: 'left'},
                    {field: 'hourPrice', title: '小时价', width:100, fixed: 'left'},
                    {fixed: 'right', width: 185, align:'center', toolbar: '#barDemo'}
                ]]
            });
            //---------------在这里加入下面的组件----------------
        });
</script>
</body>
</html>
