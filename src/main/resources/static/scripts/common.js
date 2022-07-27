function getExperiencedTester(countries, deviceIds) {

    let apiUrl = '/api/v1/testers';

    if (countries !== undefined || deviceIds !== undefined) {
        apiUrl = '/api/v1/testers?countries=' + countries + '&deviceIds=' + deviceIds;
    }

    $.ajax({
        url: apiUrl,
        type: 'GET',
        context: document.body,
    }).done(function (json) {
        console.log(json);
    });

}