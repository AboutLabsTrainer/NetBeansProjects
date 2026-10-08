<%@ page contentType="text/html; charset=UTF-8" import="de.ibb.util.HtmlEscaper" %>
<footer style="border-top:1px solid #eee;margin-top:32px;padding:12px 0;color:#666">
  <div style="max-width:960px;margin:0 auto">
    © <%= java.time.Year.now() %> · Request: <%= HtmlEscaper.escape(request.getRequestURI()) %>
  </div>
</footer>
