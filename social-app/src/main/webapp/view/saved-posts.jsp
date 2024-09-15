<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Saved</title>

<jsp:include page="/resource/style.jsp"></jsp:include>
<style type="text/css">
.display-image {
	width: 100%;
	max-height: 150px;
	overflow: hidden;
	min-height: 150px;
	border-radius: 10px;
	display: flex;
	align-items: center;
	background: transparent;
	transition: transform 0.3s ease, box-shadow 0.3s ease; /* Transition for smooth hover effect */
}

.display-image img {
	object-fit: cover;
	width: 100%;
	height: 100%;
}

.card-hover-effect {
	transition: transform 0.3s ease, box-shadow 0.3s ease; /* Smooth transition for hover effect */
}

.card-hover-effect:hover {
	transform: scale(1.05); /* Slightly scales up the card */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Adds a shadow for depth effect */
}

.card-container {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 20px;
    transition: all 0.5s ease; /* Smooth rearrangement of cards */
}

.col {
    transition: transform 0.5s ease; /* Transition for smooth movement */
}

</style>
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
			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="savedActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- side bar end -->

			<div class="col-8 mb-2">
				<div class="mb-3 txt-text">
					<span class="d-block h4">Saved Posts</span>
					<c:choose>
						<c:when test="${savedPostCount gt 1}">
							<small id="saved-post-count" count="${savedPostCount}" class="txt-grey">${savedPostCount} items</small>
						</c:when>
						<c:when test="${savedPostCount eq 1}">
							<small id="saved-post-count" count="1" class="txt-grey">${savedPostCount} item</small>
						</c:when>
						<c:otherwise>
							<small id="saved-post-count" count="0" class="txt-grey">0 item</small>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
					<c:choose>
						<c:when test="${not empty savedPostViews}">
						<c:url var="unsaveUrl" value="/api/post/unsave"></c:url>
						<div class="d-none" url="${unsaveUrl}" id="unsaveUrl"></div>
							<c:forEach var="savedPostView" items="${savedPostViews}">
								<c:url var="postDetailLink" value="/post">
									<c:param name="postId" value="${savedPostView.postId}"></c:param>
								</c:url>
								<div class="col pointer card-container">
									<div class="h-100 position-relative rounded shadow bg-card p-2 card-hover-effect">
									<a href="${postDetailLink}" class="d-none">go to</a>
									<div class="unsave-post-btn position-absolute top-0 end-0 text-danger fs-4 fa-solid fa-trash" postId="${savedPostView.postId}"></div>
										<div class="rounded display-image mx-auto">
											<img
												src="${savedPostView.displayImage}"
												class="rounded" alt="Saved Post Image">
										</div>
										<div class="mt-2">
											<p class="h5 txt-text d-block">${savedPostView.postContent}</p>
											<div class="w-100 text-end">
												<span class="txt-grey text-end d-block">Posted by ${savedPostView.postOwnerName}</span>
												<small class="txt-grey">${savedPostView.savedAt}</small>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="w-100 bg-card-2 rounded p-5 text-center">
								<h4 class="txt-grey mb-3">There is no saved post</h4>
								<i class="fa-regular fa-face-smile-wink txt-grey fa-8x"></i>
							</div>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</div>
	</main>
	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="savedPostHelper" value="/resource/ajax/saved-post-helper.js"></c:url>
	<script type="text/javascript" src="${savedPostHelper}"></script>

</body>
</html>
