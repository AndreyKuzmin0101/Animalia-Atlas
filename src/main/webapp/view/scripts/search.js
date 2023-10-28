$('#search-button').on('click', function () {
    let query = $('#search-input').val();
    let category = $('#filter').val();
    window.location.replace('/search?query=' + query + '&category=' + category);
});
