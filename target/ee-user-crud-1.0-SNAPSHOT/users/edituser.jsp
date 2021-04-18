<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/header.jsp" %>

<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">

        <%--        tu bedzie naglowek MÓJ MÓJ MÓJ --%>
        <h1 class="h3 mb-0 text-gray-800"></h1>


        <a href="/userlist" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Lista użytkowników</a>
    </div>

    <%--    tutaj daje treść --%>

    <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Edytuj użytkownika</h6>
            </div>
            <div class="card-body">


                <form action="/edituser" method="post">

                    <label>
                        Nazwa
                        <input type="text" name="newUserName" value="${user.userName}"/>
                    </label><br>
                    <label>
                        Email
                        <input type="text" name="newEmail" value="${user.email}"/>
                    </label><br>
                    <label>
                        Hasło
                        <input type="password" name="newPassword" placeholder="Podaj nowe hasło"/>
                    </label><br>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <button type="submit" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Zapisz</button>
                </form>



            </div>
        </div>
    </div>


    <div class="row">


    </div>

    <%@ include file="/footer.jsp" %>
