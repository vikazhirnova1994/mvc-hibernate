<%--
  Created by IntelliJ IDEA.
  User: vikaz
  Date: 11.09.2022
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
    <meta charset="ISO-8859-1">
    <title>MVC Hibernate</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Navbar</a>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link " href="/customer/list">Customer</a>
            <a class="nav-item nav-link" href="/project/list">Project</a>
            <a class="nav-item nav-link active" href="/employee/list">Employee <span
                    class="sr-only">(do not work add)</span></a>
            <a class="nav-item nav-link " href="/position/list">Position </a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-offset-1 col-md-10">

        <h2>Employee List</h2>
        <hr/>

        <input type="button" value="ADD CUSTOMER"
               onclick="window.location.href='showForm'; return false;" class="btn btn-primary"/>
        <br/><br/>

        <div class="panel panel-info">

            <div class="panel-heading">
                <div class="panel-title">table from db</div>
            </div>

            <div class="panel-body">


                <table class="table table-striped table-bordered">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Position</th>
                        <th>Action</th>
                    </tr>
                    <!-- loop over and print our employees -->
                    <c:forEach var="oneEmpl" items="${employees}">

                        <!-- construct an "update" link with employee id -->
                        <c:url var="updateLink" value="/employee/updateForm">
                            <c:param name="employeeId" value="${oneEmpl.employeeId}"/>
                        </c:url>

                        <!-- construct an "delete" link with employee id -->
                        <c:url var="deleteLink" value="/employee/delete">
                            <c:param name="employeeId" value="${oneEmpl.employeeId}"/>
                        </c:url>

                        <tr>
                            <td>${oneEmpl.firstName}</td>
                            <td>${oneEmpl.lastName}</td>
                            <td>${oneEmpl.position}</td>
                            <td>
                                <!-- display the update link -->
                                <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}">Delete</a>
                            </td>

                        </tr>

                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>
