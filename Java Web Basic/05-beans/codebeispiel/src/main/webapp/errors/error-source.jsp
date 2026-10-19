<%@ page contentType="text/html; charset=UTF-8" errorPage="error-target.jsp" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Error Source</title></head>
<body>
<h1>Error-Quelle</h1>
<form method="post"><button name="boom" value="1">Boom!</button></form>
<%
  if ("1".equals(request.getParameter("boom"))) {
    throw new RuntimeException("Absichtlicher Fehler für Demo");
  }
%>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
</body></html>
