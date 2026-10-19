<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8" />
  <title>Tag 5 – JSP verstehen</title>
  <style>body{font-family:system-ui,Arial,sans-serif;margin:40px}a{display:block;margin:8px 0}</style>
</head>
<body>
  <h1>Tag 5 – JSP (Jakarta Pages) Demos</h1>
  <a href="<%=request.getContextPath()%>/demo/el">/demo/el – EL &amp; Scopes</a>
  <a href="<%=request.getContextPath()%>/demo/jstl">/demo/jstl – JSTL Core</a>
  <a href="<%=request.getContextPath()%>/demo/include">/demo/include – Include</a>
  <a href="<%=request.getContextPath()%>/demo/form">/demo/form – Formular</a>
</body>
</html>
