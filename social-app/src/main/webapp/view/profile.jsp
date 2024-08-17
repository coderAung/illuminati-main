<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Profile Features</title>
<c:url value="/resourse/css2/.css" var="css2"></c:url>
<link rel="stylesheet" href="${css2}">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>

  /*======================== NAV BAR ========================*/
  

        .navbar {
            display: flex;
            align-items: center;
            background:  linear-gradient(135deg, #b63ed6, #a05dff);
            padding: 5px 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            justify-content: space-between;
            position: fixed;
            top: 0;
            z-index: 1000;
            width: 100%;
        }

        .navbar:hover {
            background-color: #c87ef7;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }

        .logo img {
            height: 40px;
            border: 2px solid #fff1f1;
            border-radius:100%;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .logo img:hover {
            transform: scale(1.1);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .nav-links {
            display: flex;
            align-items: center;
        }

        .nav-links a {
            color: white;
            padding: 12px 20px;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease, color 0.3s ease;
            font-size: 16px;
            display: flex;
            align-items: center;
        }

        .nav-links a:hover {
            color: rgb(19, 0, 32);
            transform: rotate(7deg) scale(1.2);
        }

        .nav-links a .icon {
            margin-right: 8px;
        }

        .search-bar {
            display: flex;
            flex-grow: 1;
            max-width: 400px;
            margin: 0 20px;
            position: relative;
        }

        .search-bar input[type="text"] {
            width: 100%;
            padding: 10px 20px 10px 40px;
            border-radius: 5px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            font-size: 14px;
            color: #333;
            outline: none;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        .search-bar input[type="text"]:focus {
            border-color: #a851ff;
            box-shadow: 0 2px 8px rgba(18, 255, 243, 0.2);
        }

        .search-bar input[type="text"]::placeholder {
            color: #55455e;
            font-style: italic;
        }

        .search-bar .icon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #888;
        }

        @media (max-width: 768px) {
            .navbar {
                flex-direction: column;
                align-items: flex-start;
            }

            .nav-links {
                flex-direction: column;
                width: 100%;
                margin-top: 10px;
            }

            .search-bar {
                margin: 10px 0;
            }
        }

        @keyframes bounce {
            0%, 20%, 50%, 80%, 100% {
                transform: translateY(0);
            }
            40% {
                transform: translateY(-10px);
            }
            60% {
                transform: translateY(-5px);
            }
        }

        @keyframes pulse {
            0% {
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            }
            50% {
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.20);
            }
            100% {
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            }
        }

        @keyframes gradientShift {
            0% {
                background: linear-gradient(to right, #da91f0, #f1c0e8);
            }
            50% {
                background: linear-gradient(to right, #f1c0e8, #da91f0);
            }
            100% {
                background: linear-gradient(to right, #da91f0, #f1c0e8);
            }
        }
        
        body{
        font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
    margin: 0;
    padding: 0;
        }


body {
	width: 100%;
	background: #f5f5f5;
	
}

/*======================== PROFILE ========================*/


.profile-detail {
	width: 90%;
	background-color: #fff;
	padding: 0px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	text-align: left;
	margin-left: 390px;
	border-radius: 1rem;
}


.profileCoverImg {
	width: 100%;
	height: 350px;
	object-fit: cover;
	border-radius: 10px 10px 0 0;
}

.profileUserImg {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
	position: absolute;
	left: 33%;
	right: 0;
	transform: translateX(-50%);
	top: 270px;
	border: 3px solid white;
}

.profileInfo {
	margin-top: 50px;
	text-align: left;
}

.profileName {
	font-size: 24px;
	line-height: 0;
	padding: 0px 43px;

}

.profileDesc {
	margin-top: 10px;
	font-style: italic;
	padding: 0px 43px;
}

.userdetail {
	margin-top: 20px;
	text-align: left;
	padding: 0px 20px ;
}

.userdetail p {
	margin: 5px 0;
}

.profile-button {
	margin-left: 700px;
	display: flex;
	justify-content: center;
	gap: 30px;
	margin-top: 20px;
}

.profile-button button {
	padding: 10px 20px;
	font-size: 14px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	background: linear-gradient(135deg, #b63ed6, #a05dff);;
	color: white;
	transition: background-color 0.3s ease;
}

.profile-button button:hover {
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
	background-color: #c87ef7;
	
}

/*======================== FRIEND LIST ========================*/


.friends-container {
   font-size: 18px;
   font-weight: 500;
   width: 66%;
   left: 390px; 
   position: absolute;
   bottom: auto;
   box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
   margin-top: 0px; 
   display: flex;
   flex-direction: column;
   padding: 0px 60px;
   Top: 700px;
   background-color: #fff;
   border-radius: 1rem; 
   
}

.friend-img {
	width: 100px;
	height: 100px;
	object-fit: cover;
	border-radius: 10px;
}

.friends {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between; 
}

.friend {
	display: flex;
	flex-direction: column;
	margin-bottom: 20px;
	cursor: pointer;
	text-align: center;
}

.seeall-friends{
	padding: 10px;
	font-size: 14px 0px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	background: linear-gradient(135deg, #b63ed6, #a05dff);
	color: white;
	transition: background-color 0.3s ease;
	margin-left: 850px;
	}
	
	.seeall-friends:hover {
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
	background-color: #c87ef7;
}

/*======================== Side bar========================*/


    .profile-photo {
        aspect-ratio: 1/1;
        width: 2.7em; /* Adjust the height as needed */
        border-radius: 50%; /* This makes the image circular */
        overflow: hidden; /* Ensures the image stays within the circular container */
    	border: 3px solid white;
    }

    .profile-photo img {
        width: 100%;
        height: 100%;
        object-fit: cover; /* Ensures the image covers the entire circular area */
    }

  	main .container {
	column-gap: 3rem;
	position: relative;
	display: flex;
}
    
    main .container .left{
    	height: max-content;
    	position: fixed;
    	top: 4.4rem;
    
    }
    
    main .container .left .profile{
    	
    	background: white;
    	border-radius: 1rem;
    	display: flex;
    	align-items: center;
    	column-gap: 1rem;
    	width: 100%;
    	margin-right: 200px;
    }
    
    .left .sidebar {
    	margin-top: 1rem;
    	background: white;
    	border-radius: 1rem; 
    	margin-right: 
    	
    }
    
    .left .sidebar .menu-item{
    	display: flex;
    	align-items: center;
    	height: 4rem;
    	cursor: pointer;
    	translation: all 300ms ease;
    	position: relative;
    }
    
    .left .sidebar .menu-item:hover {
    	background:  #f5f5f5; 
    }
    
    
    .left .sidebar i{
    	font-size: 1.4rem;
    	color: gray;
    	margin-left: 2rem;
    	position: relative;
    }
    
    .left .sidebar i .notification-count{
    	background: red;
    	color: white;
    	font-size: 0.7rem;
    	width: fit-content;
    	border-radius: 0.8rem;
    	padding: 0.1rem 0.4rem;
    	position: absolute;
    	top: -0.2rem;
    	right: -0.3rem;
    }
    
    .left .sidebar h3{
    margin-left: 1.5rem;
    font-size: 1rem;
    	
    }
    
   .left .sidebar {
		margin-top: 1rem;
		background: white;
		border-radius: 1rem;
	}
	
	.left .sidebar .active {
		background: light;
	}
	
	.left .sidebar .active i, 
	.left .sidebar .active h3{
		color: rgba(173, 0, 226, 1);
	}
	
	.left .sidebar .active::before{
		content: "";
		display: block;
		width: 0.5rem;
		height: 100%;
		position: absolute;
		background: rgba(173, 0, 226, 1);
	}
	
	.left .sidebar .menu-item:first-child.active{
		border-top-left-radius: 1rem;
		overflow: hidden;
	}
	.left .sidebar .menu-item:last-child.active{
		border-buttom-left-radius: 1rem;
		overflow: hidden;
	}
	
    
    .left .notifications-popup{
    	position: absolute;
    	top: 0;
    	left: 110%;
    	width: 30rem;
    	background: white;
    	border-radius: 2rem;
    	padding: 1rem;
    	box-shadow: 0 0 2rem gray;
    	display: none;
    }
    
    .left .notifications-popup::before{
    	content: "";
    	width: 1.2rem;
    	height: 1.2rem;
    	display: block;
    	background: red;
    	position: absolute;
    	left: -0.6rem;
    	transform: rotate(45deg);
    }
    
    .left .notifications-popup > div{
    	display: flex;
    	align-items: start;
    	gap: 1rem;
    	margin-bottom: 1rem;
    }
    
    .left .notificatins-popup small{
    	display: block;
    }

	.text-muted {
		color: grey;
	}
	
	.left {
		position: relative;
	}
	
	

/*======================== Create Post Form========================*/


.post-create-container {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
    background: white;  
}

.post-create-container .creating-post {
    width: 89%;
    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
    top: 270px;
    border-radius: 2rem;
    padding: 20px;
    background: white;
    display: flex;
    align-items: center; /* Center content vertically */
    justify-content: space-between; /* Space between profile photo and input */
}

.post-create-container .profile-photo {
    display: flex;
    align-items: center; /* Center image vertically */
}

.post-create-container .profile-photo img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    object-fit: cover;
}

.creating-post input[type="text"] {
    width: 100%;
    padding: 10px;
    border-radius: 1rem;
    border: 1px solid #ccc;
    margin-left: 20px;
}

.creating-post input[type="submit"] {
    padding: 10px 20px;
    border-radius: 1rem;
    background-color: #1877f2;
    color: white;
    border: none;
    cursor: pointer;
    margin-left: 20px;
}

/*========================FEEDS========================*/

.right .feeds .feed {
	background: rgba(226, 220, 223, 0.32);
	border-radius: 1rem;
	padding: 1rem;
	margin: 1rem 0;
	font-size: 0.85rem;
	line-height: 0.2;
	width: 85%;
	margin-left: 60px;
}

.right .feed .head{
	display: flex;
	justify-content: space-between;;
}

.right .feed .user {
	display: flex;
	gap: 1rem;
}

.right .feed .photo{
	border-radius: 1rem;
	overflow: hidden;
	
	height: 500px;
}

.right .feed .action-button{
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 1rem;
	margin: 0.6rem;
}

.right .feed .action-button .interaction-buttons {
	display: flex;
	justify-content: center;
	gap: 30px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	
}


</style>
</head>
<body>
<!-- nav-bar begin-->
	<div class="navbar">
        <div class="logo">
            <img src="${image}" alt="Logo">
        </div>
        <div class="search-bar">
            <form action="search.jsp" method="get">
                <i class="fas fa-search icon"></i>
                <input type="text" name="query" placeholder="Search...">
            </form>
        </div>
        <div class="nav-links">
            <a href="${home}">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-heart-fill" viewBox="0 0 16 16">
                    <path d="M7.293 1.5a1 1 0 0 1 1.414 0L11 3.793V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v3.293l2.354 2.353a.5.5 0 0 1-.708.707L8 2.207 1.354 8.853a.5.5 0 1 1-.708-.707z"/>
                    <path d="m14 9.293-6-6-6 6V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5zm-6-.811c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.691 0-5.018"/>
                </svg>
            </a>
            <a href="${login}">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0z"/>
                    <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L10.293 8l-2.647 2.646a.5.5 0 0 0 .708.708l3-3z"/>
                    <path fill-rule="evenodd" d="M9.5 8a.5.5 0 0 0-.5-.5H1.5a.5.5 0 0 0 0 1h7.5a.5.5 0 0 0 .5-.5z"/>
                </svg>
            </a>
            <a href="${notifications}">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bell" viewBox="0 0 16 16">
                    <path d="M8 16a2 2 0 0 0 1.984-1.75H6.016A2 2 0 0 0 8 16z"/>
                    <path d="M8 1a5 5 0 0 0-5 5v3.277l-.858 2.148A1 1 0 0 0 3 13h10a1 1 0 0 0 .858-1.574L13 9.277V6a5 5 0 0 0-5-5zm5 11H3l.857-2.143A1 1 0 0 0 4 9.278V6a4 4 0 1 1 8 0v3.277a1 1 0 0 0 .143.579L13 12z"/>
                </svg>
            </a>
            <a href="${setting}">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-gear-fill" viewBox="0 0 16 16">
                    <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
                    <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.291a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.692 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.693-1.115l-.291.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 2.1 8.873l-.318-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.537 4.31l-.16-.291c-.415-.764.42-1.6 1.185-1.184l.291.159a1.873 1.873 0 0 0 2.692-1.116l.094-.318z"/>
                </svg>
            </a>
            <a href="${profile}">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 1 0 0 14A7 7 0 0 0 8 1z"/>
                </svg>
            </a>
        </div>
    </div>
	<!-- main part start -->
	<main>
	<div class="container">
	<!-- LEFT start -->
		<div class="left">
			<a class="profile">
				<div class="profile-photo">
					<img src="data:image/jpeg;base64,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">
				</div>
				<div class="handle">
					<h4>Khin Thaw Tar</h4>
					<p class="text-muted">
						@khin
					</p>
				</div>
			</a>
			<!-- ==========================sidebar start ======================================-->
			<div class="sidebar">
				<a class="menu-item active">
					<span><i class="bi bi-house-heart-fill"></i></span><h3>Home</h3>
				</a>
				<a class="menu-item">
					<span><i class="bi bi-0-circle"></i></span><h3>Explore</h3>
				</a>
				<a class="menu-item" id="notifications">
					<span><i class="bi bi-bell-fill" ><small class="notification-count">9+</small></i></span><h3>Notifications</h3>
					<div class="notifications-popup">
						<div>
							<div class="profile-photo">
								<img src="data:image/jpeg;base64,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">
							</div>
							<div class="notification-photo">
								<b>Malar</b> acceptd your friend request<br>
								<small class="text-muted">2 DAYS AGO</small>
							</div> 
						</div>
						<div>
							<div class="profile-photo">
								<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLgQuAVzO6KzgjozXp4Mdn6cAH_GiPS5ZHwQ&s">
							</div>
							<div class="notification-photo">
								<b>Zuu Zuu</b> acceptd your friend request<br>
								<small class="text-muted">2 DAYS AGO</small>
							</div>
						</div>
						<div>
							<div class="profile-photo">
								<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBL9Os1k7XoZ8FSNrzu3iawOmBoP2Pfoss9NpJ67ZroIltqXpfSZ3uqZgpUFXVby_n6j0&usqp=CAU">
							</div>
							<div class="notification-photo">
								<b>Kyi Pyar</b> acceptd your friend request<br>
								<small class="text-muted">2 DAYS AGO</small>
							</div>
						</div>
						<div>
							<div class="profile-photo">
								<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDbFGjB-EW1k6T1DtveUgK2x97KmogfBvssg&s">
							</div>
							<div class="notification-photo">
								<b>Thura</b> commented on your post<br>
								<small class="text-muted">2 HOURS AGO</small>
							</div>
						</div>
						<div>
							<div class="profile-photo">
								<img src="data:image/jpeg;base64,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">
							</div>
							<div class="notification-photo">
								<b>Kyaw Swar</b> 238 others liked your post<br>
								<small class="text-muted">4 MINUTES AGO</small>
							</div>
						</div>
						<div>
							<div class="profile-photo">
								<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVAv601Xv00kTyH_O8MvHKaBVJXXhCB21P_Q&s">
							</div>
							<div class="notification-photo">
								<b>Zar Ni</b> commented on a post your are tagged in<br>
								<small class="text-muted">2 DAYS AGO</small>
							</div>
						</div>
					</div>
				</a>
				<a class="menu-item" id="messages-notificcations">
					<span><i class="bi bi-chat-left-heart-fill"><small class="notification-count">6</small></i></span><h3>Messages</h3>
				</a>
				<a class="menu-item">
					<span><i class="bi bi-bookmark-heart-fill"></i></span><h3>Bookmarks</h3>
				</a>
				<a class="menu-item">
					<span><i class="bi bi-bar-chart-line-fill"></i></span><h3>Analytics</h3>
				</a>
				<a class="menu-item">
					<span><i class="bi bi-arrow-through-heart-fill"></i></span><h3>Theme</h3>
				</a>
				<a class="menu-item">
					<span><i class="bi bi-gear-wide-connected"></i></span><h3>Settings</h3>
				</a>
			</div>
			<!--  =====================side bar end  -->
		</div>
		<!-- RIGHT start -->
		<div class="right">
			<div class="profile-detail">
				<div class="profile-container">
	       			<img class="profileCoverImg" alt="" src="https://cdn4.sharechat.com/img_7170528894_435735_291ad095_1663762433294_sc.jpg?tenant=sc&referrer=pwa-sharechat-service&f=294_sc.jpg">
		    		<img class="profileUserImg" alt="" src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUSExMVFRUVGBcXGBgVGBUVFRUVFxcXFhcYFhUYHSggGBolGxUVITEhJSkrLi8uFx8zODMtNygtLisBCgoKDg0OGxAQGi0lHyUtLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAOEA4QMBIgACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAFBgMEAAIHAQj/xABGEAABAwIEAwUEBwUIAQMFAAABAAIRAwQFEiExQVFhBiJxgZETobHRFDJCUmLB8COCkuHxBxUWM3KiwtKyVJOjJENTc4P/xAAZAQADAQEBAAAAAAAAAAAAAAACAwQBAAX/xAAkEQACAgICAgIDAQEAAAAAAAAAAQIRAyESMRNBBFEUIjJxQv/aAAwDAQACEQMRAD8A3hZIULqiifVXkwaTPVkmy0HrZteEOdXK0NcqjyRE+OQY+mFQuroWazlqHuXeaKN8UmEva9V57XqqTJKkDEP5KOWBlj2vVauqrRtFT07ZA/lBfjldz1Zw7DjVcdYaNydlYo2MrztBfi3YKTdCd+qZjyyyOvRksUYKwhSvaFvpSaMw+26C884P2R0CgodoiTJd71z+4xnvboa/GSCdVUopE7yHdMJx6Y1RK/Zb3A/aMaXNjK7TNB1ieXRcTwTtLAMnZG7XtSXEweg5eJ6fJc0mYpezplrZWzNmj9czxROna0XbNb6D4pAw7FJ1Jk9f1p4JpsLzqhcUg02yTF8FbGYAeiXa9jHBPlGqHCDxQfErCJU+aLW0OxST0xSdQCgdbjkiN3ThUypfKx3A9o2zVDeWojZSCpCq4hdd1K5NsYolPCcrXlqZGtC56y7c2rmlNdlelwCOaaARcr24JUlGw6K1aU5Rq1tgt42gLAv929Fiafo4Xq7gdyEu77MvDZY8OI4ER6GUuupkEgiCN+a6iG6JM7Q0R7WRx3QS0MTsAezWeyV5tFbCgs5GlAUV77JX/YFe/RzyXcjii2miVnhVR4kCBwnj5LLW1l7QRoSne1oCNAsScujroVDgT26mI6Kxb4YZTgKQIVC8rsoiTvwCbj+O5MCWZUD6diGiTp8+i5/2rsnVXuI1lMWLdoCT06IOcQG86Feri+NxRFP5CYkf4ce7i7eBt8fNeXfYurllriTrvtPIxsuhWVZjnN2+sPei9BzO6OpPvRSi0+xacWujgVKyrZ/Zta7MZ0HCN55RG6acLwavo1ozHpJ9BEu8Yj4psbRoio+BoXEuiNTO08tvRMFjWEQ1oAO8Df8A1ExPmtkmBFKxdw7CLlu+UdM2voNZ8k04cXt+t8Y+KuW0HQAH3j5fFT3VqA3m7olOJRFhGyvwNCfL9bIw8iozrwSXY2ziZgwNZ4TxA6Jkw6sYQ96YT+0A8UZwQktTFjFOXdf1ogrmrzZwcWVxlYOrGEKvn6FFrwQl+8esgthuWgZUZqmDCOCDMZJTLg1tsiyP0AkM+F09Aj1Bqp4dbwAirKadGOhMmewsW2VYi4g2CRXLh3Qhtxg+YyUbtGaBW/ZqXhy7HOaXQs08CCmbgoTBCyFniiZ5GBqWCt5KR+ENjZGWBbVAiWONA+V2KdfD8pBHBFLK7EdV7iJCEB2uiGLUZUh1ckH6t21rS6dAFzTtHj4qPPe8EY7V3uWlkDonfcn0XLrymHOygucTsJA9QOHmF6uJUiHLKrC1a6J4yivZvszXvD3XsptOaC+ZeWFoeWMG+UuAJ0EmNdYSq1PI2GHzG3l8+hKdOxnbV1rZfRK1F76bSTTdScG1O/UL8pa7Q99x1njEaKnm/ZIlGT2T9oOyV1ZB1UPp1WMb7SoKbj7RjJ/zDTcJySDqCdjyQJnaUnM6dhA/1OMn0Epox7tdVvGm1p0xQoZclV056rmb+zz6QIBzATO06pKvT7N7QxoayB3YBAB11HHhKzb2ZSWkXMOuuc89N/HT46pkw+qw/aJ8Tm9xI+CC08IbUZmpw0jdhnJJ+6d2eIka6hRsc9jshc5juDakQf8AS86O9Qei5ysOONxOgWbx9+PEFvxCKMbOwzeGySLG9qtMGfIQf4SJ9JR+zxN3F3+34xr7ktuh6TGuzoO4gD3ADkFe+iicw80Bs75p+1PkfkjFC/HBD2btdA/EqcuQitS1KbTZtqmZgEe/kly+olrnMdu0/wBD5iCo82Nrfoox5E9exfvmJUvzBTvfW5yh0aEkA8yIn4pfwei117TDvs5ngc3NGnpM/upSjQfI0sOz1wQHGkQN4JAd/DMjw3TbgmF7SjtrTlFLW2EytWLk7Mlk4ojtbSAp3U4RBlPRQ3QEKuUOKJFltlGVij9oFil8g+n9Au1ugrbrwQk6niHVa18TMbpuXFW0bCal2M9TFGg7qM4u3mk1lVzzMq3QomV58udlSUBxt8QlbXOIgBAGGBuqOKX8NK7yS6OWOL2X73EcyitX7k7AEnwGqWKF/mO/gil3Wy2zzxdDfXV3uCPBjbybCySUYaFHtPibq1QgTGwA67DxQttrkGX7ThLzwA3DQeXEnjpzVqJqBv2nTJ+4yJdH4i2deXiqONXcH2Y3dq6P9rfgvZgeRkJrS3FRwA2nU8x+vcIR2jatkOjaXDy7rfzPmhWEmB11+SLCtA8m/NO1QmC2z1lMMY4Dcg+pPyCGYjRkB3LL6QFdNXT9dVFSdLQDxEehj8wtUvR0ohHAXxoRMaRzB2B8RI8kXucObWBpnUxLCftNiQJ+9G3mOBS9auynxEcvD4EJks62dgIOrddNwJmR1Dod+877qVPsoh0LVGi6k4sDy2PsnvMI/wBJBAHgByR2yuakCaYeObD/AMSHAeRCsY3hjrml7SlAr09SNg7p0Dhx4GORQfAMQbU01a8aOadHNcNwRx/WiXbQaQyUb+n9pjgerRPqH/ki1niNLgCfX83KrZ1XRzHMaj+SK0K5H6A/JZyN4oK2l0SAcuVo2mB6BU8QxGh7cEtBgAZzqzNyI20073yUTbpxcOR85Q3FqOV5CHJkcYmwgm9hPtRe0zQAcJJ1YQQI69Rw6+S5O2rU+lsfT+tTObXaIIIPQgkeaYcTuoBCD4LGdxO5UuXNy2PhhpnSMHxRjwDBaeIOseY0KZbR0pDwZ/eACe7QQEWCVi/kRouF6D47iIpsJKu13wEp49SdU04SmZm3FpC8EFyTYN/v/wAV6vf7rWLzPx8n2en5cf0JFpfyrftZS3Y1EaoOXqSlZ5mNew5ZuEIgKoAlL7LiFFdYppEqWSKEHauJCN0p9ocZ0IBVC7xF0FK2J3DidVmPDcrZ2TPwjoZsBvZfqmfH7vJbt5yT57A+SQ+zlT9oDyEpj7TXH7GmJ1Mx05u8hJVaglMRHI3jKODODW1qztcrdzzcT8QxwQLCmurVXVDwJPnuPQK9c1osHkae0qhv7jWNAH+4ox2RwwCgxx0zGTPIjT4p/oUlboq2pjT9bq/Wfp5D8l5eWJY4xqAeHvHpqpxRkDqPh+gu5GrGVKTjA8/yWzRoY+yc0fhOjgp7W1MRyM+7+SIOsCzLUAkHQjx0Pz/Wucg1CwdSrjY7Hj1+8PdI5a8Fds7w0qgJ2PmDz8t9ORcOKiucLI1bqw6gjhxULWmMjxpzHTZzeRC1yvsxRaHe0rZXB7djqOJy/aaeZaZ8YdzQ/tN2fBd9LoAAn/MaNp01+Hjpx3pYFfZT7OodDBDuAOga/wANmkcBl5EpqtKuUlrtjLSDqAOvhJ/dd0QvZvsE4LfOgc+uh9Qmm2rB27Z8gfeEv3FiG1Dl0PLn4FF8OlL2mHpoO2lJu4aB1/qhfaGicxhFbVSVqGfVbkXKNAY3U7OZX2E1HbBAfZvo1IcCJXaTh4jZLfabA2vpkRrwPJSxw12UvNa0COylUOfPJdBoVRC452dxA0ahY4wQYT5bY0NNVqfjdAteRWhlrPlUatCStaFxmV4BWQVollKtFL2CxXcqxHxQHkZ85WFRHbZ6WLGoj1rUU09DsTLNzUgIHXvO9CKXbtEsXLu+gSTQUpUFXMkIRiNoeCMWTpCYLfDQxgqOAk/VzfVb+Ij7TuIbrzQwtSGSjGUQB2dwkth1TukjRv2nDgSPsjXjvwHFQdtbrUgcf2bY5CM59Yb5FNVGjBmczj3pM7uHdmdT94+XJKOPW5dWHIOY0Tync9SST5qpPdiOH66NcbEWlMfjqH4D8k34TgD30WF9XI0NGg0AEAakpY7Q05tm/hz/AJH80ydqKtRtCm1khop5zl3hrZ066QPHoi9BQVSYRodnWH6lfMRycD6hELXAiBHwSNgFCs2vFKuXvY5ge0HNSDqjg1rQ8kl2pgnoQuuYbUzsa+IzDUHcHYg9QQR5LvG0d5U1aA9PAyHTHj+v1ususKqBsNMJuZR0VDFKopsc86BoJPgBKxxNjPYk/Q7un9VocOQI+BUtJxOla3c3qBoOqXK/aq+qOz0mhtIjM3MNSwAnMGyJECYkmNpTjgeP1mPbRu6YBOzuDhzaQO8I1IiQATqASB4NBc4t0uwdf4Plb7Sn3qfqWcNRyjTw0PNXMHvZim8w4QGOOzhwpvcftD7LjvsdU+jDGOaS0AZt+R8QufY1ZG3rezLc1N50B4TMQeHEeiGT4nRqQdq0y9sD67NhtIG7SOenu6a74fcbKjh93s2S7aJP7QDhBP8AmDb8SLMtxU1aQHceR8Ru0+ULf66BquwtSqaIvZtlqXaIc3RwI+HkUcwqrwRrsVk60EDTCFYnS0KMIfiOxXZFoDE3ZwLt200brMNM2vmFWwnHXGo1pPFF/wC1Zolh4h3xCRsMqRVYeoSnFTjbG8pY5NI+gcEqS0I6w6JY7PVe4EwCronRegJRbLOdeKt7cLEdgcGfNFm9HrJ6AWVApgsbchTZEHhss1m6JdxGnDpTU+logt5QzPaI3PuGpSYy2WTguIW7I4YHua522mnz+P8AVF72t7asAfqA+jBqQB1AW/ZulDHHk13noRKgtx33dQ6PQo7WgUtHmHvzuJO7jmP7x+XxQ7HbYe29D/DqjGB2pEuI4ADxgKj2iMFzv3R48fyT0haegDenNb1Byn3tP5gLrlbBWPpNDhIyZT1a5uUjmNOS4/R1a9g3c1wHiBI+C77ZkFjTzaD6iU3ErBl2LeE9nqVJ+cSTOb6rBmfBAe/KBmIkxsJMxMQcs7LI2JnvPdqAPruLzt1cUQFMLG6mE1RFzf0iWlS0QTtNhbq1MMBaBna5wdMOa05ssgGO8GmY4JnDdFUqAFBSZ0W0zltj2Hd7Z5dJFRjaT3l1NzRRaGtIpt+sHOYwN5N4EgQX7EcFbcBrS2MpBDti2PuwiDLYSr9uyFj+gqSakvRphtmWNDSZgRKWf7QrGWMfxDo/P/j707NS523E02N4uqNA96VOP6mYpt5BCxNzWBrXAnNMxu1uY6/yVusX0mNeyo4tyvOvejK3MN9Rw2U9xZvqE5aTnD6rXRDSRv3jpEyoe1Y+jWQpZpqFuXTeHOb7Rw/CNGz+JR8W22enyioJPspYd2+qN0eOUjL3o6jNp5p4wXGGVYIGU+4riWBUc1XL94OHmJhdW7PW0Bh6R6HRNhOV0TZIRofWvkShuJ1e6VOKkNSv2hxTK06pmWWibFDZyv8AtHug9wAPGUmUjDgeRCPdrXTWdro6HN8HDN6SY8kAWwVKgMruR1/sziY9m3wCZG4gIXJsExAtaBKMjG44rKaQ2MkzoH04c1iQ/wC/hzWJfNjqiC8MwsQNEYp2AHBT4ezREg0J2RaJ8b2B6lshotP2gPj8ky1qaGVGQ5eXyqRf2i3grNS3mHAehhRstiHT1Uts/KZHAyrlwBMjY6j5KnHtf4Km6ZrTpBrQOQ/X5JQ7Q1Mz4Gw+PEppua3dSliTgwZuJ269fBXRVonboCGtke3oQSu3dk7z2lpRMzlb7M+NPue/KD5rgdZx35ldC/s3xvI72Lz3asFpPCqIZ/uaGjxA5roOmamdUNTqqtR9QOBZET3gZkiPsnnK1NTRCKuNVmkgW7tDuXMgidx3gUblqg4Y5Tl+oz29zVLoLW+zy6GTnzTsWxEROs8Oum7jqgNp2lds6hUDebWl3qBqrltirapIaHiPvseyfDMBPkhjJG5ME4PaC1NytUnIZTertFy2xcloJs2ST2hxNta69ixwmiQ3p7Wp145QQfJyOdoMY+j0XOH1oMcmgCS49AAdOJ844Vi+KPYWsa4ioJrPcPre2d3m682iPNxSskr/AFQOCFNyZ1PtdjzcNo02tp+0c8EM1AYC3LJfxnvAwN9dQuaMv6lzcPNZxc6q1zRwAiHMa0cGyNup4klLd7j1xduJuKpqOa3K3RrQ0cYa0Aanc8dOSM0BpTqt6H94AfyScz9eijCrVvst4dQyVQeTp8j/AFXXsGpjKOWhHmFz99uHhtVvHfx4/PzTv2ZuZYGncLMa2dlegzePgFc17ZXBAK6TcMzBI3aLD8xgjSdfBNmrEQdHKe0Z/aNB3FNjT5f1QsBGsfoF1Vx5z73OIg+BCHXNHK6OUT4xr75Ronn2T0HwFDc3hC9nRDLyoioVyo3/ALxdzWIXnWLOKN8kjttrorzSqVAK61dl6KMfZjtkKvd0SqvCEXtVeR/2ekujyjXjdEbe4BGUnT3g8wgYqqanXABPJPg2mBJWgldCASfqjU9enmk7FXFzvH3DgB0TBcXEU2/iJP5D80v31aSWtHiea9DlSok4WwLVZrG/zRnCWwFXo2RRe3wqsWZm03EcDoB4yYnySeTlpDFFR7HLAO0rTFOs7Xg87Ho88D+Ljx11Ld7AO0KReyHZB75q3ALafBk96p4kHus8NT0ET020tKbGhuRoA0AAAAHIKiN1sVKaT0VLewA4KWrRARWnZN4aeZQ7F7Wq1uZkPA3Ed8dRGjvDfxWSVKzPLye2VC+FLRuOSFU5dqSiNAJXJvoc0gT2vJ+j1HnXQe9zR+a4NeXOau9xO7nTPLUfBfR2J2gqU3Uzs5pB851HVcAx/szcW9Z2Zji0kkPaC5h13kbeB18d1iW9mS/mkBLU5asHjonLAHAtNN31T7uR8pg9IS+3BK1SC2m87cD5QTomfDOz920BzqFVpHHI+COui6ceStA4pcdMZsHaW9w7H9Ag/rimKwbkMhLeHe1AAyz5afrzTFa1ngatHvWQQybsZaNaQqmI2geDI+YVahejlCuOrgjdNQhoRcT7OAEuaM3Ecx4cEkYtgjmuOhjczu3xHJdeu0JvaFOoIdp+Ibj+XTZFRklZxesUFvHJ17S4MaNQiNDqI26x0/pwSbiVAgokSNNFBYvFi047taqes+AoLHZZiLoBTMuO1Q2E6dgy6xEDSUPq3WZAb66JqEcArNKpovMeBRZcs/LouvrLX6T3SPBUa9ZU3XMFaobO8gyXFSaLHctPefmELtmd6OqtYNVFRrqR495vUgajx6dF7UtqjGl7QZbvAnQ92eglzTKd3oG6XIcey3Z9r/2tRssboGnZzuR5tHHmdOBCb22wc7UAgRpzOwHgvLXIKbBT+plbl/0kSD1JmZ6rSuDu06jUdVdHH44UiB5PLkuXQbotGaODdT1cdf5+Y5Ka3dncT9kaDrG59dB4HmliliLgS2dHSesxqPFSXF44WbA0/WysJGhy5SSPOI9VPLIivwNKxjo45RnKHg+CIUbpr9BquYUWS4f0T3gIgAJcMknKjsuGKjaKOJ0Ayq4DQHvDz394Kyg5W+0je8w8wR6EfNDmuha47Ng7ii9UqCENrMznT14D9cljsztNhxPL+ampsAgAfz6lNhivbFzy8dIlsbNrTIGvP7R8+AV2jcT9Rhf1zZGeRiT4xHiq9GmHgtP1djH2jt6A+vxK21PLoim30hUae2LmPYL7WalMOpVeLSe47qCNJ678wkg3dSm8sdmBBgg812ZzARBXNv7SMPDKjKo0zy13UtiPcSP3QpcsGlyRRgyW+LNbG+zRPqiDakJWwmpwTBSOiyErQ2caZBe3MHT+SoVa+o6iVtd6noqz26piYFA/tGwVKLSd2PLZ6QfkEhYzZaEp4v6wdDRqAST1KBYtQlpTlHVskm1y0c99gvUV+jLEuwTruHt0CrYyYaUQsWd1DO0Bhh8FQ8iCUGjm9Z01D4q/RdoqLWS4nqr1NuihnK2PxxohrvVQAkwBJKvOoEkAAknQAAkknYAcUd7KYTVp3lE1aNRvekZ6b26wcphw4OynyRQVnT0Xez3Ya57tR7m0hoYdObp3RJB8YT8MEYaFWnPerMdTLojcEAgcNTPkFeYyPrb8vmV46sroYorZJLLJqgH2NvTUs6ciHU5puHIt2b5NLR5I0XJZwj9jd3VDZrz7dgmdH9555/Xe4Rypo8KvVGugUi7VsWupF5GsgA8dx66AqsbbuFjTAOsHUE/EePXiiVyYtqQ+84u8gD/2Q7Mk+OMnbQ9ZZRVJlW3tSHiR+YPmm7ChBCA22rkwYfoQlOCjLQx5HKOzzH6UhkcyPUD5IWKYG/oPzKN43/lzycPgR+aAOqJ0IJ7EvI0qRs5/9FHmJOVu53P3W8/E6geZ4KtWugNAfkPHn4BEcPpabb8TuTzP65BHJ0BFF+ypQByHuRVgnyVC3GivUikXYZMEkf2qt/8Ap6f/AOwf+D07hJv9pGtIDkWu9HQ73EoMv8MZg/tCPgDCUxT7lTwayyhxRN1MNGvj/MpGNaLJvYLqsABe4gAaknQAeKSsR7Siq806M5Bu/Yv8OTfipe3OJPq/s2kimOA4nmfkl/C7OITYK3slzT1SD1B2i0vWS0qa3pLerS0VMpaJY3Yr/R1iM/RByWKex3EdbU90IT2hEsd4K1QrGFBfjMIUUck26PRlCKViTStVcp2iM07FWadnyEk7c/JULG2Tc0e9jLNorl7hq1pyzwJIBI6xI8yn41yBEkBBMKwoUu87V52A2b48z7kW9lxP81fhg4xpkWWSlKzR1SdArFrR17zQdOJIj04rRojRoknzW9Wg4ADNk5nc+n63W5dROwK5nlTs/RNdlw2o5r2NyEFrXNe3vRmmNs7o8eiKjDhH1v8A4x+TkKZSDd61Q+OQD3NXtzib6Ra1lcumZa4AkbQdBsfyUzzNFn40WtB27tgRTbmIDGxAYDMxxnTbZU3WQ4h5/cpf9lBb4zVP2vcPktsQxF4Ye+fLT4LPOA8DIbaM7gAREbxx6DZGrI6hK/Z+pIceZ/M/yTNaHZHLsGqLWOf5Dzyg+hBPulI9xXc4ADcmIHVdBr0s9NzPvNLfUQl2zwptIZnMkj7RM6noDomQkkhVWQ2FgxkFwDnaROrW+A/NGw3ukwFFQcw7CPh8Ver/AFPNDJ2alRXo7SrdM7KoZ2U7HaBAFRaaquKYeyvTdTqCWuBHIjqDwKmY5ShbVoHoTaeHGkPZu1y8dsw4H0QrFnyCPX8gmfG6nfd+FoHmZP5pXu9ZSarRUpNq2JmJ2MlRW1jCY7ihKibbokJktlBluvX0ESFJeOpImDxA30dYinsliw08o0VIaKVW39T77vVStvqn33eqHglsc7fsY/YRuiuBWwzF54aDxO58Y+KF1rwlrZ4BEMBuPrt8D+R/JF8eachefG1Fhoua3YfmfVaUjmJ10G53j+a1dQ0kuAHTfwWB4iAIH618Vf7IqVaLXt8o00Hv8zxVW7pZm/5uWRuAHOE8p0B8ZUFesqrGteMofld0ifESp8/RV8YHu7B27iXvurt5OsvfRd10mloOiuUMEtqDSWV3yBoHGkZdwENaDql+v2OrGo53tG1QTI9oXZm7aCZEb8R4Ija4U6jGZkToCIIPmPzUcv8AD0I6XYxWRUeN1oYvbPZDe0taGHwKFLYDZf7LHuHxH/i1NNs5KnZk6PH4v+LUzUCqZdsl7SDtA6Knb1P2j2HaSPUB35qa0eqLnRcP/dPqI/4rL6BS7Nq1oWvGX6h9Wn5K493d/XJSVXaKlWqcP6LTFswu0XrDooDUUjCssKizSKtMKpsKttEBamBIVb5xNas3idusAR7kpXONUwSCHeiaO0B9ncB33wPUafJLXabBQ+a9Pj9YdeiUuyn0ii/GqR5rz++aXX0/mlmo2FHKZQFIav76o/i9B81hxqj+L0HzSpKyVx3FDT/fNH8XoPmvUrSsXHcURBSArsRwK1/9NQ/9qn8lqcMtm7UKI/8A5U/kh5BUzn1apACkwy8yVAZ0PdPn/OE23opcKNL/ANtnyS5iFKdmtHg1o+AQ4sMrtGzzRriwzWu5A8Vo+4gJZN1WaIy5x4wfWDK0fi74g0neRaVe2yKo/Yw1a6D4kJ4xyI0I8Ch1TGnf/hqf7P8AsqFzjTuFF5PUtA9QSly2hkWl7DNga2v7apofvE8OqMWuY/Wc53iZjy4JV7N40e+K7RTkgtOpbEQQXRvoDOkz6s9tf0jtUYfBwKllAqhltdh23OiX+1NeGuPIImMQpgavaPEhJfbLGKVRjqbHh5docpkAcZI20+KxLZsnoa+wl4KgqQft/wDEJ0Y6Fxj+z3GaVo97KhDWPhwcdg4CDJ6iP4TzXQ39qbfhWp/xt+aNy9sCEG0h0tqqr1D+3cfwM+L0pHthRGzw7o3vH3bKSh2oZv3y524FOpA5AEtH6lLU02FLG0O1zW7o13VGtU5b8zsEB/xHmEChUOn2sjR/5E+5Vq99c1BDctIfh77/AONwgfw+aJ5YoWsUg1dYhTpaveBO2YgT68F5bY1RP/3qf8bfmlWlgLc2d0ucd3OJc4+LjqUUtsOA4JLyyb0h6xRS2xrtr+m4917SeQM/BXW3bJjMCdNBqdYjTzHqly1pZeAIIggzqNDw6gK8wmZ4n06D3D0TIz+xEse9FTtTaOq081MFzmEFobuZIBE+BnyVO0sa2rHUzqNdo8jz+SZLccz+vNXGUgESVuwXNxVHKcS7JV6lQ5aTwCHEGABmDSWgknSXADzQ3/BV7IHsHTmcCS6nkgAQ7NnmDJ4cF2zLzXhphMA5s4j/AIKvoJ+jnThnpSdSNO90963uOxF6HQyg97Yac00WaloJBBqbgkjyXbMgXoauO5s4f/gnEP8A0rv46P8A3WLuKxZTO5sXqqoXSxYloqBNdUK6xYrMZ5+XspP4qBYsVJOiKpsqr1ixAw/RC5D7xYsUuUoxFeyRmjw8visWKXJ0VxLtBScVixKHotUuCIUFixI9h+i/SVtqxYnRFskapmL1YjALLFMF6sW+zC1SV2juvVibEnmSrFixMFGLFixccYsWLFxx/9k=">
				</div>
				<div class="profile-button">
					<button class="edit-button" type="button">Edit</button>
  					<button class="detail-button" type="button">Detail</button>
				</div>
				<div class="profileInfo">
					<h5 class="profileName">Khin Thaw Tar</h5>
					<span class="profileDesc">" No stars is out of reach! "</span>
			
					<div class="userdetail">
						<h3>User Information:</h3>
						<p><span id="bold">Location&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: </span>&nbsp;Yangon</p>
						<p><span id="bold">Education&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: </span>&nbsp;University of Computer Studies, Yangon</p>
						<p><span id="bold">Relationship&nbsp;: </span>&nbsp;Single</p>
						<span>Show more info.......<br/><br/></span>
					</div>
				</div>
			</div>
				
			<div class="friends-container">
				<h4>Friends 
				<button class="seeall-friends" type="button">See all </button>
				</h4>

				<div class="friends">
        			<div class="friend">
            			<img class="friend-img" src="data:image/jpeg;base64,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" alt="Friend 1">
            			<p>Malar</p>
        			</div>
       				 <div class="friend">
            			<img class="friend-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLgQuAVzO6KzgjozXp4Mdn6cAH_GiPS5ZHwQ&s" alt="Friend 2">
            			<p>Zuu Zuu</p>
        			</div>
        			<div class="friend">
           				 <img class="friend-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBL9Os1k7XoZ8FSNrzu3iawOmBoP2Pfoss9NpJ67ZroIltqXpfSZ3uqZgpUFXVby_n6j0&usqp=CAU" alt="Friend 3">
           				 <p>Kyi Pyar</p>
        			</div>
        			<div class="friend">
           				 <img class="friend-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDbFGjB-EW1k6T1DtveUgK2x97KmogfBvssg&s" alt="Friend 4">
           				 <p>Thura</p>
        			</div>
        			<div class="friend" >
            			<img class="friend-img" src="data:image/jpeg;base64,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" alt="Friend 4">
            			<p>Kyaw Swar</p>
       				</div>
        			<div class="friend" >
            				<img class="friend-img" src="data:image/jpeg;base64,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" alt="Friend 4">
            				<p>Yu Ya</p>
        			</div>
        			<div class="friend" >
            				<img class="friend-img" src="data:image/jpeg;base64,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" alt="Friend 4">
            				<p>Yupar</p>
        			</div>
        			<div class="friend" >
            				<img class="friend-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu7kV3_X_s936Kv7DsBzT2T4GOCW2WyPmq-Q&s" alt="Friend 4">
            				<p>Nyein Chan</p>
        			</div>
    			</div>
    			 <p>............................................................................................................................................................................................................................................</p>
    			 
    			 <!-- ===================create post start ==========================-->
    			<div class="post-create-container">
    			
    				<form class="creating-post">
						<div class="profile-photo">
							<img src="data:image/jpeg;base64,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">
						</div>
						<input type="text" placeholder="What're you thinking, Khin Thaw Tar?" id="create-post">
						<input type="submit" value="Post" class="post-buttom">
					</form>
				</div>
				
				<div class="feeds">
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="data:image/jpeg;base64,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">
								</div>
								<div class="ingo">
									<h3>Khin Thaw Tar</h3>
									<small>Yangon, 15 MINUTES AGO</small>
								</div>
							</div>
							<span class="edit">
									<i class="bi bi-three-dots"></i>
							</span>
						</div>
						<p>Love is the flower you've got to let grow.</p>
						<div class="photo">
							<img src="https://www.ugaoo.com/cdn/shop/articles/shutterstock_223679731.jpg?v=1661873480">
						</div>
						<div class="action-button">
							<div class="interaction-buttons">
								<button><span><i class="bi bi-share-fill"></i>&nbsp;Share</span></button>
								<button><span><i class="bi bi-chat-left-heart-fill"></i>&nbsp;Comment</span></button>
							</div>
						</div>
					</div>
					<!-- one post start -->
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="data:image/jpeg;base64,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">
								</div>
								<div class="ingo">
									<h3>Khin Thaw Tar</h3>
									<small>Yangon, 1 DAYS AGO</small>
								</div>
							</div>
							<span class="edit">
									<i class="bi bi-three-dots"></i>
							</span>
						</div>
						<p>Live your live in full bloom just like a sunflower.</p>
						<div class="photo">
							<img src="https://cdn.britannica.com/84/73184-050-05ED59CB/Sunflower-field-Fargo-North-Dakota.jpg">
						</div>
						<div class="action-button">
							<div class="interaction-buttons">
								<button><span><i class="bi bi-share-fill"></i>&nbsp;Share</span></button>
								<button><span><i class="bi bi-chat-left-heart-fill"></i>&nbsp;Comment</span></button>
							</div>
						</div>
					</div>
					<!-- one post end -->
					
					<div class="feed">
						<div class="head">
							<div class="user">
								<div class="profile-photo">
									<img src="data:image/jpeg;base64,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">
								</div>
								<div class="ingo">
									<h3>Khin Thaw Tar</h3>
									<small>Yangon, 2 WEEKS AGO</small>
								</div>
							</div>
							<span class="edit">
									<i class="bi bi-three-dots"></i>
							</span>
						</div>
						<p>Smile because you never know who is falling for it.</p>
						<div class="photo">
							<img src="https://wallpapers.com/images/featured/beautiful-girl-56gern7ymjxmeamp.jpg">
						</div>
						<div class="action-button">
							<div class="interaction-buttons">
								<button><span><i class="bi bi-share-fill"></i>&nbsp;Share</span></button>
								<button><span><i class="bi bi-chat-left-heart-fill"></i>&nbsp;Comment</span></button>
							</div>
						</div>
					</div>
					
					
				</div>
   			 </div>
   			 
		</div>
	</div>
	

</main>
    <!-- main part end -->
    </body>
</html>