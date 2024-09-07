<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Home</title>

<jsp:include page="/resource/style.jsp"></jsp:include>

</head>
<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="homeActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main content start -->
	<main>
		<div class="container-fluid row">

			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="homeActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- sidebar end -->
			<div class="col-6 px-0">

				<!-- post form start -->
				<jsp:include page="/component/create-post-link.jsp"></jsp:include>
				<!-- post form end -->

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
										<c:choose>
											<c:when test="${loginUser.id eq pv.userId}">
												<c:url var="profile" value="/profile"></c:url>
											</c:when>
											<c:otherwise>
												<c:url var="profile"
													value="/other/profile?userId=${pv.userId}"></c:url>
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
												<div class="carousel-inner rounded">
													<c:forEach var="pi" items="${pv.postImageList}"
														varStatus="status">
														<div class="carousel-item ${status.first ? 'active' : ''}">
															<img class="d-block w-100 rounded" src="${pi}">
														</div>
													</c:forEach>
												</div>
												<button class="carousel-control-prev" type="button"
													data-bs-target="#carousel-${pv.id}" data-bs-slide="prev">
													<span class="carousel-control-prev-icon" aria-hidden="true"></span>
													<span class="visually-hidden">Previous</span>
												</button>
												<button class="carousel-control-next" type="button"
													data-bs-target="#carousel-${pv.id}" data-bs-slide="next">
													<span class="carousel-control-next-icon" aria-hidden="true"></span>
													<span class="visually-hidden">Next</span>
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
	<!-- main content end -->

	<c:url var="script" value="/resource/js/script.js"></c:url>
	<script type="text/javascript" src="${script}"></script>

	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="carousel" value="/resource/js/carousel.js"></c:url>
	<script type="text/javascript" src="${carousel}"></script>
</body>
</html>