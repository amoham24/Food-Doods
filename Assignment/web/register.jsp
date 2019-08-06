<jsp:include page="commons/header.jsp" />
<link rel="stylesheet" type="text/css" href="style/index.css">
<jsp:include page="commons/user-navigation.jsp" />

<main>
    <jsp:include page="commons/site-navigation.jsp" />
    <div id="core"><br>

        <h2>Register to Food Doods</h2>
        <p>Please enter your information to register with us!</p>
        <p id="errorMessage"><strong>${regMessage}</strong></p>
        <form id="register" action="ProfileController" method="POST">
            <input type="text" name="action" value="register" hidden>
            Username (no special characters): 
            <input type="text" name="username"><br>
            Password (no special characters):  
            <input type="password" name="password"><br>
            Email: 
            <input type="email" name="email"><br>
            First Name (no special characters):
            <input type="text" name="firstname"><br>
            Last Name (no special characters):
            <input type="text" name="lastname"><br>

            <br><input type="submit" value="Register">
        </form>
        <br><br><p>Already have an account? Click <a href="login.jsp">here</a> to log in!</p>
    </div>

</main>
<jsp:include page="commons/footer.jsp" />
