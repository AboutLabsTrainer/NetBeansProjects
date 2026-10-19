<%@ page contentType="text/html; charset=UTF-8" import="de.ibb.util.HtmlEscaper" %>
<%
  Object pageTitleAttr = request.getAttribute("pageTitle");
  String pageTitle = pageTitleAttr != null ? pageTitleAttr.toString() : "Tag 7 Demo";
%>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><%= HtmlEscaper.escape(pageTitle) %></title>
