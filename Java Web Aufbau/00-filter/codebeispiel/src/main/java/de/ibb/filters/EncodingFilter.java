package de.ibb.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

/**
 * EncodingFilter – setzt zentrales UTF-8 Encoding für alle Requests/Responses.
 */
public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig config) throws ServletException {
        String encodingParam = config.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
        System.out.println("EncodingFilter initialized with: " + encoding);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // BEFORE: Encoding setzen
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);

        // Weiter zum nächsten Filter / Servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("EncodingFilter destroyed");
    }
}

