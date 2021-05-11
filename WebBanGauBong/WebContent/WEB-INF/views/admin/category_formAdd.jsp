
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thêm loại hàng</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Category Details</h2>
						<div>
							

							<p style="text-align: center; color: red">
								<u>${failAddCate}</u>
							</p>
							

							<p style="text-align: center; color: red">
								<u>${successAddCate}</u>
							</p>

						</div>
						<div class="col-md-12 compose-right">
							<div class="inbox-details-default">
								<div class="inbox-details-heading">Thêm loại hàng</div>
								<div class="inbox-details-body">
									<div class="alert alert-info"
										style="text-align: center; color: white">
										<u>Hãy điền đầy đủ thông tin bên dưới</u>
									</div>
									<form:form method="post" modelAttribute="cate"
										class="com-mail" action="${pageContext.request.contextPath}/admin/category/add.htm">
										<br>
										<label>Mã loại:</label>
										<form:input path="id" />
										<label>Tên:</label>
										<form:input path="name" />
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
