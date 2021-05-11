
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<!DOCTYPE html>
<html>
	<head>
		<style>
		.img{
		width:204px;
		height:177px 
		</style>

		<meta charset="utf-8">
		<base href="${pageContext.servletContext.contextPath}/">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
        
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <link href="<c:url value='/resources/admin/css/bootstrap.css'/>" rel="stylesheet" type="text/css"/>
        <!-- Custom Theme files -->
        <link href="<c:url value='/resources/admin/css/style.css'/>" rel="stylesheet" type="text/css"/>
        <!--icons-css-->
        <link href="<c:url value='/resources/admin/css/font-awesome.css'/>" rel="stylesheet" type="text/css"/> 
		<!--js-->
		<script src="<c:url value='/resources/admin/js/jquery-2.1.1.min.js'/>"></script> 
        <link href='//fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
		<link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
    </head>
    <body>

        <div class="login-page">
            <div class="login-main">  	
                <div class="login-head">
                    <h1>Login</h1>
                </div>
                <br>
                <br>
                <p style="text-align:center; color:red" >${errorLogin}</p>
                <p style="text-align:center; color:red" >${msgSu}</p>
                <div class="login-block">
                    <form:form method="post" action="${pageContext.servletContext.contextPath}/login.htm" modelAttribute="user">
                     
                        <p>Username:</p>
                        <form:input path="username"/>
                        <p>Password:</p>
                        <form:password path="password"/>
                        <div class="forgot-top-grids">
                            <div class="forgot">
                                <a href="forgotPassword.htm">Forgot password?</a>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                        <input type="submit" name="Sign In" value="Login">	
						<h3><i>Not a member?</i><a href="client/register.htm"> Sign up now</a></h3>				
                        <h2>or login with</h2>
                        <div class="login-icons">
                            <ul>
                                <li><a href="#" class="facebook"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#" class="twitter"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#" class="google"><i class="fa fa-google-plus"></i></a></li>						
                            </ul>
                        </div>
                    </form:form>
					<h5><a href="trang-chu.htm">Go Back to Home</a></h5>
                </div>
            </div>
        </div>
        <!--inner block end here-->
        <!--copy rights start here-->
       

<!--scrolling js-->
		<script src="<c:url value='/resources/admin/js/jquery.nicescroll.js'/>"></script>
		<script src="<c:url value='/resources/admin/js/scripts.js'/>"></script>
		<!--//scrolling js-->
		<script src="<c:url value='/resources/admin/js/bootstrap.js'/>"> </script>
    </body>
</html>
