<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>
        Вход
    </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <c:if test="${not empty message}">
        <p style="text-align: center" class="text-danger">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="text-align: center" class="text-danger">${error}</p>
    </c:if>
    <div class="row">
        <div class="col-md-3 offset-md-4">
            <h2>Вход</h2>
            <p>Заполните поля для входа</p>
            <form method="post" class="needs-validation" novalidate>
                <input type="hidden" name="command" value="login">
                <div class="form-group">
                    <%--@declare id="email"--%><label for="email">Имя пользователя:</label>
                    <input type="text" class="form-control" id="login" placeholder="Введите email" name="email"
                           required>
                    <div class="invalid-feedback">Имя пользователя должно быть введено!</div>
                </div>
                <div class="form-group">
                    <label for="password">Пароль:</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password"
                           name="password" required>
                    <div class="invalid-feedback">Пароль должен быть введен!</div>
                </div>
                <button id="loginBtn" class="btn btn-primary">Войти</button>
                <a href="${contextPath}/shop?command=registration">
                    <button class="btn btn-danger" type="button">Регистрация</button>
                </a>
            </form>
        </div>
    </div>
</div>
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
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
    document.getElementById('loginBtn').disabled = true;
    document.getElementById('login').addEventListener('keyup', e => {
        document.getElementById('loginBtn').disabled = e.target.value === "";
    });
    document.getElementById('password').addEventListener('keyup', e => {
        document.getElementById('loginBtn').disabled = e.target.value === "";
    });
</script>
</body>
</html>