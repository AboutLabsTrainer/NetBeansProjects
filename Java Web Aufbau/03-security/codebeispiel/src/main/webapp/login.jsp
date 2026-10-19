<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <style>
    body{font-family:Arial,sans-serif;background:#ececec;margin:0;min-height:100vh;display:flex;align-items:center;justify-content:center;color:#111}
    .login{width:min(360px,92vw);background:#efefef;border:2px solid #222;padding:18px}
    h1{font-size:42px;text-align:center;margin:0}
    .line{border-top:2px solid #222;margin:8px 0 12px}
    .error{border:2px solid #222;background:#f7f7f7;padding:8px;margin-bottom:10px;font-size:14px}
    label{display:block;font-weight:700;margin:8px 0 5px;font-size:13px}
    input,button{width:100%;box-sizing:border-box;border:2px solid #222;background:#fff;padding:9px;font-size:14px}
    button{margin-top:10px;background:#efefef;cursor:pointer;font-weight:700}
    .muted{margin-top:10px;border:2px solid #222;padding:8px;text-align:center;font-size:11px}
  </style>
</head>
<body>
  <div class="login">
    <h1>Secure Login</h1>
    <div class="line"></div>
    <% if (request.getParameter("error") != null) { %><div class="error">Login failed! Invalid credentials.</div><% } %>
    <form method="post" action="j_security_check">
      <label for="j_username">Username</label>
      <input id="j_username" type="text" name="j_username" required autofocus>
      <label for="j_password">Password</label>
      <input id="j_password" type="password" name="j_password" required>
      <button type="submit">Log In</button>
    </form>
    <p class="muted">Demo: admin/admin123, manager/manager123 oder user/user123
    </p>
  </div>
</body>
</html>
