package de.ibb.examples.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Gibt HTTP-Methode und Header zurück, damit der Request sichtbar wird.
 */
@WebServlet("/method-echo/*")
public class MethodEchoServlet extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { handle("GET", req, resp); }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { handle("POST", req, resp); }
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { handle("PUT", req, resp); }
    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { handle("DELETE", req, resp); }
    @Override protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { resp.setStatus(HttpServletResponse.SC_OK); }
    @Override protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Allow", "GET,POST,PUT,DELETE,HEAD,OPTIONS");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void handle(String method, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Method Echo</title></head><body>");
        out.println("<h1>HTTP Method: " + method + "</h1>");
        out.println("<h3>Request URI</h3><pre>" + req.getRequestURI() + "</pre>");
        out.println("<h3>Query String</h3><pre>" + (req.getQueryString()==null?"":req.getQueryString()) + "</pre>");
        out.println("<h3>Headers</h3><pre>");
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            out.println(name + ": " + req.getHeader(name));
        }
        out.println("</pre>");
        out.println("</body></html>");
    }
}