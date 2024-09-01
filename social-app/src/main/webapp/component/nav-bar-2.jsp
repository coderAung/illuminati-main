<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="home" value="/welcome"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="register" value="/register"></c:url>

<c:url var="logo" value="/img/circle-i-logo.png"></c:url>

<div class="bg-card navbar py-3 mb-3 sticky-top shadow px-3">
	<div class="d-flex text-center logo-container mx-auto pointer">
		<div class="logo">
			<i class="fa-solid fa-i"></i>
		</div>
		<span class="ms-3 fs-5 txt-text">Illuminati</span>
	</div>
	<!-- nav-links start -->
	<div
		class="nav-links d-flex justify-content-evenly w-50 position-relative">
		<a href="${home}" class="nav-link ${param.homeActive}">Home</a>
		<a href="${login}"
			class="nav-link ${param.loginActive}">Login</a>
		<a href="${register}"
			class="nav-link ${param.registerActive}">Register</a>
	</div>
	<!-- nav-links end -->
</div>

