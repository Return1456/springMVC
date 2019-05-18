<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.hibernate.*,com.zdq.springmvc.model.Good,com.zdq.springmvc.model.Order,com.zdq.springmvc.model.User,com.zdq.springmvc.model.UserService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@page import="org.springframework.web.context.WebApplicationContext" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="/springMVC/jsp/cart/cart.css">

<!-- 	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
	  $(document).ready(function() {
  $("tr:even").css("background-color","#D3D3D3");
  });
	</script> -->
  </head>
  
  <body>
 <h1> 购物车</h1>
    <hr>
       <table>
     <tr>
       <th>商品名称</th> 
       <th>商品单价</th>
       <th>购买数量</th>
       <th>金额</th>
       <th>编辑</th>
     </tr>
     <% WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		UserService userService = (UserService)context.getBean("userService");
     
     String user=(String)session.getAttribute("user");
     SessionFactory sf=userService.getSessionFactory();//hibernate 查询shop表
 	Session ses=sf.openSession();
 	Transaction ts=ses.beginTransaction();
 	String hql="from Order ord where ord.user.username='"+user+"'";
 		@SuppressWarnings("unchecked")
 	List<Order> list=ses.createQuery(hql).list();
     Iterator<Order> it=list.iterator();
     while(it.hasNext()){
     Order ord=it.next();
    // System.out.println(ucm.getSp_num());
     Good gd=ord.getGood();
     out.print("<tr>");
     out.print("<td>"+gd.getGd_name()+"</td>");
 	 out.print("<td>"+gd.getGd_price()+"</td>");
 	 out.print("<td>"+ord.getNum()+"</td>");
 	 out.print("<td>"+ord.getNum()*gd.getGd_price()+"</td>");
 	 out.print("<td align='center'><a href='deletecart.action?id="+ord.getId()+"'>删除</a></td>");
     out.print("</tr>");
     }
     ts.commit();
     ses.close();
     %>
     </table>
     
           <br>
   <a href="shop" target="right">继续购物</a>
   <% 
/*    String user=(String)session.getAttribute("user"); */
   out.print("<a href='deletecartall?user="+user+"' target='right'>清空购物车</a>");%>
      <a href="buyall" target="right">全部购买</a>
  </body>
</html>
