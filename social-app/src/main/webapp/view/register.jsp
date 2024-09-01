<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="login" value="/view/login.jsp"></c:url>
<c:url var="image" value="/img/logo.jpg"></c:url>
<c:url var="welcome" value="/view/welcome.jsp"></c:url>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<jsp:include page="/resource/style.jsp"></jsp:include>
</head>

<body class="main-bg">

	<!-- nav bar start -->
	<jsp:include page="/component/nav-bar-2.jsp">
		<jsp:param value="nav-link-active" name="registerActive" />
	</jsp:include>
	<!-- nav bar end -->
	<div class="container-fluid mt-5">
		<div class="row justify-content-center">
			<div
				class="bg-card rounded login-form col-4 txt-text text-center py-4 px-4">
				<h1 class="pointer h2">Register</h1>
				<c:url var="userCreate" value="/user/create"></c:url>
				<form action="${userCreate}" method="post">
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar">
						<span class="position-absolute"><i class="fas fa-user txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="name"
							placeholder="Name" required>
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar">
						<span class="position-absolute"><i class="fa-solid fa-envelope txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="text" name="email"
							placeholder="Email" required>
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar">
						<span class="position-absolute"><i class="fas fa-lock txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="password" name="password"
							placeholder="Password" required>
					</div>
					<div class="mb-3 bg-card-2 rounded py-2 px-3 search-bar">
						<span class="position-absolute"><i class="fas fa-unlock txt-app"></i></span>
						<input class="w-100 txt-text ps-4" type="password" name="confirmPassword"
							placeholder="Confirm Password" required>
					</div>
					<button type="submit" class="btn-login w-100 rounded py-2 mb-3">Register</button>
				</form>

				<c:url var="login" value="/login"></c:url>
				<a href="${login}" class="signup-link">Already have an account? Login</a>
			</div>
		</div>
	</div>
</body>

</html>