<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Saved Posts</title>

<c:url value="/resource/css2/profile.css" var="css2"></c:url>
<link rel="stylesheet" href="${css2}">
<jsp:include page="/resource/library/bootstrap.jsp"></jsp:include>
<jsp:include page="/resource/library/icon.jsp"></jsp:include>

<c:url var="navbarCss" value="/resource/css/nav-bar.css"></c:url>
<link href="${navbarCss}" rel="stylesheet">

<c:url var="commonCss" value="/resource/css/common.css"></c:url>
<link href="${commonCss}" rel="stylesheet">

</head>
<body>
    
    <!-- nav bar start -->
    <jsp:include page="/component/nav-bar.jsp">
        <jsp:param value="nav-link-active" name="profileActive" />
    </jsp:include>
    <!-- nav bar end -->

    <!-- main part start -->
    <main>
        <div class="container-fluid row">
            <!-- LEFT start -->
            <jsp:include page="/component/sidebar.jsp"></jsp:include>
            <!-- RIGHT start -->
            <div class="col-8 bg-pale mb-2 p-3 rounded">
                <h3>Saved Posts</h3>
                
                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
                
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <div class="position-relative p-2"> <!-- Added padding here -->
                                <img src="https://images.pexels.com/photos/1308881/pexels-photo-1308881.jpeg?cs=srgb&dl=pexels-soldiervip-1308881.jpg&fm=jpg" 
                                     class="card-img-top rounded" 
                                     alt="Saved Post Image">
                                <button type="button" class="btn btn-outline-danger position-absolute top-0 end-0 m-2 rounded-circle">
                                    <i class="bi bi-heart-fill"></i>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Life is too short</h5>
                                <p class="card-text text-muted mb-2">Posted by Kyi Pyar</p>
                                <p class="card-text text-muted">5 minutes ago</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <div class="position-relative p-2"> <!-- Added padding here -->
                                <img src="https://images.pexels.com/photos/1308881/pexels-photo-1308881.jpeg?cs=srgb&dl=pexels-soldiervip-1308881.jpg&fm=jpg" 
                                     class="card-img-top rounded" 
                                     alt="Saved Post Image">
                                <button type="button" class="btn btn-outline-danger position-absolute top-0 end-0 m-2 rounded-circle">
                                    <i class="bi bi-heart-fill"></i>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Life is too short</h5>
                                <p class="card-text text-muted mb-2">Posted by Kyi Pyar</p>
                                <p class="card-text text-muted">5 minutes ago</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <div class="position-relative p-2"> <!-- Added padding here -->
                                <img src="https://images.pexels.com/photos/1308881/pexels-photo-1308881.jpeg?cs=srgb&dl=pexels-soldiervip-1308881.jpg&fm=jpg" 
                                     class="card-img-top rounded" 
                                     alt="Saved Post Image">
                                <button type="button" class="btn btn-outline-danger position-absolute top-0 end-0 m-2 rounded-circle">
                                    <i class="bi bi-heart-fill"></i>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Life is too short</h5>
                                <p class="card-text text-muted mb-2">Posted by Kyi Pyar</p>
                                <p class="card-text text-muted">5 minutes ago</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <div class="position-relative p-2"> <!-- Added padding here -->
                                <img src="https://images.pexels.com/photos/1308881/pexels-photo-1308881.jpeg?cs=srgb&dl=pexels-soldiervip-1308881.jpg&fm=jpg" 
                                     class="card-img-top rounded" 
                                     alt="Saved Post Image">
                                <button type="button" class="btn btn-outline-danger position-absolute top-0 end-0 m-2 rounded-circle">
                                    <i class="bi bi-heart-fill"></i>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Life is too short</h5>
                                <p class="card-text text-muted mb-2">Posted by Kyi Pyar</p>
                                <p class="card-text text-muted">5 minutes ago</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <div class="position-relative p-2"> <!-- Added padding here -->
                                <img src="https://images.pexels.com/photos/1308881/pexels-photo-1308881.jpeg?cs=srgb&dl=pexels-soldiervip-1308881.jpg&fm=jpg" 
                                     class="card-img-top rounded" 
                                     alt="Saved Post Image">
                                <button type="button" class="btn btn-outline-danger position-absolute top-0 end-0 m-2 rounded-circle">
                                    <i class="bi bi-heart-fill"></i>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Life is too short</h5>
                                <p class="card-text text-muted mb-2">Posted by Kyi Pyar</p>
                                <p class="card-text text-muted">5 minutes ago</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col">
                        <div class="card h-100 border-0 shadow-sm">
                            <div class="position-relative p-2"> <!-- Added padding here -->
                                <img src="https://images.pexels.com/photos/1308881/pexels-photo-1308881.jpeg?cs=srgb&dl=pexels-soldiervip-1308881.jpg&fm=jpg" 
                                     class="card-img-top rounded" 
                                     alt="Saved Post Image">
                                <button type="button" class="btn btn-outline-danger position-absolute top-0 end-0 m-2 rounded-circle">
                                    <i class="bi bi-heart-fill"></i>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Life is too short</h5>
                           		<p class="card-text text-muted mb-2">Posted by Kyi Pyar</p>
                                <p class="card-text text-muted">5 minutes ago</p>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Repeat the above card div block for more saved posts -->
                </div>
            </div>
        </div>
    </main>
        
</body>
</html>
