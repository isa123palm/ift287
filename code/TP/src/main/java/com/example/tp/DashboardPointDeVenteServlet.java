package com.example.tp;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DashboardPointDeVenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"distributeur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        req.getRequestDispatcher("dashboardPointDeVente.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
