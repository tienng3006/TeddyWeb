
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
<title>Order List</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Quản lý Đơn đặt hàng</h2>
						<div class="col-md-12 mailbox-content  tab-content tab-content-in">
							<div class="tab-pane active text-style" id="tab1">
								<div class="mailbox-border">
									<div class="mail-toolbar clearfix">
										<div class="float-left">
											
											<div class="clearfix"></div>
										</div>
										<div style="text-align: right; color: green">
											<u>${successRemoveOrder}</u> <u>${failRemoveeOrder}</u> <u>${failRemoveUser}</u>
											<u>${successEditOrder}</u> <u>${failEditOrder}</u> 
										</div>
										<div class="float-right">
											<span class="text-muted m-r-sm">Pages </span>
											<div class="btn-group">
												<a class="btn btn-default"><i class="fa fa-angle-left"></i></a>
												<c:forEach var="i" begin="0" end="${totalItem}">
													<a class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/order/list/${i+1}.htm"><c:out
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
												<td class="hidden-xs" style="width: 70px;"><b>ID</b></td>
												<td class="hidden-xs" style="width: 200px;"><b>Người đặt hàng</b>
												</td>
												<td style="text-align: center; width: 150px;"><b>Email</b>
												</td>
												<td style="text-align: center; width: 150px;"><b>Ngày đặt hàng</b>
												</td>
												<td style="text-align: center; width: 100px;"><b>Tổng tiền</b>
												</td>
												<td style="text-align: center; width: 180px;"><b>Trạng thái</b>
												</td>
												<td style="text-align: center; width: 100px;"><b>Option</b>
												</td>
											</tr>
											<c:forEach var="item" items="${listOrDetail}">
												<tr class="unread checked">
													<td class="hidden-xs" style="width: 70px;"> <a href="${pageContext.request.contextPath}/admin/order/orderDetail/${item[0]}.htm">${item[0]}</a></td>
													<td class="hidden-xs" style="width: 200px;">
														${item[1]}</td>
													<td style="text-align: left; width: 150px;">
														${item[2]}</td>
													<td style="text-align: center; width: 150px;">
														${item[3]}</td>
													<td style="text-align: center; width: 100px;">
														$${item[5]}</td>
													<td style="text-align: center; width: 180px;">
														<u style="color: red">${item[4]}</u></td>
													<td style="text-align: center; width: 100px;"><a
														class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/order/edit/${item[0]}.htm"><i
															class="fa fa-edit"></i></a> <a class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/order/remove/${item[0]}.htm"><i
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
