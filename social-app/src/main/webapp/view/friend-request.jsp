<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Friend Requests</title>
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
					<jsp:param name="friendRequestActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- sidebar end -->

			<div class="col-6 bg-card mb-2 p-3 rounded">
				<h4 class="txt-text">Friend Requests</h4>

				<c:choose>
					<c:when test="${not empty friendRequestViews}">
						<div class="mb-3 d-flex justify-content-between">
							<c:choose>
								<c:when test="${friendRequestViews.size() eq 1}">
									<small class="txt-grey">1 friend request</small>
								</c:when>
								<c:when test="${friendRequestViews.size() gt 1}">
									<small class="txt-grey">${friendViews.size()} friend
										requests</small>
								</c:when>
							</c:choose>
							<div class="d-flex search-bar py-2 px-3 bg-card-2 w-50 rounded">
								<input type="text" class="w-100 rounded txt-white"
									placeholder="Find friends">
							</div>
						</div>
						<c:url var="friendUrl" value="/api/friend"></c:url>
						<c:forEach var="frv" items="${friendRequestViews}">
							<div
								class="mb-3 rounded d-flex justify-content-between align-items-center px-3 py-2 txt-text bg-card-normal">
								<div class="d-flex align-items-center">
									<div class="profile-photo">
										<img src="${frv.friendProfileImage}">
									</div>
									<div class="ms-3 d-flex flex-column">
										<a class="text-decoration-none pointer txt-text fw-bold"
											href="#">${frv.friendName}</a> <small class="txt-grey">3
											mutual friends</small>
									</div>
								</div>
								<div>

									<button type="button" url="${friendUrl}"
										otherUserId="${frv.friendId}"
										class="btn btn-deep me-3 confirm-friend-request">Confirm</button>
									<button type="button" url="${friendUrl}"
										otherUserId="${frv.friendId}"
										class="btn logout-btn delete-friend-request">Delete</button>
								</div>
							</div>
						</c:forEach>
					</c:when>

					<c:otherwise>

						<div class="mb-3 d-flex justify-content-between">
							<span class="txt-grey">No Friend Requests</span>
						</div>
						<div
							class="text-center my-auto">
							<span><i class="fa-regular fa-face-smile-wink txt-grey fa-8x"></i></span>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</main>
	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="friendRequest" value="/resource/ajax/friend-request.js"></c:url>
	<script type="text/javascript" src="${friendRequest}"></script>

</body>
</html>