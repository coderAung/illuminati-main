package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

	protected void view(HttpServletRequest req, HttpServletResponse resp, String view) 
			throws ServletException, IOException {
		getServletContext()
		.getRequestDispatcher("/view/%s.jsp".formatted(view))
		.forward(req, resp);
    }

    protected void redirect(HttpServletRequest req, HttpServletResponse resp, String path) 
    		throws IOException {
    	var url = getServletContext().getContextPath().concat(path);
    	resp.sendRedirect(url);
    }

}