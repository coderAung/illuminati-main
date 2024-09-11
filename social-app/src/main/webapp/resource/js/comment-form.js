document.addEventListener('DOMContentLoaded', function() {
	const commentForm = document.getElementById('commentForm');
	let lastScrollTop = window.pageYOffset
		|| document.documentElement.scrollTop;

	window.addEventListener('scroll', function() {
		let currentScrollTop = window.pageYOffset
			|| document.documentElement.scrollTop;

		if (currentScrollTop > lastScrollTop) {
			// Scrolling down
			commentForm.classList.add('show');
		} else {
			// Scrolling up
			commentForm.classList.remove('show');
		}

		lastScrollTop = currentScrollTop <= 0 ? 0 : currentScrollTop; // Prevent negative scroll
	});
});
