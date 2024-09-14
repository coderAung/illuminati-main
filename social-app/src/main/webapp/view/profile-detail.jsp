<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Detail</title>
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
		<jsp:param value="nav-link-active" name="profileActive" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">

			<!-- side bar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="profileActive" value="menu-item-active" />
				</jsp:include>
			</div>

			<!-- main content start -->
			<div class="col-6 px-0 txt-white">
				<div class="w-100">
					<button type="button" data-bs-toggle="modal" data-bs-target="#edit-form"
						class="float-end pointer text-decoration-none btn btn-sm btn-app">
						<i class="bi bi-pencil"></i>
					</button>
					<h4 class="mb-3">${loginUser.name}'s Details</h4>
				</div>

				<div class="bg-card p-3 rounded">
					<div class="d-flex mb-3">
						<div class="me-3">

							<div
								class="position-relative d-flex justify-content-center align-items-center rounded"
								style="width: 180px; height: 180px; overflow: hidden;">
								<img id="profile-image" src="${loginUser.profileImage}"
									style="object-fit: cover;" alt="" class="img-fluid rounded" />

								<c:url var="profilePhoto" value="/photo/profile/upload"></c:url>
								<span id="profilePhotoUploadUrl" class="d-none" url="${profilePhoto}"></span>
								<input id="profile-photo" type="file" name="profilePhoto"
									accept="image/*" class="d-none">
							</div>

							<div id="upload-profile-photo-btn"
								class="btn btn-primary btn-sm w-100 mt-2">Upload</div>
							<div id="save-profile-photo-btn"
								class="btn btn-primary btn-sm w-100 mt-2 d-none">Save</div>
							<div id="cancel-profile-photo-btn"
								class="btn btn-danger btn-sm w-100 mt-2 d-none">Cancel</div>
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

					<div class="bg-card-2 rounded p-3 d-flex">
						<div
							class="col-3 position-relative d-flex justify-content-center align-items-center rounded"
							style="width: 200px; height: 150px; overflow: hidden;">
							<img id="cover-image" src="${loginUser.coverImage}" alt=""
								style="object-fit: cover;" class="img-fluid rounded" />
							<c:url var="coverPhoto" value="/photo/cover/upload"></c:url>
							<span id="coverPhotoUploadUrl" class="d-none" url="${coverPhoto}"></span>
								<input id="cover-photo" type="file" name="coverPhoto"
									accept="image/*" class="d-none">
						</div>
						<div class="ms-3">
							<span class="fs-5 txt-grey">You can upload cover photo
								here</span>
							<div id="upload-cover-photo-btn"
								class="btn btn-sm btn-primary mt-2 d-block">Upload</div>
							<div class="d-flex w-100">
								<div id="save-cover-photo-btn"
									class="btn btn-sm btn-primary mt-2 w-50 d-none me-2">Save</div>
								<div id="cancel-cover-photo-btn"
									class="btn btn-sm btn-danger mt-2 w-50 d-none">Cancel</div>
							</div>

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
		
		<jsp:include page="/component/modal/detail-edit-form.jsp"></jsp:include>
		
	</main>
	<!-- main content end -->
	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="photo" value="/resource/ajax/photo.js"></c:url>
	<script type="text/javascript" src="${photo}"></script>

</body>
</html>