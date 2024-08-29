<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post Detail</title>

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
					<jsp:param name="homeActive" value="menu-item-active" />
				</jsp:include>
			</div>
			<!-- sidebar end -->

			<div class="col-6 position-relative">

				<!-- new feed start -->
				<div class="p-2 rounded mb-3 post-card">
					<span class="float-end pointer"> <i
						class="bi bi-three-dots-vertical"></i>
					</span>
					<!-- user info start -->
					<div class="d-flex px-2">
						<a href="#" class="me-3"> <img
							style="width: 45px; height: 45px" class="rounded-circle"
							src="https://i.ebayimg.com/images/g/42YAAOSwtupiTgU7/s-l1200.webp">
						</a>
						<div class="d-flex flex-column">
							<a href="#" class="text-decoration-none txt-white fw-bold">
								Ko Ko </a> <small class="txt-grey">13 hours ago</small>
						</div>
					</div>
					<!-- user info end -->

					<!-- caption start -->
					<div class="mt-2 px-2">
						<p>What A Day!</p>
					</div>
					<!-- caption start -->

					<!-- Post Images start -->

					<div class="text-center post-image-container pointer">
						<img
							src="https://www.komar.de/media/catalog/product/cache/5/image/1230x/17f82f742ffe127f42dca9de82fb58b1/I/A/IADX4-079.jpg">
					</div>
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


				<!-- comments section start -->
				<!-- Example Comment -->
			<div class="bg-primary">
			<c:forEach begin="1" end="20" step="1">
				<div class="d-flex txt-text mb-3">
					<a href="#" class="me-3"> 
					<img
						style="width: 40px; height: 40px" class="rounded-circle"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNERpqjIW8laIlKLNkwOBewYyPx5bnz7PktmGBfHc63qKPkyzoxeZUX06Ooop0YHi67TI&usqp=CAU">
					</a>
					<div class="flex-grow-1">
						<div class="bg-card py-2 px-3 rounded">
							<div class="d-flex justify-content-between mb-2">
								<div>
									<a class="text-decoration-none fw-bold txt-text" href="#">Anya
										Taylor Joy</a>
									<small class="txt-grey ms-3">Just now</small>
									
								</div>
								<span class="pointer">
									<i class="bi bi-three-dots-vertical"></i>
								</span>
							</div>
							<p>Beautiful Flowers</p>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
				<!-- Add a new comment -->
				<form action="" method="post" class="d-flex w-100 bg-danger position-absolute start-0">
					<input type="hidden" name="postId" value="${post.id}"> 
					<a href="#" class="me-3"> 
						<img style="width: 40px; height: 40px" class="rounded-circle"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
					</a>
					
					<div class="w-100 d-flex">
						<div class="search-bar bg-card-2 d-flex align-items-center rounded py-2 px-3 flex-fill">
							<input type="text" name="comment"
								class="w-100 txt-text" placeholder="Say something...">						
						</div>
						<button type="submit" class="send-btn ms-2 rounded p-2">
							send
						</button>
					</div>
				</form>

			</div>
			<!-- comments section end -->
		

		</div>

	</main>
	<!-- main part end -->
</body>
</html>
