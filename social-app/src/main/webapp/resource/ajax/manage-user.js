$("document").ready(() => {

	const activateUrl = $("#activate-url").attr("url")
	const suspendUrl = $("#suspend-url").attr("url")
	let suspendCount = $("#suspended-count").attr("count")

	$("#user-container").on("click", ".activate-btn", function(event) {
		const userId = $(event.target).attr("userId")
		$.post(
			activateUrl,
			{ "userId": userId },
			response => {
				if (response.result === "success") {

					suspendCount = parseInt(suspendCount) - 1
					$("#suspended-count").attr("count", suspendCount)
					$("#suspended-count").text(suspendCount)

					const target = event.target
					if (target instanceof HTMLElement && target.classList.contains("activate-btn")) {
						target.classList.remove("activate-btn")
						target.classList.add("suspend-btn")
						target.innerHTML = "Suspend"
						target.classList.replace("txt-text", "text-danger")
						document.querySelector(`#view-${userId}`)
							.querySelectorAll(".status")
							.forEach(status => {
								if (status.classList.contains("btn-outline-danger")) {
									status.classList.replace("btn-outline-danger", "btn-primary")
									status.classList.add("text-white")
									status.innerHTML = "Active"
								}
								if (status.classList.contains("btn-danger")) {
									status.classList.replace("btn-danger", "btn-primary")
								}
							})
					}

				}
			},
			"json"
		)

	})

	$("#user-container").on("click", ".suspend-btn", function(event) {
		const userId = $(event.target).attr("userId")
		$.post(
			suspendUrl,
			{ "userId": userId },
			response => {
				if (response.result === "success") {

					suspendCount = parseInt(suspendCount) + 1
					$("#suspended-count").attr("count", suspendCount)
					$("#suspended-count").text(suspendCount)


					const target = event.target
					if (target instanceof HTMLElement && target.classList.contains("suspend-btn")) {

						console.log("changint ui")

						target.classList.remove("suspend-btn")
						target.classList.add("activate-btn")
						target.innerHTML = "Activate"
						target.classList.replace("text-danger", "txt-text")
						let firstBtn = true
						document.querySelector(`#view-${userId}`)
							.querySelectorAll(".status")
							.forEach(status => {
								if (status.classList.contains("btn-primary")) {
									if (firstBtn) {
										status.classList.replace("btn-primary", "btn-outline-danger")
										status.classList.add("txt-text")
										status.innerHTML = "Suspended"
										if(status.classList.contains("text-white")) {
											status.classList.remove("text-white")
										}
										firstBtn = false
									} else {
										status.classList.replace("btn-primary", "btn-danger")
									}
								}
							})
					}

				}
			},
			"json"
		)
	})
})