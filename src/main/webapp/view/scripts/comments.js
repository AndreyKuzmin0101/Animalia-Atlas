$('document').ready(function () {
    $.get("/comments" + window.location.search, function (response) {
        $('#comment-list').append(response);
    });

    $('#send-button').on('click', function () {
        $.get('/auth-check', function (response) {
            if (Number(response)) {
                let content = $('#content').val();
                if (content.length > 2000) {
                    alert("Количество символов превышает 2000: " + content.length);
                    return;
                }

                $.post('/comments' + window.location.search, {content : content}).done(function (){
                    $('#content').val("");
                    $('#comment-list').text("");
                    $.get("/comments" + window.location.search, function (response) {
                        $('#comment-list').append(response);
                    });
                });
            } else {
                alert('Войдите в аккаунт, чтобы получить возможность оставлять комментарии.');
            }
        });
    });
});