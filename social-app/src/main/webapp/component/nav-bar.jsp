<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>

<c:url var="logo" value="/img/circle-i-logo.png"></c:url>

<div class="bg-card navbar txt-white py-2 d-flex mb-3 sticky-top shadow">
	<div class="d-flex align-items-center mx-auto text-center logo-container pointer">
		<div class="logo">
			<i class="fa-solid fa-i"></i>
		</div>
		<span class="ms-3 fs-5">Illuminati</span>
	</div>

	<!-- Search Bar start -->
	<div class="d-flex search-bar py-2 px-3 bg-card-2 w-25 rounded">
		<form action="#" class="w-100" method="get">
			<input type="text" name="" class="w-100 rounded txt-white"
				placeholder="Type a word...">
		</form>
	</div>
	<!-- Search Bar end -->

	<!-- nav-links start -->
	<div
		class="nav-links d-flex justify-content-evenly w-50 position-relative">
		<a href="${home}" class="nav-link text-white ${param.homeActive}">Home</a>
		<a href="${profile}"
			class="nav-link text-white ${param.profileActive}">Profile</a> <a
			href="${notifications}"
			class="nav-link text-white ${param.noificationActive}">Notifications</a>
		<a href="${setting}"
			class="nav-link text-white ${param.settingActive}">Setting</a>
	</div>
	<!-- nav-links end -->
</div>

