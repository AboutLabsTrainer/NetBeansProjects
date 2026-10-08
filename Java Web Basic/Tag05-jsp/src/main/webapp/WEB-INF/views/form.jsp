<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Formular</title></head>
<body>
<h1>Kontakt</h1>
<form method="post" action="<%=request.getContextPath()%>/demo/submit">
  <label>Name: <input name="name" required /></label><br/>
  <label>Email: <input type="email" name="email" required /></label><br/>
  <label>Nachricht:<br/><textarea name="message" rows="4" cols="40" required></textarea></label><br/>
  <button type="submit">Senden</button>
</form>
<p><a href="<%=request.getContextPath()%>/">← zurück</a></p>
</body></html>
