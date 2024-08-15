<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>

<div class="navbar py-2 d-flex mb-3 shadow">
	<div class="logo mx-auto">
		<img src="${image}" alt="Logo">
	</div>

	<!-- Search Bar start -->
	<div class="search-bar">
		<form action="#" class="w-100" method="get">
			<i class="fas fa-search icon"></i>
			<input type="text" name="query"
			class="w-100 ps-5 pe-2 py-2 rounded"
				placeholder="Type a word...">
		</form>
	</div>
	<!-- Search Bar end -->

	<!-- nav-links start -->
	<div class="nav-links d-flex justify-content-evenly w-50 position-relative">
		<a href="${home}" class="nav-link text-white">Home</a>
		<a href="${profile}" class="nav-link text-white">Profile</a>
		<a href="${notifications}" class="nav-link text-white">Notifications</a>
		<a href="${setting}" class="nav-link text-white">Setting</a>
	</div>
	<!-- nav-links end -->
</div>
