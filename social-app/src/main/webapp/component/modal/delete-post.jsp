<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal" id="logout">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content bg-card txt-white">
			<div class="modal-body">
				<div class="mb-3 text-center">
					<span class="fs-5">Are you sure to delete the post?</span> <span
						class="float-end txt-white" data-bs-dismiss="modal"><i
						class="bi bi-x-lg pointer"></i></span>
				</div>
				
				<div class="d-flex justify-content-center">
					<button class="btn logout-btn" type="submit">Delete</button>
				</div>

			</div>
		</div>
	</div>
</div>