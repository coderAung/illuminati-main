<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | ${profileDetailView.name}</title>
<jsp:include page="/resource/style.jsp"></jsp:include>

<style type="text/css">
select {
	outline: none;
	background: transparent;
	border: none;
}
</style>

</head>

<body class="main-bg">
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">

			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="" value="menu-item-active" />
				</jsp:include>
			</div>

			<!-- main content start -->
			<div class="col-6 px-0 txt-white">
				<div class="w-100">
					<h4 class="mb-3">${profileDetailView.name}'s Details</h4>
				</div>

				<div class="bg-card p-3 rounded">
					<div class="d-flex mb-3">
						<div class="me-3">

							<div
								class="position-relative d-flex justify-content-center align-items-center rounded"
								style="width: 180px; height: 180px; overflow: hidden;">
								<img id="profile-image" src="${profileImage}"
									style="object-fit: cover;" alt="" class="img-fluid rounded" />

							</div>
						</div>

						<div class="flex-fill p-2 user-info">
							<div class="px-2 py-1 pointer rounded">
								<i class="bi bi-person-circle me-2"></i>
								<span>${profileDetailView.name}</span>
							</div>
							<div class="w-100 bg-card-2 my-1" style="height: 1px"></div>

							<div class="px-2 py-1 pointer rounded">
								<i class="bi bi-envelope-check-fill me-2"></i>
								<span>${profileDetailView.email}</span>
							</div>
							<div class="w-100 bg-card-2 my-1" style="height: 1px"></div>

							<c:choose>
								<c:when test="${not empty profileDetailView.phoneNumber}">
									<div class="px-2 py-1 pointer rounded">
										<i class="bi bi-telephone-fill me-2"></i>
										<span>${profileDetailView.phoneNumber}</span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="px-2 py-1 pointer txt-grey rounded">
										<i class="bi bi-telephone-fill me-2"></i>
										<span>Phone number</span>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="w-100 bg-card-2 my-1" style="height: 1px"></div>

							<c:choose>
								<c:when test="${not empty profileDetailView.address}">
									<div class="px-2 py-1 pointer rounded">
										<i class="bi bi-geo-alt-fill me-2"></i>
										<span>${profileDetailView.address}</span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="px-2 py-1 pointer txt-grey rounded">
										<i class="bi bi-geo-alt-fill me-2"></i>
										<span>Address</span>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="w-100 bg-card-2 my-1" style="height: 1px"></div>

							<c:choose>
								<c:when test="${not empty profileDetailView.birthDate}">
									<div class="px-2 py-1 pointer rounded">
									<i class="bi bi-cake2-fill me-2"></i>
									<span>${profileDetailView.birthDate}</span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="px-2 py-1 pointer txt-grey rounded">
										<i class="bi bi-cake2-fill me-2"></i>
										<span>Birthday</span>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="w-100 bg-card-2 my-1" style="height: 1px"></div>

							<c:choose>
								<c:when test="${not empty profileDetailView.bio}">
									<div class="px-2 py-1 pointer rounded">
										<i class="bi bi-journal-bookmark-fill me-2"></i>
										<span>${profileDetailView.bio}</span>
									</div>
								</c:when>
								<c:otherwise>
									<div class="px-2 py-1 pointer txt-grey rounded">
										<i class="bi bi-journal-bookmark-fill me-2"></i>
										<span>Bio</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>

					</div>

					<div class="w-100 bg-card-2 my-3" style="height: 1px"></div>

					<div class="bg-card-2 rounded p-3 d-flex justify-content-center align-items-center">
						<div
							class="col-3 position-relative w-75 d-flex justify-content-center align-items-center rounded"
							>
							<img id="cover-image" src="${coverImage}" alt=""
								style="object-fit: cover;" class="img-fluid rounded" />
						</div>
					</div>

					<div class="w-100 bg-card-2 my-3" style="height: 1px"></div>

					<div class="container-fluid">
						<div class="row justify-content-evenly">
							<div class="col-auto text-center bg-card-2 py-3 pointer rounded shadow">
								<h4>Gender</h4>
								<span>${empty profileDetailView.gender ? 'Not Defined': profileDetailView.gender}</span>
							</div>
							<div class="col-auto text-center bg-card-2 py-3 pointer rounded shadow">
								<h4>Relationship</h4>
								<span>${empty profileDetailView.relationship ? 'Not Defined': profileDetailView.relationship}</span>
							</div>
							<div class="col-auto text-center bg-card-2 py-3 pointer rounded shadow">
								<h4>Occupation</h4>
								<span>${empty profileDetailView.occupation ? 'Not Defined': profileDetailView.occupation}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- main content end -->
		</div>
		
	</main>
	<!-- main content end -->
</body>
</html>