<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/index.css">
<jsp:include page="commons/user-navigation.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <div id="core"><br>
        <h2>Give your feedback for</h2>

        <img id="cheeseburg" src="<c:out value = "${theItem.getItem().getImageURL()}" />" alt="foodimg">
        <h2 id="foodName"><c:out value = "${theItem.getItem().getItemName()}" /></h2>
        <h3 id="category"><c:out value = "${theItem.getItem().getCategory()}" /></h3>

        <p><c:out value = "${theItem.getItem().getDescription()}" /></p>
        <form action="ProfileController?action=updateRating&itemCode=<c:out value = "${theItem.getItem().getItemCode()}" />" method="get">

            Rating:  
            <select name="rating">
                <option value="1">1 Star &#9733;</option>
                <option value="2">2 Stars &#9733;&#9733;</option>
                <option value="3">3 Stars &#9733;&#9733;&#9733;</option>
                <option value="4">4 Stars &#9733;&#9733;&#9733;&#9733;</option>
                <option value="5">5 Stars &#9733;&#9733;&#9733;&#9733;&#9733;</option>
            </select> <br>
            <input type="checkbox" name="isMade" value="true"> I made it<br>
            <input type="hidden" name="itemCode" value="<c:out value = "${theItem.getItem().getItemCode()}" />">
            <input type="hidden" name="action" value="updateRating">

            <input type="submit" value="Submit Rating">
        </form>
    </div>
</main>
<jsp:include page="commons/footer.jsp" />
