<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div
	class="mb-2 bg-card txt-white position-relative rounded color-white">
	<!-- Cover Image -->

	<div class="cover-img rounded-top pointer w-100 p-1">
		<img alt="" class="rounded-top"
			src="https://platform.polygon.com/wp-content/uploads/sites/2/chorus/uploads/chorus_asset/file/18943387/mark_85.jpg?quality=90&strip=all&crop=10.44921875,0,79.1015625,100">
	</div>

	<div class="profile-img position-absolute pointer">
		<img alt=""
			src="https://i.ebayimg.com/images/g/42YAAOSwtupiTgU7/s-l1200.webp">
	</div>

	<!-- Profile Information -->
	<div class="px-3 pb-3 mt-5">
		<div class="w-50 d-flex float-end justify-content-end">
			<a href="#" class="btn btn-normal w-25 me-3">Detail</a> <a href="#"
				class="btn w-25 btn-normal">Edit</a>
		</div>

		<div>
			<div class="h5">${profileView.name}</div>
			<i class="txt-grey">Life is Gift</i>
		</div>
	</div>
</div>
