<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Illuminati | Post</title>
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
		<jsp:param value="nav-link-active" name="" />
	</jsp:include>
	<!-- nav bar end -->

	<main>
		<div class="container-fluid row">

			<!-- sidebar start -->
			<div class="col-3 left-side mx-3">
				<jsp:include page="/component/sidebar.jsp">
					<jsp:param name="profileActive" value="" />
				</jsp:include>
			</div>
			<!-- sidebar end -->
			<div class="col-6 px-0">
					<!-- user info start -->
					<div class="d-flex mb-3">
						<a href="#" class="me-3"> <img
							style="width: 45px; height: 45px; object-fit: cover;" class="rounded"
							src="${loginUser.profileImage}">
						</a>
						<div class="d-flex flex-column">
							<a href="#" class="text-decoration-none txt-white fw-bold">
								${loginUser.name}
							</a>
							<small class="txt-grey">Creating new post</small>
						</div>
						<div class="d-flex flex-fill py-1 flex-row-reverse">
							<button id="post-create-btn" class="btn btn-app w-50 rounded">Post</button>
						</div>
					</div>
					<!-- user info end -->

				<div class="bg-card p-3 rounded">
					<c:url var="postCreate" value="/post/create"></c:url>
					<form id="post-form" action="${postCreate}" method="post"
						enctype="multipart/form-data">
						<div class="d-flex flex-row-reverse mb-2">
							<div id="photo-input-btn" class="btn btn-primary">Upload Image</div>
						</div>
						<textarea name="content"
							class="w-100 px-3 py-2 rounded post-input"
							placeholder="Write Something..." rows="3" value='' required></textarea>
						<input id="photo-input" type="file" name="photos" accept="image/*" class="d-none" multiple>
						<div id="photo-preview" class="bg-card-2 d-none d-flex mx-auto py-2 overflow-auto rounded w-100">
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
<c:url var="script" value="/resource/js/script.js"></c:url>
<script type="text/javascript" src="${script}"></script>
</body>
</html>
