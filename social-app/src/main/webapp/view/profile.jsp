<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Illuminati | Profile</title>

<jsp:include page="/resource/style.jsp"></jsp:include>

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">

</head>
<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="profileActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main part start -->
	<main>
		<div class="container-fluid row">
			
			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param value="menu-item-active" name="profileActive" />
				</jsp:include>
			</div>
			
			
			<div class="col-6 px-0">

				<!-- Profile Card -->
				<jsp:include page="/component/profile-card.jsp"></jsp:include>
				<!-- Profile Card End -->

				<!-- Friend Preview start -->
				<jsp:include page="/component/friend-preview.jsp"></jsp:include>
				<!-- Friend Preview end -->

				<!-- create post start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- create post end -->

				<!-- posts start -->
				<jsp:include page="/component/posts.jsp"></jsp:include>
				<!-- posts end -->
			</div>
		</div>
	</main>
	<!-- main part end -->
</body>
</html>