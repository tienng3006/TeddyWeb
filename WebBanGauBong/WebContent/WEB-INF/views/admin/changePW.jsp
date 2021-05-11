
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<style>
.img {
	width: 204px;
	height: 177px
}
</style>

<meta charset="utf-8">
<base href="${pageContext.servletContext.contextPath}/">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đổi mật khẩu</title>

<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link href="<c:url value='/resources/admin/css/bootstrap.css'/>"
	rel="stylesheet" type="text/css" />
<!-- Custom Theme files -->
<link href="<c:url value='/resources/admin/css/style.css'/>"
	rel="stylesheet" type="text/css" />
<!--icons-css-->
<link href="<c:url value='/resources/admin/css/font-awesome.css'/>"
	rel="stylesheet" type="text/css" />
<!--js-->
<script src="<c:url value='/resources/admin/js/jquery-2.1.1.min.js'/>"></script>
<link href='//fonts.googleapis.com/css?family=Carrois+Gothic'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Work+Sans:400,500,600'
	rel='stylesheet' type='text/css'>
</head>
<body>

	<div class="login-page">
		<div class="login-main">
			<div class="login-head">
				<h1>Đổi mật khẩu</h1>
			</div>
			<br> <br>
			<p style="text-align: center; color: red">${failOldPass}</p>
			<p style="text-align: center; color: red">${failConfirmPass}</p>
			
			<p style="text-align: center; color: red">${successChange}</p>
			<p style="text-align: center; color: red">${failChange}</p>
			<div class="login-block">

				<form action="client/account/changePW.htm" method="post">

					<p>Mật khẩu cũ:</p>
					<input type="password" name="passOld" />
					<p>Mật khẩu mới:</p>
					<input type="password" name="pass" />
					<p>Xác nhận mật khẩu:</p>
					<input type="password" name="confirm" />

					<div class="forgot-top-grids">

						<div class="clearfix"></div>
					</div>
					<input type="submit" name="change" value="Xác nhận">

				</form>
				<br><br>
				<a href="client/account.htm">
				<input type="submit" name="back" value="Hủy bỏ">
				</a>
				
				<h5>
					<a href="trang-chu.htm">Go Back to Home</a>
				</h5>
			</div>
		</div>
	</div>
	<!--inner block end here-->
	<!--copy rights start here-->


	<!--scrolling js-->
	<script src="<c:url value='/resources/admin/js/jquery.nicescroll.js'/>"></script>
	<script src="<c:url value='/resources/admin/js/scripts.js'/>"></script>
	<!--//scrolling js-->
	<script src="<c:url value='/resources/admin/js/bootstrap.js'/>">
		
	</script>
</body>
</html>
