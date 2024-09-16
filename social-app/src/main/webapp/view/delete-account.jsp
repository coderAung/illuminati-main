<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Setting</title>

<!-- Include the CSS file dynamically based on the selected theme -->
<c:choose>
	<c:when test="${sessionScope.theme == 'dark'}">
		<link rel="stylesheet" href="/resource/css/dark.css">
	</c:when>
	<c:when test="${sessionScope.theme == 'light'}">
		<link rel="stylesheet" href="/resource/css/light.css">
	</c:when>
	<c:when test="${sessionScope.theme == 'blue'}">
		<link rel="stylesheet" href="/resource/css/blue.css">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" href="/resource/css/default.css">
		<!-- Fallback in case no theme is selected -->
	</c:otherwise>
</c:choose>
</head>

<jsp:include page="/resource/style.jsp"></jsp:include>

<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="settingActive" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">

			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="settingActive" value="menu-item-active" />
				</jsp:include>
			</div>

			<!-- main content start -->
			<div class="col-6 px-0 txt-white">

				<div class="px-3">
					<div class="alert alert-warning">
						<i class="fa-solid fa-triangle-exclamation"></i> <span>Your
							are deleting the account permanently !</span>
					</div>

					<c:url var="profileDelete" value="/profile/delete"></c:url>
					<form action="${profileDelete}" method="post" class="rounded mb-3 bg-card px-3 py-3">
						<h5>Confirm Password to Proceed</h5>
						<div class="d-flex">
							<div class="search-bar w-75 txt-text bg-card-2 py-2 px-3 rounded">
								<input class="w-100" type="password" name="password"
									placeholder="Confirm Password" />
							</div>
							<div class="flex-fill mx-3">
								<button class="btn btn-danger"><i class="fa-solid fa-arrow-right"></i> Proceed</button>
							</div>
						</div>
					</form>
					<div class="rounded bg-card-2 p-3 text-center txt-grey">
						<p>Your action cannot be undone</p>
					</div>
				</div>

			</div>
			<!-- main content end -->

			<c:if test="${loginUser.role.name() eq 'ADMIN'}">
				<div class="col-2 mx-auto right-side px-0">
					<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
				</div>
			</c:if>
		</div>

		<jsp:include page="/component/modal/change-password.jsp"></jsp:include>
		<jsp:include page="/component/modal/logout.jsp"></jsp:include>

	</main>
	<!-- main content end -->
</body>
</html>