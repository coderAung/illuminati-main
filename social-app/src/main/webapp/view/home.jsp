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

				<!-- post form start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- post form end -->

				<!-- post start -->
				<jsp:include page="/component/posts.jsp"></jsp:include>
				<!-- post end -->

			</div>

			<c:if test="${loginUser.role.name() eq 'ADMIN'}">
				<div class="col-2 mx-auto right-side px-0">
					<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
				</div>
			</c:if>

		</div>
	</main>
	<!-- main content end -->

	<c:url var="script" value="/resource/js/script.js"></c:url>
	<script type="text/javascript" src="${script}"></script>

	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="carousel" value="/resource/js/carousel.js"></c:url>
	<script type="text/javascript" src="${carousel}"></script>

	<c:url var="controlPanelDisplay"
		value="/resource/js/control-panel-display-home.js"></c:url>
	<script type="text/javascript" src="${controlPanelDisplay}"></script>

	<c:url var="editPost" value="/resource/js/edit-post.js"></c:url>
	<script type="text/javascript" src="${editPost}"></script>

	<c:url var="savePost" value="/resource/ajax/save-post.js"></c:url>
	<script type="text/javascript" src="${savePost}"></script>

	<c:url var="postDelete" value="/resource/ajax/post-delete.js"></c:url>
	<script type="text/javascript" src="${postDelete}"></script>

	<c:url var="reaction" value="/resource/ajax/reaction.js"></c:url>
	<script type="text/javascript" src="${reaction}"></script>
</body>
</html>