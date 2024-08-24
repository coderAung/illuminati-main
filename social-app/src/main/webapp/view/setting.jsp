<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Setting</title>
</head>
<jsp:include page="/resource/style.jsp"></jsp:include>

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">
<body class="bg-black">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="settingActive" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">

			<div class="col-3 left-side mx-3">
				<!-- sidebar start -->
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="settingActive" value="menu-item-active" />
				</jsp:include>
			</div>

			<div class="col-6 px-0 txt-white">
					<h4 class="mb-3">Setting</h4>
					<div class="bg-card setting-div rounded p-3 mb-3">
						<span>Profile Detail</span>
					</div>
					<div class="bg-card setting-div rounded p-3 mb-3 d-flex align-items-center justify-content-between">
						<span>Password</span>
						<button type="button" data-bs-toggle="modal" data-bs-target="#change-password" class="btn change-password-btn shadow">Change Password</button>
					</div>
					<div class="bg-card setting-div rounded p-3 mb-3">
						<span>About Us</span>
					</div>
					<div class="bg-card setting-div rounded p-3 d-flex align-items-center justify-content-between">
						<span>Logout</span>
						<button class="btn logout-btn shadow" data-bs-toggle="modal" data-bs-target="#logout">Logout</button>
					</div>
				</div>
			</div>
		<jsp:include page="/component/modal/change-password.jsp"></jsp:include>
		<jsp:include page="/component/modal/logout.jsp"></jsp:include>
		
	</main>
	<!-- main content end -->
</body>
</html>