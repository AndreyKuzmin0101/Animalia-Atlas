$('document').ready(function () {
    $.get("/comments" + window.location.search, function (response) {
        $('#comment-list').append(response);
    });

    $('#send-button').on('click', function () {
        let content = $('#content').val();
        $.post('/comments', {content : content}).done(function (){
            $('#content').val("");
            $('#comment-list').text("");
            $.get("/comments" + window.location.search, function (response) {
                $('#comment-list').append(response);
            });
        });
    });
});