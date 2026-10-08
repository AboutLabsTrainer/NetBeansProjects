<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="person" class="de.ibb.beans.PersonBean" scope="request"/>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Result View</title></head>
<body>
<%@ include file="../fragments/header.jspf" %>
<h1>Result View</h1>
<p>Quelle: ${param.source}</p>
<p>${person.firstName} ${person.lastName} &lt;${person.email}&gt;, Alter ${person.age}</p>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
<%@ include file="../fragments/footer.jspf" %>
</body></html>
