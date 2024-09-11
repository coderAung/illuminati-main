<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Post</title>
<jsp:include page="/resource/style.jsp"></jsp:include>

<!-- Add the custom CSS here or in your stylesheet -->
<style>
</style>
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
					<jsp:param name="homeActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- sidebar end -->

			<!-- Center Column Start -->
			<div class="col-6 px-0 pb-5 mb-3">
				<!-- Post Container Start -->

				<c:if test="${not empty postDetailView}">
					<c:set var="pv" value="${postDetailView.postView}"></c:set>
				</c:if>

				<div
					class="post-content bg-card txt-text p-2 rounded mb-5 post-card">
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
							<c:when test="${pv.postImageList.size() eq 1}">
								<div class="text-center post-image-container rounded pointer">
									<img class="rounded" src="${pv.postImageList[0]}">
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
					<div class=" d-flex align-items-center py-1 text-center">
						<a
							class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
							Comment <i class="bi bi-dot"></i> 
							<c:choose>
								<c:when test="${empty postDetailView.commentViews}">
									<small id="comment-count" count="0" class="txt-grey">no comment</small>
								</c:when>
								<c:otherwise>
									<small id="comment-count" count="${postDetailView.commentViews.size()}" class="txt-grey">${postDetailView.commentViews.size()} comments</small>
								</c:otherwise>
							</c:choose>
						</a> <span class="mx-2">|</span> <a
							class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
							Share <i class="bi bi-dot"></i> <small class="txt-grey">20
								shares</small>
						</a>
					</div>

					<div class="p-2 mb-2 border-top" id="comments">
						<span class="fs-5">Comments</span>
					</div>

					<!-- Comments Section Start -->
					<div class="comments-section">

						<c:choose>
							<c:when test="${not empty postDetailView.commentViews}">
								<c:set var="commentViews" value="${postDetailView.commentViews}" scope="page"></c:set>
								<div class="bg-card-2 p-3 rounded" id="comment-container">
									<!-- Example Comments -->
									<c:forEach var="cv" items="${commentViews}">
										<div class="d-flex txt-text mb-3">
											<a href="#" class="me-3"> <img
												style="width: 40px; height: 40px" class="rounded-circle"
												src="${cv.profileImage}"
												alt="Commenter Profile Picture">
											</a>
											<div class="flex-grow-1">
												<div class="bg-card py-2 px-3 rounded">
													<div class="d-flex justify-content-between mb-2">
														<div>
															<a class="text-decoration-none fw-bold txt-text" href="#">${cv.userName}</a>
															<small class="txt-grey ms-3">${cv.createdAt}</small>
														</div>
														<span class="pointer"> <i
															class="bi bi-three-dots-vertical"></i>
														</span>
													</div>
													<p>
														<c:out value="${cv.content}"></c:out>
													</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</c:when>
							<c:otherwise>
								<div
									class="bg-card-2 p-3 rounded d-flex flex-column align-items-center">
									<span class="txt-grey">No comment yet</span> <span
										class="txt-grey">Be the first to comment here!</span> <span><i
										class="fa-regular fa-comment-dots txt-grey fa-5x"></i></span>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- Comments Section End -->
				</div>
				<!-- Post Container End -->

				<!-- Comment Form Start -->
				<div id="commentForm" class="comment-form">
					<div
						class="d-flex justify-content-center shadow-lg align-items-center w-100 bg-card rounded px-5 py-3">

						<c:url var="profile" value="/profile"></c:url>
						<a href="${profile}"> <img style="width: 45px; height: 45px"
							class="rounded-circle"
							src="${loginUser.profileImage}"
							alt="User Profile Picture">
						</a>

						<div class="search-bar bg-card-2 rounded p-2 w-50 mx-3">
							<textarea type="text" class="w-100 h-100 txt-text" name="comment"
								id="commentContent" rows="1" placeholder="Write a comment..."></textarea>
						</div>
						<input id="post-id" type="hidden" class="d-none" value="${pv.id}" />
						<c:url var="commentCreate" value="/api/comment/create"></c:url>
						<button url="${commentCreate}" id="comment-btn"
							class="btn btn-primary">Post</button>
					</div>
				</div>
				<!-- Comment Form End -->
			</div>
			<!-- Center Column End -->
		</div>
	</main>
	<!-- main part end -->

	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="comment" value="/resource/ajax/comment.js"></c:url>
	<script type="text/javascript" src="${comment}"></script>

	<!-- JavaScript for Scroll Effect -->
	<c:url var="commentForm" value="/resource/js/comment-form.js"></c:url>
	<script type="text/javascript" src="${commentForm}"></script>
</body>
</html>
