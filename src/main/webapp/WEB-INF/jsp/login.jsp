<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logowanie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css">
</head>
<body>
<h1>Logowanie</h1>
<form action="/login" method="post">
    <label for="username">Nazwa użytkownika:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Hasło:</label>
    <input type="password" id="password" name="password" required>
    <input type="submit" value="Zaloguj się">
    <a href="/home">Powrót</a>
</form>
<p>${error}</p>
</body>
</html>