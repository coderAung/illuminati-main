$("document").ready(() => {
	
	const authUrl = $("#auth").attr("url")
	$.get(
		authUrl,
		{},
		response => {
			if(response.result === "done") {
				window.location.reload()
			}
		},
		"json"
	)
})