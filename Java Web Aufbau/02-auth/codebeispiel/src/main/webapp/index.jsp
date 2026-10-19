<%@ page import="de.ibb.model.Customer" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  Customer c = (Customer) session.getAttribute("customer");
  if (c == null) {
    response.sendRedirect(request.getContextPath() + "/signin.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Dashboard</title>
  <style>body{font-family:system-ui,Arial,sans-serif;margin:40px}</style>
</head>
<body>
  <h1>Welcome</h1>
  <p>You are logged in as <strong><%= c.getEmail() %></strong>.</p>
  <form action="signout.jsp" method="post">
    <button type="submit">Sign out</button>
  </form>
</body>
</html>
