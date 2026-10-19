<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Tag2 – Listener Mini‑Challenge</title>
  <style>
    body{font-family:system-ui,Arial,sans-serif;margin:40px}
    .btn{display:inline-block;padding:10px 14px;border-radius:10px;background:#0a7;color:#fff;text-decoration:none}
    .row{display:flex;gap:8px;flex-wrap:wrap;margin-top:14px}
    code{background:#f6f6f6;padding:2px 6px;border-radius:6px}
  </style>
</head>
<body>
  <h1>Tag 2 – Listener meistern</h1>
  <p>Mini‑Challenge: Application‑Startup, Session‑Tracking, Request‑Performance, Admin‑Dashboard.</p>
  <div class="row">
    <a class="btn" href="<%=request.getContextPath()%>/admin/monitor">Admin Dashboard</a>
    <a class="btn" href="<%=request.getContextPath()%>/admin/api/metrics">API / JSON</a>
  </div>
  <p>Environment: <code><%= getServletContext().getAttribute("environment") %></code> ·
     Version: <code><%= getServletContext().getAttribute("appVersion") %></code></p>
</body>
</html>
