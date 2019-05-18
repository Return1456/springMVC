<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="org.hibernate.*,com.zdq.springmvc.model.Good,com.zdq.springmvc.model.Order,com.zdq.springmvc.model.User,com.zdq.springmvc.model.UserService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.web.context.WebApplicationContext" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shop.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="/springMVC/jsp/cart/shop.css">

<!-- 	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	  $(document).ready(function() {
  $("tr:odd").css("background-color","#D3D3D3");
  });
	</script> -->

  </head>
  
  <body>
  <div class="header">  <h1>Shop</h1></div>
      <hr>

      <div class="nav"> 	<a href="cart" target="right">查看购物车</a></div>

  <table>
  <tr>
  <th>图片</th>
  <th>商品摘要</th>
    <th>在线购买</th>

    <%	WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		UserService userService = (UserService)context.getBean("userService");	//从spring ioc获取userService的bean
		String realpath=System.getProperty("evan.webapp");//从web.xml配置的获取项目的绝对路径
    	String user=(String)session.getAttribute("user");
    	
		int cid=userService.getCid(user);
		List<Good> list=userService.getAllgood();
		Iterator<Good> it=list.iterator();
		while(it.hasNext()){	//循环取出商品展示
		Good gd=it.next();
		if(gd.getGd_info()==null){
		gd.setGd_info("暂无简介");
		}
		userService.readphoto(gd.getGd_number());
		 out.print("<tr>");
		 if(gd.getPhotoFileName()==null){out.println(
		 "<td align='center'>无图片</td>");
		 }else{
		 out.println("<td align='center'><img src='/springMVC/jsp/cartadmin/upload/"+gd.getPhotoFileName() +"' border=0 height=60 width=60></td>");
		 }
         out.println("<td>");        
		 out.println("商品编号："+ gd.getGd_number() +"<br>");
         out.println("商品名称："+ gd.getGd_name() +"<br>");
         out.println("商品价格："+ gd.getGd_price() +"元<br>");
         out.println("商品简介："+ gd.getGd_info() +"<br>");
  		 out.println("商品库存："+ gd.getGd_storage() +"<br>");
         out.println("</td>");
		 out.println("<td align=center><a href='addcart?cid="+cid+"&gid="+gd.getGd_number()+"&num=1'>放入购物车</a></td>");
		 out.print("</tr>");
	
		}
		 %>
  
  
  </table>


  </body>
</html>
