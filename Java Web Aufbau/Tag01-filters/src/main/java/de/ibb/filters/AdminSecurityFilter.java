package de.ibb.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AdminSecurityFilter – schützt /admin/* und leitet auf /login um,
 * falls kein User in der Session vorhanden ist.
 */
public class AdminSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no-op
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        Object user = (session != null) ? session.getAttribute("user") : null;

        if (user == null) {
            String loginURL = httpRequest.getContextPath() + "/login";
            httpResponse.sendRedirect(loginURL);
            return; // kein chain.doFilter – Request wird geblockt
        }

        // User eingeloggt → weiter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // no-op
    }
}

