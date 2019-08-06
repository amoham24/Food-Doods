<nav>
    <ul>


        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:choose>
            <c:when test="${userProfile==null}">
                <li><a href="login.jsp">Sign in</a></li>
            </c:when>    
            <c:otherwise>
                <li><a href="SignOut">Sign out</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="myItems.jsp">Saved Recipes</a></li>
        <li><a href="#">Cart</a></li>
    </ul>
</nav>
</header>