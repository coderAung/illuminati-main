<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal" id="logout">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content w-75 mx-auto h-75 bg-card txt-white">
			<div class="py-4 px-3">
				<span class="float-end txt-white" data-bs-dismiss="modal"><i
					class="bi bi-x-lg pointer"></i></span>
				<div class="mb-4 text-center py-2 fs-5">
					<i class="bi bi-exclamation-triangle-fill me-2"></i>
					<span>Are you sure to logout ?</span>
				</div>

				<c:url var="logout" value="/logout"></c:url>
				<form action="${logout}" method="post">
					<div class="d-flex justify-content-end">
						<button class="btn logout-btn" type="submit">Logout</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>