<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>EL Demo</title></head>
<body>
<h1>EL – Implicit Objects</h1>
<ul>
  <li>param.q = <strong>${param.q}</strong></li>
  <li>header.User-Agent = <strong>${header['User-Agent']}</strong></li>
  <li>session id = <strong>${pageContext.session.id}</strong></li>
</ul>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
</body></html>
