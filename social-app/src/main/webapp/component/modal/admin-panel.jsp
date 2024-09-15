<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="users" value="/admin/users"></c:url>

<div class="sidebar bg-card rounded p-2 mb-3 shadow bg-app">
	<div class="py-2 text-center text-white px-4 pointer fw-bold d-block text-decoration-none">
		Admin Panel
	</div>
</div>
<div class="sidebar bg-card rounded p-2">
	<a href="${users}"
		class="menu-item ${param.usersActive} py-2 px-4 pointer d-block text-decoration-none">
		Manage Users </a>
	<a href="${users}"
		class="menu-item ${param.usersActive} py-2 px-4 pointer d-block text-decoration-none">
		Suspended Users </a>
</div>
