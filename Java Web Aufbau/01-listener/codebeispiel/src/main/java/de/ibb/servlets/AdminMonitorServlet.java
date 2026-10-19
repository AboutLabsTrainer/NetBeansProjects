package de.ibb.servlets;

import de.ibb.listeners.AppStartupListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HTML-Dashboard für die Listener-Metriken.
 * Liest nur die Zähler, die AppStartupListener, SessionTrackingListener
 * und RequestPerformanceListener im ServletContext halten.
 */
@WebServlet(name = "AdminMonitorServlet", urlPatterns = {"/admin/monitor"})
public class AdminMonitorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Object startObj = ctx.getAttribute(AppStartupListener.ATTR_APP_START);
        long start = startObj instanceof Long ? (Long) startObj : System.currentTimeMillis();
        String version = (String) ctx.getAttribute(AppStartupListener.ATTR_APP_VERSION);
        if (version == null) version = "unknown";
        String env = (String) ctx.getAttribute(AppStartupListener.ATTR_ENV);
        if (env == null) env = "unknown";

        AtomicInteger active = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_ACTIVE_SESSIONS);
        AtomicInteger peak = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_PEAK_SESSIONS);
        AtomicInteger totalSessions = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_TOTAL_SESSIONS);
        AtomicLong totalSessionDur = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_TOTAL_SESSION_DURATION_MS);

        AtomicLong reqCount = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_COUNT);
        AtomicLong reqFast = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_FAST);
        AtomicLong reqOk = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_OK);
        AtomicLong reqSlow = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_SLOW);

        @SuppressWarnings("unchecked")
        Map<String, AtomicLong> hist = (Map<String, AtomicLong>) ctx.getAttribute(AppStartupListener.ATTR_HISTOGRAM);

        long uptimeMs = Math.max(0, System.currentTimeMillis() - start);
        long avgSessionMs = (totalSessions != null && totalSessionDur != null && totalSessions.get() > 0)
            ? totalSessionDur.get() / totalSessions.get() : 0;

        out.println("<!DOCTYPE html><html lang='de'><head><meta charset='UTF-8'><title>Admin Monitor</title>" +
                "<style>body{font-family:system-ui,Arial,sans-serif;margin:2rem}table{border-collapse:collapse}td,th{padding:.4rem .6rem;border-bottom:1px solid #eee}</style>" +
                "</head><body>");
        out.println("<h1>Monitoring Dashboard</h1>");
        out.println("<h3>App</h3><table>");
        out.println("<tr><th>Version</th><td>" + version + "</td></tr>");
        out.println("<tr><th>Environment</th><td>" + env + "</td></tr>");
        out.println("<tr><th>Start</th><td>" + DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(start)) + "</td></tr>");
        out.println("<tr><th>Uptime (ms)</th><td>" + uptimeMs + "</td></tr></table>");

        out.println("<h3>Sessions</h3><table>");
        out.println("<tr><th>Active</th><td>" + (active != null ? active.get() : 0) + "</td></tr>");
        out.println("<tr><th>Peak</th><td>" + (peak != null ? peak.get() : 0) + "</td></tr>");
        out.println("<tr><th>Total Sessions</th><td>" + (totalSessions != null ? totalSessions.get() : 0) + "</td></tr>");
        out.println("<tr><th>Avg. Duration (ms)</th><td>" + avgSessionMs + "</td></tr></table>");

        out.println("<h3>Requests</h3><table>");
        out.println("<tr><th>Total</th><td>" + (reqCount != null ? reqCount.get() : 0) + "</td></tr>");
        out.println("<tr><th>Fast (&lt;100ms)</th><td>" + (reqFast != null ? reqFast.get() : 0) + "</td></tr>");
        out.println("<tr><th>OK (&lt;500ms)</th><td>" + (reqOk != null ? reqOk.get() : 0) + "</td></tr>");
        out.println("<tr><th>Slow (&ge;500ms)</th><td>" + (reqSlow != null ? reqSlow.get() : 0) + "</td></tr></table>");

        out.println("<h3>Histogram (ms)</h3><table>");
        if (hist != null) {
            for (String bucket : AppStartupListener.HISTOGRAM_BUCKETS) {
                AtomicLong value = hist.get(bucket);
                long count = value != null ? value.get() : 0;
                out.println("<tr><th>" + bucket + "</th><td>" + count + "</td></tr>");
            }
        }
        out.println("</table>");

        if (active != null && active.get() > 1000) {
            out.println("<p style='color:#b00'><strong>ALERT:</strong> Active sessions &gt; 1000!</p>");
        }

        out.println("<p><a href='" + req.getContextPath() + "/'>Zurück</a></p>");
        out.println("</body></html>");
    }
}
