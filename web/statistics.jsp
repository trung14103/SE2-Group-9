<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Statistics</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="./assets/libs/css/main.css">
    <link rel="stylesheet" href="./assets/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="./assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" defer></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js" defer></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" defer></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js" defer></script>
    <script src='./assets/libs/js/main.js' defer></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light navbar-white">

    <!--Navbar items-->
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="statistics.html">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about.html">About</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Sign In</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <!-- Row 1 -->
    <div class="row">
        <div class="col">
            <h2 class="header-box">GLOBAL</h2>
            <img src="./assets/images/unflag.gif" class="flag" alt="global"/>
            <div class="infowrap">
                <div class="col-md-4">
                    <div class="cases">INFECTED
                        <br>
                        3,237,600</div>
                </div>
                <div class="col-md-4">
                    <div class="deaths">DEATHS
                        <br>
                        228,828</div>
                </div>
                <div class="col-md-4">
                    <div class="recover">RECOVERED
                        <br>
                        1,010,260</div>
                </div>
            </div>
        </div>
        <div class="col">
            <h2 class="header-box">VIETNAM</h2>
            <img src="./assets/images/VN.png" class="flag" alt="VN Flag"/>
            <div class="infowrap">
                <div class="col-md-4">
                    <div class="cases">INFECTED
                        <br>
                        270</div>
                </div>
                <div class="col-md-4">
                    <div class="deaths">DEATHS
                        <br>
                        0</div>
                </div>
                <div class="col-md-4">
                    <div class="recover">RECOVERED
                        <br>
                        219</div>
                </div>
            </div>
        </div>
    </div>
    <br/>
</div>
<!--Row 2-->
<div class="container">
    <div class="row">
        <div class="col">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>City, Province</th>
                    <th>Infected</th>
                    <th>Critical</th>
                    <th>Death</th>
                    <th>Recovered</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><c:out value="${generalData.getCity().name}"/></td>
                    <td><c:out value="${generalData.infected}"/></td>
                    <td><c:out value="${generalData.critical}"/></td>
                    <td><c:out value="${generalData.death}"/></td>
                    <td><c:out value="${generalData.recovered}"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="footer">
    Copyright &copy; 2020 by Supernho Corp.
</div>

<!--Opt JS-->
<script src="./assets/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="./assets/vendor/slimscroll/jquery.slimscroll.js"></script>
</body>
</html>