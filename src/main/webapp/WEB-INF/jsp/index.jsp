<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Świadomy Oddech</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css">
    <script>
        function navigateTo(url) {
            window.location.href = url;
        }
    </script>
</head>
<body>
<form action="/home" method="post">
    <h1>Witaj w aplikacji "Świadomy Oddech"</h1>
    <button type="button" onclick="navigateTo('register')">Rejestracja</button>
    <button type="button" onclick="navigateTo('login')">Logowanie</button>
</form>
</body>
</html>
