<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="/view/styles/styles-group1.css" />
</head>
<body>
<div class="avatar-container">
    <div class="avatar" style="padding: 10px">
        <img <#if user.image = "">src="https://res.cloudinary.com/debjgvnym/image/upload/bjgclwsmr3lkkpsjeebg.png" <#else> src="${user.image}" </#if> width="150px">
        <form action="upload" method="post" enctype="multipart/form-data">
            <label for="file">Аватарка</label>
            <input type="file" name="image" id="file" accept=".png, .jpg, .jpeg">
            <br>
            <input type="submit" value="Загрузить">
        </form>
    </div>

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
</div>


</body>
</html>
