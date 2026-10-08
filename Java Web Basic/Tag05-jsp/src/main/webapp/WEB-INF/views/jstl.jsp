<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="de">
<head><meta charset="UTF-8" /><title>JSTL Core</title>
<style>body{font-family:system-ui,Arial,sans-serif;margin:40px}table{border-collapse:collapse}th,td{border:1px solid #ddd;padding:8px}</style>
</head>
<body>
<jsp:include page="fragments/header.jspf" />
<h1>JSTL Core – Iteration &amp; Bedingungen</h1>
<table>
  <thead><tr><th>#</th><th>Name</th><th>Alter</th><th>Status</th></tr></thead>
  <tbody>
    <c:forEach var="p" items="${people}" varStatus="st">
      <tr>
        <td>${st.index}</td>
        <td>${p.name}</td>
        <td>${p.age}</td>
        <td><c:choose><c:when test="${p.active}">aktiv</c:when><c:otherwise>inaktiv</c:otherwise></c:choose></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<jsp:include page="fragments/footer.jspf" />
</body></html>
