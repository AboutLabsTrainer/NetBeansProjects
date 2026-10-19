<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="counter" class="de.ibb.beans.CounterBean" scope="session"/>
<% counter.increment(); %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Session-Bean</title></head>
<body>
<jsp:include page="../fragments/header.jspf"/>
<h1>Session-Bean (Counter)</h1>
<p>Aufrufe in dieser Session: <strong><jsp:getProperty name="counter" property="count"/></strong></p>
<p><a href="session.jsp">Neu laden</a></p>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
<jsp:include page="../fragments/footer.jspf"/>
</body></html>
