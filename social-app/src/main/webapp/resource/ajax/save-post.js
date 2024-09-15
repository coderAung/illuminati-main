$("document").ready(() => {
	const savePostUrl = document.getElementById("savePostUrl").getAttribute("url")
	const unsavePostUrl = document.getElementById("unsavePostUrl").getAttribute("url")

	const savePost = event => {

		console.log("Saving the post")

		let target = event.target
		if (target instanceof HTMLElement) {

			if (!target.classList.contains("save-post-btn")) {
				target = target.parentElement
			}
			const postId = target.getAttribute("postId")

			$.post(
				savePostUrl,
				{
					"postId": postId
				},
				response => {
					if (response.result === "success") {

						console.log("success fully saved")

						const unsaveBtn = document.createElement("div")
						// px-2 py-2 pointer rounded save-post-btn
						unsaveBtn.classList.add("px-2", "py-2", "pointer", "rounded", "unsave-post-btn")
						unsaveBtn.setAttribute("postId", postId)

						unsaveBtn.innerHTML = "<span class='txt-text'>Unsave</span>"

						if (target instanceof HTMLElement) {
							target.replaceWith(unsaveBtn)
						}
					}
				},
				"json"
			)
		}
	}

	const unsavePost = event => {
		console.log("Unsaving the post")

		let target = event.target
		if (target instanceof HTMLElement) {

			if (!target.classList.contains("unsave-post-btn")) {
				target = target.parentElement
			}
			const postId = target.getAttribute("postId")

			$.post(
				unsavePostUrl,
				{
					"postId": postId
				},
				response => {
					if (response.result === "success") {

						console.log("success fully unsaved")

						const saveBtn = document.createElement("div")
						// px-2 py-2 pointer rounded save-post-btn
						saveBtn.classList.add("px-2", "py-2", "pointer", "rounded", "save-post-btn")
						saveBtn.setAttribute("postId", postId)

						saveBtn.innerHTML = "<span class='txt-text'>Save</span>"

						if (target instanceof HTMLElement) {
							target.replaceWith(saveBtn)
						}
					}
				},
				"json"
			)
		}
	}

	$("#post-container").on("click", ".save-post-btn", function(event) {
		savePost(event);
	});

	$("#post-container").on("click", ".unsave-post-btn", function(event) {
		unsavePost(event);
	});

})