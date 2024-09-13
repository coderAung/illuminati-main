
document.addEventListener("DOMContentLoaded", () => {
	let photoIdToDelete = []

	const loadPhoto = () => {

		const originalPhotos = document.querySelectorAll(".original-photo")
		if (originalPhotos) {

			originalPhotos.forEach(photo => {
				const loadedBtn = photo.querySelector(".remove-photo-btn")
				loadedBtn.addEventListener("click", event => {

					let target = event.target

					if (!target.classList.contains("remove-photo-btn")) {
						target = target.parentElement
					}
					const postImageId = target.getAttribute("postImageId")
					photoIdToDelete.push(postImageId)
					loadedBtn.parentElement.remove()

					if (0 === document.getElementById("photo-preview").childElementCount) {
						document.getElementById("photo-preview").classList.add("d-none")
					}
					/*					$.post(
											url,
											{
												"postImageId": postImageId
											},
											response => {
												if(response.result === "success") {
													loadedBtn.parentElement.remove()
													console.log("deleted")
												}
											},
											"json"
											)
					*/

				})
			})
		}
	}

	loadPhoto()

	const photoUploadBtn = document.querySelector("#photo-input-btn")
	const photoUploadInput = document.querySelector("#photo-input")
	const photoPreview = document.querySelector("#photo-preview")
	const postCreateBtn = document.querySelector("#post-create-btn")
	const postForm = document.querySelector("#post-form")

	let fileListArray = []

	if (postCreateBtn) {
		postCreateBtn.addEventListener("click", function() {
			var content = document.querySelector(".post-input").value
			if (fileListArray.length === 0 &&
			 (content === '' || !content) &&
			 (photoIdToDelete.length === 0)) {
			} else {

				const dataTransfer = new DataTransfer()
				if (0 === fileListArray.length) {
					photoUploadInput.value = ''
					console.log(photoUploadInput.files)
				} else {
					fileListArray.forEach(file => dataTransfer.items.add(file))
					photoUploadInput.files = dataTransfer.files
				}

				if (photoIdToDelete.length > 0) {
					const url = document.querySelector("#delete-photo-url").getAttribute("url")
					console.log("deleting image if exist")
					$.ajax({
						url: url,  // Replace with your actual servlet URL
						type: 'POST',
						contentType: 'application/json',  // Set content type to JSON
						data: JSON.stringify({ "photoIds": photoIdToDelete }),  // Convert the array to JSON string
						success: function(response) {
							console.log('Data sent successfully:', response);
							if(response.result === "success") {
								console.log("photo are deleted")
							}
						},
						error: function(xhr, status, error) {
							console.error('Error sending data:', error);
						}
					})
				}

				postForm.submit();
			}
		})

	}

	if (photoUploadBtn) {
		photoUploadBtn.addEventListener("click", () => {
			openImageUploadWindow();
		})
	}


	if (photoUploadInput) {
		photoUploadInput.addEventListener("change", () => {

			console.log("A photo is chosed")
			console.log(photoUploadInput.files)

			const photos = photoUploadInput.files

			fileListArray = [...fileListArray, ...photos]

			photoPreview.classList.remove("d-none")

			for (let i = 0; i < photos.length; i++) {
				const reader = new FileReader()
				reader.readAsDataURL(photos[i])
				reader.onload = () => {
					const photoUrl = reader.result
					const photoDiv = document.createElement("div")
					photoDiv.classList.add("photo")
					photoDiv.id = i + 1
					photoDiv.classList.add("mx-2")

					const image = document.createElement("img")
					image.classList.add("img-fluid")
					image.classList.add("w-100")
					image.classList.add("h-100")
					image.classList.add("rounded")
					image.src = photoUrl

					const removePhotoBtn = document.createElement("span")
					removePhotoBtn.classList.add("remove-photo-btn")
					removePhotoBtn.classList.add("pointer")
					removePhotoBtn.innerHTML = `<i class="bi bi-dash"></i>`

					removePhotoBtn.addEventListener("click", function() {
						const id = removePhotoBtn.parentElement.id
						console.log(removePhotoBtn.parentElement.id)
						fileListArray.splice(id - 1, 1)
						removePhotoBtn.parentElement.remove()
						console.log(photoPreview.childElementCount)
						if (0 === photoPreview.childElementCount) {
							photoPreview.classList.add("d-none")
						}

					})

					photoDiv.appendChild(removePhotoBtn)
					photoDiv.appendChild(image)
					photoPreview.appendChild(photoDiv)
				}
			}
		})
	}
})

function goToCreatePostPage() {
	const createPostPageURL = document.getElementById("createPostPage").href
	console.log(createPostPageURL)
	window.location.href = createPostPageURL
}

function openImageUploadWindow() {
	document.querySelector("#photo-input").click();
}

