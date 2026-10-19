<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="person" class="de.ibb.beans.PersonBean" scope="request"/>
<jsp:setProperty name="person" property="*"/>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Ergebnis</title></head>
<body>
<jsp:include page="../fragments/header.jspf"/>
<h1>Erfasste Person</h1>
<ul>
  <li>Vorname: <jsp:getProperty name="person" property="firstName"/></li>
  <li>Nachname: <jsp:getProperty name="person" property="lastName"/></li>
  <li>Email: <jsp:getProperty name="person" property="email"/></li>
  <li>Alter: <jsp:getProperty name="person" property="age"/></li>
</ul>
<form method="post" action="view.jsp">
  <input type="hidden" name="firstName" value="<jsp:getProperty name='person' property='firstName'/>"/>
  <input type="hidden" name="lastName"  value="<jsp:getProperty name='person' property='lastName'/>"/>
  <input type="hidden" name="email"     value="<jsp:getProperty name='person' property='email'/>"/>
  <input type="hidden" name="age"       value="<jsp:getProperty name='person' property='age'/>"/>
  <button type="submit">Weiter (jsp:forward)</button>
</form>
<jsp:include page="../fragments/footer.jspf"/>
</body></html>
