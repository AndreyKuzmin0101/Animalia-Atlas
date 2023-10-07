<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="styles/group1/styles-group1.css" />
</head>
<body>
<div class="container">
    <div class="animal-icon">&#129434;</div>
    <div class="header">Профиль пользователя</div>

    <div class="profile-field">
        <div class="profile-label">Логин:</div>
        <div class="profile-value">${user.login}</div>
    </div>

    <div class="profile-field">
        <div class="profile-label">Имя:</div>
        <div class="profile-value">${user.firstName}</div>
    </div>

    <div class="profile-field">
        <div class="profile-label">Фамилия:</div>
        <div class="profile-value">${user.lastName}</div>
    </div>

    <div class="profile-field">
        <div class="profile-label">Возраст:</div>
        <div class="profile-value">${user.age}</div>
    </div>

    <div class="profile-field">
        <div class="profile-label">Email:</div>
        <div class="profile-value">${user.email}</div>
    </div>

    <div class="profile-buttons">
        <form action="profile" method="post"><input type="submit" class="button" value="Настройки"/></form>
        <form action="logout" method="post"><input type="submit" class="button" value="Выход"/></form>
    </div>
</div>
</body>
</html>
