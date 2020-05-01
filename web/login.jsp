<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Login Page</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="./assets/libs/css/login.css">
    <link rel="stylesheet" href="./assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="./assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" defer></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js" defer></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" defer></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js" defer></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-7">
            <img src="img/virus.png" class="login-img" alt="virus"/>
        </div>
        <div class="col-md-4">

            <form action="/login?command=check" method="post" id="basicform" data-parsley-validate="">
            <h1 class="login-header">Login</h1>
            <div class="input-container">
                <i class="fas fa-user" aria-hidden="true"></i>
                <input id="inputName" type="text" name="username"
                       data-parsley-trigger="change" required="" placeholder="UserName"
                       autocomplete="off" class="input-field">
            </div>
                <c:if test="${error!= null}">
                    <li style="color: red"><%=request.getAttribute("error")%></li>
                </c:if>
            <div class="input-container">
                <i class="fas fa-lock" aria-hidden="true"></i>
                <input id="inputPws" type="text" name="password"
                       data-parsley-trigger="change" required="" placeholder="Password"
                       autocomplete="off" class="input-field">
            </div>
            <br/>
            <input type="submit" id="input-submit" value="Login" class="input-field">
            </form>
        </div>
    </div>
</div>

<%--<td><span--%>
<%--        style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>--%>
<%--</td>--%>

<!--Opt JS-->
<script src="./assets/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="./assets/vendor/slimscroll/jquery.slimscroll.js"></script>
</body>
</html>