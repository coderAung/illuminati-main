<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="change-password">
	<div class="modal-dialog">
		<div class="modal-content bg-card txt-white">
			<c:url var="changePassword" value="/setting/change-password"></c:url>
			<form action="${changePassword}" method="post">
				<div class="modal-body">
					<div class="mb-3">
						<span class="fs-5">Change password</span>
						<span class="float-end txt-white" data-bs-dismiss="modal"><i class="bi bi-x-lg pointer"></i></span>
					</div>
					<div class="bg-card-2 mb-3 password-input rounded px-2 py-2">
						<input class="w-100" type="password" name="oldPassword" placeholder="Old password" /> 
					</div>
					<div class="bg-card-2 mb-3 password-input rounded px-2 py-2">
						<input class="w-100" type="password" name="newPassword" placeholder="New password" /> 
					</div>
					<div class="d-flex justify-content-end">
						<button class="btn cancel-btn me-3" data-bs-dismiss="modal" type="button">Cancel</button>
						<button class="btn change-password-btn" type="submit">Change</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>