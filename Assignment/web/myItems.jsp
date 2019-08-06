<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/myItems.css">
<jsp:include page="commons/user-navigation.jsp" />
<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div id="core"><br>
        <h2>My Recipe Book</h2>
        <table>

            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <c:choose>
                <c:when test="${userProfile==null}">
                    <h3>Please sign in to see your items</h3>
                </c:when>    
                <c:otherwise>

                    <c:choose>
                        <c:when test="${userProfile.getItems().isEmpty()}">
                            <h2>You currently have no items, please add some from the categories page!</h2>
                        </c:when>    
                        <c:otherwise>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Recipe</th>
                                    <th>Category</th>
                                    <th>My Rating</th>
                                    <th>Made it</th>
                                    <td></td>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${userProfile.getItems()}" var="i">
                                    <tr>
                                        <th>&#9733;</th>
                                        <td><c:out value = "${i.getItem().getItemName()}" /></td>
                                        <td><c:out value = "${i.getItem().getCategory()}" /></td>
                                        <c:choose>
                                            <c:when test="${i.getRating()==0}">
                                                <td>N/A</td>
                                            </c:when>    
                                            <c:otherwise>
                                                <td><c:out value = "${i.getRating()}/5" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${i.isMadeIt()==false}">
                                                <td>&#x25a2;</td>
                                            </c:when>    
                                            <c:otherwise>
                                                <td>&#10004;</td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td><a class="button" href="ProfileController?action=updateProfile&itemCode=<c:out value = "${i.getItem().getItemCode()}" />">Update</a></td>
                                        <td><a class="button" href="ProfileController?action=deleteItem&itemCode=<c:out value = "${i.getItem().getItemCode()}" />">Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>

                <!--            <tr>
                                <td></td>
                                <td>Tap Water</td>
                                <td>Desserts</td>
                                <td>3/5</td>
                                <td><input type="checkbox"></td>
                                <td><a class="button" href="item.jsp">Update</a></td>
                                <td><a class="button" href="myItems.jsp">Delete</a></td>
                            </tr>-->
            </tbody>
        </table>
    </div>
</main>
<jsp:include page="commons/footer.jsp" />