package com.example.tp;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class DashboardProducteurServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"producteur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        req.getRequestDispatcher("dashboardProducteur.jsp").forward(req, resp);
    }
}
