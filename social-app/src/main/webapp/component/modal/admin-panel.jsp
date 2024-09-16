<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="activeUsers" value="/admin/users/active"></c:url>
<c:url var="suspendedUsers" value="/admin/users/suspended"></c:url>
<c:url var="dashboard" value="/admin/dashboard"></c:url>

<c:choose>
	<c:when test="${active eq 'dashboard'}">
		<c:set var="dashboardActive" value="menu-item-active"></c:set>
	</c:when>
	<c:when test="${active eq 'activeUsers'}">
		<c:set var="activeUsersActive" value="menu-item-active"></c:set>
	</c:when>
	<c:when test="${active eq 'suspendedUsers'}">
		<c:set var="suspendedUsersActive" value="menu-item-active"></c:set>
	</c:when>
</c:choose>

<div class="sidebar bg-card rounded p-2 mb-3 shadow bg-app">
	<div class="text-center text-white px-4 pointer fw-bold d-block text-decoration-none">
		Admin Panel
	</div>
</div>
<div class="sidebar bg-card rounded p-2">
	<a href="${dashboard}"
		class="menu-item ${dashboardActive} py-2 px-4 pointer d-block text-decoration-none">
		Dashboard </a>
	<a href="${dashboard}#users"
		class="menu-item ${manageUserActive} py-2 px-4 pointer d-block text-decoration-none">
		Manage Users </a>
	<a href="${activeUsers}"
		class="menu-item ${activeUsersActive} py-2 px-4 pointer d-block text-decoration-none">
		Active Users </a>
	<a href="${suspendedUsers}"
		class="menu-item ${suspendedUsersActive} py-2 px-4 pointer d-block text-decoration-none">
		Suspended Users </a>
</div>
