<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listener Demo -- Counters</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background: #f4f4f4; }
        .card { background: #fff; padding: 24px; border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1); max-width: 700px; }
        h1 { margin-top: 0; color: #2c3e50; }
        table { border-collapse: collapse; width: 100%; margin-top: 16px; }
        th, td { border: 1px solid #ddd; padding: 8px 12px; text-align: left; }
        th { background: #3498db; color: #fff; }
        tr:nth-child(even) { background: #f9f9f9; }
        a.back { display: inline-block; margin-top: 20px; padding: 8px 14px;
                 background: #3498db; color: #fff; text-decoration: none;
                 border-radius: 4px; }
        a.back:hover { background: #2980b9; }
        code { background: #eee; padding: 2px 6px; border-radius: 3px; font-size: 13px; }
    </style>
</head>
<body>
<div class="card">
    <h1>Application Counters</h1>
    <p>Values stored in the <code>ServletContext</code> by
        <code>AppContextListener</code> and updated by the request/session listeners.</p>

    <table>
        <tr><th>Metric</th><th>Value</th></tr>
        <tr><td>Application start time</td><td>${appStartTime}</td></tr>
        <tr><td>Total requests handled</td><td>${appRequestCount}</td></tr>
        <tr><td>Active sessions</td><td>${appSessionCount}</td></tr>
    </table>

    <p><a class="back" href="index.jsp">Back to demo</a></p>
</div>
</body>
</html>