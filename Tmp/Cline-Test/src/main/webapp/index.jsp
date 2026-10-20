<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java EE Listener Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background: #f4f4f4; }
        .card { background: #fff; padding: 24px; border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1); max-width: 640px; }
        h1 { margin-top: 0; color: #2c3e50; }
        a.btn { display: inline-block; margin: 6px 12px 6px 0; padding: 8px 14px;
                background: #3498db; color: #fff; text-decoration: none;
                border-radius: 4px; font-size: 14px; border: 1px solid #2980b9; }
        a.btn:hover { background: #2980b9; }
        a.btn.danger { background: #e74c3c; border-color: #c0392b; }
        a.btn.danger:hover { background: #c0392b; }
        code { background: #eee; padding: 2px 6px; border-radius: 3px; font-size: 13px; }
        .hint { color: #666; font-size: 13px; margin-top: 16px;
                border-top: 1px solid #ddd; padding-top: 12px; }
    </style>
</head>
<body>
<div class="card">
    <h1>Java EE Listener Demo</h1>
    <p>This demo shows the Java EE servlet listeners in action:</p>
    <ul>
        <li><code>ServletContextListener</code> -- application start / shutdown</li>
        <li><code>ServletRequestListener</code> -- each request's life cycle</li>
        <li><code>HttpSessionListener</code> -- session create / invalidate</li>
        <li><code>HttpSessionAttributeListener</code> -- session attribute add / replace / remove</li>
    </ul>
    <p>Open your server console to see the log lines each listener emits.</p>

    <h2>Trigger actions</h2>
    <a class="btn" href="demo?action=createSession">Create session</a>
    <a class="btn danger" href="demo?action=invalidateSession">Invalidate session</a>
    <a class="btn" href="demo?action=setAttribute">Set session attribute</a>
    <a class="btn" href="demo?action=removeAttribute">Remove session attribute</a>

    <p class="hint">
        View current counters: <a href="result.jsp">result.jsp</a><br>
        All listeners are declared in <code>WEB-INF/web.xml</code>.
    </p>
</div>
</body>
</html>