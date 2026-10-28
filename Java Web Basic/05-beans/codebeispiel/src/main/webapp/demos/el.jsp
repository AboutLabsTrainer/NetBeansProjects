<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>EL Demo</title></head>
<body>
<h1>EL – Implicit Objects</h1>
<%-- param und header kommen vom Browser. c:out maskiert sie, sonst wäre ?q=<script>...</script> ausführbar. --%>
<ul>
  <li>param.q = <strong><c:out value="${param.q}"/></strong></li>
  <li>header.User-Agent = <strong><c:out value="${header['User-Agent']}"/></strong></li>
  <li>session id = <strong>${pageContext.session.id}</strong></li>
</ul>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
</body></html>
