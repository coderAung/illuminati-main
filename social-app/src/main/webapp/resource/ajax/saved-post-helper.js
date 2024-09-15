$(document).ready(function() {
	
	let savedPostCount = $("#savedPostCount").attr("count")

	$('.card-container').on('click', function(event) {
		// Check if the click is on an element with the unsave-post-btn class
		if (!$(event.target).closest('.unsave-post-btn').length) {
			// Navigate to the link inside the card container
			const link = $(this).find('a');
			if (link.length) {
				window.location.href = link.attr('href');
			}
		}
	});


	$('.card-container').on('click', '.unsave-post-btn', function(event) {
		event.stopPropagation(); // Prevent other interactions on the card
		
		const postId = event.target.getAttribute("postId")
		const url = $("#unsaveUrl").attr("url")
		
		$.post(
			url,
			{
				"postId": postId
			},
			response => {
				if(response.result === "success") {
					
					savedPostCount = parseInt(savedPostCount) - 1
					$("#savedPostCount").attr("count", savedPostCount)
					
					if(1 < savedPostCount) {
						$("#savedPostCount").text(`${savedPostCount} items`)					
					} else if(1 === savedPostCount) {
						$("#savedPostCount").text(`${savedPostCount} item`)					
					} else {
						window.location.reload()
					}
					// Step 1: Remove the card immediately from the DOM
					const $card = $(this).closest('.col');
			
					// Step 2: Remove the card immediately but allow grid animation for others
					$card.remove();
			
					// Step 3: Trigger the grid layout to animate the movement of remaining cards
					// No additional code required here as the CSS Grid automatically handles the rearrangement
				}
			},
			"json"
		)
	});
});
