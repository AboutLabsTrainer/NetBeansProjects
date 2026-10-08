<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Sign in</title>
  <style>
    body{font-family:system-ui,Arial,sans-serif;margin:40px}
    .error{color:#b00}
    form{display:flex;flex-direction:column;gap:.6rem;max-width:360px}
    input,button{padding:.6rem;font-size:1rem}
    .muted{color:#555}
    code{background:#f6f6f6;padding:2px 6px;border-radius:6px}
  </style>
</head>
<body>
  <h1>Sign in</h1>
  <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
  <% } %>
  <form method="post" action="signin">
    <label>Email <input type="email" name="email" required></label>
    <label>Password <input type="password" name="password" required minlength="6"></label>
    <button type="submit">Sign in</button>
  </form>

  <p class="muted">Demo user after running SQL: <code>test@example.com / test123</code></p>
  <p><a href="<%=request.getContextPath()%>/">Back to app</a></p>
</body>
</html>
