<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">


<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Country Form</title>
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
                        <h2 class="pageheader-title">Update Covid Data Form </h2>
                        <div class="page-breadcrumb">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">Covid Data</a></li>
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
                        <h5 class="card-header">
                            <c:if test="${GeneralData == null}">Add New Covid Data Form</c:if>
                            <c:if test="${GeneralData != null}">Update Form</c:if>
                        </h5>
                        <div class="card-body">
                            <c:if test="${GeneralData != null}">
                            <form action="<%request.getServletPath();%>/generalData?command=update" method="post"
                                  id="basicform" data-parsley-validate="">
                                </c:if>
                                <c:if test="${GeneralData== null}">
                                <form action="<%request.getServletPath();%>/generalData?command=insert" method="post"
                                      id="basicform" data-parsley-validate="">
                                    </c:if>

                                    <c:if test="${GeneralData != null}">
                                        <input type="hidden" name="id" value="<c:out value='${GeneralData.id}' />"/>
                                    </c:if>

                                    <div class="form-group">
                                        <label for="inputCountry">Country ID</label>
                                        <input id="inputCountry" type="number" name="country_id"
                                               value="<c:out value='${GeneralData.country_id}' />"
                                               data-parsley-trigger="change" required="" placeholder="Country ID"
                                               autocomplete="off" class="form-control">
                                        <label id="err_countryId"></label>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputCity">City ID</label>
                                        <input id="inputCity" type="number" name="city_id" data-parsley-trigger="change"
                                               required=""
                                               value="<c:out value='${GeneralData.city_id}' />" placeholder="City ID"
                                               autocomplete="off" class="form-control">
                                        <label id="err_cityId"></label>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputInfected">Infected Cases</label>
                                        <input id="inputInfected" type="number" name="infected"
                                               data-parsley-trigger="change" required=""
                                               value="<c:out value='${GeneralData.infected}' />" placeholder="Infected"
                                               autocomplete="off" class="form-control">
                                        <label id="err_infected"></label>
                                    </div>

                                    <div class="form-group">
                                        <label for="inputRecovered">Recovered</label>
                                        <input id="inputRecovered" type="number" name="recovered"
                                               data-parsley-trigger="change" required=""
                                               value="<c:out value='${GeneralData.recovered}' />"
                                               placeholder="Recovered" autocomplete="off" class="form-control">
                                        <label id="err_recovered"></label>
                                    </div>

                                    <div class="form-group">
                                        <label for="inputCritical">Critical</label>
                                        <input id="inputCritical" type="number" name="critical"
                                               data-parsley-trigger="change" required=""
                                               value="<c:out value='${GeneralData.critical}' />" placeholder="Critical"
                                               autocomplete="off" class="form-control">
                                        <label id="err_critical"></label>
                                    </div>

                                    <div class="form-group">
                                        <label for="inputDeath">Death</label>
                                        <input id="inputDeath" type="number" name="death" data-parsley-trigger="change"
                                               required=""
                                               value="<c:out value='${GeneralData.death}' />" placeholder="Death"
                                               autocomplete="off" class="form-control">
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
</body>

</html>