<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <title>Login</title>
            <style>
                body {
                    margin: 0;
                    padding: 0;
                    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                    background: linear-gradient(135deg, rgb(63, 98, 160), rgb(99, 60, 146), rgb(60, 83, 155), rgb(121, 124, 156), rgb(84, 91, 134), rgb(106, 75, 134));
                    background-size: 400% 400%;
                    animation: gradientShift 10s ease infinite;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                    overflow: hidden;
                    position: relative;
                }

                @keyframes gradientShift {
                    0% {
                        background-position: 0% 50%;
                    }

                    50% {
                        background-position: 100% 50%;
                    }

                    100% {
                        background-position: 0% 50%;
                    }
                }

                body::before {
                    content: '';
                    position: absolute;
                    top: 0;
                    left: 0;
                    right: 0;
                    bottom: 0;
                    background-image: url('https://www.transparenttextures.com/patterns/cubes.png');
                    opacity: 0.1;
                    z-index: 0;
                    animation: movePattern 10s linear infinite;
                }

                @keyframes movePattern {
                    0% {
                        background-position: 0 0;
                    }

                    100% {
                        background-position: 100% 100%;
                    }
                }

                .container {
                    background: white;
                    padding: 40px;
                    border-radius: 12px;
                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                    width: 350px;
                    text-align: center;
                    animation: fadeIn 1s ease;
                    z-index: 1;
                    position: relative;
                    overflow: hidden;
                }

                .container:hover {
                    animation: bounce 1.0s ease-in-out;
                }

                .container::before {
                    content: '';
                    position: absolute;
                    top: -50%;
                    left: -50%;
                    width: 200%;
                    height: 200%;
                    background: linear-gradient(135deg, rgba(63, 98, 160, 0.2), rgba(99, 60, 146, 0.2), rgba(60, 83, 155, 0.2), rgba(121, 124, 156, 0.2));
                    z-index: -1;
                    animation: rotateBG 5s linear infinite;
                }

                @keyframes rotateBG {
                    from {
                        transform: rotate(0deg);
                    }

                    to {
                        transform: rotate(360deg);
                    }
                }



                @keyframes bounce {

                    0%,
                    100% {

                        transform: translateY(29);

                    }

                    50% {

                        transform: translateY(25px);

                    }

                }

                .container h1 {
                    font-size: 28px;
                    margin-bottom: 20px;
                    color: rgb(63, 98, 160);
                    animation: textFadeIn 1.5s ease-in-out;
                    position: relative;
                    display: inline-block;
                    padding-bottom: 5px;
                    border-bottom: 2px solid rgba(63, 98, 160, 0.5);
                    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
                }

                .container h1::after {
                    content: '';
                    position: absolute;
                    left: 50%;
                    bottom: -2px;
                    width: 0;
                    height: 2px;
                    background: rgb(99, 60, 146);
                    transition: width 0.4s ease, left 0.4s ease;
                }

                .container h1:hover::after {
                    width: 100%;
                    left: 0;
                }


                @keyframes textFadeIn {
                    from {
                        opacity: 0;
                        transform: translateY(-20px);
                    }

                    to {
                        opacity: 1;
                        transform: translateY(0);
                    }
                }

                .container input {
                    width: 100%;
                    padding: 12px;
                    margin: 10px 0;
                    border: 1px solid #ccc;
                    border-radius: 8px;
                    transition: border-color 0.3s ease;
                }

                .container input:focus {
                    border-color: rgb(63, 98, 160);
                    outline: none;
                    box-shadow: 0 0 5px rgba(63, 98, 160, 0.5);
                }

                .container button {
                    background: rgb(63, 98, 160);
                    color: white;
                    padding: 12px 20px;
                    border: none;
                    border-radius: 8px;
                    cursor: pointer;
                    transition: background 0.3s ease, transform 0.3s ease;
                }

                .container button:hover {
                    background: rgb(99, 60, 146);
                    transform: scale(1.05);
                }

                .container button:active {
                    transform: scale(0.95);
                }

                .container .links {
                    margin-top: 20px;
                    animation: textFadeIn 2s ease-in-out;
                }

                .container .links a {
                    color: rgb(63, 98, 160);
                    text-decoration: none;
                    margin: 0 5px;
                    transition: color 0.3s ease;
                }

                .container .links a:hover {
                    text-decoration: underline;
                    color: rgb(99, 60, 146);
                }

                @keyframes fadeIn {
                    from {
                        opacity: 0;
                        transform: translateY(-20px);
                    }

                    to {
                        opacity: 1;
                        transform: translateY(0);
                    }
                }

                .container h1 {
                    color: #6a11cb;
                    margin-bottom: 20px;
                    text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.1);
                    animation: textPopUp 1s ease;
                }

                @keyframes textPopUp {
                    0% {
                        opacity: 0;
                        transform: scale(0.8);
                    }

                    100% {
                        opacity: 1;
                        transform: scale(1);
                    }
                }

                .container input[type="text"],
                .container input[type="password"] {
                    width: 100%;
                    padding: 12px;
                    margin: 8px 0;
                    border: 1px solid #ddd;
                    border-radius: 8px;
                    box-sizing: border-box;
                    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
                    transition: border 0.3s ease, box-shadow 0.3s ease;
                }

                .container input[type="text"]:focus,
                .container input[type="password"]:focus {
                    border-color: #6a11cb;
                    box-shadow: 0 0 8px rgba(106, 17, 203, 0.5);
                }

                .container input[type="submit"] {
                    background: linear-gradient(to right, #6a11cb, #b52db7);
                    color: white;
                    border: none;
                    padding: 12px;
                    border-radius: 8px;
                    width: 100%;
                    cursor: pointer;
                    transition: background 0.3s ease, transform 0.2s ease;
                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                }

                .container input[type="submit"]:hover {
                    background: linear-gradient(to right, #501a96, #8417cb);
                    transform: scale(1.05);
                    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
                }

                .container .signup-link {
                    display: block;
                    margin-top: 20px;
                    color: #6a11cb;
                    text-decoration: none;
                    transition: color 0.3s ease, transform 0.2s ease;
                    font-weight: bold;
                }

                .container .signup-link:hover {
                    color: #501a96;
                    transform: scale(1.05);
                }

                .container .social-icons {
                    display: flex;
                    justify-content: center;
                    margin-top: 20px;
                }

                .container .social-icons a {
                    color: #6a11cb;
                    font-size: 24px;
                    margin: 0 10px;
                    transition: color 0.3s ease, transform 0.3s ease;
                }

                .container .social-icons a:hover {
                    color: #501a96;
                    transform: rotate(20deg) scale(1.2);
                }



                .navbar {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    background: linear-gradient(135deg, #b63ed6, #a05dff);
                    padding: 7px 14px;
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    z-index: 1000;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    transition: background-color 0.3s ease, box-shadow 0.3s ease;
                }

                .navbar:hover {
                    background-color: #c87ef7;
                    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
                }

                .logo img {
                    height: 40px;
                    border: 2px solid #fff1f1;
                    border-radius: 100%;
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

                 #particles-js {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1; /* Place it behind all other content */
        }
            </style>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        </head>

        <body>
            <div id="particles-js"></div>
            <div class="navbar">
                <div class="logo">
                    <img src="${image}" alt="Logo">
                </div>

                <div class="nav-links">
                    <a href="${welcome }">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-house-heart-fill" viewBox="0 0 16 16">
                            <path
                                d="M7.293 1.5a1 1 0 0 1 1.414 0L11 3.793V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v3.293l2.354 2.353a.5.5 0 0 1-.708.707L8 2.207 1.354 8.853a.5.5 0 1 1-.708-.707z" />
                            <path
                                d="m14 9.293-6-6-6 6V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5zm-6-.811c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.691 0-5.018" />
                        </svg>
                    </a>
                    <a href="${login}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0z" />
                            <path fill-rule="evenodd"
                                d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L10.293 8l-2.647 2.646a.5.5 0 0 0 .708.708l3-3z" />
                            <path fill-rule="evenodd"
                                d="M9.5 8a.5.5 0 0 0-.5-.5H1.5a.5.5 0 0 0 0 1h7.5a.5.5 0 0 0 .5-.5z" />
                        </svg>
                    </a>


                    <a href="${register}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-door-open" viewBox="0 0 16 16">
                            <path d="M8.5 10c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1" />
                            <path
                                d="M10.828.122A.5.5 0 0 1 11 .5V1h.5A1.5 1.5 0 0 1 13 2.5V15h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V1.5a.5.5 0 0 1 .43-.495l7-1a.5.5 0 0 1 .398.117M11.5 2H11v13h1V2.5a.5.5 0 0 0-.5-.5M4 1.934V15h6V1.077z" />
                        </svg>
                    </a>
                </div>
            </div>

            <div class="container">
                <h1>Login</h1>
                <form action="login" method="post">
                    <input type="text" name="username" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="submit" value="Login">
                </form>
                <a href="${register }" class="signup-link">Don't have an account? Sign up</a>
                <div class="social-icons">
                    <a href="${facebook}" style="color: #3b5998;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-facebook" viewBox="0 0 16 16">
                            <path
                                d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951" />
                        </svg>
                    </a>
                    <a href="${twitter}" style="color: #1DA1F2;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-twitter" viewBox="0 0 16 16">
                            <path
                                d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334q.002-.211-.006-.422A6.7 6.7 0 0 0 16 3.542a6.7 6.7 0 0 1-1.889.518 3.3 3.3 0 0 0 1.447-1.817 6.5 6.5 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.32 9.32 0 0 1-6.767-3.429 3.29 3.29 0 0 0 1.018 4.382A3.3 3.3 0 0 1 .64 6.575v.045a3.29 3.29 0 0 0 2.632 3.218 3.2 3.2 0 0 1-.865.115 3 3 0 0 1-.614-.057 3.28 3.28 0 0 0 3.067 2.277A6.6 6.6 0 0 1 .78 13.58a6 6 0 0 1-.78-.045A9.34 9.34 0 0 0 5.026 15" />
                        </svg>
                    </a>
                    <a href="${google}" style="color: #DB4437;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-google" viewBox="0 0 16 16">
                            <path
                                d="M15.545 6.558a9.4 9.4 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.7 7.7 0 0 1 5.352 2.082l-2.284 2.284A4.35 4.35 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.8 4.8 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.7 3.7 0 0 0 1.599-2.431H8v-3.08z" />
                        </svg>
                    </a>
                    <a href="${youtube}" style="color: #FF0000;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-youtube" viewBox="0 0 16 16">
                            <path
                                d="M8.051 1.999h.089c.822.003 4.987.033 6.11.335a2.01 2.01 0 0 1 1.415 1.42c.101.38.172.883.22 1.402l.01.104.022.26.008.104c.065.914.073 1.77.074 1.957v.075c-.001.194-.01 1.108-.082 2.06l-.008.105-.009.104c-.05.572-.124 1.14-.235 1.558a2.01 2.01 0 0 1-1.415 1.42c-1.16.312-5.569.334-6.18.335h-.142c-.309 0-1.587-.006-2.927-.052l-.17-.006-.087-.004-.171-.007-.171-.007c-1.11-.049-2.167-.128-2.654-.26a2.01 2.01 0 0 1-1.415-1.419c-.111-.417-.185-.986-.235-1.558L.09 9.82l-.008-.104A31 31 0 0 1 0 7.68v-.123c.002-.215.01-.958.064-1.778l.007-.103.003-.052.008-.104.022-.26.01-.104c.048-.519.119-1.023.22-1.402a2.01 2.01 0 0 1 1.415-1.42c.487-.13 1.544-.21 2.654-.26l.17-.007.172-.006.086-.003.171-.007A100 100 0 0 1 7.858 2zM6.4 5.209v4.818l4.157-2.408z" />
                        </svg>
                    </a>
                    <a href="${instagram }" style="color: #C13584;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-instagram" viewBox="0 0 16 16">
                            <path
                                d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334" />
                        </svg>
                    </a>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/particles.js@2.0.0/particles.min.js"></script>
    <script>
        particlesJS('particles-js', {
            "particles": {
                "number": {
                    "value": 100,
                    "density": {
                        "enable": true,
                        "value_area": 800
                    }
                },
                "color": {
                    "value": ["#00bfff", "#1e90ff", "#87ceeb"] // Water-themed gradient colors
                },
                "shape": {
                    "type": "circle",
                    "stroke": {
                        "width": 0,
                        "color": "#ffffff"
                    }
                },
                "opacity": {
                    "value": 0.6,
                    "random": true,
                    "anim": {
                        "enable": true,
                        "speed": 1,
                        "opacity_min": 0.2,
                        "sync": false
                    }
                },
                "size": {
                    "value": 6,
                    "random": true,
                    "anim": {
                        "enable": true,
                        "speed": 10,
                        "size_min": 1,
                        "sync": false
                    }
                },
                "line_linked": {
                    "enable": false
                },
                "move": {
                    "enable": true,
                    "speed": 2,
                    "direction": "none",
                    "random": true,
                    "straight": false,
                    "out_mode": "out",
                    "bounce": false,
                    "attract": {
                        "enable": false,
                        "rotateX": 600,
                        "rotateY": 1200
                    }
                }
            },
            "interactivity": {
                "detect_on": "canvas",
                "events": {
                    "onhover": {
                        "enable": true,
                        "mode": "bubble"
                    },
                    "onclick": {
                        "enable": true,
                        "mode": "push"
                    },
                    "resize": true
                },
                "modes": {
                    "grab": {
                        "distance": 400,
                        "line_linked": {
                            "opacity": 1
                        }
                    },
                    "bubble": {
                        "distance": 200,
                        "size": 8,
                        "duration": 2,
                        "opacity": 0.8,
                        "speed": 3
                    },
                    "repulse": {
                        "distance": 200,
                        "duration": 0.4
                    },
                    "push": {
                        "particles_nb": 4
                    },
                    "remove": {
                        "particles_nb": 2
                    }
                }
            },
            "retina_detect": true
        });
    </script>
</body>

</html>