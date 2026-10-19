<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <%
    request.setAttribute("pageTitle", "Tag 7 – Components Demo");
  %>
  <%@ include file="/WEB-INF/includes/meta-tags.jsp" %>
</head>
<body>
  <%@ include file="/WEB-INF/includes/header.jsp" %>

  <main style="max-width:960px;margin:24px auto;font-family:system-ui,Arial,sans-serif">
    <h1>Tag 7 – Mini‑Challenge Demo</h1>

    <h2>Alert-Komponente (&lt;jsp:include&gt; mit Parametern)</h2>
    <jsp:include page="/WEB-INF/components/Alert.jsp">
      <jsp:param name="type" value="success"/>
      <jsp:param name="message" value="Alles hat funktioniert!"/>
    </jsp:include>

    <jsp:include page="/WEB-INF/components/Alert.jsp">
      <jsp:param name="type" value="warn"/>
      <jsp:param name="message" value="Achtung: Prüfe deine Eingaben."/>
    </jsp:include>

    <h2>Button-Komponente</h2>
    <div style="display:flex;gap:8px;flex-wrap:wrap">
      <jsp:include page="/WEB-INF/components/Button.jsp">
        <jsp:param name="label" value="Primary"/>
        <jsp:param name="type" value="primary"/>
        <jsp:param name="size" value="md"/>
      </jsp:include>
      <jsp:include page="/WEB-INF/components/Button.jsp">
        <jsp:param name="label" value="Secondary"/>
        <jsp:param name="type" value="secondary"/>
        <jsp:param name="size" value="sm"/>
      </jsp:include>
      <jsp:include page="/WEB-INF/components/Button.jsp">
        <jsp:param name="label" value="Danger"/>
        <jsp:param name="type" value="danger"/>
        <jsp:param name="size" value="lg"/>
      </jsp:include>
    </div>

    <h2>Card-Komponente</h2>
    <jsp:include page="/WEB-INF/components/Card.jsp">
      <jsp:param name="title" value="Komponenten machen Spaß"/>
      <jsp:param name="content" value="Diese Card wurde per &lt;jsp:include&gt; eingebunden und erhält ihre Inhalte über Parameter."/>
    </jsp:include>

    <h2>Fehlerbehandlung & Default‑Werte</h2>
    <p>Bewusst ohne <code>message</code> &amp; ohne <code>type</code>:</p>
    <jsp:include page="/WEB-INF/components/Alert.jsp" />
  </main>

  <%@ include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
