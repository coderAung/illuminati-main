<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:url var="image" value="/img/logo.jpg"></c:url>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Welcome to Illuminati</title>
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
            <style>
                body {
                    margin: 0;
                    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                    background: linear-gradient(to right, #da91f0, #f1c0e8);
                    height: 100%;
                    display: flex;
                    flex-direction: column;
                    justify-content: flex-start;
                    align-items: center;
                    overflow-x: hidden;
                    background-attachment: fixed;
                    animation: gradientShift 10s ease infinite;
                }

                .navbar {
                    display: flex;
                    align-items: center;
                    background-color: #4e1b58;
                    padding-bottom: 0;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                    transition: background-color 0.3s ease, box-shadow 0.3s ease;
                    justify-content: space-between;
                    width: 100%;
                    position: sticky;
                    top: 0;
                    z-index: 1000;
                }

                .navbar:hover {
                    background-color: #c87ef7;
                    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
                }

                .navbar .logo {
                    margin: 10px 20px;
                }

                .navbar .logo img {
                    height: 40px;
                    border-radius: 50%;
                    border: 2px solid #000;
                    transition: transform 0.3s ease, box-shadow 0.3s ease;
                }

                .navbar .logo img:hover {
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
                    transform: scale(1.1);
                }

                .nav-links a .icon {
                    margin-right: 8px;
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


                .container {
                    text-align: center;
                    background: #f1d8f3;
                    padding: 20px;
                    border-radius: 20px;
                    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
                    border: 2px solid transparent;
                    backdrop-filter: blur(10px);
                    margin: 40px auto;
                    transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease, border-color 0.3s ease;
                }

                .container:hover {
                    transform: translateY(-12px) scale(1.03);
                    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
                    background-color: #e6cee7;
                    border-color: #da91f0;
                    animation: pulse 1s infinite;
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
                    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
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
                    transition: background-color 0.3s ease, color 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
                    position: relative;
                    overflow: hidden;
                    text-align: center;
                    font-family: 'Arial', sans-serif;
                    margin-bottom: 10px;
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
                    z-index: 0;
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
                    transition: background 0.3s ease, color 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease, filter 0.3s ease;
                    position: relative;
                    overflow: hidden;
                    text-align: center;
                    font-family: 'Arial', sans-serif;
                    margin: 5px;
                    width: 120px;
                    height: auto;
                    line-height: 1.5;
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
                    z-index: 0;
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
                    z-index: 1;
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

                .next-container {
                    display: flex;
                    justify-content: center;
                    margin: 20px 0;
                }

                body {
                    font-family: Arial, sans-serif;
                }
            </style>

        </head>


        <body>
            <div class="navbar">
                <div class="logo">
                    <img src="${image }" alt="Logo">
                </div>
                <div class="nav-links">
                    <a href="${home }">
                        <i class="fas fa-home icon"></i>
                    </a>
                    <a href="${login }">
                        <i class="fas fa-sign-in-alt icon"></i>
                    </a>
                    <a href="${register}">
                        <i class="fas fa-user-plus icon"></i>
                    </a>
                </div>
            </div>
            <div class="container">
                <div class="logo">
                    <img src="${image }" alt="Logo">
                </div>
                <div class="group-name">Illuminati</div>
                <p class="subtitle">Welcome to Illuminati, where magical connections happen.<br> Dive into a world of
                    joy and
                    create lasting memories with your friends. <br>Explore our features and get started on your journey
                    today!
                </p>
                <a href="#" class="cta-button">Get Started</a>
                <div class="arrow">
                    <i class="fa-solid fa-arrow-down"></i>
                </div>
            </div>
            <div class="next-container">
                <a href="#" class="secondary-button">Login</a>
                <a href="#" class="secondary-button">Register</a>
            </div>

            <jsp:include page="/component/footer.jsp"></jsp:include>
        </body>

        </html>