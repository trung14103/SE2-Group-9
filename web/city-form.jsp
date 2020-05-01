<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">


<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>City, Province Management</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="./assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="./assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="./assets/libs/css/style.css">
    <link rel="stylesheet" href="./assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
</head>

<body>
<!-- ============================================================== -->
<!-- main wrapper -->
<!-- ============================================================== -->
<div class="dashboard-main-wrapper">
    <!-- ============================================================== -->
    <!-- navbar -->
    <!-- ============================================================== -->
    <div class="dashboard-header">
        <nav class="navbar navbar-expand-lg bg-white fixed-top">
            <a class="navbar-brand" href="#">#COVID-19</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto navbar-right-top">
                    <li class="nav-item">
                        <div id="custom-search" class="top-search-bar">
                            <input class="form-control" type="text" placeholder="Search..">
                        </div>
                    </li>
                    <li class="nav-item dropdown notification">
                        <a class="nav-link nav-icons" href="#" id="navbarDropdownMenuLink1" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><i class="fas fa-fw fa-bell"></i> <span
                                class="indicator"></span></a>
                        <ul class="dropdown-menu dropdown-menu-right notification-dropdown">
                            <li>
                                <div class="notification-title"> Notification</div>
                                <div class="notification-list">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item list-group-item-action active">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img
                                                        src="./assets/images/avatar-2.jpg" alt=""
                                                        class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span
                                                        class="notification-list-user-name">Jeremy Rakestraw</span>accepted
                                                    your invitation to join the team.
                                                    <div class="notification-date">2 min ago</div>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#" class="list-group-item list-group-item-action">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img
                                                        src="./assets/images/avatar-3.jpg" alt=""
                                                        class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span
                                                        class="notification-list-user-name">
