package de.ibb.secure;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Geschützte Benutzerseite. Der Container prüft die Rolle user im Realm jwebak_tag04_securityRealm, bevor dieses Servlet läuft.
 */
@WebServlet(name="UserDashboard", urlPatterns={"/user/dashboard"})
public class UserDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String user = req.getUserPrincipal() != null ? req.getUserPrincipal().getName() : "anonymous";
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>User Dashboard</title><style>"
                + "body{font-family:Arial,sans-serif;background:#ececec;margin:0;padding:36px;color:#111}"
                + ".box{max-width:760px;margin:0 auto;background:#efefef;border:2px solid #222;padding:22px}"
                + "h1{margin:0 0 10px;font-size:28px}.line{border-top:2px solid #222;margin:10px 0 16px}"
                + "ul{font-size:16px}.links a{color:#111;margin-right:18px;font-size:16px}</style></head><body><div class='box'>");
        out.println("<h1>User Dashboard</h1><div class='line'></div>");
        out.println("<p>Authenticated as: <strong>" + user + "</strong></p>");
        out.println("<ul>");
        out.println("<li>isUserInRole('user'): " + req.isUserInRole("user") + "</li>");
        out.println("<li>isUserInRole('manager'): " + req.isUserInRole("manager") + "</li>");
        out.println("<li>isUserInRole('admin'): " + req.isUserInRole("admin") + "</li>");
        out.println("</ul>");
        out.println("<p class='links'><a href='" + req.getContextPath() + "/logout'>Logout</a><a href='" + req.getContextPath() + "/'>Back</a></p>");
        out.println("</div></body></html>");
    }
}
