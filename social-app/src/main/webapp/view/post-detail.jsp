<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post Detail</title>

<c:url value="/resource/css2/profile.css" var="css2"></c:url>
<link rel="stylesheet" href="${css2}">
<jsp:include page="/resource/library/bootstrap.jsp"></jsp:include>
<jsp:include page="/resource/library/icon.jsp"></jsp:include>

<c:url var="navbarCss" value="/resource/css/nav-bar.css"></c:url>
<link href="${navbarCss}" rel="stylesheet">

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">

<style>
.comment {
	background-color: #f0f0f0;
	padding: 10px; /* Add some padding inside the comment box */
	border-radius: 8px; /* Rounded corners for the comment box */
	margin-bottom: 10px; /* Space between comments */
}

.comment p {
	margin-bottom: 0;
	/* Remove extra margin at the bottom of the paragraph */
}

.comment .d-flex {
	align-items: center;
	/* Align items in the comment to be centered vertically */
}

.comment img {
	border-radius: 50%; /* Ensure the profile image is circular */
}

.comment-box {
	margin-top: 20px; /* Space above the comment box */
}

.reply-input {
	display: none; /* Initially hide the reply input field */
	margin-top: 10px;
}
</style>

</head>
<body>
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="profileActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main part start -->
	<main>
		<div class="container-fluid row">
			<!-- LEFT start -->
			<jsp:include page="/component/sidebar.jsp"></jsp:include>
			<!-- RIGHT start -->
			<div class="col-8">

				<div class="container-fluid px-0">
					<div class="bg-pale p-2 rounded mb-2 post-card">
						<span class="float-end pointer"> <i
							class="bi bi-three-dots-vertical"></i>
						</span>
						<!-- user info start -->
						<div class="d-flex">
							<a href="#" class="me-3"> <img
								style="width: 50px; height: 50px" class="rounded-circle"
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
							</a>
							<div class="d-flex flex-column">
								<a href="#" class="text-decoration-none fw-bold"> Ko Ko </a> <small
									class="text-muted">13 hours ago</small>
							</div>
						</div>
						<!-- user info end -->

						<!-- caption start -->
						<div class="mt-2 px-2">
							<p>What A Day!</p>
						</div>
						<!-- caption end -->

						<!-- Post Images start -->
						<div class="p-2 text-center post-image-container">
							<img class="rounded"
								src="https://www.ugaoo.com/cdn/shop/articles/shutterstock_223679731.jpg?v=1661873480">
						</div>
						<!-- Post Images end -->

						<!-- comment and share section -->
						<div
							class="d-flex align-items-center border-purple rounded-bottom text-center">
							<a class="py-2 pointer w-50 text-decoration-none purple fw-bold">Comment</a>
							<span class="purple fw-bold">|</span> <a
								class="py-2 pointer w-50 text-decoration-none purple fw-bold">Share</a>
						</div>

						<!-- comments section start -->
						<!-- Example Comment -->
						<div class="d-flex">
							<a href="#" class="me-3"> <img
								style="width: 40px; height: 40px" class="rounded-circle"
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNERpqjIW8laIlKLNkwOBewYyPx5bnz7PktmGBfHc63qKPkyzoxeZUX06Ooop0YHi67TI&usqp=CAU">
							</a>
							<div class="flex-grow-1">
								<div class="comment bg-light p-3 rounded">
									<div class="d-flex justify-content-between">
										<span class="fw-bold">Anya Taylor Joy</span> <small
											class="text-muted">Just now</small>
									</div>
									<p>Beautiful Flowers</p>
								</div>

								<!-- Nested comments -->
								<div class="ms-5">
									<c:forEach var="reply" items="${comment.replies}">
										<div class="d-flex">
											<a href="#" class="me-3"> <img
												style="width: 30px; height: 30px" class="rounded-circle"
												src="${reply.userImageUrl}">
											</a>
											<div class="flex-grow-1">
												<div class="comment bg-grey p-2 rounded">
													<div class="d-flex justify-content-between">
														<span class="fw-bold">${reply.userName}</span> <small
															class="text-muted">${reply.timeAgo}</small>
													</div>
													<p>${reply.text}</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>

								<!-- Reply to comment -->
								<a href="#" class="text-decoration-none text-muted"
									onclick="toggleReply(this); return false;">Reply</a>

								<!-- Reply input -->
								<div class="reply-input" style="display: none;">
									<input type="text" class="form-control"
										placeholder="Write a reply...">
									<button class="btn btn-primary mt-2"
										onclick="sendReply(this); return false;">Send</button>
								</div>
							</div>
						</div>



						<!-- Example Comment -->
						<!-- Example Comment -->
						<div class="d-flex">
							<a href="#" class="me-3"> <img
								style="width: 40px; height: 40px" class="rounded-circle"
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNERpqjIW8laIlKLNkwOBewYyPx5bnz7PktmGBfHc63qKPkyzoxeZUX06Ooop0YHi67TI&usqp=CAU">
							</a>
							<div class="flex-grow-1">
								<div class="comment bg-light p-3 rounded">
									<div class="d-flex justify-content-between">
										<span class="fw-bold">Anya Taylor Joy</span> <small
											class="text-muted">Just now</small>
									</div>
									<p>Beautiful Flowers</p>
								</div>

								<!-- Nested comments -->
								<div class="ms-5">
									<c:forEach var="reply" items="${comment.replies}">
										<div class="d-flex">
											<a href="#" class="me-3"> <img
												style="width: 30px; height: 30px" class="rounded-circle"
												src="${reply.userImageUrl}">
											</a>
											<div class="flex-grow-1">
												<div class="comment bg-grey p-2 rounded">
													<div class="d-flex justify-content-between">
														<span class="fw-bold">${reply.userName}</span> <small
															class="text-muted">${reply.timeAgo}</small>
													</div>
													<p>${reply.text}</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>

								<!-- Reply to comment -->
								<a href="#" class="text-decoration-none text-muted"
									onclick="toggleReply(this); return false;">Reply</a>

								<!-- Reply input -->
								<div class="reply-input" style="display: none;">
									<input type="text" class="form-control"
										placeholder="Write a reply...">
									<button class="btn btn-primary mt-2"
										onclick="sendReply(this); return false;">Send</button>
								</div>
							</div>
						</div>


						<hr>

						<!-- Add a new comment -->
						<form action="addComment" method="post"
							class="d-flex mt-2 comment-box">
							<input type="hidden" name="postId" value="${post.id}"> <a
								href="#" class="me-3"> <img
								style="width: 40px; height: 40px" class="rounded-circle"
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
							</a> <input type="text" name="commentText"
								class="form-control comment-input"
								placeholder="Add a comment...">
							<button type="submit" class="btn btn-primary ms-2">
								<i class="bi bi-send-fill"></i>
							</button>
						</form>

					</div>
					<!-- comments section end -->

				</div>
				<!-- post-card end -->
			</div>
			<!-- RIGHT end -->
		</div>
		
	</main>
	<!-- main part end -->
	<script>
	
	function toggleReply(replyLink) {
	    var replyInput = replyLink.nextElementSibling; // Get the next sibling element (reply input)
	    if (replyInput.style.display === "none" || replyInput.style.display === "") {
	        replyInput.style.display = "block"; // Show the reply input if hidden
	    } else {
	        replyInput.style.display = "none"; // Hide the reply input if visible
	    }
	}

	function sendReply(sendButton) {
	    var replyInput = sendButton.previousElementSibling; // Get the input field before the button
	    var replyText = replyInput.value.trim(); // Get the trimmed reply text

	    if (replyText) {
	        // Here you would typically make an AJAX request to send the reply to the server
	        // For now, we will just append it to the comment section for demonstration

	        var commentSection = sendButton.parentNode.parentNode; // Get the comment section
	        var newReply = document.createElement('div');
	        newReply.className = 'd-flex';
	        newReply.innerHTML = `
	            <a href="#" class="me-3">
	                <img style="width: 30px; height: 30px" class="rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSseyxPBdJXmPED_RXpfKnNe_K-aAq-R94Hyg&s">
	            </a>
	            <div class="flex-grow-1">
	                <div class="comment bg-grey p-2 rounded">
	                    <div class="d-flex justify-content-between">
	                        <span class="fw-bold">Ko Ko</span> <small class="text-muted">Just now</small>
	                    </div>
	                    <p>Thanks</p>
	                </div>
	            </div>
	        `;
	        commentSection.querySelector('.ms-5').appendChild(newReply); // Add the new reply to the nested comments section

	        replyInput.value = ''; // Clear the input field
	        toggleReply(sendButton); // Hide the reply input
	    }
	}

	</script>
</body>
</html>
