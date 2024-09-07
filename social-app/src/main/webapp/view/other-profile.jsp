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


			<div class="col-6 px-0">

				<!-- Profile Card -->
				<div
					class="mb-2 bg-card txt-white position-relative rounded color-white">
					<!-- Cover Image -->

					<div class="cover-img rounded-top pointer w-100 p-1">
						<img alt="" class="rounded-top"
							src="${profileView.coverImage}">
					</div>

					<div class="profile-img position-absolute pointer">
						<img alt=""
							src="${profileView.profileImage}">
					</div>

					<!-- Profile Information -->
					<div class="px-3 pb-3 mt-5">
						<div class="w-50 d-flex float-end justify-content-end">
						
							<c:url var="friendRequestUrl" value="/api/friend-request"></c:url>
							<span id="friendRequestUrl" class="d-none" url="${friendRequestUrl}"></span>
							<c:url var="friendUrl" value="/api/friend"></c:url>
							<span id="friendUrl" class="d-none" url="${friendUrl}"></span>
						
							<span id="userData" otherUserId="${profileView.userId}" class="d-none"></span>
						
							<c:choose>
								<c:when test="${'IS_FRIEND' eq otherUserData.friendStatus.name()}">
									<div id="is-friend-status" class="btn btn-normal w-auto me-3">Friend</div>
									<button id="friend-btn" status="${otherUserData.friendStatus.name()}" type="button" class="btn btn-danger w-auto me-3">Unfriend</button>
								</c:when>
								<c:when test="${'NEED_TO_CONFIRM' eq otherUserData.friendStatus.name()}">
									<button id="friend-btn" status="${otherUserData.friendStatus.name()}" type="button" class="btn btn-light w-auto me-3">Confirm</button>
									<button id="delete-friend-btn" status="DELETE_FRIEND_REQUEST" type="button" class="btn btn-danger w-auto me-3">Delete</button>
								</c:when>
								<c:when test="${'REQUESTED' eq otherUserData.friendStatus.name()}">
									<button id="friend-btn" status="${otherUserData.friendStatus.name()}" type="button" class="btn btn-danger w-auto me-3">Cancel Request</button>
								</c:when>
								<c:otherwise>
									<button id="friend-btn" status="NOT_FRIEND" type="button" class="btn btn-normal w-auto me-3">Add Friend</button>
								</c:otherwise>
							</c:choose>
							<a href="#" class="btn btn-normal w-auto">Detail</a>
						</div>

						<div>
							<div class="h5">${profileView.name}</div>
							<i class="txt-grey">Life is Gift</i>
						</div>
					</div>
				</div>
				<!-- Profile Card End -->

				<!-- Friend Preview start -->
				<jsp:include page="/component/friend-preview.jsp"></jsp:include>
				<!-- Friend Preview end -->

				<!-- post start -->
				<!-- real data -->
				<c:choose>
					<c:when test="${not empty postViews}">
						<c:forEach var="pv" items="${postViews}">
							<!-- new feed start -->
							<div class="p-2 rounded mb-2 post-card">
								<span class="float-end pointer"> <i
									class="bi bi-three-dots-vertical"></i>
								</span>
								<!-- user info start -->
								<div class="d-flex px-2">
									<a href="#" class="me-3"> <img
										style="width: 45px; height: 45px" class="rounded-circle"
										src="${pv.profileImage}">
									</a>
									<div class="d-flex flex-column">
										<a href="#" class="text-decoration-none txt-white fw-bold">
											${pv.userName} </a> <small class="txt-grey">${pv.updatedAt}</small>
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
										<c:when test="${pv.postImageList.size() eq 0}">
										
										</c:when>
										
										<c:when test="${pv.postImageList.size() eq 1}">
											<div class="text-center post-image-container pointer">
												<img
													src="${pv.postImageList[0]}">
											</div>
										</c:when>

										<c:when test="${pv.postImageList.size() gt 1}">
											<div>carousel here</div>
										</c:when>
										
									</c:choose>
								
								</c:if>
								<!-- Post Images end -->

								<!-- comment and share section -->
								<div class=" d-flex align-items-center pt-1 text-center">
									<a
										class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
										Comment <i class="bi bi-dot"></i> <small class="txt-grey">20
											comments</small>
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
					<c:otherwise>
						<h2 class="bg-danger text-white">THERE IS NO POSTS</h2>
					</c:otherwise>
				</c:choose>
				<!-- post end -->
			</div>
		</div>
	</main>
	<!-- main part end -->

<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
<script type="text/javascript" src="${jquery}"></script>
<c:url var="friend" value="/resource/ajax/friend.js"></c:url>
<script type="text/javascript" src="${friend}"></script>
</body>
</html>