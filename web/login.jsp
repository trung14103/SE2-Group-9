<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 25/04/2020
  Time: 3:08 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Login Page</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="./css/login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
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
            <form action="" method="POST">
                <h1 class="login-header">Login</h1>
                <div class="input-container">
                    <i class="fas fa-user" aria-hidden="true"></i>
                    <input type="text" class="input-field" placeholder="Username..." name="username" required/>
                </div>
                <div class="input-container">
                    <i class="fas fa-lock" aria-hidden="true"></i>
                    <input type="password" class="input-field" placeholder="Password..." name="pwd" required/>
                </div>
                <br/>
                <input type="submit" id="input-submit" value="Login" class="input-field">
            </form>
        </div>
    </div>
</div>
</body>
</html>