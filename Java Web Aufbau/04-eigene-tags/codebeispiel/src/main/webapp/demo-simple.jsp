<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fleet" uri="https://de.ibb/simple" %>
<!DOCTYPE html><html lang="de"><head>
        <meta charset="UTF-8"><title>Demo – SimpleTagSupport</title>
        <link rel="stylesheet" href="assets/styles.css">
    </head><body>
        <h1>Demo – SimpleTagSupport (Tag 5)</h1>
        <div class="grid">
            <div class="card"><h2>Greeting</h2><p><strong>Output:</strong> <fleet:greeting name="Nova" time="morning"/></p><pre>&lt;fleet:greeting name="Nova" time="morning"/&gt;</pre></div>
            <div class="card"><h2>Badge</h2><p>Notifications: <fleet:badge value="5" color="danger"/></p><pre>&lt;fleet:badge value="5" color="danger"/&gt;</pre></div>
            <div class="card"><h2>Truncate</h2><p><strong>Output:</strong>
                    <fleet:truncate maxLength="40">Java Fleet builds pragmatic content for Jakarta EE developers – with love for clean code and DX.</fleet:truncate>
                    </p><pre>&lt;fleet:truncate maxLength="40"&gt;...&lt;/fleet:truncate&gt;</pre></div>
                <div class="card"><h2>Repeat</h2><p><strong>Output:</strong> <fleet:repeat times="3"><fleet:badge value="★" color="primary"/></fleet:repeat></p><pre>&lt;fleet:repeat times="3"&gt;&lt;fleet:badge value="★" color="primary"/&gt;&lt;/fleet:repeat&gt;</pre></div>
            </div>
            <p><a href="<%=request.getContextPath()%>/">Zurück</a></p>
    </body></html>