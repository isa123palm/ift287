package com.example.tp;

import tp.objets.Fournisseur;
import tp.objets.Produit;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class RetirerProduitFournisseurServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"fournisseur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            int idProduit = Integer.parseInt(req.getParameter("idProduit"));
            Apce.getGestionFournisseurs().retirerProduit(idProduit, ((Fournisseur) session.getAttribute("utilisateur")).getId());
            req.setAttribute("message", "Produit retiré avec succès.");
        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur : " + e.getMessage());
        }

        req.getRequestDispatcher("retirerProduitFournisseur.jsp").forward(req, resp);
    }
}
