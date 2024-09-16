

$("document").ready(() => {
	console.log("It's work")

	$("#search-result").on("click", ".add-friend-btn", function(event) {
		sendFriendRequest(event)
	})

	$("#search-result").on("click", ".cancel-friend-btn", function(event) {
		cancelFriendRequest(event)
	})
	
	$("#search-result").on("click", ".delete-friend-btn", function(event) {
		deleteFriendRequest(event)
	})
})

function deleteFriendRequest(event) {
	const url = $("#friendRequestUrl").attr("url").concat("/delete")
	const otherUserId = event.target.getAttribute("otherUserId");

	$.post(url,
		{
			"requestBy": otherUserId
		},
		(data) => {
			if (data.result === "success") {
				const target = event.target
				if (target instanceof HTMLElement) {
					const addFriendBtn = document.createElement("button")
					addFriendBtn.setAttribute("otherUserId", otherUserId)
					addFriendBtn.classList.add("add-friend-btn", "btn", "btn-app", "w-auto", "me-3")
					addFriendBtn.innerText = "Add Friend"
					target.previousElementSibling.remove()
					target.replaceWith(addFriendBtn)
				}

			}
		},
		"json")

}

function sendFriendRequest(event) {

	const url = $("#friendRequestUrl").attr("url").concat("/create")
	const otherUserId = event.target.getAttribute("otherUserId");

	$.post(url,
		{
			"requestTo": otherUserId,
		},
		(data) => {
			if (data.result === "success") {
				const target = event.target
				if (target instanceof HTMLElement) {

					const cancelBtn = document.createElement("button")
					cancelBtn.setAttribute("otherUserId", otherUserId)
					cancelBtn.classList.add("cancel-friend-btn", "btn", "btn-danger", "w-auto", "me-3")
					cancelBtn.innerText = "Cancel Request"

					target.replaceWith(cancelBtn)
				}
			}
		},
		"json")

}

function cancelFriendRequest(event) {
	const url = $("#friendRequestUrl").attr("url").concat("/cancel")
	const otherUserId = event.target.getAttribute("otherUserId");

	$.post(url,
		{
			"requestTo": otherUserId,
		},
		(data) => {
			if (data.result === "success") {
				const target = event.target
				if (target instanceof HTMLElement) {

					const addFriendBtn = document.createElement("button")
					addFriendBtn.setAttribute("otherUserId", otherUserId)
					addFriendBtn.classList.add("add-friend-btn", "btn", "btn-app", "w-auto", "me-3")
					addFriendBtn.innerText = "Add Friend"

					target.replaceWith(addFriendBtn)
				}
			}
		}
		,
		"json")
}

function confrimFriendRequest() {
	console.log("accepting friend request")

	const url = $("#friendUrl").attr("url").concat("/create")
	const userIdToConfrim = $("#userData").attr("otherUserId")

	$.post(url,
		{
			"userIdToConfrim": userIdToConfrim,
		},
		(data) => {
			if (data.result === "success") {

				const friendInfo = `<div class="btn btn-normal w-auto me-3">Friend</div>`

				const btn = $("#friend-btn")
				btn.attr("status", "IS_FRIEND")
				btn.text("Unfriend")

				btn.addClass("btn-danger")
				btn.removeClass("btn-light")

				btn.before(friendInfo)

				$("#delete-friend-btn").remove()

				location.reload()
				console.log("successfully canceled the friend request to user " + data.requestTo)
			}
		},
		"json")
}

function unfriend() {
	console.log("deleting friend")

	const url = $("#friendUrl").attr("url").concat("/delete")
	const friendId = $("#userData").attr("otherUserId")


	$.post(url,
		{
			"friendId": friendId,
		},
		(data) => {
			if (data.result === "success") {
				const btn = $("#friend-btn")
				btn.attr("status", "NOT_FRIEND")
				btn.text("Add Friend")

				$("#is-friend-status").remove();

				btn.removeClass("btn-danger")
				btn.addClass("btn-normal")

				location.reload()
			}
		},
		"json")

}