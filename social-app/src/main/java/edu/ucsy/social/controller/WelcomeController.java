package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "/welcome" },
		loadOnStartup = 1)
public class WelcomeController extends Controller {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
<<<<<<< HEAD
		view(req, resp, "index");
=======
		view(req, resp, "welcome");
>>>>>>> kst-dev
	}
}
