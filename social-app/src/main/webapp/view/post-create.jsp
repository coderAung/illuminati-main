<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="home" value="/home"></c:url>
<c:url var="login" value="/login"></c:url>
<c:url var="notifications" value="/notifications"></c:url>
<c:url var="setting" value="/setting"></c:url>
<c:url var="profile" value="/profile"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post Creation</title>

<jsp:include page="/resource/library/bootstrap.jsp"></jsp:include>
<jsp:include page="/resource/library/icon.jsp"></jsp:include>

<c:url var="navbarCss" value="/resource/css/nav-bar.css"></c:url>
<link href="${navbarCss}" rel="stylesheet">

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">

<head>
    <meta charset="UTF-8">
    <title>Create New Post</title>
    <style>
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="file"] {
            margin: 10px 0;
            padding: 10px;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .preview-container {
            margin-top: 30px;
            text-align: left;
        }
        .preview-title {
            font-weight: bold;
            font-size: 18px;
            margin-top: 20px;
        }
        .preview-content {
            margin-top: 10px;
            font-size: 16px;
            color: #555;
        }
        .preview-container img {
            max-width: 100%;
            height: auto;
            margin-top: 10px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            position: relative;
        }
        .preview-container img:hover {
            transform: scale(1.05);
        }
        .preview-container .cross-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: gray;
            color: white;
            border: none;
            border-radius: 50%;
            cursor: pointer;
        }
    </style>
    <script>
        function updatePreview() {
            const previewTitle = document.getElementById('previewTitle');
            const previewContent = document.getElementById('previewContent');
            const previewContainer = document.getElementById('previewContainer');
            const fileInput = document.getElementsByName('photo')[0];
            const titleInput = document.getElementsByName('title')[0].value;
            const contentInput = document.getElementsByName('content')[0].value;

            // Update title and content preview
            previewTitle.textContent = titleInput;
            previewContent.textContent = contentInput;

            // Clear previous preview images
            previewContainer.innerHTML = '';

            Array.from(fileInput.files).forEach((file, index) => {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const imageWrapper = document.createElement('div');
                    imageWrapper.style.position = 'relative';
                    const img = document.createElement('img');
                    img.src = e.target.result;
                    img.alt = "Image Preview";
                    img.style.maxWidth = '100%';
                    img.style.height = 'auto';
                    img.style.marginTop = '10px';
                    img.style.borderRadius = '8px';
                    img.style.boxShadow = '0 4px 8px rgba(0, 0, 0, 0.2)';
                    img.style.position = 'relative';

                    const crossButton = document.createElement('button');
                    crossButton.className = 'cross-button';
                    crossButton.innerHTML = '&times;';
                    crossButton.onclick = function() {
                        imageWrapper.remove();
                    };

                    imageWrapper.appendChild(img);
                    imageWrapper.appendChild(crossButton);
                    previewContainer.appendChild(imageWrapper);
                }
                reader.readAsDataURL(file);
            });
        }
    </script>
</head>
<body>
	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar.jsp">
		<jsp:param value="nav-link-active" name="profileActive" />
	</jsp:include>
	<!-- nav bar end -->

	<!-- main part start -->
	
	<!-- LEFT start -->
	<jsp:include page="/component/sidebar.jsp"></jsp:include>
			
    <div class="container">
        <h2>Create New Post</h2>
        <form action="/post/create" method="post" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="Enter post title" required onkeyup="updatePreview()">
            <textarea name="content" placeholder="Enter post content" rows="4" required onkeyup="updatePreview()"></textarea>
            <input type="file" name="photo" accept="image/*" multiple required onchange="updatePreview()">
            <button type="submit" class="btn">Create Post</button>
        </form>
        <div class="preview-container">
       	    <h3>Post Preview:</h3>
            <div id="previewTitle" class="preview-title"></div>
            <div id="previewContent" class="preview-content"></div>
            <div id="previewContainer"></div>
        </div>
    </div>
</body>
</html>
