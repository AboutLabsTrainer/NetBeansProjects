<%@ page contentType="text/html; charset=UTF-8" import="de.ibb.util.HtmlEscaper" %>
<%
  String label = request.getParameter("label");
  String type = request.getParameter("type");
  String size = request.getParameter("size");

  if (label == null || label.isBlank()) label = "Button";
  if (type == null || type.isBlank()) type = "primary";
  if (size == null || size.isBlank()) size = "md";

  String bg = "#0a7", color = "#fff";
  if ("secondary".equalsIgnoreCase(type)) { bg = "#555"; }
  if ("danger".equalsIgnoreCase(type)) { bg = "#c0392b"; }

  String padding = "8px 14px", font = "14px";
  if ("sm".equalsIgnoreCase(size)) { padding = "6px 10px"; font = "12px"; }
  if ("lg".equalsIgnoreCase(size)) { padding = "10px 18px"; font = "16px"; }
%>
<button style="background:<%=bg%>;color:<%=color%>;border:none;border-radius:10px;padding:<%=padding%>;font-size:<%=font%>;cursor:pointer" type="button">
  <%= HtmlEscaper.escape(label) %>
</button>
