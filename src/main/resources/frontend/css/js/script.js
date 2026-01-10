$(document).ready(function () {
    const createAPI_URL = 'http://localhost:8080/api/users/create';

    $('#createAccountForm').submit(function (event) {
        event.preventDefault();

        // thu nhap du lieu tu form
        const accountData = {
            username: $('#username').val(),
            password: $('#password').val(),
            firstname: $('#firstname').val(),
            lastname: $('#lastname').val(),
            role: $('#role').val()
        };

        $.ajax({
            url: createAPI_URL,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(accountData),
            dataType: 'json',

            success: function (response) {
                window.alert('Account created successfully!');
                $('#createAccountModal').modal('hide');
            },

            error: function (xhr, status, error) {
                window.alert('Error creating account: ' + xhr.responseText);
            }
        });
    });
});
