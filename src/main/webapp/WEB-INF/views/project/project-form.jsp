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
    <a class="navbar-brand" href="/">Navbar</a>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link " href="/customer/list">Customer</a>
            <a class="nav-item nav-link active" href="/project/list">Project </a> <span class="sr-only"> current </span></a>
            <a class="nav-item nav-link " href="/employee/list">Employee</a>
            <a class="nav-item nav-link " href="/position/list">Position </a>
        </div>
    </div>
</nav>


<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Add Project</h2>
        <div class="panel panel-info">

            <div class="panel-body">
                <form:form action="saveProject" cssClass="form-horizontal"
                           method="post" modelAttribute="project">

                    <!-- need to associate this data with user id -->
                    <form:hidden path="projectId" />

                    <div class="form-group">
                        <label for="name" class="col-md-3 control-label">Name of project</label>
                        <div class="col-md-9">
                            <form:input path="name" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="name" cssClass="alert alert-warning" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createAt" class="col-md-3 control-label">Creation date</label>
                        <div class="col-md-9">
                            <form:input path="createAt" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="createAt" cssClass="alert alert-warning" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="finishAt" class="col-md-3 control-label">Finish date</label>
                        <div class="col-md-9">
                            <form:input path="finishAt" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="finishAt" cssClass="alert alert-warning" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nameCustomer" class="col-md-3 control-label">Name customer</label>
                        <div class="col-md-9">
                            <form:input path="nameCustomer" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="nameCustomer" cssClass="alert alert-warning" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="emailCustomer" class="col-md-3 control-label">Name customer</label>
                        <div class="col-md-9">
                            <form:input path="emailCustomer" cssClass="form-control" />
                        </div>
                        <div class="col-md-3">
                            <form:errors path="emailCustomer" cssClass="alert alert-warning" />
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
