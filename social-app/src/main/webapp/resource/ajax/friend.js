

$("document").ready(() => {
	console.log("It's work")

	if ($("#delete-friend-btn")) {
		$("#delete-friend-btn").click(() => {
			deleteFriendRequest()
		})
	}

	$("#friend-btn").click(() => {
		console.log("clicked")

		const status = $("#friend-btn").attr("status")

		console.log(status)

		if ("NOT_FRIEND" === status) {
			sendFriendRequest()
		}

		if ("REQUESTED" === status) {
			cancelFriendRequest()
		}

		if ("NEED_TO_CONFIRM" === status) {
			confrimFriendRequest()
		}

		if ("IS_FRIEND" === status) {
			unfriend()
		}
	})
})

function deleteFriendRequest() {
	const url = $("#friendRequestUrl").attr("url").concat("/delete")
	const otherUserId = $("#userData").attr("otherUserId")

	$.post(url,
		{
			"requestBy": otherUserId
		},
		(data) => {
			if (data.result === "success") {
				const btn = $("#friend-btn")
				btn.attr("status", "NOT_FRIEND")
				btn.text("Add Friend")

				btn.removeClass("btn-light")
				btn.addClass("btn-app")
				$("#delete-friend-btn").remove()
			}
		},
		"json")

}

function sendFriendRequest() {

	const url = $("#friendRequestUrl").attr("url").concat("/create")
	const otherUserId = $("#userData").attr("otherUserId")

	$.post(url,
		{
			"requestTo": otherUserId,
		},
		(data) => {
			if (data.result === "success") {
				const btn = $("#friend-btn")
				btn.attr("status", "REQUESTED")
				btn.text("Cancel Request")
				btn.removeClass("btn-app")
				btn.addClass("btn-danger")
			}
		},
		"json")

}

function cancelFriendRequest() {
	const url = $("#friendRequestUrl").attr("url").concat("/cancel")
	const otherUserId = $("#userData").attr("otherUserId")

	$.post(url,
		{
			"requestTo": otherUserId,
		},
		(data) => {
			if (data.result === "success") {
				const btn = $("#friend-btn")
				btn.attr("status", "NOT_FRIEND")
				btn.text("Add Friend")

				btn.removeClass("btn-danger")
				btn.addClass("btn-app")

				console.log("successfully canceled the friend request to user " + data.requestTo)
			}
		},
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