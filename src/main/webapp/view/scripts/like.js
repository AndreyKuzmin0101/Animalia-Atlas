let resp;
$.get('/authorization', function (response) {
    if (Number(response)) {
        $.get('/like', function (response2) {
            console.log(response2);
            if (Number(response2)) {
                $('#like-button').addClass('like');
            }
        });
    }
    resp = response;
});

$('#like-button').on('click', function () {
    if (Number(resp)) {
        let likes = Number($('#like-count').html());

        $.post('/like', function (response) {
            if (Number(response) > likes) {
                $('#like-button').addClass('like');
            } else {
                $('#like-button').removeClass('like');
            }
            $('#like-count').html(response);
        });
    } else {
        alert('Войдите в аккаунт, чтобы лайкать статьи.');
    }


});