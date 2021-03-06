<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NP Geek Capstone</title>
    <c:url value="/css/npgeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    		<c:url value="/" var="homePageHref" />
    		<c:url value="/img/logo.png" var="logoSrc" />
        <a href="${homePageHref}">
        		<img src="${logoSrc}" alt="NPGeek logo" />
        </a>

        <h1>Rochelle & Evan's Module 3 Capstone</h1>
 
    </header>
    <nav>
        <ul>
            <li><a href="${homePageHref}">Home</a></li>
            <c:url value="/survey" var="surveyUrl" />
            <li class="liSurvey"><a href="${surveyUrl}">Vote for Your Favorite Park</a></li> <%--href = controller --%>
            <c:url value="/favparks" var="favParkUrl" />
            <li><a href="${favParkUrl}">Favorite Parks</a></li> <%--href = controller --%>
        </ul>
    </nav>