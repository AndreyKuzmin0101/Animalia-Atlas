$.get('/authorization', function (response) {
    if (Number(response)) {
        $('#header-buttons').append('<button id="profile-button" class="button header-button" type="button">Профиль</button>' +
            '<button id="logout-button" class="button header-button" type="button" style="background-color: red">Выйти</button>'
        );

        $('#profile-button').on('click', function () {
            window.location.replace('/profile');
        });
        $('#logout-button').on('click', function () {
            window.location.replace('/logout');
        });
    } else {
        $('#header-buttons').append('<button id="login-button" class="button header-button" type="button">Войти</button>' +
            '<button id="register-button" class="button header-button" type="button">Регистрация</button>');
        $('#login-button').on('click', function () {
            window.location.replace('/login');
        });
        $('#register-button').on('click', function () {
            window.location.replace('/register');
        });
    }
});