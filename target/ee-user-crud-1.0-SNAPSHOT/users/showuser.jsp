

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/header.jsp" %>

<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">

        <%--        tu bedzie naglowek MÓJ MÓJ MÓJ --%>
        <h1 class="h3 mb-0 text-gray-800"></h1>

        <a href="/edituser?id=${user.id}" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i>Edytuj użytkownika</a>
    </div>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Szczegóły użytkownika</h6>
            </div>
            <div class="card-body">

                <table class="table">

                    <tbody>

                        <tr>
                            <th scope="row">Id</th>
                            <td><c:out value="${user.id}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Nazwa użytkownika</th>
                            <td>${user.userName}</td>
                        </tr>
                        <tr>
                            <th scope="row">Email</th>
                            <td>${user.email}</td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>



    <div class="row">

    </div>

<%@ include file="/footer.jsp" %>