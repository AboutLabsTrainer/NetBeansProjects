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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * JSON-Sicht auf dieselben Listener-Zähler wie das HTML-Dashboard.
 * Die Werte stammen aus dem ServletContext, nicht aus einer Datenbank.
 */
@WebServlet(name = "MetricsApiServlet", urlPatterns = {"/admin/api/metrics"})
public class MetricsApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Map<String, Object> json = new LinkedHashMap<>();
        json.put("appStart", ctx.getAttribute(AppStartupListener.ATTR_APP_START));
        json.put("appVersion", ctx.getAttribute(AppStartupListener.ATTR_APP_VERSION));
        json.put("environment", ctx.getAttribute(AppStartupListener.ATTR_ENV));

        Object activeObj = ctx.getAttribute(AppStartupListener.ATTR_ACTIVE_SESSIONS);
        json.put("activeSessions", activeObj instanceof AtomicInteger ? ((AtomicInteger) activeObj).get() : 0);
        Object peakObj = ctx.getAttribute(AppStartupListener.ATTR_PEAK_SESSIONS);
        json.put("peakSessions", peakObj instanceof AtomicInteger ? ((AtomicInteger) peakObj).get() : 0);
        Object totalSessionsObj = ctx.getAttribute(AppStartupListener.ATTR_TOTAL_SESSIONS);
        json.put("totalSessions", totalSessionsObj instanceof AtomicInteger ? ((AtomicInteger) totalSessionsObj).get() : 0);
        Object totalSessionDurObj = ctx.getAttribute(AppStartupListener.ATTR_TOTAL_SESSION_DURATION_MS);
        json.put("totalSessionDurationMs", totalSessionDurObj instanceof AtomicLong ? ((AtomicLong) totalSessionDurObj).get() : 0);

        Object reqCountObj = ctx.getAttribute(AppStartupListener.ATTR_REQ_COUNT);
        json.put("reqCount", reqCountObj instanceof AtomicLong ? ((AtomicLong) reqCountObj).get() : 0);
        Object reqFastObj = ctx.getAttribute(AppStartupListener.ATTR_REQ_FAST);
        json.put("reqFast", reqFastObj instanceof AtomicLong ? ((AtomicLong) reqFastObj).get() : 0);
        Object reqOkObj = ctx.getAttribute(AppStartupListener.ATTR_REQ_OK);
        json.put("reqOk", reqOkObj instanceof AtomicLong ? ((AtomicLong) reqOkObj).get() : 0);
        Object reqSlowObj = ctx.getAttribute(AppStartupListener.ATTR_REQ_SLOW);
        json.put("reqSlow", reqSlowObj instanceof AtomicLong ? ((AtomicLong) reqSlowObj).get() : 0);

        @SuppressWarnings("unchecked")
        Map<String, AtomicLong> hist = (Map<String, AtomicLong>) ctx.getAttribute(AppStartupListener.ATTR_HISTOGRAM);
        Map<String, Long> histCopy = new LinkedHashMap<>();
        if (hist != null) {
            for (String bucket : AppStartupListener.HISTOGRAM_BUCKETS) {
                AtomicLong value = hist.get(bucket);
                histCopy.put(bucket, value != null ? value.get() : 0L);
            }
        }
        json.put("histogram", histCopy);

        out.print(toJson(json));
    }

    private String toJson(Object o) {
        if (o == null) return "null";
        if (o instanceof String s) return "\""+ s.replace("\\", "\\\\").replace("\"", "\\\"") +"\"";
        if (o instanceof Number || o instanceof Boolean) return String.valueOf(o);
        if (o instanceof Map<?,?> m) {
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (var e : m.entrySet()) {
                if (!first) sb.append(",");
                first = false;
                sb.append(toJson(String.valueOf(e.getKey()))).append(":").append(toJson(e.getValue()));
            }
            sb.append("}");
            return sb.toString();
        }
        return toJson(String.valueOf(o));
    }
}
