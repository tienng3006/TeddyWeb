
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
<title>Edit profile</title>

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
				<h1>Chỉnh sửa thông tin tài khoản</h1>
			</div>
			<br> <br>
			<p style="text-align: center; color: red">${successEditUser}</p>
			<p style="text-align: center; color: red">${failEditUser}</p>
			<div class="login-block">

				<form:form method="post"
					action="${pageContext.servletContext.contextPath}/client/account/edit/${uId}.htm"
					modelAttribute="u">

					<p style="color: blue"><u>Tài khoản: ${us.username}</u></p>
					<br>

					<p>Họ và tên:</p>
					<form:input path="name" placeholder="${us.name}" />

					<p>Giới tính:</p>
					<form:radiobutton path="gender" value="true" label="Nam" />
					<form:radiobutton path="gender" value="false" label="Nữ" />
					<br>

					<p>Email:</p>
					<form:input path="email" placeholder="${us.email}" />

					<p>Số điện thoại:</p>
					<form:input path="phone" placeholder="${us.phone}" />

					<p>Địa chỉ:</p>
					<form:input path="address" placeholder="${us.address}" />
					<br>


					<div class="forgot-top-grids">

						<div class="clearfix"></div>
					</div>
					<input type="submit" name="forget " value="Xác nhận">

				</form:form>
				
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
