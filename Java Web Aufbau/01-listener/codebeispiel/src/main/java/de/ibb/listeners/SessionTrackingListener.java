package de.ibb.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * HttpSessionListener für aktive, höchste und gesamte Sitzungszahlen.
 * Beim Zerstören der Session addiert er die Dauer auf den Zähler im ServletContext.
 */
@WebListener
public class SessionTrackingListener implements HttpSessionListener {
    private static final Logger LOG = Logger.getLogger(SessionTrackingListener.class.getName());
    private static final String ATTR_SESSION_START = "session.startTime";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        AtomicInteger active = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_ACTIVE_SESSIONS);
        AtomicInteger peak = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_PEAK_SESSIONS);
        AtomicInteger total = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_TOTAL_SESSIONS);

        if (active != null && peak != null && total != null) {
            int now = active.incrementAndGet();
            total.incrementAndGet();
            peak.accumulateAndGet(now, Math::max);

            se.getSession().setAttribute(ATTR_SESSION_START, System.currentTimeMillis());

            if (now > 1000) {
                ctx.log("ALERT: Active sessions exceeded 1000!");
            }
            LOG.info("Session created: id=" + se.getSession().getId() + ", active=" + now);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        AtomicInteger active = (AtomicInteger) ctx.getAttribute(AppStartupListener.ATTR_ACTIVE_SESSIONS);
        AtomicLong totalDuration = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_TOTAL_SESSION_DURATION_MS);

        if (active != null) {
            active.decrementAndGet();
        }

        Object startObj = se.getSession().getAttribute(ATTR_SESSION_START);
        if (startObj instanceof Long && totalDuration != null) {
            long dur = Math.max(0, System.currentTimeMillis() - (Long) startObj);
            totalDuration.addAndGet(dur);
        }
        LOG.info("Session destroyed: id=" + se.getSession().getId());
    }
}
