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
				<div class="mb-3">
					<span class="h4">Setting</span>
					<c:if test="${not empty alert}">
						<c:choose>
							<c:when test="${'DANGER' eq alert.alertType.name()}">
								<div class="alert w-auto ms-3 alert-danger text-center d-inline-block">
									${alert.message}
								</div>
							</c:when>
							<c:when test="${'INFO' eq alert.alertType.name()}">
								<div class="alert w-auto ms-3 alert-info text-center d-inline-block">
									${alert.message}
								</div>
							</c:when>
						</c:choose>
					
						<c:remove var="alert" scope="session"/>
					</c:if>
				</div>

				<!-- Profile Detail -->
				<div class="bg-card setting-div rounded p-3 mb-3 d-flex align-items-center justify-content-between">
					<span>Profile Detail</span>
					<c:url var="profile" value="/profile/detail"></c:url>
					<a href="${profile}" class="btn text-decoration-none btn-app shadow">View</a>
				</div>

				<!-- Change Password -->
				<div
					class="bg-card setting-div rounded p-3 mb-3 d-flex align-items-center justify-content-between">
					<span>Password</span>
					<button type="button" data-bs-toggle="modal"
						data-bs-target="#change-password"
						class="btn change-password-btn shadow">Change Password</button>
				</div>

				<!-- Theme Selection -->
				<!-- Theme Selection -->
				<div
					class="bg-card setting-div rounded p-3 mb-3 d-flex justify-content-between align-items-center">
					<!-- Theme Label -->
					<span class="me-auto">Theme</span>

					<!-- Theme Buttons -->
					<c:url var="changeTheme" value="/setting/change-theme"></c:url>
					<form action="${changeTheme}" method="post"
						class="d-flex gap-3">
						<!-- Light Theme Button -->
						<button type="submit" name="theme" value="light"
							class="btn theme-btn shadow-sm d-flex align-items-center"
							style="background-color: #f8f9fa;"
							onmouseover="this.style.backgroundColor='#f8f9fa'; this.style.color='#000';"
							onmouseout="this.style.backgroundColor='grey'; this.style.color='#000';">
							<i class="fas fa-sun me-2"></i> Light
						</button>

						<!-- Dark Theme Button -->
						<button type="submit" name="theme" value="dark"
							class="btn theme-btn shadow-sm d-flex align-items-center"
							style="background-color: #343a40; color: white;"
							onmouseover="this.style.backgroundColor='#495057'; this.style.color='#fff';"
							onmouseout="this.style.backgroundColor='grey'; this.style.color='#fff';">
							<i class="fas fa-moon me-2"></i> Dark
						</button>

						<!-- Blue Theme Button -->
						<button type="submit" name="theme" value="blue"
							class="btn theme-btn shadow-sm d-flex align-items-center"
							style="background-color: #007bff; color: white;"
							onmouseover="this.style.backgroundColor='#0056b3'; this.style.color='#fff';"
							onmouseout="this.style.backgroundColor='grey'; this.style.color='#fff';">
							<i class="fas fa-water me-2"></i> Blue
						</button>
					</form>
				</div>


				<!-- About Us -->
				<div
					class="bg-card setting-div rounded p-3 mb-3 d-flex align-items-center justify-content-between">
					<span>About us</span>
					<button class="btn btn-normal shadow">Go</button>
				</div>

				<!-- Logout -->
				<div
					class="bg-card setting-div rounded p-3 mb-3 d-flex align-items-center justify-content-between">
					<span>Logout</span>
					<button class="btn logout-btn shadow" data-bs-toggle="modal"
						data-bs-target="#logout">Logout</button>
				</div>


				<!-- Delete Account -->
				<div
					class="bg-card setting-div rounded p-3 d-flex align-items-center justify-content-between">
					<span>Delete Account</span>
					<c:url var="deleteAccount" value="/profile/delete"></c:url>
					<a href="${deleteAccount}" class="btn logout-btn text-decoration-none">Delete Account</a>
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