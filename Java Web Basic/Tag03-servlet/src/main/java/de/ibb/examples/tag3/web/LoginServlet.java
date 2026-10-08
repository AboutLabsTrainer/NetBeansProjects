package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Nimmt Login-Daten entgegen und speichert den Benutzer in der Session.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> USERS = Map.of(
            "admin", "password",
            "user", "test123"
    );

    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCK_DURATION_SECONDS = 5 * 60;

    private static final String SESSION_USER = "user";
    private static final String SESSION_ATTEMPTS = "loginAttempts";
    private static final String SESSION_LOCK_UNTIL = "lockUntil";

    private static final DateTimeFormatter LOCK_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(SESSION_USER) != null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (isLocked(session)) {
            String message = buildLockMessage(session);
            req.setAttribute("errorMessage", message);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (isValidCredentials(username, password)) {
            session.setAttribute(SESSION_USER, username);
            session.removeAttribute(SESSION_ATTEMPTS);
            session.removeAttribute(SESSION_LOCK_UNTIL);
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            handleFailedAttempt(session);
            String message = buildErrorMessage(session);
            req.setAttribute("errorMessage", message);
            req.setAttribute("submittedUsername", username);

            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        String expectedPassword = USERS.get(username);
        return expectedPassword != null && expectedPassword.equals(password);
    }

    private void handleFailedAttempt(HttpSession session) {
        Integer attempts = (Integer) session.getAttribute(SESSION_ATTEMPTS);
        int newAttempts = attempts == null ? 1 : attempts + 1;
        session.setAttribute(SESSION_ATTEMPTS, newAttempts);

        if (newAttempts >= MAX_ATTEMPTS) {
            Instant lockUntil = Instant.now().plusSeconds(LOCK_DURATION_SECONDS);
            session.setAttribute(SESSION_LOCK_UNTIL, lockUntil);
        }
    }

    private boolean isLocked(HttpSession session) {
        Instant lockUntil = (Instant) session.getAttribute(SESSION_LOCK_UNTIL);
        if (lockUntil == null) {
            return false;
        }
        if (Instant.now().isBefore(lockUntil)) {
            return true;
        }
        session.removeAttribute(SESSION_LOCK_UNTIL);
        session.removeAttribute(SESSION_ATTEMPTS);
        return false;
    }

    private String buildErrorMessage(HttpSession session) {
        Instant lockUntil = (Instant) session.getAttribute(SESSION_LOCK_UNTIL);
        if (lockUntil != null && Instant.now().isBefore(lockUntil)) {
            return buildLockMessage(session);
        }

        Integer attempts = (Integer) session.getAttribute(SESSION_ATTEMPTS);
        int remaining = Math.max(0, MAX_ATTEMPTS - (attempts == null ? 0 : attempts));
        if (remaining == 0) {
            return buildLockMessage(session);
        }
        return "Ungültige Zugangsdaten. Verbleibende Versuche: " + remaining + ".";
    }

    private String buildLockMessage(HttpSession session) {
        Instant lockUntil = (Instant) session.getAttribute(SESSION_LOCK_UNTIL);
        if (lockUntil == null) {
            return "Ihr Konto ist gesperrt. Bitte versuchen Sie es später erneut.";
        }
        String formattedTime = LOCK_TIME_FORMATTER.format(lockUntil);
        return "Zu viele Fehlversuche. Konto bis " + formattedTime + " Uhr gesperrt.";
    }
}

