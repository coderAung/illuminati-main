<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Illuminati | Home</title>

<jsp:include page="/resource/style.jsp"></jsp:include>

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">

</head>
<body class="bg-black">

	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="homeActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main content start -->
	<main>
		<div class="container-fluid row">

			<div class="col-3 left-side mx-3">
				<!-- sidebar start -->
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="homeActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- sidebar end -->
			<div class="col-6 px-0">

				<!-- post form start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- post form end -->

				<!-- post start -->
				<jsp:include page="/component/posts.jsp"></jsp:include>
				<!-- post end -->
			</div>
		</div>
	</main>
	<!-- main content end -->

</body>
</html>