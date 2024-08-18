<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-3 left-side">
	<!-- ==========================side bar start ======================================-->
	<div class="sidebar bg-common rounded mb-2 p-2">

		<a href="#"
			class="menu-item ${param.homeActive} py-2 px-4 pointer d-block text-decoration-none">
			Home </a> <a href="#"
			class="menu-item ${param.profileActive} py-2 px-4 pointer d-block text-decoration-none">
			Profile </a> <a href="#"
			class="menu-item ${param.friendsActive} py-2 px-4 pointer d-block text-decoration-none">
			Friends</a> <a href="#"
			class="menu-item ${param.friendRequestActive} py-2 px-4 pointer d-block text-decoration-none">
			Friend Requests</a>
	</div>

	<div class="sidebar bg-common rounded p-2">

		<a href="#"
			class="menu-item ${param.savedActive} py-2 px-4 pointer d-block text-decoration-none">
			Saved </a> <a href="#"
			class="menu-item ${param.notificationActive} py-2 px-4 pointer d-block text-decoration-none">
			Notifications </a> <a href="#"
			class="menu-item ${param.settingActive} py-2 px-4 pointer d-block text-decoration-none">
			Setting </a>
	</div>
	<!--  =====================side bar end  -->
</div>