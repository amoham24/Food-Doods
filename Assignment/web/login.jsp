<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/index.css">
<jsp:include page="commons/user-navigation.jsp" />

<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <div id="core"><br>

        <h2>Welcome to Food Doods</h2>
        <p>Please enter your username and password to sign in</p>
        <p id="errorMessage"><strong>${logInMessage}</strong></p>
        <form id="signin" action="ProfileController" method="POST">
            <input type="text" name="action" value="signIn" hidden>
            Username: 
            <input type="text" name="username"><br>
            Password:  
            <input type="password" name="password">

            <br><input type="submit" value="Login">
        </form>
        <br><br><p>New To Food Doods? Click <a href="register.jsp">here</a> to create a new account!</p>
    </div>

</main>
<jsp:include page="commons/footer.jsp" />
