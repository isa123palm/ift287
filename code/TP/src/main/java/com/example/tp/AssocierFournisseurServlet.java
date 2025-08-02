package com.example.tp;

import tp.objets.Fournisseur;
import tp.objets.Produit;
import tp.objets.Producteur;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AssocierFournisseurServlet extends HttpServlet {
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

            // Associer les entités
            produit.ajouterFournisseur(fournisseur);
            fournisseur.ajouterProduit(produit);

            // Associer le fournisseur au producteur du produit
            Producteur producteur = produit.getProducteur();
            if (!producteur.getFournisseurs().contains(fournisseur)) {
                producteur.getFournisseurs().add(fournisseur);
            }

            // Sauvegarder
            Apce.getEntityManager().merge(produit);
            Apce.getEntityManager().merge(producteur);

            req.setAttribute("message", "Fournisseur associé avec succès !");
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.getRequestDispatcher("associerFournisseur.jsp").forward(req, resp);
    }
}
