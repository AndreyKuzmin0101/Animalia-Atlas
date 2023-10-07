<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SETTINGS</title>
    <link rel="stylesheet" type="text/css" href="styles/group1/styles-group1.css" />
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

<#--<form class="container" action="settings" method="post">-->
<#--    <div class="header">Настройки</div>-->
<#--    <span style="color: #fff">Enter new data. If you leave the field empty, this data will not be updated.</span>-->
<#--    <label for="name">Имя:</label>-->
<#--    <input class="settings-input" type="text" name="first_name" id="name">-->

<#--    <label for="lastName">Фамилия:</label>-->
<#--    <input class="settings-input" type="text" name="last_name" id="lastName">-->

<#--    <label for="age">Возраст:</label>-->
<#--    <input class="settings-input" type="number" name="age" id="age">-->

<#--    <label for="email">Email:</label>-->
<#--    <input class="settings-input" type="text" name="email" required id="email">-->

<#--    <label for="login">Логин:</label>-->
<#--    <input class="settings-input" type="text" name="login" required id="login">-->

<#--    <label for="password">Пароль:</label>-->
<#--    <input class="settings-input" type="password" name="password" required id="password">-->

<#--    <input class="button" type="submit" value="Сохранить">-->

<#--    <form action="logout" method="post">-->
<#--        <input class="button" type="submit" value="Отмена">-->
<#--    </form>-->
<#--</form>-->
</body>
</html>