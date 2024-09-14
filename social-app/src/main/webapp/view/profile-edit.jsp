<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Profile</title>
<jsp:include page="/resource/style.jsp"></jsp:include>
</head>
<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="profileActive" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">
			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="profileActive" value="menu-item-active" />
				</jsp:include>
			</div>

			<div class="col-6 px-0 txt-white">
				<h4 class="mb-3">Editing ${loginUser.name}'s Info</h4>
				<div class="bg-card p-3 rounded">
					
				</div>
			</div>
		</div>
	</main>
</body>
</html>
