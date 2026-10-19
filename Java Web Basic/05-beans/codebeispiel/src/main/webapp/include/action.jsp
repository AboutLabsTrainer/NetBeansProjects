<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>jsp:include</title></head>
<body>
<jsp:include page="../fragments/header.jspf"/>
<h1>jsp:include</h1>
<p>Footer via <code>&lt;jsp:include&gt;</code> (Request-Time) mit Parametern:</p>
<jsp:include page="../fragments/footer.jspf"><jsp:param name="dyn" value="from-include"/></jsp:include>
</body></html>
