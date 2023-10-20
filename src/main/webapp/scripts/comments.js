$('document').ready(function () {
    $.get("/comments" + window.location.search, function (response) {
        $('#comment-list').append(response);
    });

    $('#send-button').on('click', function () {
        let content = $('#content').val();
        if (content.length > 2000) {
            alert("Количество символов превышает 2000: " + content.length);
            return;
        }
        $.post('/comments', {content : content}).done(function (){
            $('#content').val("");
            $('#comment-list').text("");
            $.get("/comments" + window.location.search, function (response) {
                $('#comment-list').append(response);
            });
        });
    });
});