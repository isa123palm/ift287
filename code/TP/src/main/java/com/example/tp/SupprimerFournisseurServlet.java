package com.example.tp;

import tp.objets.Fournisseur;
import tp.objets.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SupprimerFournisseurServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"fournisseur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Fournisseur fournisseur = (Fournisseur) session.getAttribute("utilisateur");

        try {
            // Supprimer toutes les associations produit ↔ fournisseur
            for (Produit p : fournisseur.getProduits()) {
                p.retirerFournisseur(fournisseur);
            }
            fournisseur.getProduits().clear();

            // Supprimer le fournisseur
            Apce.getGestionFournisseurs().supprimerFournisseur(fournisseur.getId());

            session.invalidate();
            resp.sendRedirect("login.jsp");

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur : " + e.getMessage());
            req.getRequestDispatcher("supprimerFournisseur.jsp").forward(req, resp);
        }
    }
}
