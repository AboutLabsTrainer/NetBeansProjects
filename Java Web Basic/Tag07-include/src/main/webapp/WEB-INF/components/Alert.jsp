<%@ page contentType="text/html; charset=UTF-8" import="de.ibb.util.HtmlEscaper" %>
<%
  String type = request.getParameter("type");
  String message = request.getParameter("message");

  if (type == null || type.isBlank()) type = "info";
  if (message == null || message.isBlank()) message = "⚠️ (Kein message-Parameter übergeben)";

  String bg = "#eef";
  String border = "#99f";
  if ("success".equalsIgnoreCase(type)) { bg = "#eaffea"; border = "#6cc070"; }
  else if ("warn".equalsIgnoreCase(type) || "warning".equalsIgnoreCase(type)) { bg = "#fff8e5"; border = "#e0b100"; }
  else if ("error".equalsIgnoreCase(type) || "danger".equalsIgnoreCase(type)) { bg = "#ffeaea"; border = "#e06666"; }
%>
<div style="background:<%=bg%>;border:1px solid <%=border%>;padding:10px 12px;border-radius:8px;margin:8px 0">
  <%= HtmlEscaper.escape(message) %>
</div>
