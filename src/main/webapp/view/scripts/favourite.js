$('#favourite').on('click', function () {
    $.get('/auth-check', function (response) {
        if (Number(response)) {
            window.location.replace("/favourite-articles");
        } else {
            alert("Войдите в аккаунт, чтобы получить возможность добавлять и просматривать понравившиеся статьи");
        }
    });
});

