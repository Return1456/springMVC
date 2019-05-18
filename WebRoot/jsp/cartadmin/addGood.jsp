<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addGood.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<h1>添加商品</h1>
<hr>
<form action="addgood" method="post" enctype="multipart/form-data">
名称：<input name="gd_name"><br><br>
价格：<input name="gd_price"><br><br>
简介：<input	 name="gd_info"><br><br>
库存：<input name="gd_storage"><br><br>
图片：<input name="uploadphoto"  type="file"><br>
<br>
<input type="submit" value="添加商品"><br><br>
</form>


  </body>
</html>
