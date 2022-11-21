<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta name="renderer" content="webkit">
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
   <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css" />
   <link rel="stylesheet" href="${ctxPath}/static/css/table.css" />
   <title>角色列表</title>
   <script src="${ctxPath}/static/layui/layui.js"></script>
   <script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<!-- {1}放置搜索栏 【018行】 -->
	
	<!-- {2}放置动态表格 -->
    <table class="layui-hide" id="demo" lay-filter="test">
    </table>
    
	<!-- {3}表格工具栏 -->
	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
	
</body>
<script src="${ctxPath}/static/js/form.js"></script>
<script>
/* ----------------------- 自定义相关数据与方法 -------------------------  */

var gInputs = [
	{title:'ID',name:'id',type:'text',readonly:'readonly'},
	{title:'设置角色类型',name:'roleName',type:'text'},
	{title:'设置角色职能',name:'desctipr',type:'text'},
];

function showPopBox( user ){
	var HTML = makeTable( gInputs, user );
	layer.open({
		 type: 1
		 ,title: '编辑通知'      //显示标题栏
		 ,closeBtn: false
		 ,area: ['450px','380px']
		 ,shade: 0
		 ,id: 'LAY_layuipro'   //设定一个 id, 防止重复弹出
		 ,btn: ['保存通知', '关闭对话框']  //{ps} 两个按钮
		 ,btnAlign: 'c'        //居中对齐
		 ,moveType: 1          //拖拽模式, 0 或者 1
		 ,content: HTML        //这是上面生成的表格 html 代码
		 ,yes: function(){		   
			  saveNotify();   //调用保存用户
		 }
		 ,success: function(layero){
			 //--暂时不写代码--
		 }
	});
}

//[165行附近]设置要抓取表单项目
//用在点击提按钮时, 保存用户数据.
var items = [
 	"id","nickName","password","roleId","no","sex"
];

function editRole( _id ){
	$.ajax({
		url:'${ctxPath}/Role/getRole?id='+ _id,
		type:'get',		
		dataType:'json',
		success:function(resp){
			var ret = resp['result'];
			if(ret=='success'){
				showPopBox( resp['data'] );
			}else{
				layer.msg("获取角色失败:"+ resp.cause);
			}
		}
	});
}

function delRole( _id ){
	$.ajax({
		url:'${ctxPath}/Role/delRole?id='+ _id,
		type:'get',		
		dataType:'json',
		success:function(resp){
			var ret = resp['result'];
			if(ret=='success'){
				layer.msg('删除角色成功' + _id);
				setTimeout( reloadPage, 2000 );
			}else{
				layer.msg("删除角色失败:"+ resp.cause);
			}
		}
	});
}

function saveUser(){
	//{1}抓取表单数据。
	var user = pickData( items );
	//{2}你要测试这里有没有输出数据。
	console.log( user );
	
	//{2}用 ajax 提交表单。
	$.ajax({
		url:'${ctxPath}/admin/saveRole',
		type:'post',
		data: user,
		dataType:'json',
		success:function(resp){
			var ret = resp['result'];
			if(ret=='success'){
				layer.msg('保存设置成功。');
				//{ps}2秒后, 重载此页。
				setTimeout( reloadPage, 2000 );
			}else{
				layer.msg('保存设置失败, 原因:'+ resp.cause);
			}
		}		
	});
}
function reloadPage(){
	window.location = "${ctxPath}/Role/viewList";
}
</script>

<script>
/* ----------------------- LayUI 所有组件-------------------------  */
//{1}为了更新 js 缓存，可忽略
layui.config({
  version: '1554901098009' 
});

//{2}声明 LayUI 使用哪些组件。
layui.use(
	[ 'laypage', 'layer', 'table', 'element' ], 
	function(){
	  var laypage = layui.laypage //分页
	  ,layer = layui.layer       //弹层
	  ,table = layui.table       //表格
	  ,element = layui.element;  //元素操作
	
	//{A}动态表格
  var ran = Math.random();
  table.render({
    elem: '#demo'
    ,height: 450
    ,url: '${ctxPath}/Role/jsonList?ran='+ ran   //数据接口
    ,title: '角色表'
    ,page: true     //{ps} 开启分页
    ,id: 'roleTbl'
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
      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
      {field: 'roleName', title: '角色类型', width:100, fixed: 'left'},
      {field: 'desctipr', title: '角色职能', width:100, fixed: 'left'},
      

      {fixed: 'right', width: 185, align:'center', toolbar: '#barDemo'}
    ]]
  });

  //---------------在这里加入其它的组件----------------
	table.on('tool(test)', function(obj){  
		var data = obj.data     //获得当前行数据
		, layEvent = obj.event; //获得 lay-event 对应的值
		if(layEvent === 'del'){
		   //----在这里添加处理代码----
		   delRole( data['id'] );
		} else if(layEvent === 'edit'){
		   //data['id']: 获取表格中的 id
		   editRole( data['id'] );
		}
	});
	  
});
</script>
</html>