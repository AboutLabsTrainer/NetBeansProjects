<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Container Managed Security</title>
  <style>
    body{font-family:Arial,sans-serif;background:#ececec;margin:0;padding:22px 0;color:#111}
    .wrap{max-width:760px;margin:0 auto;background:#efefef;border:2px solid #222;padding:18px 22px}
    h1{text-align:center;margin:2px 0 8px 0;font-size:44px}
    .sub{text-align:center;margin:0 0 18px 0;font-size:15px;line-height:1.25}
    .line{border-top:2px solid #222;margin:10px 0 18px}
    .grid{display:grid;grid-template-columns:repeat(3,1fr);gap:14px}
    a.card{display:block;text-decoration:none;color:#111;border:2px solid #222;background:#f5f5f5;padding:18px 10px;text-align:center}
    a.card strong{display:block;font-size:38px;line-height:1;margin-bottom:8px}
    .info{margin-top:16px;border:2px solid #222;padding:12px 16px;background:#f5f5f5}
    .info h2{margin:0 0 6px;font-size:36px}
    .info ul{margin:8px 0 0 24px}
    .info li{margin:4px 0;font-size:15px}
    .hint{margin-top:12px;font-style:italic;font-size:14px}
    .login-link{text-align:center;margin-top:14px}
    .login-link a{color:#111}
  </style>
</head>
<body>
  <div class="wrap">
    <h1>Container Managed Security</h1>
    <div class="line"></div>
    <p class="sub">JDBC Realm Mini-Challenge<br>Teste die drei geschuetzten Bereiche mit verschiedenen Benutzerrollen</p>
    <div class="grid">
      <a class="card" href="<%=request.getContextPath()%>/admin/dashboard"><strong>Admin</strong>Administrator Bereich</a>
      <a class="card" href="<%=request.getContextPath()%>/manager/dashboard"><strong>Manager</strong>Manager Bereich</a>
      <a class="card" href="<%=request.getContextPath()%>/user/dashboard"><strong>User</strong>Benutzer Bereich</a>
    </div>
    <div class="info">
      <h2>Test-Credentials</h2>
      <div class="line"></div>
      <ul>
        <li><strong>admin</strong> / admin123 -> Rollen: admin, manager, user</li>
        <li><strong>manager</strong> / manager123 -> Rollen: manager, user</li>
        <li><strong>user</strong> / user123 -> Rolle: user</li>
      </ul>
      <p class="hint">Hinweis: Beim Klick auf einen Bereich wirst du zur Login-Seite weitergeleitet, falls du nicht angemeldet bist.</p>
    </div>
    <p class="login-link"><a href="<%=request.getContextPath()%>/login.jsp">Direkt zum Login</a></p>
  </div>
</body>
</html>
