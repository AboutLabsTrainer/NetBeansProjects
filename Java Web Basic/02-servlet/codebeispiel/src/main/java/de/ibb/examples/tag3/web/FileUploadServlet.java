package de.ibb.examples.tag3.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

/**
 * Nimmt einen Multipart-Upload entgegen und kopiert die Datei über den InputStream.
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h1>Datei-Upload</h1>");
            out.println("<form method='post' enctype='multipart/form-data'>");
            out.println("<input type='file' name='file'>");
            out.println("<button type='submit'>Hochladen</button>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><body>");
            out.println("<h1>Upload Ergebnis</h1>");
            Collection<Part> parts = req.getParts();
            if (parts == null || parts.isEmpty()) {
                out.println("<p>Keine Datei übermittelt.</p>");
            } else {
                for (Part part : parts) {
                    if (!"file".equals(part.getName())) continue;
                    String submitted = part.getSubmittedFileName();
                    if (submitted == null || submitted.isBlank()) continue;
                    Path targetDir = Files.createTempDirectory("upload-");
                    Path target = targetDir.resolve(submitted);
                    try (InputStream is = part.getInputStream()) {
                        Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
                    }
                    out.println("<p>Gespeichert unter: " + target.toAbsolutePath() + "</p>");
                }
            }
            out.println("</body></html>");
        }
    }
}
