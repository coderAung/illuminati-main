<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Home</title>

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
			<div class="col-6 px-0" id="post-container">
			
				<div class="rounded h-75 w-100 d-flex flex-column align-items-center justify-content-center bg-card-2">
					<h3 class="txt-grey mb-3">You have no access to this page</h3>
					<i class="fa-solid fa-skull-crossbones fa-7x text-danger"></i>
				</div>
			
			</div>
		
			<c:if test="${loginUser.role.name() eq 'ADMIN'}">
				<div class="col-2 mx-auto px-0">
					<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
				</div>
			</c:if>
		
		</div>
	</main>
	<!-- main content end -->

</body>
</html>