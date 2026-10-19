package de.ibb.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ServletRequestListener für die Laufzeit jeder Anfrage.
 * Zählt Requests und sortiert die Dauer in die Körbe, die AppStartupListener angelegt hat.
 */
@WebListener
public class RequestPerformanceListener implements ServletRequestListener {
    private static final String ATTR_REQ_START = "req.start";

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletRequest().setAttribute(ATTR_REQ_START, System.nanoTime());
        ServletContext ctx = sre.getServletContext();
        AtomicLong reqCount = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_COUNT);
        if (reqCount != null) {
            reqCount.incrementAndGet();
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        Object startObj = sre.getServletRequest().getAttribute(ATTR_REQ_START);
        if (!(startObj instanceof Long)) return;

        long durationNs = System.nanoTime() - (Long) startObj;
        long ms = Math.max(0, durationNs / 1_000_000);

        ServletContext ctx = sre.getServletContext();
        AtomicLong fast = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_FAST);
        AtomicLong ok = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_OK);
        AtomicLong slow = (AtomicLong) ctx.getAttribute(AppStartupListener.ATTR_REQ_SLOW);

        if (fast != null && ok != null && slow != null) {
            if (ms < 100) fast.incrementAndGet();
            else if (ms < 500) ok.incrementAndGet();
            else slow.incrementAndGet();
        }

        @SuppressWarnings("unchecked")
        Map<String, AtomicLong> hist = (Map<String, AtomicLong>) ctx.getAttribute(AppStartupListener.ATTR_HISTOGRAM);
        if (hist != null) {
            AtomicLong bucket = null;
            if (ms < 100) bucket = hist.get("0-100");
            else if (ms < 500) bucket = hist.get("100-500");
            else if (ms < 1000) bucket = hist.get("500-1000");
            else bucket = hist.get("1000+");
            if (bucket != null) {
                bucket.incrementAndGet();
            }
        }
    }
}
