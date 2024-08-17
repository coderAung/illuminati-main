<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:url var="image" value="/img/logo.jpg"></c:url>
        <c:url var="register" value="/view/register.jsp"></c:url>
        <c:url var="welcome" value="/view/welcome.jsp"></c:url>
        <c:url var="login" value="/view/login.jsp"></c:url>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>About Us</title>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
            <style>
                body {
                    margin: 0;
                    padding: 0;
                    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                    background: linear-gradient(135deg, rgb(14, 157, 205), rgb(4, 97, 93), rgb(68, 32, 120), rgb(49, 54, 15));
                    background-size: 300% 300%;
                    animation: gradientBG 10s ease infinite;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    min-height: 100vh;
                    overflow-y: auto;
                    position: relative;
                }

                @keyframes gradientBG {
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

                body::before,
                body::after {
                    content: '';
                    position: absolute;
                    border-radius: 50%;
                    opacity: 0.6;
                    z-index: -1;
                    animation: float 6s ease-in-out infinite;
                }

                body::before {
                    width: 300px;
                    height: 300px;
                    background: rgba(255, 255, 255, 0.3);
                    top: 10%;
                    left: 20%;
                    animation-duration: 7s;
                }

                body::after {
                    width: 400px;
                    height: 400px;
                    background: rgba(255, 255, 255, 0.4);
                    bottom: 15%;
                    right: 10%;
                }

                @keyframes float {

                    0%,
                    100% {
                        transform: translateY(0);
                    }

                    50% {
                        transform: translateY(-20px);
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

                .contact-form {
                    background-color: rgba(255, 255, 255, 0.2);
                    /* Transparent background */
                    border-radius: 25px;
                    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                    padding: 2rem;
                    max-width: 700px;
                    text-align: center;
                    position: relative;
                    border: 2px solid rgba(255, 255, 255, 0.5);
                    overflow: hidden;
                    transition: transform 0.3s, box-shadow 0.3s;
                }

                .contact-form:hover {
                    transform: translateY(-10px);
                    box-shadow: 0 20px 50px rgba(0, 0, 0, 0.2);
                }

                .contact-form::before,
                .contact-form::after {
                    content: '';
                    position: absolute;
                    width: 50px;
                    height: 50px;
                    background: radial-gradient(circle, #fff, rgba(255, 255, 255, 0));
                    border-radius: 50%;
                    z-index: 1;
                    opacity: 0.9;
                    box-shadow: 0 0 15px rgba(255, 255, 255, 0.8);
                    transform: scale(1);
                }

                .contact-form::before {
                    top: -25px;
                    right: -25px;
                    animation: float-right 6s ease-in-out infinite;
                }

                .contact-form::after {
                    bottom: -25px;
                    left: -25px;
                    animation: float-left 6s ease-in-out infinite;
                }

                @keyframes float-right {

                    0%,
                    100% {
                        transform: translateX(0) translateY(0) scale(1);
                    }

                    50% {
                        transform: translateX(15px) translateY(15px) scale(1.1);
                    }
                }

                @keyframes float-left {

                    0%,
                    100% {
                        transform: translateX(0) translateY(0) scale(1);
                    }

                    50% {
                        transform: translateX(-15px) translateY(-15px) scale(1.1);
                    }
                }

                @keyframes twinkle {
                    0% {
                        opacity: 0.9;
                    }

                    50% {
                        opacity: 1;
                    }

                    100% {
                        opacity: 0.9;
                    }
                }

                .contact-form h2 {
                    font-size: 28px;
                    margin-bottom: 20px;
                    color: rgb(63, 98, 160);
                    animation: textFadeIn 1.5s ease-in-out;
                    position: relative;
                    display: inline-block;
                    padding-bottom: 5px;
                    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
                }

                .contact-form h2 {
                    color: rgb(255, 255, 255);
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

                .contact-form .form-group {
                    display: flex;
                    align-items: center;
                    margin-bottom: 1.5rem;
                }

                .contact-form .form-group label {
                    flex: 1;
                    font-weight: bold;
                    color: #fff;
                    margin-right: 0.5rem;
                }

                .contact-form .form-group input,
                .contact-form .form-group textarea {
                    flex: 3;
                    padding: 0.75rem;
                    border: 1px solid #ddd;
                    border-radius: 8px;
                    font-size: 1rem;
                    background: rgba(255, 255, 255, 0.3);
                    /* Adjusted background for transparency */
                    color: #fff;
                    /* Adjusted color for better readability */
                    transition: border-color 0.3s, box-shadow 0.3s;
                }

                .contact-form .form-group input:focus,
                .contact-form .form-group textarea:focus {
                    border-color: #ff9a9e;
                    outline: none;
                    box-shadow: 0 0 8px rgba(255, 154, 158, 0.5);
                }

                .contact-form .form-group input::placeholder,
                .contact-form .form-group textarea::placeholder {
                    color: #ddd;
                    font-style: italic;
                }

                .contact-form button {
                    background: linear-gradient(to right, #6a11cb, #b52db7);
                    color: white;
                    border: none;
                    padding: 12px;
                    border-radius: 8px;
                    width: 50%;
                    cursor: pointer;
                    transition: background 0.3s ease, transform 0.2s ease;
                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                    margin-top: 10px;
                }

                .contact-form .error-message {
                    color: #ff6b81;
                    font-size: 0.875rem;
                    margin-top: -0.75rem;
                    margin-bottom: 1rem;
                }

                .contact-form .success-message {
                    color: #28a745;
                    font-size: 0.875rem;
                    margin-top: -0.75rem;
                    margin-bottom: 1rem;
                }

                .contact-form button:hover {
                    background: linear-gradient(135deg, #2cb1e5, #ac57b1);
                    transform: translateY(-3px);
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                }

                .contact-form button:active {
                    background: linear-gradient(135deg, #2cb1e5, #ac57b1);
                    transform: translateY(2px);
                    box-shadow: none;
                }
                
                 .navbar {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    background: linear-gradient(135deg, #b63ed6, #a05dff);
                    padding: 5px 14px;
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
            </style>
        </head>

        <body>
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
        
            <div class="contact-form">
                <div class="logo">
                    <img src="<c:out value='${image}' />" alt="Logo">
                </div>
                <h2>Contact Us</h2>
                <form>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" placeholder="Enter your name">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Enter your email">
                    </div>
                    <div class="form-group">
                        <label for="message">Message</label>
                        <textarea id="message" name="message" placeholder="Enter your message"></textarea>
                    </div>
                    <button type="submit">Send Message</button>
                </form>
            </div>
        </body>

        </html>