<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Friend Request</title>
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

				<div class="mb-3 d-flex justify-content-between">
					<small class="txt-grey">20 friend request</small>
					<div class="d-flex search-bar py-2 px-3 bg-card-2 w-50 rounded">
						<input type="text" class="w-100 rounded txt-white"
							placeholder="Find friends">
					</div>
				</div>

				<c:forEach begin="1" end="20" step="1">
					<div
						class="mb-3 rounded d-flex justify-content-between align-items-center px-3 py-2 txt-text bg-card-normal">
						<div class="d-flex align-items-center">
							<div class="profile-photo">
								<img
									src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBL9Os1k7XoZ8FSNrzu3iawOmBoP2Pfoss9NpJ67ZroIltqXpfSZ3uqZgpUFXVby_n6j0&usqp=CAU">
							</div>
							<div class="ms-3 d-flex flex-column">
								<a class="text-decoration-none pointer txt-text fw-bold"
									href="#">Kyi Pyar</a> <small class="txt-grey">3 mutual
									friends</small>
							</div>
						</div>
						<div>
							<button type="button" class="btn btn-deep me-3">Confirm</button>
							<button type="button" class="btn logout-btn">Delete</button>
						</div>
					</div>
				</c:forEach>




				<div
					class="profile-info border rounded d-flex justify-content-between">
					<div class="d-flex align-items-center">
						<div class="profile-photo">
							<img
								src="data:image/jpeg;base64,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">
						</div>
						<div class="profile-name">
							<b>Malar</b><br> <small class="text-muted">10 mutual
								friends</small>
						</div>
					</div>
					<div>
						<button type="button" class="btn btn-primary">Confirm</button>
						<button type="button" class="btn btn-primary">Delete</button>
					</div>
				</div>



				<div
					class="profile-info border rounded d-flex justify-content-between">
					<div class="d-flex align-items-center">
						<div class="profile-photo">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLgQuAVzO6KzgjozXp4Mdn6cAH_GiPS5ZHwQ&s">
						</div>
						<div class="profile-name">
							<b>Zuu Zuu</b><br> <small class="text-muted">1
								mutual friends</small>
						</div>
					</div>
					<div>
						<button type="button" class="btn btn-primary">Confirm</button>
						<button type="button" class="btn btn-primary">Delete</button>
					</div>
				</div>



				<div
					class="profile-info border rounded d-flex justify-content-between">
					<div class="d-flex align-items-center">
						<div class="profile-photo">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDbFGjB-EW1k6T1DtveUgK2x97KmogfBvssg&s">
						</div>
						<div class="profile-name">
							<b>Thura</b><br> <small class="text-muted">20 mutual
								friends</small>
						</div>
					</div>
					<div>
						<button type="button" class="btn btn-primary">Confirm</button>
						<button type="button" class="btn btn-primary">Delete</button>
					</div>
				</div>



				<div
					class="profile-info border rounded d-flex justify-content-between">
					<div class="d-flex align-items-center">
						<div class="profile-photo">
							<img
								src="data:image/jpeg;base64,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">
						</div>
						<div class="profile-name">
							<b>Kyaw Swar</b><br> <small class="text-muted">15
								mutual friends</small>
						</div>
					</div>
					<div>
						<button type="button" class="btn btn-primary">Confirm</button>
						<button type="button" class="btn btn-primary">Delete</button>
					</div>
				</div>



				<div
					class="profile-info border rounded d-flex justify-content-between">
					<div class="d-flex align-items-center">
						<div class="profile-photo">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVAv601Xv00kTyH_O8MvHKaBVJXXhCB21P_Q&s">
						</div>
						<div class="profile-name">
							<b>Zar Ni</b><br> <small class="text-muted">33
								mutual friends</small>
						</div>
					</div>
					<div>
						<button type="button" class="btn btn-primary">Confirm</button>
						<button type="button" class="btn btn-primary">Delete</button>
					</div>
				</div>



			</div>
		</div>

	</main>

</body>
</html>