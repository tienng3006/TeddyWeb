
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
<title>Category List</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Quản lý Loại hàng</h2>
						<div class="col-md-12 mailbox-content  tab-content tab-content-in">
							<div class="tab-pane active text-style" id="tab1">
								<div class="mailbox-border">
									<div class="mail-toolbar clearfix">
										<div class="float-left">
											<div class="btn btn_1 btn-default mrg5R">
												<a
													href="${pageContext.request.contextPath}/admin/category/add.htm"><i
													class="fa fa-plus"> </i></a>
											</div>
											<div class="clearfix"></div>
										</div>
										<div style="text-align: right; color: green">
											<u>${failRemoveCate}</u> <u>${failRemoveecate}</u> <u>${successRemoveCate}</u>
											<u>${successEditCate}</u> <u>${failEditCate}</u>
										</div>

									</div>
									<table class="table tab-border">
										<tbody>
											<tr>
												<td class="hidden-xs" style="width: 250px;"><b><u>Mã loại</u></b></td>
												<td class="hidden-xs" style="width: 250px;"><b><u>Tên</u></b></td>
												<td style="text-align: center; width: 200px;"><b><u>Option</u></b>
												</td>
											</tr>
											<c:forEach var="item" items="${listCategory}">
												<tr class="unread checked">
													<td class="hidden-xs" style="width: 250px;">${item.id}</td>
													<td class="hidden-xs" style="width: 250px;">
														${item.name}</td>
													<td style="text-align: center; width: 200px;"><a
														class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/category/edit/${item.id}.htm"><i
															class="fa fa-edit"></i></a> <a class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/category/remove/${item.id}.htm"><i
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
