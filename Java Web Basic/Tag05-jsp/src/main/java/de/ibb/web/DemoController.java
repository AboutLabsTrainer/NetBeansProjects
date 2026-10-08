package de.ibb.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wählt die JSP-Demos für EL, JSTL, Include und Formular.
 */
@WebServlet(name = "DemoController", urlPatterns = {"/demo/*"})
public class DemoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || "/".equals(path)) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        switch (path) {
            case "/el":
                seed(req);
                req.getRequestDispatcher("/WEB-INF/views/el.jsp").forward(req, resp);
                break;
            case "/jstl":
                seed(req);
                req.setAttribute("people", people());
                req.getRequestDispatcher("/WEB-INF/views/jstl.jsp").forward(req, resp);
                break;
            case "/include":
                seed(req);
                req.getRequestDispatcher("/WEB-INF/views/include.jsp").forward(req, resp);
                break;
            case "/form":
                req.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/submit".equals(req.getPathInfo())) {
            req.setAttribute("submittedAt", LocalDateTime.now());
            req.getRequestDispatcher("/WEB-INF/views/form-result.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void seed(HttpServletRequest req) {
        req.setAttribute("now", LocalDateTime.now());
        req.setAttribute("course", "Tag 5 – JSP verstehen");
        req.getSession().setAttribute("role", "trainer");
        getServletContext().setAttribute("appName", "JSP Demo App");
    }

    private List<Map<String, Object>> people() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(p("Anna", 28, true));
        list.add(p("Ben", 34, false));
        list.add(p("Cara", 19, true));
        list.add(p("Dieter", 42, false));
        return list;
    }

    private Map<String, Object> p(String name, int age, boolean active) {
        Map<String, Object> m = new HashMap<>();
        m.put("name", name);
        m.put("age", age);
        m.put("active", active);
        return m;
    }
}
