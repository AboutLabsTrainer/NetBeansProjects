<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="person" class="de.ibb.beans.PersonBean" scope="request"/>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Result View</title></head>
<body>
<%@ include file="../fragments/header.jspf" %>
<h1>Result View</h1>
<%-- Die Bean-Felder stammen aus dem Formular, also maskiert ausgeben. --%>
<p>Quelle: <c:out value="${param.source}"/></p>
<p><c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/> &lt;<c:out value="${person.email}"/>&gt;, Alter ${person.age}</p>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
<%@ include file="../fragments/footer.jspf" %>
</body></html>
