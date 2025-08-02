package com.example.tp;

import tp.objets.Producteur;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SupprimerProducteurServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"producteur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Producteur producteur = (Producteur) session.getAttribute("utilisateur");

        try {
            Apce.getGestionProducteurs().supprimerProducteur(producteur.getId());
            session.invalidate();
            resp.sendRedirect("login.jsp");

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur lors de la suppression : " + e.getMessage());
            req.getRequestDispatcher("supprimerProducteur.jsp").forward(req, resp);
        }
    }
}
