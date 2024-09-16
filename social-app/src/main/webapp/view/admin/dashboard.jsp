<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Dashboard</title>
<jsp:include page="/resource/style.jsp"></jsp:include>
</head>
<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="homeActive" />
	</jsp:include>
	<!-- nav bar end -->
	<!-- main content start -->
	<main>
		<div class="container-fluid row">

			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="homeActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- side bar end -->

			<div class="col-6 px-0">
				<div class="row justify-content-evenly mt-3">
					<div
						class="pointer col-lg-3 col-sm-9 col-md-8 bg-card txt-text text-center p-3 rounded shadow mb-3">
						<h4>Users</h4>
						<div class="fs-5 txt-grey mb-2">Total</div>
						<div class="rounded bg-card-2 py-3 fs-4 fw-bold color-app">${userCount}</div>
					</div>
					<div
						class="pointer col-lg-3 col-sm-9 col-md-8 bg-card txt-text text-center p-3 rounded shadow mb-3">
						<h4>Suspended</h4>
						<div class="fs-5 txt-grey mb-2">Total</div>
						<div class="rounded bg-card-2 py-3 fs-4 fw-bold text-danger" id="suspended-count" count="${suspendedUserCount}">${suspendedUserCount}</div>
					</div>
					<div
						class="pointer col-lg-3 col-sm-9 col-md-8 bg-card txt-text text-center p-3 rounded shadow mb-3">
						<h4>Posts</h4>
						<div class="fs-5 txt-grey mb-2">Total</div>
						<div class="rounded bg-card-2 py-3 fs-4 fw-bold color-app">${postCount}</div>
					</div>
				</div>

				<div class="mb-2 txt-text">
					<div class="fs-5 fw-bold" id="users">All Users</div>
				</div>

				<div class="py-3 mb-3 txt-text" id="user-container">
				
				<c:url var="activateUrl" value="/api/admin/user/activate"></c:url>
				<span id="activate-url" class="d-none" url="${activateUrl}"></span>
				<c:url var="suspendUrl" value="/api/admin/user/suspend"></c:url>
				<span id="suspend-url" class="d-none" url="${suspendUrl}"></span>
				
					<c:forEach var="view" items="${userManagedViews}">
						<div id="view-${view.userId}" class="mb-2 d-flex">
							<div
								class="w-75 bg-card rounded px-2 py-2 d-flex align-items-center">

								<img class="rounded-circle mx-3"
									src="${view.profileImage}"
									alt="profile" width="50px" height="50px"
									style="object-fit: cover" />

								<div>${view.userName}</div>
								<div class="flex-fill d-flex justify-content-end">
									<c:choose>
										<c:when test="${view.postCount eq 1}">
											<div class="btn btn-sm btn-success me-2">${view.postCount}
												post</div>
										</c:when>
										<c:when test="${view.postCount gt 1}">
											<div class="btn btn-sm btn-success me-2">${view.postCount}
												post</div>
										</c:when>
										<c:otherwise>
											<div class="btn btn-sm btn-success me-2">${view.postCount}
												post</div>
										</c:otherwise>
									</c:choose>
									<c:url var="otherProfile" value="/other/profile">
										<c:param name="userId" value="${view.userId}"></c:param>
									</c:url>
									<a href="${otherProfile}" class="btn btn-sm btn-app me-2 text-decoration-none">View</a>
								</div>
							</div>
							<div
								class="flex-fill ms-2 d-flex align-items-center justify-content-end rounded">

								<div class="btn-group w-100">
									<c:choose>
										<c:when test="${'ACTIVE' eq view.status.name()}">
											<button type="button" class="btn btn-sm status btn-primary py-1">Active</button>
											<button type="button"
												class="btn btn-sm btn-primary status dropdown-toggle dropdown-toggle-split"
												data-bs-toggle="dropdown" aria-expanded="false">
												<span class="visually-hidden">Toggle Dropdown</span>
											</button>
											<ul class="dropdown-menu bg-card">
												<li><div userId="${view.userId}"
													class="pointer suspend-btn text-danger hover-item d-block w-100 text-decoration-none px-2 py-1"
													>Suspend</div></li>
											</ul>
										</c:when>
										<c:when test="${'SUSPENDED' eq view.status.name() }">
											<button type="button" class="btn btn-sm status btn-outline-danger txt-text">Suspended</button>
											<button type="button"
												class="btn btn-sm btn-danger status dropdown-toggle dropdown-toggle-split"
												data-bs-toggle="dropdown" aria-expanded="false">
												<span class="visually-hidden">Toggle Drop down</span>
											</button>
											<ul class="dropdown-menu bg-card px-2">
												<li><div userId="${view.userId}"
													class="pointer activate-btn txt-text hover-item d-block w-100 text-decoration-none rounded px-2 py-1"
													>Activate</div></li>
											</ul>
										</c:when>
									</c:choose>
								</div>


							</div>
						</div>
					</c:forEach>
				</div>

			</div>

			<c:if test="${loginUser.role.name() eq 'ADMIN'}">
				<div class="col-2 mx-auto right-side px-0">
					<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
				</div>
			</c:if>
		</div>

	</main>
	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="manageUser" value="/resource/ajax/manage-user.js"></c:url>
	<script type="text/javascript" src="${manageUser}"></script>
</body>
</html>