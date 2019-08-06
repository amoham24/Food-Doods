<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/item.css">
<jsp:include page="commons/user-navigation.jsp" />
<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div id="core"><br>
        <a href="CatalogController">Back</a>
        <p id ="directory"><a href="index.jsp">Home</a> > <a href="CatalogController">Categories</a> > Item</p>
        <img id="cheeseburg" src="<c:out value = "${theItem.getImageURL()}" />" alt="foodimg">
        <h2 id="foodName"><c:out value = "${theItem.getItemName()}" /></h2>
        <h3 id="category"><c:out value = "${theItem.getCategory()}" /></h3>
        <h4 id="avgRating">Average Rating: <c:out value = "${theItem.getRating()}/5" /></h4>

        <c:choose>
            <c:when test="${userProfile==null || userProfile.getUserItemByID(theItem.getItemCode()) == null || userProfile.getUserItemByID(theItem.getItemCode()).getRating() == 0}">

            </c:when>    
            <c:otherwise>
                <h4>Your Rating: <c:out value = "${userProfile.getUserItemByID(theItem.getItemCode()).getRating()}/5" /></h4>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${userProfile==null}">
                <a class="button" href="SignOut?action=cant">Save it</a>
                <a class="button" href="SignOut?action=cant">Save with a Rate</a>
            </c:when>    
            <c:otherwise>
                <a class="button" href="ProfileController?action=save&itemCode=${theItem.getItemCode()}">Save it</a>
                <a class="button" href="ProfileController?action=updateProfile&itemCode=${theItem.getItemCode()}">Save with a Rate</a>
            </c:otherwise>
        </c:choose>


        <div id="foodInfo">
            <h4>Description</h4>
            <p><c:out value = "${theItem.getDescription()}" /></p>
        </div>
    </div>
</main>
<jsp:include page="commons/footer.jsp" />