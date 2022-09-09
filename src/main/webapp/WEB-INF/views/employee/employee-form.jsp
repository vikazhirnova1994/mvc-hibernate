<%--
  Created by IntelliJ IDEA.
  User: vikaz
  Date: 09.09.2022
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: vikaz
  Date: 08.09.2022
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>MVC Hibernate</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link " href="/customer/list">Customer</a>
            <a class="nav-item nav-link" href="/project/list">Project</a>
            <a class="nav-item nav-link active" href="/employee/list">Employee <span class="sr-only">(do not work add)</span></a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Add Employee</h2>
        <div class="panel panel-info">

            <div class="panel-body">
                <form:form action="saveEmployee" cssClass="form-horizontal"
                           method="post" modelAttribute="employee">  <!-- need to associate this data with user id -->
                    <form:hidden path="employeeId" />

                    <div class="form-group">
                        <label for="firstName" class="col-md-3 control-label">Last Name</label>
                        <div class="col-md-9">
                            <form:input path="firstName" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="firstName" cssClass="alert alert-warning" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastName" class="col-md-3 control-label">First Name</label>
                        <div class="col-md-9">
                            <form:input path="lastName" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="lastName" cssClass="alert alert-warning" />
                        </div>
                    </div>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary">Submit</form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
