$("document").ready(() => {
	const deleteBtns = document.querySelectorAll(".post-delete-btn")
	if(deleteBtns) {
		deleteBtns.forEach(btn => {
			btn.addEventListener("click", event => deletePost(event))
		})
	}
})

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