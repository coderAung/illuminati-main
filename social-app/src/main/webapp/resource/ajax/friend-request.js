$("document").ready(() => {
	
	// confirming friend request
	document.querySelectorAll(".confirm-friend-request")
			.forEach(el => {
				el.addEventListener("click", () => {
					console.log("confirm friend")
					const url = el.getAttribute("url").concat("/create")
					const otherUserId = el.getAttribute("otherUserId")
					
					confrimFriendRequest(el, url, otherUserId)
				})
			})
	
	
	// deleting friend request
	document.querySelectorAll(".delete-friend-request")
			.forEach(el => {
				el.addEventListener("click", () => {
					console.log("delete friend")
					console.log(`${el.getAttribute("url")}/delete`)
				})
			})
})

function confrimFriendRequest(el, url, otherUserId) {
	console.log("accepting friend request")

	$.post(url,
		{
			"userIdToConfrim": otherUserId,
		},
		(data) => {
			if (data.result === "success") {
				
				if(el instanceof HTMLElement) {
					el.innerText = "Friend"
					el.classList.remove("confirm-friend-request")
					el.classList.remove("btn-deep")
					el.classList.add("btn-app")
					el.nextElementSibling.remove()
				}
				
				console.log("successfully canceled the friend request to user " + data.requestTo)
			}
		},
		"json")
}
