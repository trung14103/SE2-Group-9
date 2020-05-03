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
<% //In case, if Admin session is not set, redirect to Login page
    if(!(request.getSession(false).getAttribute("role").equals("admin")))
    {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }%>
<body>
<!-- ============================================================== -->
<!-- main wrapper -->
<!-- ============================================================== -->
<div class="dashboard-main-wrapper">
    <div>
        <jsp:include page = "navbar.jsp"></jsp:include>
    </div>
    <div>
        <jsp:include page = "sidebar.jsp"></jsp:include>
    </div>
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
                            <form action="city?command=update" method="post">
                                </c:if>
                                <c:if test="${city == null}">
                                <form action="city?command=insert" method="post">
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
                                        <c:if test="${errorCity!= null && errorCity != ''}">
                                            <li style="color: red"><%=request.getAttribute("errorCity")%></li>
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputCountryID">Country ID</label>
                                        <input id="inputCountryID" type="number" name="countryId"
                                               placeholder="Country ID" required=""
                                               value="<c:out value='${city.countryId}' />" class="form-control">
                                        <label id="err"></label>
                                        <c:if test="${errorCountry!= null && errorCountry != ''}">
                                            <li style="color: red"><%=request.getAttribute("errorCountry")%></li>
                                        </c:if>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputInfected">Infected</label>
                                        <input id="inputInfected" type="number" name="infected"
                                               placeholder="Infected" required=""
                                               value="<c:out value='${city.infected}' />" class="form-control">
                                        <label id="err"></label>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputCritical">Critical</label>
                                        <input id="inputCritical" type="number" name="critical"
                                               placeholder="Critical" required=""
                                               value="<c:out value='${city.critical}' />" class="form-control">
                                        <label id="err"></label>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDeath">Death</label>
                                        <input id="inputDeath" type="number" name="death"
                                               placeholder="Death" required=""
                                               value="<c:out value='${city.death}' />" class="form-control">
                                        <label id="err"></label>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputRecover">Recover</label>
                                        <input id="inputRecover" type="number" name="recovered"
                                               placeholder="Recover" required=""
                                               value="<c:out value='${city.recovered}' />" class="form-control">
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