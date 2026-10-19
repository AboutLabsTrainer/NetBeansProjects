<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="de.ibb.model.BlogPost" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8" />
  <title><%= ((BlogPost)request.getAttribute("post")).getTitle() %></title>
  <style>
    body { font-family: system-ui, Arial, sans-serif; margin: 40px; }
    .meta { color:#666; font-size: 14px; margin-bottom: 10px; }
    a { color:#0a7; text-decoration: none; }
  </style>
</head>
<body>
  <%
    BlogPost post = (BlogPost) request.getAttribute("post");
  %>
  <h1><%= post.getTitle() %></h1>
  <p class="meta">von <%= post.getAuthor() %> – <%= post.getDate() %></p>
  <p><%= post.getContent() %></p>
  <p><a href="<%= request.getContextPath() %>/blog">← zurück zur Übersicht</a></p>
</body>
</html>
