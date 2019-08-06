<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/index.css">
<jsp:include page="commons/user-navigation.jsp" />

<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <div id="core"><br>
        <c:choose>
            <c:when test="${message==null}">

            </c:when>    
            <c:otherwise>
                <h3>${message}</h3>
            </c:otherwise>
        </c:choose>
        <h2>Welcome to Food Doods</h2>
        <p>Here at Food Doods, we are all about quality. We strive to get you the best recipes for you and your friends. You and all your baddies will be struck with awe on what we have to offer. All of our recipes are quality, gluten free, vegetarian, vegan, and most importantly made with love. We have recipes for your pleasure. With easy steps, our recipes make cooking food to be a breeze. Here at Food Doods, we have the Foods and you bring the Doods.</p>
    </div>
</main>
<jsp:include page="commons/footer.jsp" />
