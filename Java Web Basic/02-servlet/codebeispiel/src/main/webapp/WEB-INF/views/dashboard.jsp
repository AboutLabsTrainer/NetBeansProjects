<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 60px auto;
            background: #fff;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 6px;
        }
        h1 {
            text-align: center;
        }
        .actions {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 30px;
        }
        a, button {
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 16px;
        }
        a {
            background: #007bff;
            color: #fff;
        }
        a:hover {
            background: #0056b3;
        }
        button {
            background: #dc3545;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background: #a71d2a;
        }
        form {
            display: inline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Willkommen, <%= request.getAttribute("username") %>!</h1>
    <p>Sie sind erfolgreich eingeloggt.</p>
    <div class="actions">
        <a href="<%= request.getContextPath() %>/protected">Geschützter Bereich</a>
        <form method="post" action="<%= request.getContextPath() %>/logout">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>
</body>
</html>

