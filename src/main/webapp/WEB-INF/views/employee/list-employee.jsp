<%--
  Created by IntelliJ IDEA.
  User: vikaz
  Date: 09.09.2022
  Time: 0:45
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
    <div class="col-md-offset-1 col-md-10">
        <h2>Employee List</h2><hr />

        <input type="button" value="Add Employee"
               onclick="window.location.href='showForm'; return false;"  class="btn btn-primary" />
        <br/><br/>

        <div class="panel panel-info">
            <div class="panel-heading">  <div class="panel-title">table from db</div> </div>

            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>First Name</th>
                        <th>lastName</th>
                        <th>Position</th>
                    </tr>

                    <!-- loop over and print our users -->
                    <c:forEach var="oneEmployee" items="${employees}">
                        <c:url var="updateLink" value="/employee/updateForm"><!-- construct an "update" link with user id -->
                            <c:param name="employeeId" value="${oneEmployee.employeeId}" />
                        </c:url>
                        <c:url var="deleteLink" value="/employee/delete"><!-- construct an "delete" link with user id -->
                            <c:param name="employeeId" value="${oneEmployee.employeeId}" />
                        </c:url>
                        <tr>
                            <td>${oneEmployee.firstName}</td>
                            <td>${oneEmployee.lastName}</td>
                            <td>${oneEmployee.position}</td>
                            <td><!-- display the update link -->
                                <a href="${updateLink}">Update</a>
                                <a href="${deleteLink}" >Delete</a>
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