<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty friendViews}">
	<div class="bg-card txt-white mb-2 p-3 color-white rounded">
		<div class="mb-3 d-flex justify-content-between">
			<span class="fs-5"> Friends <i class="bi bi-dot"></i> <small
				class="txt-grey fs-6">20 friends</small>
			</span> <a href="#" class="text-decoration-none btn btn-normal"> <i
				class="bi bi-search"></i> Find a friend
			</a>
		</div>

		<div class="row row-cols-2 row-cols-md-4 g-3">
			<c:forEach var="fv" items="${friendViews}">
				<div class="col pointer">
					<div class="rounded text-center friend-card">
						<img
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNERpqjIW8laIlKLNkwOBewYyPx5bnz7PktmGBfHc63qKPkyzoxeZUX06Ooop0YHi67TI&usqp=CAU"
							alt="profile" class="img-fluid rounded-top">
						<div class="fs-6 py-2">${fv.name}</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</c:if>

