<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="classic" uri="https://de.ibb/classic" %>
<!DOCTYPE html><html lang="de"><head>
        <meta charset="UTF-8"><title>Demo – BodyTagSupport</title>
        <link rel="stylesheet" href="assets/styles.css">
    </head><body>
        <h1>Demo – BodyTagSupport (Tag 6)</h1>
        <div class="grid">
            <div class="card"><h2>GreetingClassic (TagSupport)</h2><p><strong>Output:</strong> <classic:greeting name="Nova" time="evening"/></p><pre>&lt;classic:greeting name="Nova" time="evening"/&gt;</pre></div>
            <div class="card"><h2>BadgeClassic (TagSupport)</h2><p>Badges: <classic:badge value="Legacy" color="primary"/></p><pre>&lt;classic:badge value="Legacy" color="primary"/&gt;</pre></div>
            <div class="card"><h2>TruncateClassic (BodyTagSupport)</h2><p><strong>Output:</strong> <classic:truncate maxLength="32">Classic tags can buffer and transform body content right inside doAfterBody.</classic:truncate></p><pre>&lt;classic:truncate maxLength="32"&gt;...&lt;/classic:truncate&gt;</pre></div>
            <div class="card"><h2>RepeatClassic (BodyTagSupport)</h2><p><strong>Output:</strong> <classic:repeat times="4"><classic:badge value="★" color="success"/></classic:repeat></p><pre>&lt;classic:repeat times="4"&gt;&lt;classic:badge value="★" color="success"/&gt;&lt;/classic:repeat&gt;</pre></div>
            </div>
            <p><a href="<%=request.getContextPath()%>/">Zurück</a></p>
    </body></html>
