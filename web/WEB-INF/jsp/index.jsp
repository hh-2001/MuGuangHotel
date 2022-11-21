<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>酒店管理系统</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  
  <link rel="stylesheet" href="${ctxPath}/static/layui/css/layui.css" />
  <script src="${ctxPath}/static/layui/layui.js"></script>

<style>
#primaryWin{
	width:100%;
	height:100%;
}
</style>

<script>
function gotoPage( url ){
	var obj = document.getElementById('primaryWin');
	obj.src = '${ctxPath}'+ url;
}
</script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">酒店管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    		
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="${ctxPath}/static/images/tou.jpg" 
			class="layui-nav-img">
           ${user.nickName}[${user.roleName}]
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="${ctxPath}/Common/showLogin">退了</a></li>
    </ul>
  </div>
    
  <!-- 垂直导航栏 -->
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll" id="menu"> 
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
      <c:forEach items="${applicationScope[MENU_KEY] }" var="entry">
    	<li class="layui-nav-item">
    	<a>${entry.value.menuName}</a>
	    	<dl class="layui-nav-child">
		    	<c:forEach items="${entry.value.menuItems}" var="menuItem">
	            <c:if test="${menuItem.visible=='1'}">
	            <dd>
					<a href="#" onclick="gotoPage('${menuItem.urlLink}');">
						${ menuItem.itemName }
					</a>
	            </dd>
	            </c:if>
				</c:forEach>

	    	</dl>
     	</li>
  	  </c:forEach>  
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
		<iframe id="primaryWin" scrolling="no" frameborder="0" src="${ctxPath}/Common/welcome" ></iframe>
	<!-- 内容主体区域 -->
  </div>
  
  
  
  <div class="layui-footer">
    <!-- {Note} 底部固定区域 -->
    ALECTER.COM --- 艾力特信息技术有限公司
  </div>
</div>

	
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
<script>
var _hmt = _hmt || [];
(function() {
	var hm = document.createElement("script");
	hm.src = "https://hm.baidu.com/hm.js?d214947968792b839fd669a4decaaffc";
	var s = document.getElementsByTagName("script")[0]; 
	s.parentNode.insertBefore(hm, s);
})();
</script>
</body>
</html>
