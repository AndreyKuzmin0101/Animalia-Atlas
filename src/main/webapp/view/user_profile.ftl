<#macro title>Пользователи</#macro>
<#macro parametrs>
    <style>

        .animal-icon {
            display: block;
            margin-top: -70px;
            font-size: 60px;
        }

        .container {
            background-color: #2c3338;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            width: 400px;
            text-align: center;
            margin-top: 30px;
            margin-bottom: 30px;
            display: inline-block;
            position: relative;
            left: 20px;
        }

        .avatar {
            display: inline-block;
            background-color: #2c3338;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            padding: 10px;
            border-radius: 20px;
            position: relative;
            text-align: center;
            top: -200px;
        }

        .avatar-container {
            width: 60%;


        }

        .header {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #4caf50;
        }


        .profile-field {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .profile-label {
            flex-basis: 30%;
            text-align: left;
            font-weight: bold;
            color: #4caf50;
        }

        .profile-value {
            flex-basis: 60%;
            text-align: right;
            color: #fff;
        }

        h6 {
            color: #888;
        }
    </style>
</#macro>

<#macro content>

    <div class="avatar-container">
        <div class="avatar" style="padding: 10px">
            <img <#if user.image = "">src="https://res.cloudinary.com/debjgvnym/image/upload/bjgclwsmr3lkkpsjeebg.png" <#else> src="${user.image}" </#if> width="150px">
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
        </div>
    </div>
</#macro>

<#include "base.ftl">