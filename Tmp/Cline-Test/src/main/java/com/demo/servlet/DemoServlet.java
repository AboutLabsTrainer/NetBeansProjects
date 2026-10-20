package com.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Demo servlet that lets a visitor trigger the various listener events:
 * <ul>
 *   <li>create / invalidate an HTTP session</li>
 *   <li>set / remove a session attribute</li>
 * </ul>
 * After performing the action it forwards to result.jsp, which displays the
 * application-wide counters stored in the ServletContext.
 */
public class DemoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);

        if ("createSession".equalsIgnoreCase(action)) {
            session = req.getSession(true);
            session.setAttribute("user", "demo-user");
            System.out.println("[DemoServlet] created session " + session.getId());
        } else if ("invalidateSession".equalsIgnoreCase(action)) {
            if (session != null) {
                System.out.println("[DemoServlet] invalidating session " + session.getId());
                session.invalidate();
            }
        } else if ("setAttribute".equalsIgnoreCase(action)) {
            if (session == null) {
                session = req.getSession(true);
            }
            session.setAttribute("demoAttr", "demoValue-" + System.currentTimeMillis());
            System.out.println("[DemoServlet] set session attribute demoAttr on " + session.getId());
        } else if ("removeAttribute".equalsIgnoreCase(action)) {
            if (session != null) {
                session.removeAttribute("demoAttr");
                System.out.println("[DemoServlet] removed session attribute demoAttr from "
                        + session.getId());
            }
        }

        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}