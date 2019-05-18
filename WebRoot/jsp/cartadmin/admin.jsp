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
    
    <title>My JSP 'admin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
 <!--     <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script> -->
  <script type="text/javascript" src="/springMVC/jsp/cartadmin/admin.js"></script>
  <body>

  <h1> 操作商品</h1>
    <hr>

    <table border="1" style='text-align:center'>
  <tr>
 	 <th width="80">图片</th>
  	<th width="150">名称</th>
	<th width="150">价格</th>
     <th width="150">简介</th>
      <th width="150">库存</th>
       <th width="150" colspan=2>编辑</th>
             </tr>
             
        <%	
        String num=request.getParameter("num");
     	 WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		UserService userService = (UserService)context.getBean("userService");
				String realpath=System.getProperty("evan.webapp");//从web.xml配置的获取项目的绝对路径
		List<Good> list=userService.getAllgood();
		Iterator<Good> it=list.iterator();
		while(it.hasNext()){
		Good gd=it.next();
		if(gd.getGd_info()==null){
		gd.setGd_info("暂无简介");
		}
		
		userService.readphoto(gd.getGd_number());
		if(num!=null){
		if(gd.getGd_number()==Integer.parseInt(num)){
		out.print("<tr bgcolor='#FFE4E1'>");	
			if(gd.getPhotoFileName()==null){
			
			out.println("<td align='center'>暂无图片<hr>选择新图片<br> <form id='f6' action='gaipicture' method='post' enctype='multipart/form-data'><input name='storage' id='storage'type='hidden'><input name='info' id='info'type='hidden'><input name='name' id='name'type='hidden'><input name='price' id='price'type='hidden'><input name='id' value="+gd.getGd_number()+" type='hidden'><input type='file' id='newphoto' name='uploadphoto'></from></td>");
		 }else{
		 out.println("<td align='center'><img src='/springMVC/jsp/cartadmin/upload/"+gd.getPhotoFileName() +"' border=0 height=60 width=60><br><button type='button' onclick=''>删除图片</button><hr>选择新图片<br><form id='f6' action='gaipicture' method='post' enctype='multipart/form-data'><input name='storage' id='storage'type='hidden'><input name='info' id='info'type='hidden'><input name='name' id='name'type='hidden'><input name='price' id='price'type='hidden'><input name='id' value="+gd.getGd_number()+" type='hidden'><input type='file' id='newphoto' name='uploadphoto'></from></td>");
		 }//<br>  <form id='f6' action='gaipicture' method='post' enctype='multipart/form-data'><input name='id' value="+gd.getGd_number()+" type='hidden'><input type='file' id='newphoto' name='uploadphoto'></from>
		out.println("<td><input id='q1' type=text value='"+ gd.getGd_name() + "'></td>");   //名称
		out.println("<td><input id='q2' type=text value='"+ gd.getGd_price() + "' onchange='updatePrice(this.value)'></td>"); 	//价格
		out.println("<td><input id='q3' type=text value='"+ gd.getGd_info() + "'></td>");	//简介
		out.println("<td><input id='q4' type=text value='"+ gd.getGd_storage() + "'  onchange='updateStorage(this.value)'></td>");//库存
		out.println("<td align=center>    <button type='button'  onclick='sub()'>确定 </button>&nbsp &nbsp <button type='button' onclick='refresh()'> 取消 </button></td><td><button type='button' onclick=\"xiajia("+gd.getGd_number()+",'"+gd.getGd_name()+"')\">下架</button></td>");		
		out.print("</tr>");
		}else{	
		 out.print("<tr>");
				 if(gd.getPhotoFileName()==null){out.println(
		 "<td align='center'>暂无图片</td>");
		 }else{
			
		 out.println("<td align='center'><img src='/springMVC/jsp/cartadmin/upload/"+gd.getPhotoFileName() +"' border=0 height=60 width=60></td>");
		 }
		out.println("<td>"+gd.getGd_name()+"</td>");   //名称
		out.println("<td>"+gd.getGd_price()+"</td>");	//价格
		out.println("<td>"+gd.getGd_info()+"</td>");	//简介
		out.println("<td>"+gd.getGd_storage()+"</td>"); //库存
		out.println("<td align=center colspan=2><a href='admin?num="+gd.getGd_number()+"'>编辑</a></td>");	
		out.print("</tr>");	
		}
		}else{	
				 if(gd.getPhotoFileName()==null){out.println(
		 "<td align='center'>暂无图片</td>");
		 }else{
		 out.println("<td align='center'><img src='/springMVC/jsp/cartadmin/upload/"+gd.getPhotoFileName() +"' border=0 height=60 width=60></td>");
		 }
		out.println("<td>"+gd.getGd_name()+"</td>");   //名称
		out.println("<td>"+gd.getGd_price()+"</td>");	//价格
		out.println("<td>"+gd.getGd_info()+"</td>");	//简介
		out.println("<td>"+gd.getGd_storage()+"</td>"); //库存
		out.println("<td align=center  colspan=2><a href='admin?num="+gd.getGd_number()+"'>编辑</a></td>");	 
		out.print("</tr>");	
		}
		}
		String add=request.getParameter("add");
		if(add!=null&&add.equals("addgood")){
		out.print("<tr bgcolor='#FFE4E1'><form id='addgood1' action='addgood' method='post' enctype='multipart/form-data'>");
		out.println("<td>图片<br><input name='uploadphoto'  type='file'></td>");   //名称
		out.println("<td>名称<br><input id='addname' name='gd_name'></td>");   //名称
		out.println("<td>价格<br><input id='addprice' name='gd_price' onchange='updatePrice1(this.value)'></td>");	//价格
		out.println("<td>简介<br><input name='gd_info'></td>");	//简介
		out.println("<td>库存<br><input id='addstorage' name='gd_storage' onchange='updateStorage1(this.value)'></td>"); 		//库存
		out.println("<td><button type='button' onclick='addgood()'>添加商品</button></td><td><button type='button' onclick='refresh()'> 取消 </button></td></tr>");
		}else{
		out.println("<tr><td colspan=7><a href='admin?add=addgood'>添加商品</a><br></td></tr>");
		out.println("<tr>");
		}
		
		
		 %>
<!-- 		<tr>
		<td colspan=6>
		<hr>
		<a href="admin?add=addgood">添加商品</a>
		<button type="button" onclick='admin?add=addgood'>添加商品</button>
		<br>
		</td>
		</tr> -->
</table>
<br><br>


</body>
</html>
