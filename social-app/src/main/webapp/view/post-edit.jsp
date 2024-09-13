<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Creation</title>
<jsp:include page="/resource/style.jsp"></jsp:include>
<style type="text/css">
.photo {
	width: 200px;
	height: 200px;
	flex: none;
	position: relative;
}

.remove-photo-btn {
	position: absolute;
	top: 0;
	right: 0;
	width: 25px;
	height: 25px;
	text-align: center;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #ffffff;
	border-radius: 50%;
	font-weight: bold;
	margin-top: -9px;
	margin-right: -9px;
}

.photo img {
	object-fit: cover;
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

			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="profileActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- sidebar end -->
			<div class="col-6 px-0">
				<!-- user info start -->
				<div class="d-flex mb-3">
					<a href="#" class="me-3"> <img
						style="width: 45px; height: 45px; object-fit: cover;"
						class="rounded" src="${loginUser.profileImage}">
					</a>
					<div class="d-flex flex-column">
						<a href="#" class="text-decoration-none txt-white fw-bold">
							${loginUser.name} </a> <small class="txt-grey">Editing the
							post</small>
					</div>
					<div class="d-flex flex-fill py-1 flex-row-reverse">
						<button id="post-create-btn" class="btn-app w-50 rounded">Save</button>
					</div>
				</div>
				<!-- user info end -->

				<div class="bg-card p-3 rounded">
					<c:url var="postEdit" value="/post/edit"></c:url>
					<form id="post-form" action="${postEdit}" method="post"
						enctype="multipart/form-data">
						<div class="d-flex flex-row-reverse mb-2">
							<div id="photo-input-btn" class="btn btn-primary">Upload
								Image</div>
						</div>
						<input type="hidden" class="d-none" name="postId" value="${postView.id}">
						<textarea name="content"
							class="w-100 px-3 py-2 rounded post-input"
							placeholder="Write Something..." rows="3" value="" required>${postView.content}</textarea>
						<input id="photo-input" type="file" name="photos" accept="image/*"
							class="d-none" multiple>

						<c:choose>
							<c:when test="${empty postView.postImageList}">
								<div id="photo-preview"
									class="bg-card-2 d-none d-flex mx-auto py-2 overflow-auto rounded w-100">
								</div>
							</c:when>
							<c:otherwise>
								<c:url var="deletePhotoUrl" value="/api/post-image/delete"></c:url>
								<span class="d-none" id="delete-photo-url"
									url="${deletePhotoUrl}"></span>
								<div id="photo-preview"
									class="bg-card-2 d-flex mx-auto py-2 overflow-auto rounded w-100">
									<c:forEach var="postImage" items="${postView.postImageList}">

										<div class="photo mx-2 original-photo">
											<span class="remove-photo-btn pointer"
												postImageId="${postImage.id}"> <i class="bi bi-dash"></i>
											</span> <img class="img-fluid w-100 h-100 rounded"
												src="${postImage.imageName}" alt="image" />
										</div>

									</c:forEach>

								</div>
							</c:otherwise>
						</c:choose>

					</form>
				</div>
			</div>
		</div>
	</main>

	<c:url var="jquery" value="/resource/library/jquery.min.js"></c:url>
	<script type="text/javascript" src="${jquery}"></script>
	<c:url var="editProcess" value="/resource/js/edit-post-process.js"></c:url>
	<script type="text/javascript" src="${editProcess}"></script>
</body>
</html>
