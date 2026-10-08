package de.ibb.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * ServletContextListener zum Anwendungsstart.
 * Legt Session-, Request- und Histogramm-Zähler im ServletContext ab,
 * damit die anderen Listener und die Monitor-Servlets dieselben Werte lesen.
 */
@WebListener
public class AppStartupListener implements ServletContextListener {

    public static final String ATTR_APP_START = "appStartTime";
    public static final String ATTR_APP_VERSION = "appVersion";
    public static final String ATTR_ENV = "environment";

    public static final String ATTR_ACTIVE_SESSIONS = "metric.activeSessions";
    public static final String ATTR_PEAK_SESSIONS = "metric.peakSessions";
    public static final String ATTR_TOTAL_SESSIONS = "metric.totalSessions";
    public static final String ATTR_TOTAL_SESSION_DURATION_MS = "metric.totalSessionDurationMs";

    public static final String ATTR_REQ_COUNT = "metric.request.count";
    public static final String ATTR_REQ_FAST = "metric.request.fast";
    public static final String ATTR_REQ_OK = "metric.request.ok";
    public static final String ATTR_REQ_SLOW = "metric.request.slow";

    public static final String ATTR_HISTOGRAM = "metric.histogram";
    public static final List<String> HISTOGRAM_BUCKETS = List.of("0-100", "100-500", "500-1000", "1000+");

    private static final Logger LOG = Logger.getLogger(AppStartupListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        long start = System.currentTimeMillis();
        ctx.log("=== APPLICATION STARTUP === " + start);
        ctx.setAttribute(ATTR_APP_START, start);

        String env = ctx.getInitParameter("environment");
        if (env == null) env = "dev";
        ctx.setAttribute(ATTR_ENV, env);
        String appVersion = ctx.getInitParameter("appVersion");
        if (appVersion == null) appVersion = "0.0.1-SNAPSHOT";
        ctx.setAttribute(ATTR_APP_VERSION, appVersion);

        ctx.setAttribute(ATTR_ACTIVE_SESSIONS, new AtomicInteger(0));
        ctx.setAttribute(ATTR_PEAK_SESSIONS, new AtomicInteger(0));
        ctx.setAttribute(ATTR_TOTAL_SESSIONS, new AtomicInteger(0));
        ctx.setAttribute(ATTR_TOTAL_SESSION_DURATION_MS, new AtomicLong(0L));

        ctx.setAttribute(ATTR_REQ_COUNT, new AtomicLong(0));
        ctx.setAttribute(ATTR_REQ_FAST, new AtomicLong(0));
        ctx.setAttribute(ATTR_REQ_OK, new AtomicLong(0));
        ctx.setAttribute(ATTR_REQ_SLOW, new AtomicLong(0));

        Map<String, AtomicLong> histogram = new ConcurrentHashMap<>();
        for (String bucket : HISTOGRAM_BUCKETS) {
            histogram.put(bucket, new AtomicLong(0));
        }
        ctx.setAttribute(ATTR_HISTOGRAM, histogram);

        LOG.info("Application initialized. env=" + env + ", version=" + appVersion);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.log("=== APPLICATION SHUTDOWN ===");
    }
}
