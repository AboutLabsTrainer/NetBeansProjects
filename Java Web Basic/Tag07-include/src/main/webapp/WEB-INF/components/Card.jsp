<%@ page contentType="text/html; charset=UTF-8" import="de.ibb.util.HtmlEscaper" %>
<%
  String title = request.getParameter("title");
  String content = request.getParameter("content");
  if (title == null || title.isBlank()) title = "Card";
  if (content == null || content.isBlank()) content = "Keine Inhalte übergeben – setze 'content' per <jsp:param>.";
%>
<section style="border:1px solid #eee;border-radius:12px;padding:12px 14px;margin:8px 0">
  <h3 style="margin:0 0 6px 0"><%= HtmlEscaper.escape(title) %></h3>
  <p style="margin:0"><%= HtmlEscaper.escape(content) %></p>
</section>
