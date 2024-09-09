$("document").ready(() => {
	document.querySelectorAll(".other-profile-trigger")
		.forEach(el => {
			el.addEventListener("click", () => {
				el.closest('.d-flex.align-items-center').querySelector('a').click();
			})
		})
})