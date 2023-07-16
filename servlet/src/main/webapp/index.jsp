<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
    <style>
        * {
            font-size: 15px;
            text-align: center;
            margin: 15px;
            line-height: 25px;
        }

        h1 {
            font-size: 35px;
            color: purple;
        }
      </style>
</head>
<body>
<h1>Let's count!</h1>

<form action="calculator" method="POST">
    <div>
        <input name="a">
        <input name="b">
        <span> = </span>
        <span>${result}</span>
    </div>
    <div>
        <span>Action: </span>
    </div>
    <div>
        <span>Add</span><input type="radio" name="operation" value="sum"/>
        <span>Subtract</span><input type="radio" name="operation" value="sub"/>
        <span>Multiply</span><input type="radio" name="operation" value="mul"/>
        <span>Divide</span><input type="radio" name="operation" value="div"/>
    </div>
    <div>
        <input type="submit" value="Count">
    </div>
</form>
</body>
</html>