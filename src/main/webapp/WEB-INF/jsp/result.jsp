<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.json.JSONObject" %>
<%
    String airQualityData = request.getAttribute("airQualityData").toString();
    JSONObject json = new JSONObject(airQualityData);
    String status = json.getString("status");
    if (status.equals("ok")) {
        JSONObject data = json.getJSONObject("data");
        int aqi = data.getInt("aqi");
        String city = data.getJSONObject("city").getString("name");
        String dominentPol = data.getString("dominentpol");

        String aqiClass = "";
        if (aqi <= 50) {
            aqiClass = "good";
        } else if (aqi <= 100) {
            aqiClass = "moderate";
        } else if (aqi <= 150) {
            aqiClass = "sensitive";
        } else if (aqi <= 200) {
            aqiClass = "unhealthy";
        } else if (aqi <= 300) {
            aqiClass = "very-unhealthy";
        } else {
            aqiClass = "hazardous";
        }
%>
<html>
<head>
    <title>Air Quality Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/result.css">
</head>
<body>
<h1><%= city %></h1>
<h2>Wynik:</h2>
<p class="<%= aqiClass %>">AQI: <%= aqi %></p>
<p>Dominant Pollutant: <%= dominentPol %></p>

<h4>Porównaj swój wynik z istniejącą normą:</h4>
<h3>AQI (Air Quality Index - Indeks Jakości Powietrza)</h3>
<table class="table">
    <tr>
        <th>Zakres AQI</th>
        <th>Opis</th>
    </tr>
    <tr class="good">
        <td>0 - 50</td>
        <td>Dobry</td>
    </tr>
    <tr class="moderate">
        <td>51 - 100</td>
        <td>Umiarkowany</td>
    </tr>
    <tr class="sensitive">
        <td>101 - 150</td>
        <td>Niezdrowy dla grup wrażliwych</td>
    </tr>
    <tr class="unhealthy">
        <td>151 - 200</td>
        <td>Niezdrowy</td>
    </tr>
    <tr class="very-unhealthy">
        <td>201 - 300</td>
        <td>Bardzo niezdrowy</td>
    </tr>
    <tr class="hazardous">
        <td>301 - 500</td>
        <td>Toksyczny</td>
    </tr>
</table>

<h4>Dominant Pollutant (Dominujące Zanieczyszczenia)</h4>
<p>PM 2.5 - pył składający się z cząstek o średnicy do 2.5 mikrogramów</p>
<a href="login">Back</a>
</body>
</html>
<%
} else {
    String errorMessage = json.getJSONObject("data").getString("message");
%>
<html>
<head>
    <title>Air Quality Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/result.css">
</head>
<body>
<h1>Error</h1>
<p><%= errorMessage %></p>
<a href="index.jsp">Back</a>
</body>
</html>
<%
    }
%>
