<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        Вход в систему
    </title>
    <style>
        * {
            font-size: 15px;
            text-align: center;
            margin: 15px;
            line-height: 25px;
        }

        h1 {
            font-size: 35px;
            color: gray;
        }
    </style>
</head>
<body>
<h1>Введите данные:</h1>
<form method="get" action="login" accept-charset="UTF-8">
    Логин:<input type="text" name="login"/></br>
    Пароль:<input type="text" name="password"/></br>
    <input type="submit" value="Войти">
</form>
</body>
</html>