<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html><html lang="de"><head><meta charset="UTF-8" /><title>Include-Direktive</title></head>
<body>
<%@ include file="../fragments/header.jspf" %>
<h1>Include-Direktive</h1>
<p>Header/Footer via <code>&lt;%@ include %&gt;</code> (Translate-Time).</p>
<%@ include file="../fragments/footer.jspf" %>
</body></html>
