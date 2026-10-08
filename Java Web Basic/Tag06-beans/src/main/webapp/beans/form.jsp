<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Bean-Formular</title></head>
<body>
<%@ include file="../fragments/header.jspf" %>
<h1>Person erfassen</h1>
<form method="post" action="submit.jsp">
  <label>Vorname: <input name="firstName" required></label><br/>
  <label>Nachname: <input name="lastName" required></label><br/>
  <label>Email: <input type="email" name="email" required></label><br/>
  <label>Alter: <input type="number" name="age" min="0" required></label><br/>
  <button type="submit">Speichern</button>
</form>
<%@ include file="../fragments/footer.jspf" %>
</body></html>
