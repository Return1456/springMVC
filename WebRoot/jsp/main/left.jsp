<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <style>
  p{
  text-align:center}
  
  </style>
  <body>
   <p>
   <a href="/springMVC/jsp/cart/shop.jsp" target="right">在线购物</a>
            <br><br>
            <% String user=(String)session.getAttribute("user");
            if(user.equals("zdq")){
            out.println("<a href='admin?flag=go' target='right'>商品管理</a>");
            }
            
             %>
<!--    <a href="/demo/cart/main.jsp" target="right">管理员</a> -->
   </p>
    
  </body>
</html>
