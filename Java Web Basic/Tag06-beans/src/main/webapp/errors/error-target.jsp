<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Fehlerseite</title></head>
<body>
<h1>Oops – Fehler aufgetreten</h1>
<p><strong>Typ:</strong> ${pageContext.exception.class.name}</p>
<p><strong>Nachricht:</strong> ${pageContext.exception.message}</p>
<p><a href="<%=request.getContextPath()%>/">Zurück</a></p>
</body></html>
