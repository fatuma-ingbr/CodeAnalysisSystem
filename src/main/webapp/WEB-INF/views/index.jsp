<%--
  Created by IntelliJ IDEA.
  User: Fatuma Ingabire
  Date: 20-Jul-19
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Code Analyzer</title>

    <style><%@include file="/WEB-INF/views/style/style.css"%></style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
    <!--Form that takes in user file. The enctype is multipart since..
    it is a file that is being submitted in the form. -->
    <form action="/fileupload.do" method="post" enctype="multipart/form-data">
        Upload File: <br><br>
        <input type="file" name="file" /> <br><br>
        <input type="submit" value="Upload" onclick="upload()" /> <br><br>
    </form>

    <script src="javascript/main.js"></script>
</body>
</html>
