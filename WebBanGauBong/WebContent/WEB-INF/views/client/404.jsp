
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>404 Error Page</title>
 <link href="<c:url value='/resources/admin/css/bootstrap.css'/>" rel='stylesheet' type='text/css'/>
        <!-- Custom Theme files -->
        <link href="<c:url value='/resources/admin/css/style.css'/>" rel='stylesheet' type='text/css'/>
        <!--js-->
        <script src="<c:url value='/resources/admin/js/jquery-2.1.1.min.js' />"></script>
        <!--icons-css-->
        <link href="<c:url value='/resources/admin/css/font-awesome.css'/>" rel="stylesheet" type="text/css"/> 
        <!--Google Fonts-->
        <link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
       
</head>
<body>
	<div class="inner-block">
    <div class="error-404">  	
    	<div class="error-page-left">
    		<img src="${pageContext.request.contextPath}/resources/client/images/404.png" alt="">
    	</div>
    	<div class="error-right">
	    	<h2>Oops! Page Not Open</h2>
	    	<h4>Nothing Is Found Here!!</h4>
	    	<a href="${pageContext.request.contextPath}/trang-chu.htm">Go Back</a>
    	</div>
    </div>
</div>
</body>
</html>