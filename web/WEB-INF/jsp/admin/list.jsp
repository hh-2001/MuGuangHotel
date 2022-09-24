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
   <script src="${ctxPath}/static/js/form.js"></script>
   <script src="${ctxPath}/static/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<!-- {1}放置搜索栏 【018行】 -->
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
         <label class="layui-form-label">用户名</label>
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

      <button type="button"
              class="layui-btn" onclick="testSearch();"
              style="margin-left:10px;">立即搜索</button>
      <button type="button"
              class="layui-btn layui-btn-normal"
              style="margin-left:10px;">重置</button>
   </div>
</form>
<!-- {2}放置动态表格 -->
<table class="layui-hide" id="demo" lay-filter="test">
</table>
<!-- {3}表格工具栏 -->
<script type="text/html" id="barDemo">
   <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
<script>
   /* ----------------------- 自定义相关数据与方法 -------------------------  */
   //下拉菜单的选项数据
   var role_data = [
      {text:'请选择角色',val:''},
      {text:'酒店经理',val:'1'},
      {text:'酒店前台',val:'2'},
      {text:'保洁人员',val:'3'},
      {text:'维修人员',val:'4'},
      {text:'后勤人员',val:'5'},
      {text:'财务人员',val:'6'}
   ];
   var sex_data = [
      {text:'请选择性别',val:''},
      {text:'男',val:'男'},
      {text:'女',val:'女'}
   ];
   var gInputs = [
      {title:'用户帐号',name:'account',type:'text',readonly:'readonly'},
      {title:'用户昵称',name:'nickName',type:'text'},
      {title:'设置密码',name:'password',type:'text'},
      {title:'设置工号',name:'no',type:'text'},
      {title:'设置性别',name:'sex',type:'select',options:sex_data},
      {title:'设置角色',name:'roleId',type:'select',options:role_data},
      {name:'id',type:'hide'}
   ];

   var testData = {
      result:'success',
      data:{
         account:'andy',
         nickName:'安迪',
         password:'123',
         no:'no01',
         sex:'男',
         roleId:'r01',
         id:'d01'
      }
   }

   function testTable(){
      var user = testData['data'];
      showPopBox( user );
   }

   function showPopBox( user ){
      var HTML = makeTable( gInputs, user );
      layer.open({
         type: 1
         ,title: '编辑用户'      //显示标题栏
         ,closeBtn: false
         ,area: ['450px','380px']
         ,shade: 0
         ,id: 'LAY_layuipro'   //设定一个 id, 防止重复弹出
         ,btn: ['保存用户', '关闭对话框']  //{ps} 两个按钮
         ,btnAlign: 'c'        //居中对齐
         ,moveType: 1          //拖拽模式, 0 或者 1
         ,content: HTML        //这是上面生成的表格 html 代码
         ,yes: function(){
            saveUser();   //调用保存用户
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

   function editUser( _id ){
      $.ajax({
         url:'${ctxPath}/Admin/getUser?id='+ _id,
         type:'get',
         dataType:'json',
         success:function(resp){
            var ret = resp['result'];
            if(ret=='success'){
               showPopBox( resp['data'] );
            }else{
               layer.msg("获取用户失败:"+ resp.cause);
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
         url:'${ctxPath}/Admin/addUser',
         type:'post',
         data: user,
         dataType:'json',
         success:function(resp){
            var ret = resp['result'];
            if(ret=='success'){
               layer.msg('保存用户成功。');
               //{ps}2秒后, 重载此页。
               setTimeout( reloadPage, 2000 );
            }else{
               layer.msg('保存用户失败, 原因:'+ resp.cause);
            }
         }
      });
   }
   function reloadPage(){
      window.location = "${ctxPath}/Admin/viewList";
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
                 ,url: '${ctxPath}/Admin/viewList?ran='+ ran   //数据接口
                 ,title: '用户表'
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
                    {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                    {field: 'account', title: '用户帐号', width:100, fixed: 'left'},
                    {field: 'account', title: '用户帐号', width:100, fixed: 'left'},
                    {field: 'nickName', title: '用户昵称', width:100, fixed: 'left'},
                    {field: 'roleName', title: '用户角色', width:100, fixed: 'left'},
                    {field: 'sex', title: '性别', width:100, fixed: 'left'},
                    {field: 'no', title: '工号', width:100, fixed: 'left'},
                    {field: 'createDate', title: '创建日期', width:100, fixed: 'left'},

                    {fixed: 'right', width: 185, align:'center', toolbar: '#barDemo'}
                 ]]
              });

              //---------------在这里加入其它的组件----------------
              table.on('tool(test)', function(obj){
                 var data = obj.data     //获得当前行数据
                         , layEvent = obj.event; //获得 lay-event 对应的值
                 if(layEvent === 'del'){
                    //----在这里添加处理代码----
                 } else if(layEvent === 'edit'){
                    //data['id']: 获取表格中的 id
                    editUser( data['id'] );
                 }
              });

           });
</script>
</html>