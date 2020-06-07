<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>World Covid-19 Statistics</title>
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
    <script src='./assets/libs/js/main.js' defer></script>
    <script src='./assets/vendor/charts/charts-bundle/Chart.bundle.js' defer></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light navbar-white">

    <!--Navbar items-->
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="home">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">World</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="continent">Continent</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about">About</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <c:if test="${username == null}">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="login?command=view">Log In</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${username != null}">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="user?command=view">Admin Page</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Log Out</a>
                        </li>
                    </ul>
                </c:if>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <h2 class="header-box">World Statistics</h2>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <canvas id="myChart"></canvas>
        </div>
        <div class="col-md-12">
            <div class="table responsive">
                <table class="table first table-bordered">
                    <thead class="thead-light">
                    <tr>
                        <th>Country</th>
                        <th>Infected</th>
                        <th>Critical</th>
                        <th>Death</th>
                        <th>Recovered</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="generalData" items="${generalData}">
                        <tr>
                            <td><c:out value="${generalData.getCountry().getName()}"/></td>
                            <td><c:out value="${generalData.infected}"/></td>
                            <td><c:out value="${generalData.critical}"/></td>
                            <td><c:out value="${generalData.death}"/></td>
                            <td><c:out value="${generalData.recovered}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- <div class="footer">
    Copyright &copy; 2020 by Supernho Corp.
</div> -->

<!--Opt JS-->
<script src="./assets/vendor/jquery/jquery-3.3.1.min.js"></script>
<script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="./assets/vendor/slimscroll/jquery.slimscroll.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
<script type="text/javascript">
    var myChart = document.getElementById("myChart").getContext("2d");
    var states = [];
    var confirmed = [];
    var recovered = [];
    var deaths = [];
    $.ajax({
        async: false,

        url: "data",

        dataType: "json",

        success: function (data) {
            $.each(data, function (index, obj) {
                states.push(obj.country.name);
                confirmed.push(obj.infected);
                recovered.push(obj.recovered);
                deaths.push(obj.death);
            });
        }
    })
    var chart = new Chart(myChart, {
        type: "line",
        data: {
            labels: states,
            datasets: [
                {
                    label: "Confirmed Cases",
                    data: confirmed,
                    backgroundColor: "#f1c40f",
                    minBarLength: 100,
                    lineTension: 0,
                    fill: false,
                    borderColor: "#f1c40f"
                },
                {
                    label: "Recovered",
                    data: recovered,
                    backgroundColor: "#2ecc71",
                    minBarLength: 100,
                    fill: false,
                    borderColor: "#2ecc71"
                },
                {
                    label: "Deceased",
                    data: deaths,
                    backgroundColor: "#e74c3c",
                    minBarLength: 100,
                    fill: false,
                    borderColor: "#e74c3c"
                },
            ],
        },
        option: {
            elements: {
                line: {
                    fill: false
                }
            }
        },
    });

</script>
</body>
</html>