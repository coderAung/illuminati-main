<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="d-flex mb-2 px-0">
	<div class="card bg-pale color-white">
		<!-- Cover Image -->
		<div class="position-relative" style="max-height: 250px">
			<img
				src="https://i.pinimg.com/originals/82/30/f9/8230f9270b33e80727fa422b73e4b366.jpg"
				alt="Cover Image" class="img-fluid rounded-top w-100 h-100"
				style="object-fit: cover;">
			<!-- Profile Image (overlapping cover image at the bottom left but inside) -->

			<div class="position-absolute bottom-1 start-1"
				style="left: 20px; bottom: -40px;">
				<img
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s"
					alt="Profile Image" class="rounded-circle border border-white"
					style="width: 150px; height: 150px;">
			</div>
		</div>

		<!-- Profile Information -->
		<div class="card-body position-relative">
			<div class="w-50 d-flex float-end justify-content-end">
				<a href="#" class="btn btn-common w-25 me-3">Detail</a> <a href="#"
					class="btn w-25 btn-common">Edit</a>
			</div>
			<div class="mt-5 ms-3">
				<div class="h5">Khin Thaw Tar</div>
				<i class="color-white">Life is Gift</i>
			</div>
		</div>
	</div>
</div>
