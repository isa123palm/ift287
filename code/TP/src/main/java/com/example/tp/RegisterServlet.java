package com.example.tp;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String nom = req.getParameter("nom");
        String adresse = req.getParameter("adresse");
        String nbEmployes = req.getParameter("nbEmployes");

        try {
            switch (role) {
                case "producteur" -> Apce.getGestionProducteurs().ajouterProducteur(nom, email, password, Integer.parseInt(nbEmployes), adresse);
                case "fournisseur" -> Apce.getGestionFournisseurs().ajouterFournisseur(nom, email, password, adresse);
                case "distributeur" -> Apce.getGestionPointsDeVente().ajouterPointDeVente(nom, email, password, adresse);
                default -> throw new IllegalArgumentException("Rôle invalide");
            }

            // Rediriger vers page de login si register fonctionne
            resp.sendRedirect("login.jsp");

        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
