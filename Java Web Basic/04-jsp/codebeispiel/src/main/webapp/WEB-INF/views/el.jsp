<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="de">
<head><meta charset="UTF-8" /><title>EL &amp; Scopes</title></head>
<body>
<jsp:include page="fragments/header.jspf" />

<h1>Expression Language &amp; Implicit Objects</h1>

<ul>
  <li>requestScope.course = <strong>${requestScope.course}</strong></li>
  <li>sessionScope.role = <strong>${sessionScope.role}</strong></li>
  <li>applicationScope.appName = <strong>${applicationScope.appName}</strong></li>
  <li>pageScope Beispiel = <c:set var="x" value="42" scope="page"/><strong>${pageScope.x}</strong></li>
</ul>

<h3>param / header / cookie</h3>
<ul>
  <li>param.q = <strong>${param.q}</strong></li>
  <li>header.User-Agent = <strong>${header['User-Agent']}</strong></li>
  <li>cookie.demo = <strong>${cookie.demo.value}</strong></li>
</ul>

<c:if test="${empty cookie.demo.value}">
  <%
    jakarta.servlet.http.Cookie c = new jakarta.servlet.http.Cookie("demo", "hello-el");
    c.setPath(request.getContextPath());
    c.setMaxAge(3600);
    response.addCookie(c);
  %>
  <p><em>Demo-Cookie gesetzt. Seite neu laden.</em></p>
</c:if>

<jsp:include page="fragments/footer.jspf" />
</body>
</html>
