<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="simple" uri="https://de.ibb/simple" %>
<!DOCTYPE html><html lang="de"><head>
        <meta charset="UTF-8"><title>Demo – SimpleTagSupport</title>
        <link rel="stylesheet" href="assets/styles.css">
    </head><body>
        <h1>Demo – SimpleTagSupport (Tag 5)</h1>
        <div class="grid">
            <div class="card"><h2>Greeting</h2><p><strong>Output:</strong> <simple:greeting name="Nova" time="morning"/></p><pre>&lt;simple:greeting name="Nova" time="morning"/&gt;</pre></div>
            <div class="card"><h2>Badge</h2><p>Notifications: <simple:badge value="5" color="danger"/></p><pre>&lt;simple:badge value="5" color="danger"/&gt;</pre></div>
            <div class="card"><h2>Truncate</h2><p><strong>Output:</strong>
                    <simple:truncate maxLength="40">Eigene Tags kürzen längere Texte und zeigen nur den Anfang des Inhalts.</simple:truncate>
                    </p><pre>&lt;simple:truncate maxLength="40"&gt;...&lt;/simple:truncate&gt;</pre></div>
                <div class="card"><h2>Repeat</h2><p><strong>Output:</strong> <simple:repeat times="3"><simple:badge value="★" color="primary"/></simple:repeat></p><pre>&lt;simple:repeat times="3"&gt;&lt;simple:badge value="★" color="primary"/&gt;&lt;/simple:repeat&gt;</pre></div>
            </div>
            <p><a href="<%=request.getContextPath()%>/">Zurück</a></p>
    </body></html>
