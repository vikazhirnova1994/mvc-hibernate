<%--
  Created by IntelliJ IDEA.
  User: vikaz
  Date: 11.09.2022
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
            <a class="nav-item nav-link active" href="/project/list">Project </a>
            <a class="nav-item nav-link " href="/employee/list">Employee</a>
            <a class="nav-item nav-link active" href="/position/list">Position <span class="sr-only"> current </span></a>

        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-offset-1 col-md-10">

        <h2>Position List</h2> <hr />

        <input type="button" value="ADD POSITION"
               onclick="window.location.href='showForm'; return false;" class="btn btn-primary" />
        <br/><br/>

        <div class="panel panel-info">

            <div class="panel-heading"> <div class="panel-title">table from db</div> </div>

            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Customer Name</th>
                        <th>Action</th>
                    </tr>
                    <!-- loop over and print our users -->
                    <c:forEach var="oneProject" items="${positions}">
                        <c:url var="updateLink" value="/position/updateForm"><!-- construct an "update" link with user id -->
                            <c:param name="positionId" value="${oneProject.positionId}" />
                        </c:url>
                        <c:url var="deleteLink" value="/position/delete"><!-- construct an "delete" link with user id -->
                            <c:param name="positionId" value="${oneProject.positionId}" />
                        </c:url>
                        <tr>
                            <td>${oneProject.position}</td>
                                  <td><!-- display the update link -->
                                <a href="${updateLink}">Update</a>
                                <a href="${deleteLink}">Delete</a>
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