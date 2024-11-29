<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="image" value="/img/logo.jpg"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | ${profileView.name}</title>

<jsp:include page="/resource/style.jsp"></jsp:include>

</head>
<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="homeActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main part start -->
	<main>
		<div class="container-fluid row">

			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param value="menu-item-active" name="homeActive" />
				</jsp:include>
			</div>


			<div class="col-6 px-0 mb-5">

				<!-- Profile Card -->
				<div
					class="mb-2 bg-card txt-white position-relative rounded color-white">
					<!-- Cover Image -->

					<div class="cover-img rounded-top pointer w-100 p-1">
						<img alt="" class="rounded-top" src="${profileView.coverImage}">
					</div>

					<div class="profile-img position-absolute pointer">
						<img alt="" src="${profileView.profileImage}">
					</div>

					<!-- Profile Information -->
					<div class="px-3 pb-3 mt-5">
						<div class="w-50 d-flex float-end justify-content-end">

							<c:url var="friendRequestUrl" value="/api/friend-request"></c:url>
							<span id="friendRequestUrl" class="d-none"
								url="${friendRequestUrl}"></span>
							<c:url var="friendUrl" value="/api/friend"></c:url>
							<span id="friendUrl" class="d-none" url="${friendUrl}"></span> <span
								id="userData" otherUserId="${profileView.userId}" class="d-none"></span>

							<c:choose>
								<c:when
									test="${'IS_FRIEND' eq otherUserData.friendStatus.name()}">
									<div id="is-friend-status" class="btn btn-app w-auto me-3">Friend</div>
									<button id="friend-btn"
										status="${otherUserData.friendStatus.name()}" type="button"
										class="btn btn-outline-danger w-auto me-3">Unfriend</button>
								</c:when>
								<c:when
									test="${'NEED_TO_CONFIRM' eq otherUserData.friendStatus.name()}">
									<button id="friend-btn"
										status="${otherUserData.friendStatus.name()}" type="button"
										class="btn btn-app w-auto me-3">Confirm</button>
									<button id="delete-friend-btn" status="DELETE_FRIEND_REQUEST"
										type="button" class="btn btn-danger w-auto me-3">Delete</button>
								</c:when>
								<c:when
									test="${'REQUESTED' eq otherUserData.friendStatus.name()}">
									<button id="friend-btn"
										status="${otherUserData.friendStatus.name()}" type="button"
										class="btn btn-danger w-auto me-3">Cancel Request</button>
								</c:when>
								<c:otherwise>
									<button id="friend-btn" status="NOT_FRIEND" type="button"
										class="btn btn-app w-auto me-3">Add Friend</button>
								</c:otherwise>
							</c:choose>
							<c:url var="profileDetail" value="/other/profile/detail">
								<c:param name="userId" value="${profileView.userId}"></c:param>
							</c:url>
							<a href="${profileDetail}" class="btn btn-normal w-auto">Detail</a>
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
					<jsp:param value="${profileView.userId}" name="userId" />
				</jsp:include>
				<!-- Friend Preview end -->

				<!-- post start -->
				<!-- real data -->
				<div id="post-container">
					<jsp:include page="/component/posts.jsp"></jsp:include>
				</div>
				<!-- post end -->
			</div>
		</div>
	</main>
	<!-- main part end -->

	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="friend" value="/resource/ajax/friend.js"></c:url>
	<script type="text/javascript" src="${friend}"></script>
	<c:url var="savePost" value="/resource/ajax/save-post.js"></c:url>
	<script type="text/javascript" src="${savePost}"></script>

	<c:url var="controlPanelDisplay"
		value="/resource/js/control-panel-display-home.js"></c:url>
	<script type="text/javascript" src="${controlPanelDisplay}"></script>
	<c:url var="editPost" value="/resource/js/edit-post.js"></c:url>
	<script type="text/javascript" src="${editPost}"></script>
	<c:url var="friendPreview" value="/resource/js/friend-preview.js"></c:url>
	<script type="text/javascript" src="${friendPreview}"></script>
	<c:url var="postDelete" value="/resource/ajax/post-delete.js"></c:url>
	<script type="text/javascript" src="${postDelete}"></script>
	<c:url var="reaction" value="/resource/ajax/reaction.js"></c:url>
	<script type="text/javascript" src="${reaction}"></script>

</body>
</html>