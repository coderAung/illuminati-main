<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Illuminati | Profile</title>

<jsp:include page="/resource/library/bootstrap.jsp"></jsp:include>
<jsp:include page="/resource/library/icon.jsp"></jsp:include>

<c:url var="navbarCss" value="/resource/css/nav-bar.css"></c:url>
<link href="${navbarCss}" rel="stylesheet">

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">

</head>
<body>
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="profileActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main part start -->
	<main>
		<div class="container-fluid row">
			<!-- LEFT start -->
			<jsp:include page="/component/sidebar.jsp"></jsp:include>
			<!-- RIGHT start -->
			<div class="col-7 px-0">

				<!-- Profile Card -->
				<jsp:include page="/component/profile-card.jsp"></jsp:include>
				<!-- Profile Card End -->

				<!-- Friend Preview start -->
				<jsp:include page="/component/friend-preview.jsp"></jsp:include>
				<!-- Friend Preview end -->

				<!-- create post start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- create post end -->

				<div class="container-fluid px-0 color-white">

					<c:forEach begin="1" end="5">
						<!-- new feed start -->
						<div class="bg-pale p-2 rounded mb-2 post-card">
							<span class="float-end pointer"> <i
								class="bi bi-three-dots-vertical"></i>
							</span>
							<!-- user info start -->
							<div class="d-flex">
								<a href="#" class="me-3"> <img
									style="width: 50px; height: 50px" class="rounded-circle"
									src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
								</a>
								<div class="d-flex flex-column">
									<a href="#" class="text-decoration-none color-white fw-bold"> Ko Ko </a> <small
										class="color-white">13 hours ago</small>
								</div>
							</div>
							<!-- user info end -->

							<!-- caption start -->
							<div class="mt-2 px-2">
								<p>What A Day!</p>
							</div>
							<!-- caption start -->

							<!-- Post Images start -->

							<div class="p-2 text-center post-image-container">
								<img class="rounded"
									src="https://www.ugaoo.com/cdn/shop/articles/shutterstock_223679731.jpg?v=1661873480">
							</div>
							<!-- Post Images end -->

							<!-- comment and share section -->
							<div class=" d-flex color-white p-1 align-items-center border-common rounded-bottom text-center">
								<a class="py-2 pointer link w-100 color-white w-50 text-decoration-none fw-bold">Comment</a>
								<span class="mx-2">|</span> <a
									class="py-2 pointer link w-100 color-white w-50 text-decoration-none fw-bold">Share</a>
							</div>

						</div>
						<!-- new feed end -->
					</c:forEach>

				</div>
			</div>
		</div>
	</main>
	<!-- main part end -->
</body>
</html>