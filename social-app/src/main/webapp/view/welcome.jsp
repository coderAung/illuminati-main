<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="image" value="/img/logo.jpg"></c:url>
<c:url var="login" value="/view/login.jsp"></c:url>
<c:url var="register" value="/view/register.jsp"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome to Illuminati</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />

<c:url var="navbarCss" value="/resource/css/nav-bar.css"></c:url>
<link rel="stylesheet" href="${navbarCss}">
<style>
/* Styles remain unchanged */
body {
	margin: 0;
	font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande',
		'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
	background: linear-gradient(135deg, rgb(63, 98, 160), rgb(99, 60, 146),
		rgb(60, 83, 155), rgb(121, 124, 156), rgb(84, 91, 134),
		rgb(106, 75, 134));
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
	overflow-x: hidden;
	background-attachment: fixed;
	animation: gradientShift 10s ease infinite;
}

@
keyframes gradientShift { 0% {
	background: linear-gradient(to right, #da91f0, #f1c0e8);
}

50
%
{
background
:
linear-gradient(
to
right
,
#f1c0e8
,
#da91f0
);
}
100
%
{
background
:
linear-gradient(
to
right
,
#da91f0
,
#f1c0e8
);
}
}
.container {
	text-align: center;
	background: rgba(255, 255, 255, 0.7);
	/* Slightly transparent white background */
	padding: 30px;
	border-radius: 55px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
	border: 2px solid transparent;
	backdrop-filter: blur(10px);
	margin: 50px auto;
	transition: transform 0.3s ease, box-shadow 0.3s ease, background-color
		0.3s ease, border-color 0.3s ease;
	position: relative;
	/* Ensure relative positioning for pseudo-elements */
	overflow: hidden;
	/* Ensure overflow is hidden to display the pseudo-element correctly */
}

.container::before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: linear-gradient(135deg, rgba(255, 255, 255, 0.1),
		rgba(0, 0, 0, 0.1));
	opacity: 0.5;
	z-index: 1;
	pointer-events: none;
	/* Ensure the pseudo-element doesn't interfere with interactions */
}

.container:hover {
	transform: translateY(-12px) scale(1.03);
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
	background-color: rgba(255, 255, 255, 0.9);
	border-color: #da91f0;
	animation: pulse 1s infinite;
}

