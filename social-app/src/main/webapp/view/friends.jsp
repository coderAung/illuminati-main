<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Friends</title>

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
			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="friendsActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- side bar end -->

			<div class="col-6 bg-card mb-2 p-3 rounded">
				<h4 class="txt-text">Friends</h4>

				<div class="mb-3 d-flex justify-content-between">
					<c:choose>
						<c:when test="${friendViews.size() eq 1}">
							<small class="txt-grey">1 friend</small>
						</c:when>
						<c:when test="${friendViews.size() gt 1}">
							<small class="txt-grey">${friendViews.size()} friends</small>
						</c:when>
					</c:choose>
					<div class="d-flex search-bar py-2 px-3 bg-card-2 w-50 rounded">
						<input type="text" class="w-100 rounded txt-white"
							placeholder="Find friends">
					</div>
				</div>


				<c:forEach var="fv" items="${friendViews}">
					<div
						class="mb-3 rounded d-flex justify-content-between align-items-center px-3 py-2 txt-text bg-card-normal">
						<div class="d-flex align-items-center">
							<div class="profile-photo pointer other-profile-trigger">
								<img src="${fv.profileImage}">
							</div>
							<div class="ms-3 d-flex flex-column">
								<c:url var="otherProfile" value="/other/profile">
									<c:param name="userId" value="${fv.friendId}"></c:param>
								</c:url>
								<a class="text-decoration-none pointer txt-text fw-bold"
									href="${otherProfile}">${fv.name}</a> <small class="txt-grey">3
									mutual friends</small>
							</div>
						</div>
						<div>
							<button type="button"
								class="btn btn-deep shadow other-profile-trigger">View
								Profile</button>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>

	</main>
	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="friends" value="/resource/js/friends.js"></c:url>
	<script type="text/javascript" src="${friends}"></script>

</body>
</html>
