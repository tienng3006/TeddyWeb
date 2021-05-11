
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
.image {
	width: 100px;
	height: 80px
}
</style>
<base href="${pageContext.servletContext.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chỉnh sửa Đơn hàng</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Chỉnh Sửa trạng thái Đơn hàng</h2>
						<!-- 
						<div>
						
							<p style="text-align: center; color: green">
								<u>${successEdit}</u>
							</p>

							<p style="text-align: center; color: green">
								<u>${failFile}</u>
							</p>

							<p style="text-align: center; color: green">
								<u>${failEdit}</u>
							</p>

						</div>
						 -->
						<div class="col-md-12 compose-right">
							<div class="inbox-details-default">
								<div class="inbox-details-heading">Edit</div>
								<div class="inbox-details-body">
									<div class="alert alert-info"
										style="text-align: center; color: white">
										<u>Cập nhật trạng thái mới của đơn hàng </u>
									</div>
									<form:form
										action="${pageContext.request.contextPath}/admin/order/edit/${oid}.htm" modelAttribute="order" class="com-mail">
										<br>
										<label>Id Đơn hàng: ${oid}</label>
										<!-- 
										<form:input path="id" value="${oid}" />
										<br>
										<label>Id User:${o.user.id}</label>
										<form:input path="user.id" />
										<br>
										<label>Ngày đặt hàng:${o.created}</label>
										<form:hidden path="created" value="${o.created}" />
										-->
										<br>
										<label>Trạng thái đơn hàng:</label>
										<br>
										<form:select path="status.id" items="${listStatus}"
											itemLabel="name" itemValue="id" />
										 
										<br>

										<input type="submit" value="Save" />

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
