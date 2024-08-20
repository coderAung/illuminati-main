<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid px-0 color-white">

	<c:forEach begin="1" end="5">
		<!-- new feed start -->
		<div class="bg-pale p-2 rounded mb-2 post-card">
			<span class="float-end pointer"> <i
				class="bi bi-three-dots-vertical"></i>
			</span>
			<!-- user info start -->
			<div class="d-flex">
				<a href="#" class="me-3"> <img style="width: 50px; height: 50px"
					class="rounded-circle"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
				</a>
				<div class="d-flex flex-column">
					<a href="#" class="text-decoration-none color-white fw-bold">
						Ko Ko </a> <small class="color-white">13 hours ago</small>
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
				<img
					src="https://www.ugaoo.com/cdn/shop/articles/shutterstock_223679731.jpg?v=1661873480">
			</div>
			<!-- Post Images end -->

			<!-- comment and share section -->
			<div
				class=" d-flex color-white p-1 align-items-center border-common rounded-bottom text-center">
				<a
					class="py-2 pointer link w-100 color-white w-50 text-decoration-none">
					Comment <i class="bi bi-dot"></i> <small>20 comments</small>
					</a>
				<span class="mx-2">|</span> <a
					class="py-2 pointer link w-100 color-white w-50 text-decoration-none">
					Share <i class="bi bi-dot"></i> <small>20 shares</small>
					</a>
			</div>

		</div>
		<!-- new feed end -->
	</c:forEach>


	<div class="bg-pale p-2 rounded mb-2 post-card">
		<span class="float-end pointer"> <i
			class="bi bi-three-dots-vertical"></i>
		</span>

		<!-- user info start -->
		<div class="d-flex">
			<a href="#" class="me-3"> <img style="width: 50px; height: 50px"
				class="rounded-circle"
				src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
			</a>
			<div class="d-flex flex-column">
				<a href="#" class="text-decoration-none color-white fw-bold">Ko
					Ko</a> <small class="color-white">13 hours ago</small>
			</div>
		</div>
		<!-- user info end -->

		<!-- caption start -->
		<div class="mt-2 px-2">
			<p>What A Day!</p>
		</div>
		<!-- caption end -->

		<!-- Post Images Carousel start -->
		<div class="post-image-container">
			<div id="postCarousel" class="carousel slide" data-bs-ride="carousel">
				<!-- Indicators -->
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#postCarousel"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#postCarousel"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#postCarousel"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>

				<!-- Carousel items -->
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img
							src="https://www.ugaoo.com/cdn/shop/articles/shutterstock_223679731.jpg?v=1661873480"
							class="d-block w-100 rounded" alt="Image 1">
					</div>
					<div class="carousel-item">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s"
							class="d-block w-100 rounded" alt="Image 2">
					</div>
					<div class="carousel-item">
						<img src="https://via.placeholder.com/800x400.png"
							class="d-block w-100 rounded" alt="Image 3">
					</div>
				</div>

				<!-- Controls -->
				<button class="carousel-control-prev" type="button"
					data-bs-target="#postCarousel" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#postCarousel" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<!-- Post Images Carousel end -->

		<!-- comment and share section -->
		<div
			class="d-flex color-white p-1 align-items-center border-common rounded-bottom text-center">
			<a
				class="py-2 pointer link w-100 color-white text-decoration-none fw-bold">Comment</a>
			<span class="mx-2">|</span> <a
				class="py-2 pointer link w-100 color-white text-decoration-none fw-bold">Share</a>
		</div>
	</div>


</div>
