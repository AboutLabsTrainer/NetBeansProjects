<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8" />
  <title>Tag 8 – JSTL Mini‑Challenge</title>
  <style>
    body{font-family:system-ui,Arial,sans-serif;margin:40px}
    a.button{display:inline-block;padding:10px 14px;border-radius:10px;background:#0a7;color:#fff;text-decoration:none}
  </style>
</head>
<body>
  <h1>Tag 8 – JSTL Mini‑Challenge</h1>
  <p>Diese Demo setzt die Shopping‑Cart‑Anforderungen der Mini‑Challenge mit JSTL um.</p>
  <a class="button" href="<%= request.getContextPath() %>/cart">Warenkorb anzeigen</a>
</body>
</html>
