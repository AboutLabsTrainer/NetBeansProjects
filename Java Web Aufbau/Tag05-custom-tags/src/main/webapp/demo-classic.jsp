<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fleetx" uri="https://de.ibb/classic" %>
<!DOCTYPE html><html lang="de"><head>
        <meta charset="UTF-8"><title>Demo – BodyTagSupport</title>
        <link rel="stylesheet" href="assets/styles.css">
    </head><body>
        <h1>Demo – BodyTagSupport (Tag 6)</h1>
        <div class="grid">
            <div class="card"><h2>GreetingClassic (TagSupport)</h2><p><strong>Output:</strong> <fleetx:greeting name="Nova" time="evening"/></p><pre>&lt;fleetx:greeting name="Nova" time="evening"/&gt;</pre></div>
            <div class="card"><h2>BadgeClassic (TagSupport)</h2><p>Badges: <fleetx:badge value="Legacy" color="primary"/></p><pre>&lt;fleetx:badge value="Legacy" color="primary"/&gt;</pre></div>
            <div class="card"><h2>TruncateClassic (BodyTagSupport)</h2><p><strong>Output:</strong> <fleetx:truncate maxLength="32">Classic tags can buffer and transform body content right inside doAfterBody.</fleetx:truncate></p><pre>&lt;fleetx:truncate maxLength="32"&gt;...&lt;/fleetx:truncate&gt;</pre></div>
            <div class="card"><h2>RepeatClassic (BodyTagSupport)</h2><p><strong>Output:</strong> <fleetx:repeat times="4"><fleetx:badge value="★" color="success"/></fleetx:repeat></p><pre>&lt;fleetx:repeat times="4"&gt;&lt;fleetx:badge value="★" color="success"/&gt;&lt;/fleetx:repeat&gt;</pre></div>
            </div>
            <p><a href="<%=request.getContextPath()%>/">Zurück</a></p>
    </body></html>