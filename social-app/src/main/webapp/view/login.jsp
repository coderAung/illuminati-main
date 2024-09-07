<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="facebook" value="https://www.facebook.com/login/"></c:url>
<c:url var="twitter" value="https://twitter.com/"></c:url>
<c:url var="google" value="https://www.google.com/"></c:url>
<c:url var="youtube" value="https://www.youtube.com/"></c:url>
<c:url var="instagram" value="https://www.instagram.com/"></c:url>
<c:url var="register" value="/view/register.jsp"></c:url>
<c:url var="welcome" value="/view/welcome.jsp"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Illuminati | Login</title>
<jsp:include page="/resource/style.jsp"></jsp:include>

<style>
.login-form .social-icons {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.login-form .social-icons a {
	color: #6a11cb;
	font-size: 24px;
	margin: 0 10px;
	transition: color 0.3s ease, transform 0.3s ease;
}

.login-form .social-icons a:hover {
	color: #501a96;
	transform: rotate(20deg) scale(1.2);
}
</style>
</head>

<body class="main-bg">

	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar-2.jsp">
		<jsp:param value="nav-link-active" name="loginActive" />
	</jsp:include>
	<!-- nav bar end -->

	<div class="container-fluid mt-5">
		<div class="row justify-content-center">
			<div class="bg-card rounded login-form col-4 txt-text text-center py-3 px-4">
				<h1 class="pointer h2">Login</h1>
				<c:url var="login" value="/login"></c:url>
				<form action="${login}" method="post">
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar postion-relative">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="email" placeholder="Email" required>
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar">
						<span class="position-absolute"><i class="fas fa-lock txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="password" name="password" placeholder="Password"
							required>
					</div>
					<button type="submit" class="btn-login w-100 rounded py-2 mb-3">Login</button>
				</form>
				
				<c:url var="register" value="/register"></c:url>
				<a href="${register}" class="signup-link">Don't have an account? Sign up</a>
				
				
				
				
				<div class="social-icons">
					<a href="${facebook}" style="color: #3b5998;"> <svg
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                            <path
								d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951" />
                        </svg>
					</a> <a href="${twitter}" style="color: #1DA1F2;"> <svg
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
                            <path
								d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334q.002-.211-.006-.422A6.7 6.7 0 0 0 16 3.542a6.7 6.7 0 0 1-1.889.518 3.3 3.3 0 0 0 1.447-1.817 6.5 6.5 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.32 9.32 0 0 1-6.767-3.429 3.29 3.29 0 0 0 1.018 4.382A3.3 3.3 0 0 1 .64 6.575v.045a3.29 3.29 0 0 0 2.632 3.218 3.2 3.2 0 0 1-.865.115 3 3 0 0 1-.614-.057 3.28 3.28 0 0 0 3.067 2.277A6.6 6.6 0 0 1 .78 13.58a6 6 0 0 1-.78-.045A9.34 9.34 0 0 0 5.026 15" />
                        </svg>
					</a> <a href="${google}" style="color: #DB4437;"> <svg
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                            <path
								d="M15.545 6.558a9.4 9.4 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.7 7.7 0 0 1 5.352 2.082l-2.284 2.284A4.35 4.35 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.8 4.8 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.7 3.7 0 0 0 1.599-2.431H8v-3.08z" />
                        </svg>
					</a> <a href="${youtube}" style="color: #FF0000;"> <svg
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-youtube" viewBox="0 0 16 16">
                            <path
								d="M8.051 1.999h.089c.822.003 4.987.033 6.11.335a2.01 2.01 0 0 1 1.415 1.42c.101.38.172.883.22 1.402l.01.104.022.26.008.104c.065.914.073 1.77.074 1.957v.075c-.001.194-.01 1.108-.082 2.06l-.008.105-.009.104c-.05.572-.124 1.14-.235 1.558a2.01 2.01 0 0 1-1.415 1.42c-1.16.312-5.569.334-6.18.335h-.142c-.309 0-1.587-.006-2.927-.052l-.17-.006-.087-.004-.171-.007-.171-.007c-1.11-.049-2.167-.128-2.654-.26a2.01 2.01 0 0 1-1.415-1.419c-.111-.417-.185-.986-.235-1.558L.09 9.82l-.008-.104A31 31 0 0 1 0 7.68v-.123c.002-.215.01-.958.064-1.778l.007-.103.003-.052.008-.104.022-.26.01-.104c.048-.519.119-1.023.22-1.402a2.01 2.01 0 0 1 1.415-1.42c.487-.13 1.544-.21 2.654-.26l.17-.007.172-.006.086-.003.171-.007A100 100 0 0 1 7.858 2zM6.4 5.209v4.818l4.157-2.408z" />
                        </svg>
					</a> <a href="${instagram }" style="color: #C13584;"> <svg
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                            <path
								d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334" />
                        </svg>
					</a>
				</div>
			</div>
		</div>
	</div>

</body>

</html>