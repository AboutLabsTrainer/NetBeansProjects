<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 60px auto;
            background: #fff;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
        }
        h1 {
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            border: none;
            background: #007bff;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background: #0056b3;
        }
        .error {
            color: #b30000;
            background: #ffe6e6;
            border: 1px solid #ffb3b3;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
        }
        .info {
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <%
        String error = (String) request.getAttribute("errorMessage");
        String submittedUsername = (String) request.getAttribute("submittedUsername");
    %>
    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>
    <form method="post" action="<%= request.getContextPath() %>/login">
        <label for="username">Benutzername</label>
        <input type="text" id="username" name="username" value="<%= submittedUsername != null ? submittedUsername : "" %>" required>

        <label for="password">Passwort</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Anmelden</button>
    </form>
    <div class="info">
        <p>Test-Benutzer:</p>
        <p><strong>admin / password</strong></p>
        <p><strong>user / test123</strong></p>
    </div>
</div>
</body>
</html>

