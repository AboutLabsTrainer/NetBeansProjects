<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Formular Ergebnis</title></head>
<body>
<h1>Vielen Dank!</h1>
<p>Eingang: ${submittedAt}</p>
<%-- Formularwerte kommen vom Browser. c:out maskiert < > & ' ", sonst läuft ein mitgeschicktes <script> (XSS). --%>
<ul>
  <li>Name: <strong><c:out value="${param.name}"/></strong></li>
  <li>Email: <strong><c:out value="${param.email}"/></strong></li>
  <li>Nachricht: <pre style="white-space:pre-wrap"><c:out value="${param.message}"/></pre></li>
</ul>
<c:if test="${empty param.name or empty param.email or empty param.message}">
  <p style="color:#c00">Bitte alle Felder ausfüllen.</p>
</c:if>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
</body></html>
