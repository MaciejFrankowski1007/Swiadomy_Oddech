<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Air Quality Check</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkAirQuality.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
<h1>Check Air Quality</h1>
<form action="getAirQualityByCity" method="get">
    <label for="city">City:</label>
    <input type="text" id="city" name="city">
    <br>
    <input type="submit" value="Get Air Quality">
</form>
<button id="use-my-location">Use My Location</button>
</body>
</html>
