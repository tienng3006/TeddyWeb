
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
<title>User List</title>
</head>
<body>

	<div class="page-container">

		<div class="left-content">

			<div class="mother-grid-inner">

				<jsp:include page="header.jsp"></jsp:include>

				<div class="inner-block">
					<div class="inbox">
						<h2>Quản lý người dùng</h2>
						<div class="col-md-12 mailbox-content  tab-content tab-content-in">
							<div class="tab-pane active text-style" id="tab1">
								<div class="mailbox-border">
									<div class="mail-toolbar clearfix">
										<div class="float-left">
											<div class="btn btn_1 btn-default mrg5R">
												<a
													href="${pageContext.request.contextPath}/admin/user/add.htm"><i
													class="fa fa-plus"> </i></a>
											</div>
											<div class="clearfix"></div>
										</div>
										<div style="text-align: right; color: red">
											<u>${failRemoveUser}</u> <u>${failRemoveeUser}</u> <u>${successRemoveUser}</u>

										</div>
										<div class="float-right">
											<span class="text-muted m-r-sm">Pages </span>
											<div class="btn-group">
												<a class="btn btn-default"><i class="fa fa-angle-left"></i></a>
												<c:forEach var="i" begin="0" end="${totalUser/10}">
													<a class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/user.htm"><c:out
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
												<th class="hidden-xs" style="width: 80px;"><b>ID </b></th>

												<th class="hidden-xs" style="width: 180px;"><b>UserName</b></th>
												<th style="width: 80px; text-align: center"><b>Gender</b></th>
												<th style="text-align: center; width: 150px;"><b>Email</b>
												</th>
												<th style="text-align: center; width: 100px;"><b>Phone</b>
												</th>
												<th style="text-align: center; width: 200px;"><b>Address</b>
												</th>
												<th style="text-align: center; width: 130px;"><b>Created</b>
												</th>
												<th style="text-align: center; width: 100px;"><b>User
														Role</b></th>
												<th style="text-align: center; width: 150px;"><b>Option</b>
												</th>
											</tr>
											<c:forEach var="item" items="${listUser}">
												<tr class="unread checked">
													<td class="hidden-xs" style="width: 80px;">${item.id}</td>
													<td class="hidden-xs" style="width: 180px;">
														${item.username}</td>
													<td style="width: 80px; text-align: center;">${item.gender?'Nam':'Nữ'}</td>
													<td style="text-align: left; width: 150px;">
														${item.email}</td>
													<td style="text-align: center; width: 100px;">
														${item.phone}</td>
													<td style="text-align: center; width: 200px;">${item.address}</td>
													</td>
													<td style="text-align: center; width: 130px;">
														${item.created}</td>
													<td style="text-align: center; width: 100px;">
														${item.userRole}</td>
													<td style="text-align: center; width: 150px;"><a
														class="btn btn-default"
														href="${pageContext.request.contextPath}/admin/user/remove/${item.id}.htm"><i
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
