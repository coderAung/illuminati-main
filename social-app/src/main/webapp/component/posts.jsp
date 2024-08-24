<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid px-0 color-white">

	<c:forEach begin="1" end="5">
		<!-- new feed start -->
		<div class="p-2 rounded mb-2 post-card">
			<span class="float-end pointer"> <i
				class="bi bi-three-dots-vertical"></i>
			</span>
			<!-- user info start -->
			<div class="d-flex px-2">
				<a href="#" class="me-3"> <img style="width: 45px; height: 45px"
					class="rounded-circle"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
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
					src="https://www.ugaoo.com/cdn/shop/articles/shutterstock_223679731.jpg?v=1661873480">
			</div>
			<!-- Post Images end -->

			<!-- comment and share section -->
			<div
				class=" d-flex align-items-center pt-1 text-center">
				<a
					class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
					Comment <i class="bi bi-dot"></i> <small class="txt-grey">20 comments</small>
					</a>
				<span class="mx-2">|</span> <a
					class="py-2 pointer link w-100 txt-white w-50 text-decoration-none">
					Share <i class="bi bi-dot"></i> <small class="txt-grey">20 shares</small>
					</a>
			</div>

		</div>
		<!-- new feed end -->
	</c:forEach>

</div>
