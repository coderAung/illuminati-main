<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="home" value="/home"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="friends" value="/profile/friends"></c:url>
<c:url var="friendRequests" value="/profile/friend-requests"></c:url>
<c:url var="saved" value="/profile/saved"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>

		<div class="bg-card rounded mb-2 p-3">
			<a href="${profile}" class="me-3 text-decoration-none">
				<img style="width: 50px; height: 50px"
					class="rounded"
					src="${loginUser.profileImage}">
			</a>
			<a href="${profile}" class="text-decoration-none txt-text">
				<span>
					${loginUser.name}
				</span>			
			</a>
			
		</div>

	<!-- ==========================side bar start ======================================-->
	<div class="sidebar bg-card rounded mb-2 p-2">

		<a href="${home}"
			class="menu-item ${param.homeActive} py-2 px-4 pointer d-block text-decoration-none">
			Home </a> 
		<a href="${profile}"
			class="menu-item ${param.profileActive} py-2 px-4 pointer d-block text-decoration-none">
			Profile </a> 
		<a href="${friends}"
			class="menu-item ${param.friendsActive} py-2 px-4 pointer d-block text-decoration-none">
			Friends</a> 
		<a href="${friendRequests}"
			class="menu-item ${param.friendRequestActive} py-2 px-4 pointer d-block text-decoration-none">
			Friend Requests</a>
	</div>

	<div class="sidebar bg-card rounded p-2">

		<a href="${saved}"
			class="menu-item ${param.savedActive} py-2 px-4 pointer d-block text-decoration-none">
			Saved </a> 
		<a href="${notifications}"
			class="menu-item ${param.notificationActive} py-2 px-4 pointer d-block text-decoration-none">
			Notifications </a>
		<a href="${setting}"
			class="menu-item ${param.settingActive} py-2 px-4 pointer d-block text-decoration-none">
			Setting </a>
	</div>
	<!-- ==========================side bar end ======================================-->
