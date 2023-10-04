<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SETTINGS</title>
</head>
<body>
<form action="/settings" method="post">
    <h2>Enter new data. If you leave the field empty, this data will not be updated.</h2>
    Name:
    <input type="text" name="first_name"/>
    (${user.firstName})
    <br>
    Last name:
    <input type="text" name="last_name"/>
    (${user.lastName})
    <br>
    Age:
    <input type="number" name="age"/>
    (${user.age})
    <br>
    Email:
    <input type="text" name="email"/>
    (${user.email})
    <br>
    Login:
    <input type="text" name="login"/>
    (${user.login})
    <br>
    Password:
    <input type="password" name="password"/>
    <br>
    <input type="submit" value="CHANGE">
</form>
</body>
</html>