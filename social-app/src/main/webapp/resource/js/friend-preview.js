
document.addEventListener("DOMContentLoaded", () => {
	const friendCards = document.querySelectorAll(".friend-card")
	
	goToOtherProfilePage = (event) => {
		let target = event.target
		
		if(target instanceof HTMLElement) {
			
			if(!target.classList.contains("friend-card")) {
				target = target.parentElement
			}
			
			if(target instanceof HTMLElement) {
				target.querySelector(".other-profile-link").click()
			}
		}
	}
	
	if(friendCards) {
		friendCards.forEach(fc => {
			fc.addEventListener("click", event => goToOtherProfilePage(event))
		})
	}
})