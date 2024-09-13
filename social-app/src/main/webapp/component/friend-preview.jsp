<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty friendViews}">
	<div class="bg-card txt-white mb-2 p-3 color-white rounded">
		<div class="mb-3 d-flex justify-content-between">
			<span class="fs-5"> Friends <i class="bi bi-dot"></i> <c:choose>
					<c:when test="${1 eq friendCount}">
						<small class="txt-grey fs-6">${friendCount} friend</small>
					</c:when>
					<c:when test="${1 gt friendCount}">
						<small class="txt-grey fs-6">${friendCount} friends</small>
					</c:when>
				</c:choose>
			</span> <a href="#" class="text-decoration-none btn btn-normal"> <i
				class="bi bi-search"></i> Find a friend
			</a>
		</div>

		<div class="row row-cols-2 row-cols-md-4 g-3">
			<c:forEach var="fv" items="${friendViews}">
				<div class="col pointer">
					<div class="rounded text-center friend-card">
						<img src="${fv.profileImage}" alt="profile"
							class="img-fluid rounded-top">
						<div class="fs-6 py-2">${fv.name}</div>
						<c:url var="profile" value="/other/profile">
							<c:param name="userId" value="${fv.friendId}"></c:param>
						</c:url>
						<a href="${profile}" class="d-none other-profile-link"></a>
					</div>
				</div>
			</c:forEach>
			<div class="col d-flex justify-content-center my-auto">
				<c:choose>
					<c:when test="${param.userId eq loginUser.id}">
						<c:url var="friends" value="/profile/friends"></c:url>
					</c:when>
					<c:otherwise>
						<c:url var="friends" value="/other/friends">
							<c:param name="userId" value="${param.userId}"></c:param>
						</c:url>
					</c:otherwise>
				</c:choose>
				<a href="${friends}"
					class="d-flex color-blue flex-column link-hover text-decoration-none align-items-center">
					<i class="bi bi-arrow-right-circle fs-1"></i> <span>See All</span>
				</a>
			</div>
		</div>
	</div>
</c:if>

