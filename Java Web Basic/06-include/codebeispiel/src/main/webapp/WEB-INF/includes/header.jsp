<%@ page contentType="text/html; charset=UTF-8" import="de.ibb.util.HtmlEscaper" %>
<header style="border-bottom:1px solid #eee;padding:12px 0">
  <nav style="max-width:960px;margin:0 auto;display:flex;gap:16px;align-items:center">
    <strong>Tag 7 Demo</strong>
    <a href="<%= HtmlEscaper.escape(request.getContextPath()) %>/" style="text-decoration:none">Start</a>
    <a href="<%= HtmlEscaper.escape(request.getContextPath()) %>/demo.jsp" style="text-decoration:none">Demo</a>
  </nav>
</header>
