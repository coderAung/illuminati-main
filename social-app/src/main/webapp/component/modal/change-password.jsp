<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="change-password">
	<div class="modal-dialog">
		<div class="modal-content bg-pale color-white">
			<div class="modal-header fs-5">Change Password</div>
			<form action="">
				<div class="modal-body">
					<div class="mb-3 bg-common password-input rounded px-2 py-2">
						<input class="w-100" type="password" placeholder="Old password" /> 
					</div>
					<div class="bg-common password-input rounded px-2 py-2">
						<input class="w-100" type="password" placeholder="New password" /> 
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn cancel-btn" data-bs-dismiss="modal" type="button">Cancel</button>
					<button class="btn change-password-btn" type="submit">Change</button>
				</div>
			</form>
		</div>
	</div>
</div>