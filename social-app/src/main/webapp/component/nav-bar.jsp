<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <title>Navigation Bar</title>
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
            <style>
                body {
                    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                    margin: 0;
                    padding: 0;
                }

                .navbar {
                    display: flex;
                    align-items: center;
                    background :linear-gradient(135deg, #b63ed6, #a05dff);
                    padding: 5px 15px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                    transition: background-color 0.3s ease, box-shadow 0.3s ease;
                    justify-content: space-between;
                    position: sticky;
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

                    0%,
                    20%,
                    50%,
                    80%,
                    100% {
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
            </style>
        </head>

        <body>
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
                    <a href="${notifications}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-bell" viewBox="0 0 16 16">
                            <path d="M8 16a2 2 0 0 0 1.984-1.75H6.016A2 2 0 0 0 8 16z" />
                            <path
                                d="M8 1a5 5 0 0 0-5 5v3.277l-.858 2.148A1 1 0 0 0 3 13h10a1 1 0 0 0 .858-1.574L13 9.277V6a5 5 0 0 0-5-5zm5 11H3l.857-2.143A1 1 0 0 0 4 9.278V6a4 4 0 1 1 8 0v3.277a1 1 0 0 0 .143.579L13 12z" />
                        </svg>
                    </a>
                    <a href="${setting}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-gear-fill" viewBox="0 0 16 16">
                            <path
                                d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z" />
                            <path
                                d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.291a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.692 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.693-1.115l-.291.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 2.1 8.873l-.318-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.537 4.31l-.16-.291c-.415-.764.42-1.6 1.185-1.184l.291.159a1.873 1.873 0 0 0 2.692-1.116l.094-.318z" />
                        </svg>
                    </a>
                    <a href="${profile}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                            class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                            <path fill-rule="evenodd"
                                d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 1 0 0 14A7 7 0 0 0 8 1z" />
                        </svg>
                    </a>
                </div>
            </div>
        </body>

        </html>