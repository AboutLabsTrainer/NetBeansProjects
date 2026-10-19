<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="de.ibb.model.BlogPost" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8" />
  <title><%= request.getAttribute("appTitle") %> – Übersicht</title>
  <style>
    body { font-family: system-ui, Arial, sans-serif; margin: 40px; }
    .post { padding: 12px 0; border-bottom: 1px solid #eee; }
    .meta { color:#666; font-size: 14px; }
    a { color:#0a7; text-decoration: none; }
    a:hover { text-decoration: underline; }
  </style>
</head>
<body>
  <h1><%= request.getAttribute("appTitle") %></h1>
  <h2>Beiträge</h2>
  <%
    List<BlogPost> posts = (List<BlogPost>) request.getAttribute("posts");
    for (BlogPost p : posts) {
  %>
    <div class="post">
      <h3><a href="<%= request.getContextPath() %>/blog?id=<%= p.getId() %>"><%= p.getTitle() %></a></h3>
      <p class="meta">von <%= p.getAuthor() %> – <%= p.getDate() %></p>
    </div>
  <%
    }
  %>
</body>
</html>
