<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <c:url var="login" value="/view/login.jsp"></c:url>
        <c:url var="image" value="/img/logo.jpg"></c:url>
        <c:url var="welcome" value="/view/welcome.jsp"></c:url>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Register</title>
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
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

                .particles-bg {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    z-index: -1;
                }

                .container {
                    background: rgba(255, 255, 255, 0.783);
                    padding: 4%;
                    border-radius: 12px;
                    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
                    width: 40%;
                    max-width: 500px;
                    text-align: center;
                    position: relative;
                    animation: fadeIn 1s ease;
                    margin: 20px;
                    overflow: hidden;
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

                h1 {
                    color: #6a11cb;
                    text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.1);
                    margin-bottom: 20px;
                }

                form {
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    width: 100%;
                }

                .input-group {
                    width: 100%;
                    position: relative;
                    margin-bottom: 20px;
                }

                .input-group input,
                .input-group select {
                    width: 100%;
                    padding: 12px;
                    padding-left: 40px;
                    border: 1px solid #ddd;
                    border-radius: 8px;
                    box-sizing: border-box;
                    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
                    transition: border 0.3s ease, box-shadow 0.3s ease;
                }

                .input-group input:focus,
                .input-group select:focus {
                    border-color: #6a11cb;
                    box-shadow: 0 0 8px rgba(106, 17, 203, 0.5);
                }

                .input-group i {
                    position: absolute;
                    left: 10px;
                    top: 50%;
                    transform: translateY(-50%);
                    color: #6a11cb;
                    font-size: 18px;
                }

                input[type="submit"] {
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

                input[type="submit"]:hover {
                    background: linear-gradient(to right, #501a96, #8417cb);
                    transform: scale(1.05);
                    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
                }

                .login-link {
                    display: block;
                    margin-top: 20px;
                    color: #6a11cb;
                    text-decoration: none;
                    transition: color 0.3s ease, transform 0.2s ease;
                    font-weight: bold;
                }

                .login-link:hover {
                    color: #501a96;
                    text-decoration: underline;
                    transform: scale(1.05);
                }

                .name-comparison {
                    display: flex;
                    justify-content: space-between;
                    width: 100%;
                }

                .name-comparison div {
                    width: 48%;
                }

                .input-group input:hover,
                .input-group select:hover {
                    border-color: #501a96;
                    box-shadow: 0 0 8px rgba(80, 26, 150, 0.5);
                }

                /*navigation bar*/
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

            <!-- navigation bar -->

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

            <div class="particles-bg" id="particles-js"></div>

            <div class="container">
                <h1>Register</h1>
                <form action="register" method="post">
                    <div class="name-comparison">
                        <div class="input-group">
                            <i class="fas fa-user"></i>
                            <input type="text" name="firstName" placeholder="First Name" required>
                        </div>
                        <div class="input-group">
                            <i class="fas fa-user"></i>
                            <input type="text" name="lastName" placeholder="Last Name" required>
                        </div>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-envelope"></i>
                        <input type="email" name="email" placeholder="Email" required>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-phone"></i>
                        <input type="tel" name="phone" placeholder="Phone Number" required>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-globe"></i>
                        <select name="country" required>
                            <option value="" disabled selected>Choose country</option>
                            <option value="United States">United States</option>
                            <option value="Canada">Canada</option>
                            <option value="United Kingdom">United Kingdom</option>
                            <option value="Australia">Australia</option>
                            <option value="Germany">Germany</option>
                            <option value="France">France</option>
                            <option value="India">India</option>
                            <option value="China">China</option>
                            <option value="Japan">Japan</option>
                            <option value="Brazil">Myanmar</option>
                            <option value="Italy">Italy</option>
                            <option value="South Korea">South Korea</option>
                            <option value="Spain">Spain</option>
                            <option value="Mexico">Mexico</option>
                            <option value="Russia">Russia</option>
                            <option value="Indonesia">Indonesia</option>
                            <option value="Afghanistan">Afghanistan</option>
                            <option value="Albania">Albania</option>
                            <option value="Algeria">Algeria</option>
                            <option value="Andorra">Andorra</option>
                            <option value="Angola">Angola</option>
                        </select>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-city"></i>
                        <input type="text" name="city" placeholder="City" required>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="password" placeholder="Password" required>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
                    </div>
                    <div class="input-group">
                        <i class="fas fa-venus-mars"></i>
                        <select name="gender" required>
                            <option value="" disabled selected>Gender</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                            <option value="preferNotToSay">Prefer not to say</option>
                        </select>
                    </div>
                    <input type="submit" value="Register">
                </form>
                <a href="${login }" class="login-link">Already have an account? Login here</a>
            </div>

            <script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
            <script>
                particlesJS('particles-js', {
                    "particles": {
                        "number": {
                            "value": 80,
                            "density": {
                                "enable": true,
                                "value_area": 800
                            }
                        },
                        "color": {
                            "value": "#ffffff"
                        },
                        "shape": {
                            "type": "circle",
                            "stroke": {
                                "width": 0,
                                "color": "#000000"
                            },
                            "polygon": {
                                "nb_sides": 5
                            },
                            "image": {
                                "src": "img/github.svg",
                                "width": 100,
                                "height": 100
                            }
                        },
                        "opacity": {
                            "value": 0.5,
                            "random": false,
                            "anim": {
                                "enable": false,
                                "speed": 1,
                                "opacity_min": 0.1,
                                "sync": false
                            }
                        },
                        "size": {
                            "value": 3,
                            "random": true,
                            "anim": {
                                "enable": false,
                                "speed": 40,
                                "size_min": 0.1,
                                "sync": false
                            }
                        },
                        "line_linked": {
                            "enable": true,
                            "distance": 150,
                            "color": "#ffffff",
                            "opacity": 0.4,
                            "width": 1
                        },
                        "move": {
                            "enable": true,
                            "speed": 6,
                            "direction": "none",
                            "random": false,
                            "straight": false,
                            "out_mode": "out",
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
                                "mode": "repulse"
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
                                "distance": 400,
                                "size": 40,
                                "duration": 2,
                                "opacity": 8,
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