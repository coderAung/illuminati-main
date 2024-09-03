<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="bg-card shadow create-post txt-white rounded mb-2 p-3">
	<div class="mb-3 d-flex align-items-center">
		<span class="fs-5 me-3">Create a post</span>
	</div>
	<div class="d-flex">
		<a href="#" class="me-3"> <img style="width: 45px; height: 45px"
			class="rounded-circle"
			src="${empty loginUser.profileImage ? 'https://www.freeiconspng.com/thumbs/profile-icon-png/profile-icon-9.png' : loginUser.profileImage }"
		>
		</a>
		<div onclick="goToCreatePostPage()"
			class="pointer post-form rounded flex-fill d-flex align-items-center px-3">
			Write Something...
		</div>
		<c:url var="createPostPage" value="/post/create"></c:url>
		<a href="${createPostPage}" id="createPostPage" class="d-none">create post page</a>
	</div>
</div>
