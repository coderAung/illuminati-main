<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

			<div class="px-0 col-6" id="search-result">
				<jsp:include page="/component/search-tab.jsp">
					<jsp:param value="${activeTab}" name="active" />
				</jsp:include>
				<c:choose>
					<c:when test="${not empty postViews}">
						<c:url var="savePostUrl" value="/api/post/save"></c:url>
						<span class="d-none" id="savePostUrl" url="${savePostUrl}"></span>

						<c:url var="unsavePostUrl" value="/api/post/unsave"></c:url>
						<span class="d-none" id="unsavePostUrl" url="${unsavePostUrl}"></span>

						<c:forEach var="pv" items="${postViews}">
							<!-- new feed start -->
							<div class="p-2 rounded position-relative mb-2 post-card"
								id="post-${pv.id}">
								<span class="float-end pointer control-btn"> <i
									class="bi bi-three-dots-vertical"></i>
								</span>

								<div
									class="bg-card control-panel shadow w-25 p-2 rounded position-absolute control-panel float-end top-0 end-0 me-4 mt-3">

									<c:choose>
										<c:when test="${!pv.isSaved()}">
											<div postId="${pv.id}"
												class="px-2 py-2 pointer rounded save-post-btn">
												<i class="fa-solid fa-bookmark txt-app me-3"></i><span
													class="txt-text">Save</span>
											</div>
										</c:when>
										<c:when test="${pv.isSaved()}">
											<div postId="${pv.id}"
												class="px-2 py-2 pointer rounded unsave-post-btn">
												<span class="txt-text">Unsave</span>
											</div>
										</c:when>
									</c:choose>

									<div postId="${pv.id}"
										class="post-detail-btn px-2 py-2 pointer rounded">
										<span class="txt-text">Detail</span>
										<c:url var="post" value="/post">
											<c:param name="postId" value="${pv.id}"></c:param>
										</c:url>
										<a href="${post}" class="d-none post-detail-link"></a>
									</div>

									<div postId="${pv.id}" class="px-2 py-2 pointer rounded">
										<span class="txt-text">Share</span>
									</div>

									<c:if test="${pv.userId eq loginUser.id}">
										<div postId="${pv.id}"
											class="edit-post-btn px-2 py-2 pointer rounded">
											<span class="txt-text">Edit</span>
											<c:url var="edit" value="/post/edit">
												<c:param name="postId" value="${pv.id}"></c:param>
											</c:url>
											<a href="${edit}" class="d-none edit-post-link"></a>
										</div>

									</c:if>

									<c:if
										test="${(pv.userId eq loginUser.id) or ('ADMIN' eq loginUser.role)}">
										<c:url var="postDeleteUrl" value="/api/post/delete"></c:url>
										<div postId="${pv.id}" url="${postDeleteUrl}"
											class="post-delete-btn px-2 py-2 pointer rounded text-danger">
											<i class="fa-regular fa-trash-can text-danger me-3"></i><span
												class="text-decoration-none">Delete</span>
										</div>
									</c:if>
								</div>
								<!-- user info start -->
								<div class="d-flex px-2">
									<a href="#" class="me-3"> <img
										style="width: 45px; height: 45px" class="rounded-circle"
										src="${pv.profileImage}">
									</a>
									<div class="d-flex flex-column">
										<c:choose>
											<c:when test="${loginUser.id eq pv.userId}">
												<c:url var="profile" value="/profile"></c:url>
											</c:when>
											<c:otherwise>
												<c:url var="profile" value="/other/profile">
													<c:param name="userId" value="${pv.userId}"></c:param>
												</c:url>
											</c:otherwise>
										</c:choose>
										<a href="${profile}"
											class="text-decoration-none txt-white fw-bold">
											${pv.userName} </a>
										<c:url var="post" value="/post">
											<c:param name="postId" value="${pv.id}"></c:param>
										</c:url>
										<a href="${post}" class="text-decoration-none"> <small
											class="txt-grey pointer">${pv.updatedAt}</small>
										</a>
									</div>
								</div>
								<!-- user info end -->

								<!-- caption start -->
								<div class="mt-2 px-2">
									<p>
										<c:out value="${pv.content}"></c:out>
									</p>
								</div>
								<!-- caption start -->

								<!-- Post Images start -->
								<c:if test="${not empty pv.postImageList}">
									<c:choose>
										<c:when test="${pv.postImageList.size() eq 1}">
											<div class="text-center post-image-container pointer rounded">
												<img class="rounded mb-2" src="${pv.postImageList[0]}">
											</div>
										</c:when>
										<c:when test="${pv.postImageList.size() gt 1}">
											<div id="carousel-${pv.id}" class="carousel slide"
												data-bs-ride="carousel">
												<div
													class="carousel-inner post-image-container rounded main-bg">
													<c:forEach var="pi" items="${pv.postImageList}"
														varStatus="status">
														<div class="carousel-item ${status.first ? 'active' : ''}">
															<img class="d-block w-100 rounded" src="${pi}">
														</div>
													</c:forEach>
												</div>
												<button class="carousel-control-prev" type="button"
													data-bs-target="#carousel-${pv.id}" data-bs-slide="prev">
													<span
														class="bg-card rounded px-2 py-1 d-flex justify-content-center align-items-center"
														aria-hidden="true"> <i
														class="fa-solid fa-chevron-left color-app fs-4"></i>
													</span> <span class="visually-hidden">Previous</span>
												</button>
												<button class="carousel-control-next" type="button"
													data-bs-target="#carousel-${pv.id}" data-bs-slide="next">
													<span
														class="bg-card rounded px-2 py-1 d-flex justify-content-center align-items-center"
														aria-hidden="true"> <i
														class="fa-solid fa-chevron-right color-app fs-4"></i>
													</span> <span class="visually-hidden">Next</span>
												</button>
											</div>
										</c:when>
									</c:choose>
								</c:if>
								<!-- Post Images end -->

								<!-- comment and share section -->
								<div class=" d-flex align-items-center pt-1 text-center">
									<a href="${post}#comments"
										class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
										Comment <i class="bi bi-dot"></i> <c:choose>
											<c:when test="${pv.commentCount gt 0}">
												<small class="txt-grey">${pv.commentCount} comments</small>
											</c:when>
											<c:otherwise>
												<small class="txt-grey">${pv.commentCount} comment</small>
											</c:otherwise>
										</c:choose>
									</a> <span class="mx-2">|</span> <a
										class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
										Share <i class="bi bi-dot"></i> <small class="txt-grey">20
											shares</small>
									</a>
								</div>
							</div>
							<!-- new feed end -->
						</c:forEach>
					</c:when>
					<c:when test="${not empty userViews}">
						<c:forEach var="uv" items="${userViews}">
							<div
								class="mb-3 rounded d-flex justify-content-between align-items-center px-3 py-2 txt-text bg-card">
								<div class="d-flex align-items-center">
									<div class="profile-photo pointer other-profile-trigger">
										<img src="${uv.profileImage}">
									</div>
									<div class="ms-3 d-flex flex-column">
										<c:url var="otherProfile" value="/other/profile">
											<c:param name="userId" value="${uv.userId}"></c:param>
										</c:url>
										<a class="text-decoration-none pointer txt-text fw-bold"
											href="${otherProfile}">${uv.userName}</a> <small
											class="txt-grey">3 mutual friends</small>
									</div>
								</div>
								<div>
									<c:url var="friendRequestUrl" value="/api/friend-request"></c:url>
									<span id="friendRequestUrl" class="d-none"
										url="${friendRequestUrl}"></span>
									<c:url var="friendUrl" value="/api/friend"></c:url>
									<span id="friendUrl" class="d-none" url="${friendUrl}"></span>
	
									<c:set var="otherUserData" value="${uv.data}"></c:set>
	
									<c:choose>
										<c:when
											test="${'IS_FRIEND' eq otherUserData.friendStatus.name()}">
											<div id="is-friend-status" class="btn btn-app w-auto me-3">Friend</div>
										</c:when>
										<c:when
											test="${'NEED_TO_CONFIRM' eq otherUserData.friendStatus.name()}">
											<button
												status="${otherUserData.friendStatus.name()}" type="button" otherUserId="${uv.userId}"
												class="confirm-friend-btn btn btn-app w-auto me-3">Confirm</button>
											<button status="DELETE_FRIEND_REQUEST" otherUserId="${uv.userId}"
												type="button" class="delete-friend-btn btn btn-danger w-auto me-3">Delete</button>
										</c:when>
										<c:when
											test="${'REQUESTED' eq otherUserData.friendStatus.name()}">
											<button
												status="${otherUserData.friendStatus.name()}" type="button" otherUserId="${uv.userId}"
												class="cancel-friend-btn btn btn-danger w-auto me-3">Cancel Request</button>
										</c:when>
										<c:otherwise>
											<button status="NOT_FRIEND" type="button" otherUserId="${uv.userId}"
												class="add-friend-btn btn btn-app w-auto me-3">Add Friend</button>
										</c:otherwise>
									</c:choose>

									<button type="button"
										class="btn btn-normal w-auto shadow other-profile-trigger">View
										Profile</button>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>

			</div>

			<c:if test="${loginUser.role.name() eq 'ADMIN'}">
				<div class="col-2 mx-auto px-0">
					<jsp:include page="/component/modal/admin-panel.jsp"></jsp:include>
				</div>
			</c:if>
		</div>
	</main>
	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="carousel" value="/resource/js/carousel.js"></c:url>
	<script type="text/javascript" src="${carousel}"></script>

	<c:url var="controlPanelDisplay"
		value="/resource/js/control-panel-display-home.js"></c:url>
	<script type="text/javascript" src="${controlPanelDisplay}"></script>

	<c:url var="editPost" value="/resource/js/edit-post.js"></c:url>
	<script type="text/javascript" src="${editPost}"></script>

	<c:url var="search" value="/resource/js/search.js"></c:url>
	<script type="text/javascript" src="${search}"></script>

	<c:url var="savePost" value="/resource/ajax/save-post.js"></c:url>
	<script type="text/javascript" src="${savePost}"></script>

	<c:url var="postDelete" value="/resource/ajax/post-delete.js"></c:url>
	<script type="text/javascript" src="${postDelete}"></script>

	<c:url var="friends" value="/resource/js/friends.js"></c:url>
	<script type="text/javascript" src="${friends}"></script>

	<c:url var="friend" value="/resource/ajax/search-user.js"></c:url>
	<script type="text/javascript" src="${friend}"></script>
</body>
</html>