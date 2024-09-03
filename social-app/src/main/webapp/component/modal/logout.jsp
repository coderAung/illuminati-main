<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="logout">
	<div class="modal-dialog">
		<div class="modal-content bg-card txt-white">
			<div class="modal-body">
				<div class="mb-3 text-center">
					<span class="fs-5">Are you sure to logout?</span> <span
						class="float-end txt-white" data-bs-dismiss="modal"><i
						class="bi bi-x-lg pointer"></i></span>
				</div>
				
				<c:url var="logout" value="/logout"></c:url>
				<form action="${logout}" method="post">
					<div class="d-flex justify-content-center">
						<button class="btn logout-btn" type="submit">Logout</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>