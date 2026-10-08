package de.ibb.servlets;

import de.ibb.logging.RequestLogEntry;
import de.ibb.logging.RequestLogStore;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Geschützte Admin-Seite am Ende der Filterkette. Listet die Einträge, die LoggingFilter gesammelt hat.
 */
public class AdminServlet extends HttpServlet {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"de\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\"/>");
            out.println("<title>Filter Demo - Admin</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; margin-top: 1em; }");
            out.println("th, td { border: 1px solid #ccc; padding: 6px 10px; text-align: left; }");
            out.println("th { background: #eee; }");
            out.println(".slow { background: #fff3cd; }");
            out.println(".status-5xx { color: #a00; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Admin-Bereich</h1>");
            out.println("<p>Du bist eingeloggt und siehst geschützte Inhalte.</p>");

            List<RequestLogEntry> entries = RequestLogStore.getRecentEntries();
            out.println("<h2>Logging Infos</h2>");
            if (entries.isEmpty()) {
                out.println("<p>Noch keine Requests geloggt.</p>");
            } else {
                out.println("<table>");
                out.println("<thead><tr><th>Zeit</th><th>Method</th><th>URL</th><th>Status</th><th>Dauer</th><th>Hinweis</th></tr></thead>");
                out.println("<tbody>");
                for (RequestLogEntry e : entries) {
                    String rowClass = e.isSlow() ? " class=\"slow\"" : "";
                    String statusClass = e.getStatus() >= 500 ? " class=\"status-5xx\"" : "";
                    String hint = e.isSlow() ? "⚠️ Langsam (>500ms)" : "";
                    out.print("<tr" + rowClass + ">");
                    out.print("<td>" + escape(e.getTimestamp().format(FORMATTER)) + "</td>");
                    out.print("<td>" + escape(e.getMethod()) + "</td>");
                    out.print("<td>" + escape(e.getUrl()) + "</td>");
                    out.print("<td" + statusClass + ">" + e.getStatus() + "</td>");
                    out.print("<td>" + e.getDurationMs() + " ms</td>");
                    out.print("<td>" + escape(hint) + "</td>");
                    out.println("</tr>");
                }
                out.println("</tbody></table>");
            }

            out.println("<p><a href=\"" + req.getContextPath() + "/\">Zurück zur Homepage</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}

