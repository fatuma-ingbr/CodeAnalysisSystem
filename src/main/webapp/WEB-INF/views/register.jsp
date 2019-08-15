
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register | Code Analyzer</title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    <div><b>Register to get started!</b><br><br></div>
    <form action="/register.do" method="post">
        Email: <br>
        <input type="email" name="email"/> <br><br>

        Password: <br>
        <input type="password" name="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Password should be 8 alphanumeric characters or more including 1 uppercase letter">
        <br><br>

        <div class="g-recaptcha" data-sitekey="6LfDILMUAAAAAIrJbxocwLsnNb2nHIkCfPc5uM9d"></div>
        <input type="submit" value="Submit">

    </form>

    <p>Already have an account? <a href="/login.do"> Login here</a></p>

</body>
</html>
