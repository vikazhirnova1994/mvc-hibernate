<%--
  Created by IntelliJ IDEA.
  User: vikaz
  Date: 09.09.2022
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>javaguides.net</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>

<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <h2>User Manager</h2>
        <hr />

        <input type="button" value="Add CUSTOMER"
               onclick="window.location.href='showForm'; return false;"
               class="btn btn-primary" />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">User List</div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>First Name</th>
                        <th>lastName</th>
                        <th>position</th>
                    </tr>

                    <!-- loop over and print our users -->
                    <c:forEach var="tempUser" items="${users}">

                        <!-- construct an "update" link with user id -->
                        <c:url var="updateLink" value="/project/updateForm">
                            <c:param name="userId" value="${tempUser.projectId}" />
                        </c:url>

                        <!-- construct an "delete" link with user id -->
                        <c:url var="deleteLink" value="/project/delete">
                            <c:param name="userId" value="${tempUser.projectId}" />
                        </c:url>

                        <tr>
                            <td>${tempUser.name}</td>
                            <td>${tempUser.createAt}</td>
                            <td>${tempUser.finishAt}</td>
                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
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