$("document").ready(() => {
	const deleteBtns = document.querySelectorAll(".post-delete-btn")
	if(deleteBtns) {
		deleteBtns.forEach(btn => {
			btn.addEventListener("click", event => deletePost(event))
		})
	}
	
	const postDeleteBtn = document.querySelector(".post-delete")
	if(postDeleteBtn) {
		console.log("exist")
		postDeleteBtn.addEventListener("click", event => deleteInPostDetail(event))
	}
})

function deleteInPostDetail(event) {
	console.log("clicked")
	let target = event.target
	if(target instanceof HTMLElement) {
		if(!target.classList.contains("post-delete")) {
			target = target.parentElement
		}
		
		const url = target.getAttribute("url")
		const postId = target.getAttribute("postId")

		$.post(
			url,
			{
				"postId": postId
			},
			response => {
				if(response.result === "success") {
					window.location.reload()
				}
			},
			"json"
		)
		
	}
}

function deletePost(event) {
	let target = event.target
	if(target instanceof HTMLElement) {
		if(!target.classList.contains("post-delete-btn")) {
			target = target.parentElement
		}
		
		const url = target.getAttribute("url")
		const postId = target.getAttribute("postId")

		$.post(
			url,
			{
				"postId": postId
			},
			response => {
				if(response.result === "success") {
					// showing alert will be here			
					$(`#post-${postId}`).remove()
				}
			},
			"json"
		)
		
	}
}