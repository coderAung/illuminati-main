<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="bootstrap" value="/resource/library/bootstrap.min.css"></c:url>
<link rel="stylesheet" href="${bootstrap}" />

<c:url var="faIcon" value="https://kit.fontawesome.com/3adf10d5eb.js"></c:url>
<script src="${faIcon}"></script>

<c:choose>
	<c:when test="${'LIGHT' eq loginUser.theme.name()}">
		<c:url var="light" value="/resource/css/light.css"></c:url>
		<link rel="stylesheet" href="${light}" />
	</c:when>
	<c:when test="${'DARK' eq loginUser.theme.name()}">
		<c:url var="dark" value="/resource/css/dark.css"></c:url>
		<link rel="stylesheet" href="${dark}" />
	</c:when>
	<c:when test="${'BLUE' eq loginUser.theme.name()}">
		<c:url var="blue" value="/resource/css/blue.css"></c:url>
		<link rel="stylesheet" href="${blue}" />
	</c:when>
	<c:otherwise>
		<c:url var="style" value="/resource/css/style.css"></c:url>
		<link rel="stylesheet" href="${style}" />
	</c:otherwise>
</c:choose>


<c:url var="bsIcon"
	value="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"></c:url>
<link rel="stylesheet" href="${bsIcon}">

<c:url var="bsJs"
	value="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></c:url>
<script src="${bsJs}"></script>
