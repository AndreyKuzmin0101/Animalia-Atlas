<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <link rel="stylesheet" type="text/css" href="styles/styles-group1.css" />
</head>
<body>
<div class="container">
    <form style="margin-bottom: 0" action="settings" method="post">
        <div class="header">Настройки</div>
        <span style="color: #fff">Введите новые данные. Если вы оставите поле пустым, то эти данные не будут обновлены.</span>
        <label for="name">Имя [${user.firstName}]:</label>
        <input class="settings-input" type="text" name="first_name" id="name">

        <label for="lastName">Фамилия [${user.lastName}]:</label>
        <input class="settings-input" type="text" name="last_name" id="lastName">

        <label for="age">Возраст [${user.age}]:</label>
        <input class="settings-input" type="number" name="age" id="age">

        <label for="email">Email [${user.email}]:</label>
        <input class="settings-input" type="text" name="email" id="email">

        <label for="login">Логин [${user.login}]:</label>
        <input class="settings-input" type="text" name="login" id="login">

        <label for="password">Пароль:</label>
        <input class="settings-input" type="password" name="password" id="password">

        <input class="button" type="submit" value="Сохранить">
    </form>

    <form action="profile" method="get">
        <input class="button" type="submit" value="Отмена">
    </form>
</div>
</body>
</html>