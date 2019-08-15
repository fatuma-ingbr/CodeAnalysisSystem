<%--
  Created by IntelliJ IDEA.
  User: Fatuma Ingabire
  Date: 15-Aug-19
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login | Code Analyzer</title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    <h1>Welcome to Code Analyzer!</h1>
    <p><b>Login to start reviewing your code</b></p>

    <form action="/login.do" method="post">

        <p style="color: red">${loginError}</p>
        Name:<br> <input type="email" name="email"/> <br><br>
        Password:<br> <input type="password" name="password" title="Password should be 8 alphanumeric characters or more including 1 uppercase letter" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"/>
        <br><br>

        <div class="g-recaptcha" data-sitekey="6LfDILMUAAAAAIrJbxocwLsnNb2nHIkCfPc5uM9d"></div>
        <input type="submit" value="Submit">

        <br><br>
        <p>Do not have an account? <a href="/register.do"> Register here</a></p>

    </form>
</body>
</html>
