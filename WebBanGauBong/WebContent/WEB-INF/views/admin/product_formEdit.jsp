
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
<title>Chỉnh sửa sản phẩm</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Chỉnh Sửa Sản Phẩm</h2>
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
						<div class="col-md-12 compose-right">
							<div class="inbox-details-default">
								<div class="inbox-details-heading">Edit</div>
								<div class="inbox-details-body">
									<div class="alert alert-info"
										style="text-align: center; color: white">
										<u>Hãy điền đầy đủ thông tin bên dưới </u>
									</div>
									<form:form action="${pageContext.request.contextPath}/admin/product/edit.htm" method="post"
										modelAttribute="product" class="com-mail"
										enctype="multipart/form-data">
										<br>
										<label>Id sản phẩm: ${productId} </label>
										<form:hidden path="id" value="${productId}"/>
										<br>
										<label>Name:</label>
										<form:input path="name" placeholder="${p.name}" />
										<label>Category:</label>
										<br>
										<form:select path="category.id" items="${listCategory}"
											itemLabel="name" itemValue="id" />
										
										<br>
										<label>Price:</label>
										<form:input path="price" placeholder="${p.price}" />
										<label>Quantity:</label>
										<form:input path="quantity" placeholder="${p.quantity}" />
										<label>Discount:</label>
										<form:input path="discount" placeholder="${p.discount}" />
										<label>Image:</label>
										<div class="form-group">
											<div class="btn btn-default btn-file">
												<i class="fa fa-paperclip"> </i> Image <input type="file"
													name="attachment">
											</div>
										</div>
										<label>Description:</label>
										<form:textarea path="des" rows="3" placeholder="${p.des}" />
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
