<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="image" value="/img/logo.jpg"></c:url>

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

	<!-- main part start -->
	<main>
		<div class="container-fluid row">

			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param value="menu-item-active" name="profileActive" />
				</jsp:include>
			</div>


			<div class="col-6 px-0 mb-5">

				<!-- Profile Card -->
				<div
					class="mb-2 bg-card txt-white position-relative rounded color-white">
					<!-- Cover Image -->

					<div class="cover-img rounded-top pointer w-100 p-1">
						<img alt="" class="rounded-top" src="${loginUser.coverImage}">
					</div>

					<div class="profile-img position-absolute pointer">
						<img alt="" src="${loginUser.profileImage}">
					</div>

					<!-- Profile Information -->
					<div class="px-3 pb-3 mt-5">
						<div class="w-50 d-flex float-end justify-content-end">
							<c:url var="profileDetail" value="/profile/detail"></c:url>
							<a href="${profileDetail}" class="btn btn-app w-auto me-3">Detail</a>
						</div>

						<div>
							<div class="h5">${profileView.name}</div>
							<i class="txt-grey">Life is Gift</i>
						</div>
					</div>
				</div>
				<!-- Profile Card End -->
				<!-- Friend Preview start -->
				<jsp:include page="/component/friend-preview.jsp">
					<jsp:param value="${loginUser.id}" name="userId" />
				</jsp:include>
				<!-- Friend Preview end -->

				<!-- create post start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- create post end -->

				<!-- post start -->
				<!-- real data -->
				<div id="post-container">
					<jsp:include page="/component/posts.jsp"></jsp:include>
				</div>
				<!-- post end -->
			</div>

			<c:if test="${loginUser.role.name() eq 'ADMIN'}">
				<div class="col-2 mx-auto right-side px-0">
					<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
				</div>
			</c:if>

		</div>

		<div class="w-25 d-none me-3"
			style="position: sticky; bottom: 10px; margin-left: auto; z-index: 1000;">
			<div class="alert alert-primary">A post is created!</div>
		</div>

	</main>
	<!-- main part end -->
	<c:url var="script" value="/resource/js/script.js"></c:url>
	<script type="text/javascript" src="${script}"></script>

	<c:url var="controlPanelDisplay"
		value="/resource/js/control-panel-display-home.js"></c:url>
	<script type="text/javascript" src="${controlPanelDisplay}"></script>

	<c:url var="editPost" value="/resource/js/edit-post.js"></c:url>
	<script type="text/javascript" src="${editPost}"></script>

	<c:url var="friendPreview" value="/resource/js/friend-preview.js"></c:url>
	<script type="text/javascript" src="${friendPreview}"></script>

	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="postDelete" value="/resource/ajax/post-delete.js"></c:url>
	<script type="text/javascript" src="${postDelete}"></script>
	<c:url var="savePost" value="/resource/ajax/save-post.js"></c:url>
	<script type="text/javascript" src="${savePost}"></script>
	<c:url var="reaction" value="/resource/ajax/reaction.js"></c:url>
	<script type="text/javascript" src="${reaction}"></script>

</body>
</html>