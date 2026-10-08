<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Include</title></head>
<body>
<%@ include file="fragments/header.jspf" %>
<h1>Include</h1>
<p><strong>Directive include</strong> (oben) – Translate-Time.</p>
<jsp:include page="fragments/footer.jspf" />
<p><strong>&lt;jsp:include&gt;</strong> (oben) – Request-Time.</p>
</body></html>
