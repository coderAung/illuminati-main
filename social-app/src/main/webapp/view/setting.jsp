<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Setting</title>
</head>
<jsp:include page="/resource/library/bootstrap.jsp"></jsp:include>
<jsp:include page="/resource/library/icon.jsp"></jsp:include>

<c:url var="navbarCss" value="/resource/css/nav-bar.css"></c:url>
<link href="${navbarCss}" rel="stylesheet">

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">
<body>
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="settingActive" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">

			<!-- sidebar start -->
			<jsp:include page="/component/sidebar.jsp">
				<jsp:param name="settingActive" value="menu-item-active" />
			</jsp:include>
			<!-- sidebar end -->
			<div class="col-6 px-0 color-white">
				<div class="border-blue rounded p-3">
					<h4 class="mb-3">Setting</h4>
					<div class="bg-common setting-div rounded p-3 mb-3">
						<span>Profile Detail</span>
					</div>
					<div class="bg-common setting-div rounded p-3 mb-3 d-flex align-items-center justify-content-between">
						<span>Password</span>
						<button type="button" data-bs-toggle="modal" data-bs-target="#change-password" class="btn change-password-btn">Change Password</button>
					</div>
					<div class="bg-common setting-div rounded p-3 mb-3">
						<span>About Us</span>
					</div>
					<div class="bg-common setting-div rounded p-3 d-flex align-items-center justify-content-between">
						<span>Logout</span>
						<button class="btn logout-btn">Logout</button>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/component/modal/change-password.jsp"></jsp:include>
	</main>
	<!-- main content end -->
</body>
</html>