$("document").ready(() => {
	$("#comment-btn").click(() => {
		createComment()
	})
})


function createComment() {
	const url = $("#comment-btn").attr("url")
	const commentContent = $("#commentContent").val()
	const postId = $("#post-id").val()
	if (commentContent && postId) {

		$.post(
			url,
			{
				"postId": postId,
				"commentContent": commentContent,
			},
			(response) => {
				if (response.result === "success") {

					$("#commentContent").val('')

					const count = $("#comment-count").attr("count")
					if (count === "0") {
						window.location.reload()
					} else {
						const cv = response.commentView
						
						const commentCount = parseInt(count) + 1
						
						$("#comment-count").attr("count", commentCount)
						$("#comment-count-display").text(`( ${commentCount} )`)
						if(commentContent > 1) {
							$("#comment-count").text(`${commentCount} comment`)
						} else {
							$("#comment-count").text(`${commentCount} comments`)
						}
						
						const loginUserId = $("#login-user").val()
						
						let commentControlPanel = ""
						if(cv.userId == loginUserId || postId == loginUserId) {
							commentControlPanel = `
							<span class="pointer comment-control-btn">
								<i class="bi bi-three-dots-vertical"></i>
							</span>
							<div
								class="shadow w-25 p-2 rounded position-absolute comment-control-panel float-end top-0 end-0 mt-2">
								<div commentId="${cv.id}"
									class="pointer rounded text-danger">
									<span class="text-decoration-none">Delete</span>
								</div>
							</div>
							`
						}
						

						$("#comment-container").prepend(`
					
					<div class="d-flex txt-text mb-3">
						<a href="#" class="me-3">
							<img
							style="width: 40px; height: 40px" class="rounded-circle"
							src="${cv.profileImage}"
							alt="Commenter Profile Picture">
						</a>
						<div class="flex-grow-1">
							<div class="bg-card py-2 px-3 rounded position-relative">
								<div class="d-flex justify-content-between mb-2">
									<div>
										<a class="text-decoration-none fw-bold txt-text" href="#">${cv.userName}</a>
											<small class="txt-grey ms-3">${cv.createdAt}</small>
									</div>
									
									${commentControlPanel}
									
								</div>
								<p>
									${cv.content}
								</p>
							</div>
						</div>
					</div>
					`)
					}


				}
			},
			"json"
		)

	}
}