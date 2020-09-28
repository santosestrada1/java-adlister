package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: show the registration form
        request.getSession().setAttribute("user", null);
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirmation");

        // TODO: ensure the submitted information is valid
        boolean hasInvalidInput = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || !confirmation.equals(password);
        if (hasInvalidInput) {
            request.getSession().setAttribute("message", "There was an issue with the registration credentials");
            response.sendRedirect("/register");
        }

        // TODO: create a new user based off of the submitted information
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        long userId = DaoFactory.getUsersDao().insert(user);

        // TODO: if a user was successfully created, send them to their profile
        if (userId > 0) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        } else {
            request.getSession().setAttribute("message", "There was an creating the user on the database");
            response.sendRedirect("/register");
        }

    }
}