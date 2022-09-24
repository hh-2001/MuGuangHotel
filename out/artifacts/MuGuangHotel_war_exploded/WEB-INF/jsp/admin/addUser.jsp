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
   <title>用户登录</title>
   <script src="${ctxPath}/static/layui/layui.js"></script>
   <script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title"
		  style="margin-top: 20px;">
	<legend>添加用户页</legend>
</fieldset>

<form id="userForm" class="layui-form"
	  action="" method="POST">
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
				   placeholder="请输入密码" value="123456"
				   class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">用户昵称</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="nickName"
				   name="nickName" autocomplete="off"
				   placeholder="请输入昵称"
				   class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label" style="width:100px;">
				用户角色
			</label>
			<div class="layui-input-inline">
				<select name="roleId" id="roleId">
					<option value="">请选择角色</option>
					<option value="1">酒店经理</option>
					<option value="2">酒店前台</option>
					<option value="3">保洁人员</option>
					<option value="4">维修人员</option>
					<option value="5">后勤人员</option>
					<option value="6">财务人员</option>
				</select>
			</div>
		</div>
	</div>

	<!-- 用户工号: no -->
	<div class="layui-form-item">
		<label class="layui-form-label">用户工号</label>
		<div class="layui-input-block" style="width:450px;">
			<input type="text" id="no"
				   name="no" autocomplete="off"
				   placeholder="请输入工号"
				   class="layui-input">
		</div>
	</div>

	<!-- 性别: sex -->
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label" style="width:100px;">
				性别
			</label>
			<div class="layui-input-inline">
				<select name="sex" id="sex">
					<option value="">请选择性别</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button type="button" class="layui-btn"
					onclick="doSubmit();">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>

<script>
	layui.use(
			['form', 'layedit', 'laydate'],
			function(){
				var form = layui.form;
				var layer = layui.layer;
				console.log('{DEBUG} layui.user...');
			}
	);


	var fields = ['account','nickName','password',
		'roleId','no','sex'];
	//{1}pickData 函数
	//   抓取 fields 表示的哪些数据。
	function pickData(){
		var obj = {};  //设置一个空对象。
		for(var i=0; i<fields.length; i++){
			var fldName = fields[i];
			obj[ fldName ] = $("#"+ fldName).val();
		}
		return obj;
	}

	//{2}doSubmit 函数
	function doSubmit(){
		//1. 抓取表单的数据。
		var obj = pickData();
		console.log( obj );
		$.ajax({
			url:'${ctxPath}/Admin/addUser',
			type:'post',
			data: obj,
			dataType:'json',  //返回的数据格式 json
			success:function( resp ){
				var ret = resp.result;
				if( ret=='success' ){
					layer.msg("新增用户成功。");
					//等下要跳转到列表页。【有代码】
				}else{
					var cause = resp.cause;
					layer.msg("新增用户失败, 原因:"+ cause);
				}
			}
		});
	}

//单元测试: [进入表单页]
// http://localhost:8080/Hotel/User/viewAddUser
// 在控制台打印 pickData() 结果。

</script>
</body>
</html>