John Abraham</span>is now following you
                                                    <div class="notification-date">2 days ago</div>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#" class="list-group-item list-group-item-action">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img
                                                        src="./assets/images/avatar-4.jpg" alt=""
                                                        class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span
                                                        class="notification-list-user-name">Monaan Pechi</span> is
                                                    watching your main repository
                                                    <div class="notification-date">2 min ago</div>
                                                </div>
                                            </div>
                                        </a>
                                        <a href="#" class="list-group-item list-group-item-action">
                                            <div class="notification-info">
                                                <div class="notification-list-user-img"><img
                                                        src="./assets/images/avatar-5.jpg" alt=""
                                                        class="user-avatar-md rounded-circle"></div>
                                                <div class="notification-list-user-block"><span
                                                        class="notification-list-user-name">Jessica Caruso</span>accepted
                                                    your invitation to join the team.
                                                    <div class="notification-date">2 min ago</div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="list-footer"><a href="#">View all notifications</a></div>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown connection">
                        <a class="nav-link" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"> <i class="fas fa-fw fa-th"></i> </a>
                        <ul class="dropdown-menu dropdown-menu-right connection-dropdown">
                            <li class="connection-list">
                                <div class="row">
                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                        <a href="#" class="connection-item"><img src="./assets/images/github.png"
                                                                                 alt=""> <span>Github</span></a>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                        <a href="#" class="connection-item"><img src="./assets/images/dribbble.png"
                                                                                 alt=""> <span>Dribbble</span></a>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                        <a href="#" class="connection-item"><img src="./assets/images/dropbox.png"
                                                                                 alt=""> <span>Dropbox</span></a>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                        <a href="#" class="connection-item"><img src="./assets/images/bitbucket.png"
                                                                                 alt=""> <span>Bitbucket</span></a>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                        <a href="#" class="connection-item"><img src="./assets/images/mail_chimp.png"
                                                                                 alt=""><span>Mail chimp</span></a>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 col-12 ">
                                        <a href="#" class="connection-item"><img src="./assets/images/slack.png" alt="">
                                            <span>Slack</span></a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="conntection-footer"><a href="#">More</a></div>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown nav-user">
                        <a class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><img src="./assets/images/avatar-1.jpg" alt=""
                                                                           class="user-avatar-md rounded-circle"></a>
                        <div class="dropdown-menu dropdown-menu-right nav-user-dropdown"
                             aria-labelledby="navbarDropdownMenuLink2">
                            <div class="nav-user-info">
                                <h5 class="mb-0 text-white nav-user-name">
                                    John Abraham</h5>
                                <span class="status"></span><span class="ml-2">Available</span>
                            </div>
                            <a class="dropdown-item" href="#"><i class="fas fa-user mr-2"></i>Account</a>
                            <a class="dropdown-item" href="#"><i class="fas fa-cog mr-2"></i>Setting</a>
                            <a class="dropdown-item" href="#"><i class="fas fa-power-off mr-2"></i>Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <!-- ============================================================== -->
    <!-- end navbar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- left sidebar -->
    <!-- ============================================================== -->
    <div class="nav-left-sidebar sidebar-dark">
        <div class="menu-list">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="d-xl-none d-lg-none" href="#">Dashboard</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav flex-column">
                        <li class="nav-divider">
                            Dashboard
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false"
                               data-target="#submenu-1" aria-controls="submenu-1"><i
                                    class="fa fa-fw fa-user-circle"></i>User Management <span
                                    class="badge badge-success">6</span></a>
                            <div id="submenu-1" class="collapse submenu" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="user?command=new">Create New User</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="user?command=list">User Datatable</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="#" data-toggle="collapse" aria-expanded="false"
                               data-target="#submenu-2" aria-controls="submenu-2"><i class="fa fa-fw fa-warehouse"></i>City,
                                Province </a>
                            <div id="submenu-2" class="collapse submenu" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="city?command=new">Add New Data</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="city?command=list">City, Province Datatable</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false"
                               data-target="#submenu-3" aria-controls="submenu-3"><i class="fas fa-fw fa-chart-pie"></i>Country
                            </a>
                            <div id="submenu-3" class="collapse submenu" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="country?command=new">Add New Country Data</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="country?command=list">Country Datatable</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false"
                               data-target="#submenu-4" aria-controls="submenu-4"><i class="fab fa-fw fa-wpforms"></i>Covid
                                Data </a>
                            <div id="submenu-4" class="collapse submenu" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="generalData?command=new">Add New Data</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="generalData?command=list">Covid Cases Datatable</a>
                                    </li>

                                </ul>
                            </div>
                        </li>

                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- end left sidebar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- wrapper  -->
    <!-- ============================================================== -->
    <div class="dashboard-wrapper">
        <div class="container-fluid  dashboard-content">
            <!-- ============================================================== -->
            <!-- pageheader -->
            <!-- ============================================================== -->
            <div class="row">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="page-header">
                        <h2 class="pageheader-title">Update City, Province Form </h2>
                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">City, Province
                                        Management</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Update Form</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- end pageheader -->
            <!-- ============================================================== -->
            <div class="row">
                <!-- ============================================================== -->
                <!-- basic form -->
                <!-- ============================================================== -->
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="card">
                        <div class="card-body">
                            <c:if test="${city != null}">
                            <form action="<%request.getServletPath();%>city?command=update" method="post">
                                </c:if>
                                <c:if test="${city == null}">
                                <form action="<%request.getServletPath();%>city?command=insert" method="post">
                                    </c:if>

                                    <caption>
                                        <h2>
                                            <c:if test="${city != null}">
                                                Edit City
                                            </c:if>
                                            <c:if test="${city == null}">
                                                Add New City
                                            </c:if>
                                        </h2>
                                    </caption>

                                    <c:if test="${city != null}">
                                        <input type="hidden" name="id" value="<c:out value='${city.id}' />"/>
                                        <input type="hidden" name="oldCityName" value="<c:out value='${city.name}' />"/>
                                    </c:if>

                                    <div class="form-group">
                                        <label for="inputCityName">City, Province Name</label>
                                        <input id="inputCityName" type="text" name="name"
                                               value="<c:out value='${city.name}' />" data-parsley-trigger="change"
                                               placeholder="City, Province Name" autocomplete="off"
                                               class="form-control">
                                        <c:if test="${error!= null}">
                                            <li style="color: red"><%=request.getAttribute("error")%></li>
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputCountryID">Country ID</label>
                                        <input id="inputCountryID" type="number" name="countryId"
                                               placeholder="Country ID" required=""
                                               value="<c:out value='${city.countryId}' />" class="form-control">
                                        <label id="err"></label>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12 pl-0">
                                            <p class="text-right">
                                                <button type="submit" class="btn btn-space btn-primary">Submit</button>
                                                <button class="btn btn-space btn-secondary">Cancel</button>
                                            </p>
                                        </div>
                                    </div>
                                </form>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end basic form -->
                <!-- ============================================================== -->

                <!-- ============================================================== -->
                <!-- footer -->
                <!-- ============================================================== -->
                <div class="footer">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-md-right d-none d-sm-block">
                                    Copyright © 2018 Concept. All rights reserved. Dashboard by <a
                                        href="https://colorlib.com/wp/">Colorlib</a>.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- end footer -->
                <!-- ============================================================== -->
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- end main wrapper -->
        <!-- ============================================================== -->
        <!-- Optional JavaScript -->
        <script src="./assets/vendor/jquery/jquery-3.3.1.min.js"></script>
        <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
        <script src="./assets/vendor/slimscroll/jquery.slimscroll.js"></script>
        <script src="./assets/vendor/parsley/parsley.js"></script>
        <script src="./assets/libs/js/main-js.js"></script>
        <script>
            $('#form').parsley();
        </script>
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
    </div>
</div>
</body>
</html>