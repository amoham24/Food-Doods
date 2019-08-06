<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/catagories.css">
<jsp:include page="commons/user-navigation.jsp" />
<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <div id="core"><br>
        <h2>Catagories</h2>
        <h3>Entrees</h3>
        <ul>
            <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <c:forEach items = "${entrees}" var = "i">
                <li><a href ="CatalogController?itemCode=<c:out value = "${i.itemCode}" />"><c:out value = "${i.itemName}" /></a></li>
                </c:forEach>
        </ul>

        <h3>Desserts</h3>
        <ul>
            <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <c:forEach items = "${desserts}" var = "i">
                <li><a href ="CatalogController?itemCode=<c:out value = "${i.itemCode}" />"><c:out value = "${i.itemName}" /></a></li>
                </c:forEach>
        </ul>
    </div>



</main>
<jsp:include page="commons/footer.jsp" />