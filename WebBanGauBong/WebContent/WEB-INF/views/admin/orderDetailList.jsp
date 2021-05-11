
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<style>
.img {
	width: 250px;
	height: 180px
}
</style>
<!-- <base href="${pageContext.servletContext.contextPath}/">  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chi tiết đơn hàng</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Mã số hóa đơn: ${id}</h2>
						<div class="col-md-12 mailbox-content  tab-content tab-content-in">
							<div class="tab-pane active text-style" id="tab1">
								<div class="mailbox-border">
									<div class="mail-toolbar clearfix"></div>





									<c:forEach var="item" items="${listOrderD}" varStatus="status" >
							
							<h3 style="color: blue"><u>Sản phẩm thứ ${status.index+1}:</u></h3> <br>
										<ul>
											<li><u>Id sản phẩm:</u> ${item.product.id}</li>
											<li><u>Tên sản phẩm:</u> ${item.product.name}</li>
											<li><u>Số lượng đặt hàng:</u> ${item.quantity}</li>
											<li><u>Loại:</u> ${item.product.category.name}</li>
											<li><u>Giá:</u> $${item.product.price}</li>
											<img class="img"
												src="${pageContext.request.contextPath}/resources/client/images/${item.product.photo}"
												alt="" />
										</ul> <br>

									</c:forEach>



<br><br>
									
						<a href="${pageContext.request.contextPath}/admin/order/list.htm"><button> <u>Quay lại</u></button></a>




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
