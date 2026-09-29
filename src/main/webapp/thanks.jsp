<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    <h1>Thanks for joining our email list</h1>

    <p>Here is the information that you entered:</p>
    <p>
        Email: ${user.email}<br>
        First Name: ${user.firstName}<br>
        Last Name: ${user.lastName}
    </p>

    <p>To enter another email address, click on the Back button in your browser or the Return button shown below.</p>

    <form action="emailList" method="post">
        <input type="hidden" name="action" value="join">
        <input type="submit" value="Return">
    </form>
</body>
</html>