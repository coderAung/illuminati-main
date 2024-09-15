<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Search</title>
<jsp:include page="/resource/style.jsp"></jsp:include>
</head>
<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp"></jsp:include>
	<!-- nav bar end -->
	<main>
		<div class="container-fluid row">

			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp"></jsp:include>
			</div>
			<!-- side bar end -->
		</div>


		<c:if test="${loginUser.role.name() eq 'ADMIN'}">
			<div class="col-2 mx-auto px-0">
				<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
			</div>
		</c:if>
	</main>

</body>
</html>