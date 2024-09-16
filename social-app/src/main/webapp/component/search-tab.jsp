<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="rounded bg-card txt-text p-2 d-flex mb-3 shadow">

	<c:choose>
		<c:when test="${param.active eq 'post'}">
			<div class="bg-app text-white w-50 text-center py-1 rounded pointer">Posts</div>
			<span class="mx-2 bg-app rounded" style="padding: 0px 2px;"></span>
			<c:url var="searchUser" value="/search/user">
				<c:param name="word" value="${word}"></c:param>
			</c:url>
			<div id="search-user-btn"
				class="w-50 text-center py-1 rounded pointer search-key">
				User <a href="${searchUser}" class="d-none"></a>
			</div>
		</c:when>
		<c:otherwise>
			<div class="w-50 text-center py-1 rounded pointer search-key">Posts</div>
			<span class="mx-2 bg-app rounded" style="padding: 0px 2px;"></span>
			<div class="bg-app txt-white w-50 text-center py-1 rounded pointer">User</div>
		</c:otherwise>
	</c:choose>

</div>
