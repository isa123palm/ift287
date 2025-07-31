package com.example.tp;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // vérification TEMP
        if (email.equals("admin@example.com") && password.equals("1234")) {
            req.setAttribute("utilisateur", email);
            req.getRequestDispatcher("/accueil.jsp").forward(req, resp);
        } else {
            req.setAttribute("erreur", "Identifiants invalides.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

}