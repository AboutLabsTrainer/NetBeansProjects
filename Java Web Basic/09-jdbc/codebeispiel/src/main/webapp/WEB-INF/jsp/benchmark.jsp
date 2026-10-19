<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Benchmark</title>
  <style>
    body { font-family: system-ui, Segoe UI, Roboto, Arial, sans-serif; margin: 2rem; }
    .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 1rem; }
    .card { border: 1px solid #e0e0e0; border-radius: 12px; padding: 1rem; }
    label { display:block; margin:.4rem 0 .2rem; font-weight:600; }
    input[type="number"] { width: 100%; padding:.4rem; }
    .btn { margin-top:.6rem; padding:.6rem .9rem; border-radius:8px; border:1px solid #333; background:#fafafa;}
    table { border-collapse: collapse; width: 100%; }
    th, td { padding: .5rem .6rem; border-bottom: 1px solid #eee; text-align: left; }
    .ok { color: #0a7; }
    .warn { color: #b50; }
    .err { color: #c00; }
    code { background: #f6f6f6; padding: .2rem .3rem; border-radius: 6px; }
  </style>
</head>
<body>
  <h1>Benchmark – Prepared, Batch & Pool</h1>

  <div class="grid">
    <div class="card">
      <form method="post" action="benchmark">
        <label>Operation</label>
        <select name="op">
          <option value="selectById">SELECT by ID (loop)</option>
          <option value="batchInsert">INSERT Batch</option>
          <option value="singleInsert">INSERT Single (no batch)</option>
          <option value="txCommitRollback">TX: Commit & Rollback</option>
        </select>
        <label>Iterations / Rows</label>
        <input type="number" name="iterations" value="1000" min="1" />
        <label><input type="checkbox" name="usePrepared" checked /> Use PreparedStatement</label>
        <label><input type="checkbox" name="useBatch" /> Use Batch (for inserts)</label>
        <button class="btn" type="submit">Run</button>
      </form>
      <p>JNDI DataSource: <code>jdbc/jwebb_tag10_jdbc</code></p>
    </div>

    <div class="card">
      <h3>Ergebnis</h3>
      <%
        Map<String,Object> result = (Map<String,Object>) request.getAttribute("result");
        if (result != null) {
      %>
        <table>
          <tr><th>Metrik</th><th>Wert</th></tr>
          <tr><td>Operation</td><td><%= result.get("op") %></td></tr>
          <tr><td>Iterations / Rows</td><td><%= result.get("iterations") %></td></tr>
          <tr><td>Prepared</td><td><%= result.get("prepared") %></td></tr>
          <tr><td>Batch</td><td><%= result.get("batch") %></td></tr>
          <tr><td>Dauer (ms)</td><td><%= result.get("durationMs") %></td></tr>
          <tr><td>TPS / RPS</td><td><%= result.get("throughput") %></td></tr>
        </table>
        <p class="ok">Fertig ohne Fehler.</p>
      <%
        } else {
      %>
        <p class="warn">Noch keine Messung durchgeführt.</p>
      <%
        }
      %>
    </div>

    <div class="card">
      <h3>Tipps</h3>
      <ul>
        <li>Stelle im Payara Pool <code>statement-cache-size</code> auf 50–100 und aktiviere
            <code>is-connection-validation-required</code>.</li>
        <li>Nutze <code>PreparedStatement</code> immer; reduziere Parse‑Kosten & verhindere SQL‑Injection.</li>
        <li>Für 100+ Inserts: <code>addBatch()</code> &amp; <code>executeBatch()</code> in einer Transaktion.</li>
      </ul>
    </div>
  </div>
</body>
</html>
