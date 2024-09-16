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
					<c:choose>
						<c:when test="${not empty postViews}">
							<c:url var="savePostUrl" value="/api/post/save"></c:url>
							<span class="d-none" id="savePostUrl" url="${savePostUrl}"></span>

							<c:url var="unsavePostUrl" value="/api/post/unsave"></c:url>
							<span class="d-none" id="unsavePostUrl" url="${unsavePostUrl}"></span>

							<c:forEach var="pv" items="${postViews}">
								<!-- new feed start -->
								<div class="p-2 rounded mb-2 position-relative post-card"
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
													<span class="txt-text">Save</span>
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

										<c:if test="${pv.userId eq loginUser.id}">
											<div postId="${pv.id}"
												class="edit-post-btn px-2 py-2 pointer rounded">
												<span class="txt-text">Edit</span>
												<c:url var="edit" value="/post/edit">
													<c:param name="postId" value="${pv.id}"></c:param>
												</c:url>
												<a href="${edit}" class="d-none edit-post-link"></a>
											</div>

											<c:url var="postDeleteUrl" value="/api/post/delete"></c:url>
											<div postId="${pv.id}" url="${postDeleteUrl}"
												class="post-delete-btn px-2 py-2 pointer rounded text-danger">
												<span class="text-decoration-none">Delete</span>
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
											<a href="#" class="text-decoration-none txt-white fw-bold">
												${pv.userName} </a> <a href="${post}"
												class="text-decoration-none post-detail-link"> <small
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
											<c:when test="${pv.postImageList.size() eq 0}">

											</c:when>

											<c:when test="${pv.postImageList.size() eq 1}">
												<div class="post-image-container pointer rounded">
													<div class="inner-image-container rounded">
														<img class="rounded" src="${pv.postImageList[0]}">
													</div>
												</div>
											</c:when>

											<c:when test="${pv.postImageList.size() gt 1}">
												<div id="carousel-${pv.id}" class="carousel slide"
													data-bs-ride="carousel">
													<div
														class="carousel-inner post-image-container rounded main-bg">
														<c:forEach var="pi" items="${pv.postImageList}"
															varStatus="status">
															<div
																class="carousel-item ${status.first ? 'active' : ''}">
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

										<a
											class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
											Like <i class="bi bi-dot"></i> <small class="txt-grey">20
												reactions</small>
										</a>
										
										<span class="mx-2">|</span>
										
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
										</a>
									</div>

								</div>
								<!-- new feed end -->
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="text-center txt-grey bg-card-2 p-2 fs-5 rounded">
								<span>No post available</span> <i
									class="fa-solid fa-circle-exclamation"></i>
							</div>
						</c:otherwise>
					</c:choose>
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

</body>
</html>