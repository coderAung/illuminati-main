
document.addEventListener("DOMContentLoaded", () => {

	const detailBtns = document.querySelectorAll(".post-detail-btn")
	if (detailBtns) {
		detailBtns.forEach(btn => {
			btn.addEventListener("click", e => {
				let target = e.target
				if (target instanceof HTMLElement) {
					
					if(!target.classList.contains(".post-detail-btn")) {
						target = target.parentElement
					}
					
					const link = target.querySelector(".post-detail-link")
					link.click()
				}
			})
		})
	}

	document.querySelectorAll(".edit-post-btn").forEach(btn => {
		btn.addEventListener("click", e => {

			let target = e.target

			if (target instanceof HTMLElement) {
				if(!target.classList.contains("edit-post-btn")) {
					target = target.parentElement
				}

				const link = target.querySelector(".edit-post-link")
				link.click()
			}
		})
	})
})