package com.example.tp;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class DashboardFournisseurServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || !"fournisseur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        req.getRequestDispatcher("dashboardFournisseur.jsp").forward(req, resp);
    }
}
