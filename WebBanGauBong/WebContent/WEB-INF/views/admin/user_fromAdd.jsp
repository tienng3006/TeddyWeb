
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>User Details</h2>
						<div>
							<p style="text-align: center; color: red">
								<u>${failAddUser}</u>
							</p>
							<p style="text-align: center; color: red">
								<u>${successAddUser}</u>
							</p>
							<p style="text-align: center; color: red">
								<u>${errorsPass}</u>
							</p>

						</div>
						<div class="col-md-12 compose-right">
							<div class="inbox-details-default">
								<div class="inbox-details-heading">Add user</div>
								<div class="inbox-details-body">
									<div class="alert alert-info"
										style="text-align: center; color: white">
										<u>Hãy điền đầy đủ thông tin bên dưới:</u>
									</div>
									<form:form method="post" modelAttribute="userr" class="com-mail"
										action="${pageContext.request.contextPath}/admin/user/add.htm">
										<br>
										<label>UserName:</label>
										<form:input path="username" />
										<label>Họ và tên:</label>
										<form:input path="name" />
										<label>Giới tính:</label>
										<form:radiobutton path="gender" value="true" label="Nam" />
										<form:radiobutton path="gender" value="false" label="Nữ" />
										<br>
										<label>Password:</label><br>
										<form:password path="password" />
										<br>
										<label>Confirm password:</label><br>
										<input type="password" name="repassword" />
										<br><label>User Role:</label>
										<form:radiobutton path="userRole" value="client" label="Client" />
										<form:radiobutton path="userRole" value="admin" label="Admin" />
										<br><label>Email:</label>
										<form:input path="email" />
										<label>Phone:</label>
										<form:input path="phone" />
										<label>Address:</label>
										<form:input path="address" />
										<br>
										<input type="submit" value="Save">
									</form:form>
								</div>
							</div>
						</div>

						<div class="clearfix"></div>
					</div>
				</div>

				<jsp:include page="footer.jsp"></jsp:include>

			</div>

		</div>

		<jsp:include page="navigation.jsp"></jsp:include>

	</div>

</body>
</html>
