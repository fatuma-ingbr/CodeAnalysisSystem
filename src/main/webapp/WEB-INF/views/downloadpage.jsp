<%--
  Created by IntelliJ IDEA.
  User: Fatuma Ingabire
  Date: 21-Jul-19
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Download | Code Analyzer</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        //hiding feedback div
        setTimeout(function () {
            $("#feedbackArea").fadeOut();
        }, 5000);

        //showing download div
        setTimeout(function () {
            document.getElementById("downloadArea").style.display = "block";
        }, 6000);
        alert("it is working");
    </script>
</head>
<body>

    <div id="feedbackArea">${feedback}</div>
    <div id="downloadArea" style="display: none">
        <h3>Your file is here!</h3>
        <div>
            Download your file
            <a href="/filedownload.do">here</a>
        </div>
    </div>

    <div><a href="index.jsp">Go Back to Home Page</a> <a href="/logout.do">Logout</a></div>

<%--    <script src="javascript/main.js"></script>--%>
</body>
</html>
