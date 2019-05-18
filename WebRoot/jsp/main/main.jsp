<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>Welcome</title>


		
	</head>
	
	<frameset rows="12%,*">    <!--把页面分成上下两部分，上部分占15%，下部分*表示占据其余部分-->
		<frame noresize="noresize" src="/springMVC/jsp/main/Top.jsp" name="top" scrolling="no"></frame><!--上半部分要显示的页面-->
		<frameset cols="9%,*"><!--把页面分成左右两部分，左部分占15%，右部分*表示占据其余部分-->
			<frame noresize="noresize" src="/springMVC/jsp/main/left.jsp" name="left" scrolling="no"></frame><!--做半部分要显示的页面-->
			<frame noresize="noresize" src="/springMVC/jsp/main/right.jsp" name="right" id="right"></frame><!--右半部分要显示的页面-->
		</frameset>
	</frameset>
<body>
	</body>
	
</html>