@
keyframes gradientShift { 0% {
	background-position: 0% 50%;
}

50
%
{
background-position
:
100%
50%;
}
100
%
{
background-position
:
0%
50%;
}
}
@
keyframes pulse { 0% {
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

50
%
{
box-shadow
:
0
8px
16px
rgba(
0
,
0
,
0
,
0.25
);
}
100
%
{
box-shadow
:
0
4px
12px
rgba(
0
,
0
,
0
,
0.15
);
}
}
.logo img {
	height: 65px;
	border-radius: 50%;
	border: 3px solid #040000;
	transition: transform 0.3s ease;
}

.logo img:hover {
	transform: scale(1.1);
}

.group-name {
	font-size: 50px;
	font-weight: bold;
	color: #6a1b9a;
	margin-bottom: 20px;
	text-align: center;
	text-shadow: 3px 3px 6px rgba(0, 0, 0, 0.2);
	letter-spacing: 3px;
	padding: 8px;
	border-radius: 20px;
	font-family: 'Comic Sans MS', cursive, sans-serif;
}

.subtitle {
	font-size: 15px;
	margin-top: 30px;
	margin-bottom: 45px;
	color: #6a1b9a;
	line-height: 1.6;
	text-align: center;
	font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
		'Lucida Sans', Arial, sans-serif;
}

.cta-button {
	display: inline-block;
	padding: 12px 24px;
	border: 2px solid #b63ed6;
	border-radius: 12px;
	text-decoration: none;
	color: #fff;
	font-size: 16px;
	font-weight: bold;
	background: linear-gradient(135deg, #b63ed6, #a05dff);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	transition: background-color 0.3s ease, color 0.3s ease, transform 0.3s
		ease, box-shadow 0.3s ease;
	position: relative;
	overflow: hidden;
	text-align: center;
	font-family: 'Arial', sans-serif;
	margin-bottom: 10px;
	z-index: 2;
	/* Ensure it's above other content */
}

.cta-button::before {
	content: "";
	position: absolute;
	top: 50%;
	left: 50%;
	width: 250%;
	height: 300%;
	background: rgba(255, 255, 255, 0.3);
	border-radius: 50%;
	transform: translate(-50%, -50%) scale(0.7);
	transition: transform 0.3s ease;
	z-index: 1;
	/* Behind the button text */
}

.cta-button:hover::before {
	transform: translate(-50%, -50%) scale(1);
}

.cta-button:hover {
	background-color: #b63ed6;
	color: #fff;
	transform: scale(1.05);
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
}

.secondary-button {
	display: inline-block;
	padding: 10px 20px;
	border: 2px solid #b63ed6;
	border-radius: 12px;
	text-decoration: none;
	color: #fff;
	font-size: 14px;
	font-weight: bold;
	background: linear-gradient(135deg, #b63ed6, #a05dff);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	transition: background 0.3s ease, color 0.3s ease, transform 0.3s ease,
		box-shadow 0.3s ease, filter 0.3s ease;
	position: relative;
	overflow: hidden;
	text-align: center;
	font-family: 'Arial', sans-serif;
	margin: 5px;
	width: 120px;
	height: auto;
	line-height: 1.5;
	z-index: 2;
	/* Ensure it's above other content */
}

.secondary-button::before {
	content: "";
	position: absolute;
	top: 50%;
	left: 50%;
	width: 250%;
	height: 300%;
	background: rgba(255, 255, 255, 0.3);
	border-radius: 50%;
	transform: translate(-50%, -50%) scale(0.7);
	transition: transform 0.3s ease;
	z-index: 1;
	/* Behind the button text */
}

.secondary-button:hover::before {
	transform: translate(-50%, -50%) scale(1);
}

.secondary-button:hover {
	background: linear-gradient(135deg, #a05dff, #b63ed6);
	color: #fff;
	transform: scale(1.05);
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
	filter: brightness(1.1);
	animation: pulse 1s infinite;
}

.secondary-button span {
	position: relative;
	z-index: 2;
	/* Ensure it's above the pseudo-element */
}

.arrow {
	margin-top: 2px;
	font-size: 20px;
	animation: bounce 1.5s infinite;
	color: #8d359f;
	text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
	transition: transform 0.2s ease-in-out;
}

.arrow:hover {
	transform: scale(1.2);
}

@
keyframes bounce { 0%, 20%, 50%, 80%, 100% {
	transform: translateY(0);
}

40
%
{
transform
:
translateY(
-10px
);
}
60
%
{
transform
:
translateY(
-5px
);
}
}
.next-container {
	display: flex;
	justify-content: center;
	margin: 20px 0;
}

#particles-js {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1;
	/* Place it behind all other content */
	background: rgba(0, 0, 0, 0.1);
	/* Optional: give it a slight background color */
}
</style>

</head>

<body>
	<div id="particles-js"></div>

<c:url var="image" value="/img/logo.jpg"></c:url>

<div class="navbar py-3 d-flex mb-3 sticky-top shadow">
	<div class="logo mx-auto">
		<img src="${image}" alt="Logo">
	</div>

	<!-- Search Bar start -->
	<div class="search-bar">
		<form action="#" class="w-100" method="get">
			<span class="icon pointer">
				<i class="fas fa-search"></i>
			</span>
			<input type="text" name=""
			class="w-100 ps-5 pe-2 py-2 rounded"
				placeholder="Type a word...">
			<span class="close pointer">
				<i class="bi bi-x fs-4 pointer"></i>
			</span>
		</form>
	</div>
	<!-- Search Bar end -->

	<!-- nav-links start -->
	<div class="nav-links d-flex justify-content-evenly w-50 position-relative">
		<a href="${home}" class="nav-link text-white ${param.homeActive}">Home</a>
		<a href="${profile}" class="nav-link text-white ${param.profileActive}">Profile</a>
		<a href="${notifications}" class="nav-link text-white ${param.noificationActive}">Notifications</a>
		<a href="${setting}" class="nav-link text-white" ${param.settingActive}>Setting</a>
	</div>
	<!-- nav-links end -->
</div>

	</div>
	<div>
		<div class="container">
			<div class="logo">
				<img src="${image}" alt="Logo">
			</div>
			<div class="group-name">Illuminati</div>
			<p class="subtitle">
				Welcome to Illuminati, where magical connections happen.<br>
				Dive into a world of joy and create lasting memories with your
				friends. <br>Explore our features and get started on your
				journey today!
			</p>
			<a href="#arrow-section" class="cta-button">Get Started</a>
			<div id="arrow-section" class="arrow">
				<a href="#auth-section"> <i class="fa-solid fa-arrow-down"></i>
				</a>
			</div>
		</div>
		<div id="auth-section" class="next-container">
			<a href="${login }" class="secondary-button">Login</a> <a
				href="${register }" class="secondary-button">Register</a>
		</div>
	</div>

	<jsp:include page="/component/footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/particles.js@2.0.0/particles.min.js"></script>
	<script>
		particlesJS(
				'particles-js',
				{
					"particles" : {
						"number" : {
							"value" : 100,
							"density" : {
								"enable" : true,
								"value_area" : 800
							}
						},
						"color" : {
							"value" : "#00aaff"
						},
						"shape" : {
							"type" : "multiple",
							"stroke" : {
								"width" : 0,
								"color" : "#ffffff"
							},
							"polygon" : {
								"nb_sides" : 5
							},
							"image" : {
								"src" : "https://cdn.pixabay.com/photo/2017/08/30/01/34/eiffel-tower-2681431_960_720.jpg",
								"width" : 100,
								"height" : 100
							}
						},
						"opacity" : {
							"value" : 0.6,
							"random" : true,
							"anim" : {
								"enable" : true,
								"speed" : 1,
								"opacity_min" : 0.1,
								"sync" : false
							}
						},
						"size" : {
							"value" : 4,
							"random" : true,
							"anim" : {
								"enable" : true,
								"speed" : 10,
								"size_min" : 0.1,
								"sync" : false
							}
						},
						"line_linked" : {
							"enable" : true,
							"distance" : 180,
							"color" : "#00aaff",
							"opacity" : 0.5,
							"width" : 2
						},
						"move" : {
							"enable" : true,
							"speed" : 4,
							"direction" : "none",
							"random" : false,
							"straight" : false,
							"out_mode" : "out",
							"bounce" : false,
							"attract" : {
								"enable" : true,
								"rotateX" : 600,
								"rotateY" : 1200
							}
						}
					},
					"interactivity" : {
						"detect_on" : "canvas",
						"events" : {
							"onhover" : {
								"enable" : true,
								"mode" : "repulse"
							},
							"onclick" : {
								"enable" : true,
								"mode" : "push"
							},
							"resize" : true
						},
						"modes" : {
							"grab" : {
								"distance" : 400,
								"line_linked" : {
									"opacity" : 1
								}
							},
							"bubble" : {
								"distance" : 250,
								"size" : 0,
								"duration" : 2,
								"opacity" : 0.8,
								"speed" : 3
							},
							"repulse" : {
								"distance" : 200,
								"duration" : 0.4
							},
							"push" : {
								"particles_nb" : 5
							},
							"remove" : {
								"particles_nb" : 2
							}
						}
					},
					"retina_detect" : true
				});
	</script>
</body>

</html>