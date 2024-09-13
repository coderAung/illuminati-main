
document.addEventListener("DOMContentLoaded", () => {

	const photoUploadBtn = document.querySelector("#photo-input-btn")
	const photoUploadInput = document.querySelector("#photo-input")
	const photoPreview = document.querySelector("#photo-preview")
	const postCreateBtn = document.querySelector("#post-create-btn")
	const postForm = document.querySelector("#post-form")

	let fileListArray = []

	if (postCreateBtn) {
		postCreateBtn.addEventListener("click", function() {
			var content = document.querySelector(".post-input").value
			if (fileListArray.length === 0 && (content === '' || !content)) {
			} else {

				const dataTransfer = new DataTransfer()
				if (0 === fileListArray.length) {
					photoUploadInput.value = ''
					console.log(photoUploadInput.files)
				} else {
					fileListArray.forEach(file => dataTransfer.items.add(file))
					photoUploadInput.files = dataTransfer.files
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

						console.log(fileListArray)
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