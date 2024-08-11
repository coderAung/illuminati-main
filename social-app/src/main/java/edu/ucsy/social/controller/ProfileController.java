package edu.ucsy.social.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/profile/*"})
public class ProfileController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Extract the path after "/profile/"
        String action = req.getPathInfo();

        if (action == null || action.equals("/profile")) {
           
            view(req, resp, "profile");
        } else {
            switch (action) {
                case "/detail":
                    view(req, resp, "profile/detail");
                    break;
                case "/edit":
                    view(req, resp, "profile/edit");
                    break;
                case "/friends":
                    view(req, resp, "profile/friends");
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }
}
