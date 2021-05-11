
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<style>
.img {
	width: 150px;
	height: 100px
}
</style>
<!-- <base href="${pageContext.servletContext.contextPath}/">  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Quản lý sản phẩm</h2>
						<div class="col-md-12 mailbox-content  tab-content tab-content-in">
							<div class="tab-pane active text-style" id="tab1">
								<div class="mailbox-border">
									<div class="mail-toolbar clearfix">
										<div class="float-left">
											<div >
												<a href="${pageContext.request.contextPath}/admin/product/add.htm"><u style="color: red"><i>Thêm sản phẩm</i></u></a>
											</div>
											<div class="clearfix"></div>
										</div>
										<div style="text-align: right; color: green">
											<u>${failRemove}</u> <u>${failRemovee}</u> <u>${successRemove}</u>
											<u>${successEdit}</u> <u>${failEdit}</u> <u>${msgEmpty}</u> <u>${failFile}</u>
										</div>
										<div class="float-right">
											<span class="text-muted m-r-sm">Pages </span>
											<div class="btn-group">
												<a class="btn btn-default"><i class="fa fa-angle-left"></i></a>
												<c:forEach var="i" begin="0" end="${totalItem}">
													<a class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/product/list/${i+1}.htm"><c:out
															value="${i+1}" /></a>
												</c:forEach>
												<a class="btn btn-default"><i class="fa fa-angle-right"></i></a>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
									<table class="table tab-border">
										<tbody>
											<tr>
												<td class="hidden-xs" style="width: 180px;"><b>Product
														Name</b></td>
												<td class="hidden-xs" style="width: 100px;"><b>Category</b>
												</td>
												<td style="text-align: left; width: 80px;"><b>Price</b>
												</td>
												<td style="text-align: center; width: 50px;"><b>Quantity</b>
												</td>
												<td style="text-align: center; width: 200px;"><b>Image</b>
												</td>
												<td style="text-align: right; width: 150px;"><b>Created</b>
												</td>
												<td style="text-align: center; width: 150px;"><b>Option</b>
												</td>
											</tr>
											<c:forEach var="item" items="${listProduct}">
												<tr class="unread checked">
													<td class="hidden-xs" style="width: 180px;">${item.name}</td>
													<td class="hidden-xs" style="width: 100px;">
														${item.category.name}</td>
													<td style="text-align: left; width: 80px;">
														$${item.price}</td>
													<td style="text-align: center; width: 50px;">
														${item.quantity}</td>
													<td style="text-align: right; width: 200px;"><img
														class="img" src="${pageContext.request.contextPath}/resources/client/images/${item.photo}"
														alt="" /></td>
													<td style="text-align: right; width: 150px;">
														${item.created}</td>
													<td style="text-align: center; width: 150px;"><a
														class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/product/edit/${item.id}.htm"><i
															class="fa fa-edit"></i></a> <a class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/product/remove/${item.id}.htm"><i
															class="fa fa-remove"></i></a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
