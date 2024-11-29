$("document").ready(() => {
	$("#post-container").on("click", ".reaction-btn", function (event) {
		console.log("click")
		let target = event.target
		if(!target.classList.contains("reaction-btn")) {
			target = target.parentElement
		}
		
		const url = event.target.getAttribute("url")
		const postId = event.target.getAttribute("postId")
		const status = event.target.getAttribute("status")
		
		if(status !== "true") {
			
			$.post(
				`${url}/create`,
				{
					"postId": postId
				},
				response => {
					if(response.result === "success") {
						if(target instanceof HTMLElement) {
							
							event.target.setAttribute("status", true)
							
							const reactionCount = target.querySelector(".reaction-count")
							reactionCount.setAttribute("count", response.count)
							reactionCount.innerHTML = `${response.count} reactions`

							target.classList.replace("txt-white", "txt-app")
						}
					}
				},
				"json"
			)
			
		} else {
			$.post(
				`${url}/delete`,
				{
					"postId": postId
				},
				response => {
					if(response.result === "success") {
						if(target instanceof HTMLElement) {
							event.target.setAttribute("status", false)

							const reactionCount = target.querySelector(".reaction-count")
							reactionCount.setAttribute("count", response.count)
							reactionCount.innerHTML = `${response.count} reactions`

							target.classList.replace("txt-app", "txt-white")
						}
					}
				},
				"json"
			)
		}
	})
})