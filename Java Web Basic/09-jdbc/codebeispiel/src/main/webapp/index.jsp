<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Tag10 – Connection Pools & JDBC Performance</title>
  <style>
    body { font-family: system-ui, Segoe UI, Roboto, Arial, sans-serif; margin: 2rem; }
    .card { border: 1px solid #e0e0e0; border-radius: 12px; padding: 1.25rem; margin-bottom: 1rem; }
    h1 { margin-top: 0; }
    .btn { display: inline-block; padding: .6rem 1rem; border-radius: 8px; border: 1px solid #333; text-decoration: none; }
    .hint { color: #555; }
  </style>
</head>
<body>
  <h1>Tag10 – Connection Pools & JDBC Performance</h1>
  <div class="card">
    <p>Mini‑Challenge: Messe die Performance von <strong>Statement vs. PreparedStatement</strong>, 
       <strong>Single Inserts vs. Batch</strong> und vergleiche die Wirkung des <strong>Connection Pools</strong>.</p>
    <p><a class="btn" href="benchmark">▶ Benchmark öffnen</a></p>
    <p class="hint">Tipp: Falls die Datenbank leer ist, rufe zuerst <code><a href="initdb">/initdb</a></code> auf.</p>
  </div>
</body>
</html>
