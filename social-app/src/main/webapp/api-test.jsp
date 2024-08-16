<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>API Test</title>
<jsp:include page="/css/bootstrap.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid mb-3">
		<div class="bg-primary py-3 px-4 text-white">
			<h3>API Test</h3>
		</div>
	</div>
	
	<div class="d-flex justify-content-center">
		<div class="w-75">
			<c:url var="comment" value="/api/comment"></c:url>
			<a href="${comment}" class="btn btn-primary">Get Comment</a>
		</div>
	</div>
</body>
</html>