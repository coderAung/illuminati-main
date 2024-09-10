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
			<div class="col-6">
				<!-- Post Container Start -->
				
				<c:if test="${not empty postDetailView}">
					<c:set var="pv" value="${postDetailView.postView}"></c:set>
				</c:if>
				
				<div
					class="post-content bg-card txt-text p-2 rounded mb-5 pb-5 post-card">
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
								<div class="text-center post-image-container pointer">
									<img class="rounded mb-2" src="${pv.postImageList[0]}">
								</div>
							</c:when>
							<c:when test="${pv.postImageList.size() gt 1}">
								<div id="carousel-${pv.id}" class="carousel slide"
									data-bs-ride="carousel">
									<div class="carousel-inner post-image-container rounded main-bg">
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

					<!-- Comments Section Start -->
					<div class="comments-section mt-3">
						<!-- Example Comments -->
						<c:forEach begin="1" end="20" step="1">
							<div class="d-flex txt-text mb-3">
								<a href="#" class="me-3"> <img
									style="width: 40px; height: 40px" class="rounded-circle"
									src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNERpqjIW8laIlKLNkwOBewYyPx5bnz7PktmGBfHc63qKPkyzoxeZUX06Ooop0YHi67TI&usqp=CAU"
									alt="Commenter Profile Picture">
								</a>
								<div class="flex-grow-1">
									<div class="bg-card-2 py-2 px-3 rounded">
										<div class="d-flex justify-content-between mb-2">
											<div>
												<a class="text-decoration-none fw-bold txt-text" href="#">Anya
													Taylor Joy</a> <small class="txt-grey ms-3">Just now</small>
											</div>
											<span class="pointer"> <i
												class="bi bi-three-dots-vertical"></i>
											</span>
										</div>
										<p>Beautiful Flowers</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- Comments Section End -->
				</div>
				<!-- Post Container End -->

				<!-- Comment Form Start -->
				<div id="commentForm" class="comment-form">
					<div
						class="d-flex justify-content-center align-items-center w-100 bg-card rounded px-5 py-3">

						<a href="#"> <img style="width: 45px; height: 45px"
							class="rounded-circle"
							src="https://i.ebayimg.com/images/g/42YAAOSwtupiTgU7/s-l1200.webp"
							alt="User Profile Picture">
						</a>

						<div class="search-bar bg-card-2 rounded p-2 w-50 mx-3">
							<textarea type="text" class="w-100 h-100 txt-text" rows="1"
								placeholder="Write a comment..."></textarea>
						</div>
						<button class="btn btn-primary">Post</button>
					</div>
				</div>
				<!-- Comment Form End -->
			</div>
			<!-- Center Column End -->
		</div>
	</main>
	<!-- main part end -->

	<!-- JavaScript for Scroll Effect -->
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const commentForm = document.getElementById('commentForm');
			let lastScrollTop = window.pageYOffset
					|| document.documentElement.scrollTop;

			window.addEventListener('scroll', function() {
				let currentScrollTop = window.pageYOffset
						|| document.documentElement.scrollTop;

				if (currentScrollTop > lastScrollTop) {
					// Scrolling down
					commentForm.classList.add('show');
				} else {
					// Scrolling up
					commentForm.classList.remove('show');
				}

				lastScrollTop = currentScrollTop <= 0 ? 0 : currentScrollTop; // Prevent negative scroll
			});
		});
	</script>
</body>
</html>
