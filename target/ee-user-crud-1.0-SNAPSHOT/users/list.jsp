
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/header.jsp" %>

<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">

        <%--        tu bedzie naglowek MÓJ MÓJ MÓJ --%>
            <h1 class="h3 mb-0 text-gray-800"></h1>

        <a href="/adduser" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i>Dodaj użytkownika</a>
    </div>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
            </div>
            <div class="card-body">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nazwa użytkownika</th>
                        <th scope="col">Email</th>
                        <th scope="col">Akcja</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="user" items="${users}">
                        <tr>
                            <th scope="row">${user.id}</th>
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="<c:url value="/delete?id=${user.id}"/>">Usuń</a>
                                <a href="<c:url value="/edituser?id=${user.id}"/>">Edytuj</a>
                                <a href="<c:url value="/showuser?id=${user.id}"/>">Pokaż</a>
                            </td>
                        </tr>

                    </c:forEach>


<%--            <tr>--%>
                    <%--                <th scope="row">2</th>--%>
                    <%--                <td>Jacob</td>--%>
                    <%--                <td>Thornton</td>--%>
                    <%--                <td>@fat</td>--%>
                    <%--            </tr>--%>
                    <%--            <tr>--%>
                    <%--                <th scope="row">3</th>--%>
                    <%--                <td>Larry</td>--%>
                    <%--                <td>the Bird</td>--%>
                    <%--                <td>@twitter</td>--%>
                    <%--            </tr>--%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>



    <div class="row">



    </div>

<%@ include file="/footer.jsp" %>