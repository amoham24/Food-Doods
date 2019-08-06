<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Food Doods Book</title>
        <meta charset="utf-8"/>
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">
    </head>
    <body>
        <header>
            <div id="top">
                <h1 id="title">Food Doods</h1>
                <img id="icon" src="images/icon.png" alt="icon">
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:choose>
                    <c:when test="${userProfile==null}">
                        <a id="logIn">NOT SIGNED IN.</a>
                    </c:when>    
                    <c:otherwise>
                        <a id="logIn">Welcome Back <c:out value="${userProfile.getUser().getFirstName()}" />.</a>
                    </c:otherwise>
                </c:choose>

            </div>
