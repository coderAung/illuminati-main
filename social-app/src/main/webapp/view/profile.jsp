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


			<div class="col-6 px-0">

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
							<a href="${profileDetail}" class="btn btn-normal w-auto me-3">Detail</a>
							<a href="#" class="btn w-auto btn-normal">Edit</a>
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

				<!-- create post start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- create post end -->

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
										<c:when test="${pv.postImageList.size() eq 0}">

										</c:when>

										<c:when test="${pv.postImageList.size() eq 1}">
											<div class="post-image-container pointer rounded">
												<div class="inner-image-container rounded">
													<img src="${pv.postImageList[0]}">
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
										Comment <i class="bi bi-dot"></i>
										<c:choose>
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
					<c:otherwise>
						<h2 class="bg-danger text-white">THERE IS NO POSTS</h2>
					</c:otherwise>
				</c:choose>
				<!-- post end -->
			</div>
		</div>
	</main>
	<!-- main part end -->
	<c:url var="script" value="/resource/js/script.js"></c:url>
	<script type="text/javascript" src="${script}"></script>
</body>
</html>