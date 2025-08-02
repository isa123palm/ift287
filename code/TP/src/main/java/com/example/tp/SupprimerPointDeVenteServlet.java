package com.example.tp;

import tp.objets.PointDeVente;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SupprimerPointDeVenteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"distributeur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        PointDeVente pdv = (PointDeVente) session.getAttribute("utilisateur");

        try {
            Apce.getGestionPointsDeVente().supprimerPointDeVente(pdv.getId());
            session.invalidate();
            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur lors de la suppression : " + e.getMessage());
            req.getRequestDispatcher("supprimerPointDeVente.jsp").forward(req, resp);
        }
    }
}
