<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="edit-form">
	<div class="modal-dialog">
		<div class="modal-content bg-card txt-white">
			<c:url var="profileEdit" value="/profile/edit"></c:url>
			<form action="${profileEdit}" method="post">
				<div class="modal-body">
					<div class="mb-3">
						<span class="fs-5">Edit Profile Info</span>
						<span class="float-end txt-white" data-bs-dismiss="modal"><i class="bi bi-x-lg pointer"></i></span>
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="name" placeholder="Name" value="${loginUser.name}">
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="phoneNumber" placeholder="Phone Number" value="${profileDetailView.phoneNumber}">
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="address" placeholder="Address" value="${profileDetailView.address}">
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="date" name="birthDay" placeholder="Birthday" value="${profileDetailView.birthDate}">
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="bio" placeholder="Bio" value="${profileDetailView.bio}">
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						
						<c:choose>
							<c:when test="${'Male' eq profileDetailView.gender.name()}">
								<c:set var="maleSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Female' eq profileDetailView.gender.name()}">
								<c:set var="femaleSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Other' eq profileDetailView.gender.name()}">
								<c:set var="otherSelected" value="selected"></c:set>
							</c:when>
						</c:choose>
						
						<select name="gender" class="w-100 txt-text ps-4">
							<option value="Empty" class="txt-grey">Gender</option>
							<option value="Male" ${maleSelected}>Male</option>
							<option value="Female" ${femaleSelected}>Female</option>
							<option value="Other" ${otherSelected}>Other</option>
						</select>
					</div>

				<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						
						<c:choose>
							<c:when test="${'Single' eq profileDetailView.relationship.name()}">
								<c:set var="singleSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Married' eq profileDetailView.relationship.name()}">
								<c:set var="marriedSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Divorced' eq profileDetailView.relationship.name()}">
								<c:set var="divorcedSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'InARelationship' eq profileDetailView.relationship.name()}">
								<c:set var="relationSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'IsComplicated' eq profileDetailView.relationship.name()}">
								<c:set var="isComplicatedSelected" value="selected"></c:set>
							</c:when>
						</c:choose>
						
						<select name="relationship" class="w-100 txt-text ps-4">
							<option value="Empty" class="txt-grey">Relationship</option>
							<option value="Single" ${singleSelected}>Single</option>
							<option value="Married" ${marriedSelected}>Married</option>
							<option value="Divorced" ${divorcedSelected}>Divorced</option>
							<option value="InARelationship" ${relationSelected}>In a Relationship</option>
							<option value="IsComplicated" ${isComplicatedSelected}>Is Complicated</option>
						</select>
					</div>

				<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>

						<c:choose>
							<c:when test="${'Student' eq profileDetailView.occupation.name()}">
								<c:set var="studentSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Actor' eq profileDetailView.occupation.name()}">
								<c:set var="actorSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Singer' eq profileDetailView.occupation.name()}">
								<c:set var="singerSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Dancer' eq profileDetailView.occupation.name()}">
								<c:set var="dancerSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'Influencer' eq profileDetailView.occupation.name()}">
								<c:set var="influencerSelected" value="selected"></c:set>
							</c:when>
							<c:when test="${'DigitalCreator' eq profileDetailView.occupation.name()}">
								<c:set var="digitalCreatorSelected" value="selected"></c:set>
							</c:when>
						</c:choose>
						
						<select name="occupation" class="w-100 txt-text ps-4">
							<option value="Empty" class="txt-grey">Occupation</option>
							<option value="Student" ${studentSelected}>Student</option>
							<option value="Actor" ${actorSelected}>Actor</option>
							<option value="Singer" ${singerSelected}>Singer</option>
							<option value="Dancer" ${dancerSelected}>Dancer</option>
							<option value="Influencer" ${influencerSelected}>Influencer</option>
							<option value="DigitalCreator" ${digitalCreatorSelected}>Digital Creator</option>
						</select>
					</div>

					<div class="d-flex justify-content-end">
						<button class="btn cancel-btn me-3" data-bs-dismiss="modal" type="button">Cancel</button>
						<button class="btn change-password-btn" type="submit">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>