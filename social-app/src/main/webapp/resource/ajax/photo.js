var originalProfileSrc = ""
var originalCoverSrc = ""
$("document").ready(() => {

	originalProfileSrc = document.getElementById("profile-image").src
	originalCoverSrc = document.getElementById("cover-image").src

	$("#save-profile-photo-btn").click(() => {
		saveProfilePhoto()
	})
	
	$("#save-cover-photo-btn").click(() => {
		saveCoverPhoto()
	})

	console.log("working")

	$("#upload-profile-photo-btn").click(() => {
		console.log("click")
		$("#profile-photo").click()
	})

	$("#cancel-profile-photo-btn").click(() => {

		document.getElementById("profile-image").src = originalProfileSrc

		document.getElementById("profile-photo").value = ''

		document.getElementById("upload-profile-photo-btn").classList.remove("d-none")
		document.getElementById("save-profile-photo-btn").classList.add("d-none")
		document.getElementById("cancel-profile-photo-btn").classList.add("d-none")

	})

	$("#profile-photo").change(() => {
		const reader = new FileReader()
		reader.onload = () => {
			const imageUrl = reader.result

			const imageElement = document.getElementById("profile-image")
			imageElement.src = imageUrl

			document.getElementById("upload-profile-photo-btn").classList.add("d-none")
			document.getElementById("save-profile-photo-btn").classList.remove("d-none")
			document.getElementById("cancel-profile-photo-btn").classList.remove("d-none")
		}
		const photo = document.getElementById("profile-photo").files[0]
		reader.readAsDataURL(photo)
	})

	$("#upload-cover-photo-btn").click(() => {
		$("#cover-photo").click()
	})

	$("#cover-photo").change(() => {
		const reader = new FileReader()
		reader.onload = () => {
			const imageUrl = reader.result

			const imageElement = document.getElementById("cover-image")
			imageElement.src = imageUrl

			document.getElementById("upload-cover-photo-btn").classList.add("d-none")
			document.getElementById("save-cover-photo-btn").classList.remove("d-none")
			document.getElementById("cancel-cover-photo-btn").classList.remove("d-none")
		}
		const photo = document.getElementById("cover-photo").files[0]
		reader.readAsDataURL(photo)
	})

	$("#cancel-cover-photo-btn").click(() => {

		document.getElementById("cover-image").src = originalCoverSrc

		document.getElementById("profile-photo").value = ''

		document.getElementById("upload-cover-photo-btn").classList.remove("d-none")
		document.getElementById("save-cover-photo-btn").classList.add("d-none")
		document.getElementById("cancel-cover-photo-btn").classList.add("d-none")

	})
})

function saveProfilePhoto() {
	const photo = document.getElementById("profile-photo").files[0]
	const photoData = new FormData()
	photoData.append("profilePhoto", photo)

	const url = $("#profilePhotoUploadUrl").attr("url")

	$.ajax({
		url: url, // Replace with your server-side script to handle the file
		type: 'POST',
		data: photoData,
		processData: false,  // Prevent jQuery from processing the data
		contentType: false,  // Prevent jQuery from setting the content type
		success: function(response) {
			$('#status').text('File uploaded successfully: ' + response);

			if (response.result === "success") {
				originalProfileSrc = response.imagePath
				const imageElement = document.getElementById("profile-image")
				imageElement.src = originalProfileSrc
				
				document.getElementById("upload-profile-photo-btn").classList.remove("d-none")
				document.getElementById("save-profile-photo-btn").classList.add("d-none")
				document.getElementById("cancel-profile-photo-btn").classList.add("d-none")
			}
		},
		error: function(xhr, status, error) {
			$('#status').text('File upload failed: ' + error);
		}
	});
}

function saveCoverPhoto() {
	const photo = document.getElementById("cover-photo").files[0]
	const photoData = new FormData()
	photoData.append("coverPhoto", photo)

	const url = $("#coverPhotoUploadUrl").attr("url")

	$.ajax({
		url: url, // Replace with your server-side script to handle the file
		type: 'POST',
		data: photoData,
		processData: false,  // Prevent jQuery from processing the data
		contentType: false,  // Prevent jQuery from setting the content type
		success: function(response) {
			$('#status').text('File uploaded successfully: ' + response);

			if (response.result === "success") {
				originalCoverSrc = response.imagePath
				const imageElement = document.getElementById("cover-image")
				imageElement.src = originalCoverSrc
				
				document.getElementById("upload-cover-photo-btn").classList.remove("d-none")
				document.getElementById("save-cover-photo-btn").classList.add("d-none")
				document.getElementById("cancel-cover-photo-btn").classList.add("d-none")
			}
		},
		error: function(xhr, status, error) {
			$('#status').text('File upload failed: ' + error);
		}
	});
}

