package com.example.tp;

import tp.objets.Fournisseur;
import tp.objets.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RetirerFournisseurServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"producteur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            int idProduit = Integer.parseInt(req.getParameter("idProduit"));
            int idFournisseur = Integer.parseInt(req.getParameter("idFournisseur"));

            Produit produit = Apce.getGestionProduits().chercherParId(idProduit);
            Fournisseur fournisseur = Apce.getGestionFournisseurs().chercherParId(idFournisseur);

            if (produit == null || fournisseur == null) {
                throw new Exception("Produit ou fournisseur introuvable.");
            }

            produit.retirerFournisseur(fournisseur);
            fournisseur.retirerProduit(produit);

            Apce.getEntityManager().merge(produit);

            req.setAttribute("message", "Fournisseur retiré avec succès !");
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.getRequestDispatcher("retirerFournisseur.jsp").forward(req, resp);
    }
}
