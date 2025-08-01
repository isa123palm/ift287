package com.example.tp;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class DebugServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("debug.jsp").forward(req, resp);
    }
}
