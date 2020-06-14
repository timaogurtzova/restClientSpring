$(document).ready(getAllUsers());

// Для отображения списка пользователей в таблице
function getAllUsers() {
    $("#usersTable").empty();
    $.ajax({
        type: 'POST',
        url: '/rest/getAllUsers',
        timeout: 3000,
        success: function (data) {
            //console.log(data);
            $.each(data, function (i, user) {
                $("#usersTable").append($('<tr>').append(
                    $('<td>').append($('<span>')).text(user.id),
                    $('<td>').append($('<span>')).text(user.name),
                    $('<td>').append($('<span>')).text(user.password),
                    $('<td>').append($('<span>')).text(user.age),
                    $('<td>').append($('<span>')).text(user.city),
                    $('<td>').append($('<span>')).text(user.roles),
                    $('<td>').append($('<button>').text("Update").attr({
                        "type": "button",
                        "class": "btn btn-info",
                        "data-toggle": "modal",
                        "data-target": "#updateModal",
                        "id" : "updateButton",
                    })
                        .data("user", user)),
                    $('<td>').append($('<button>').text("Delete").attr({
                        "type": "button",
                        "class": "btn btn-danger",
                        "data-toggle": "modal",
                        "data-target": "#deleteModal",
                        "id" : "deleteButton",
                    })
                        .data("user", user))
                    )
                );
            });
        }
    });
}

// Для добавления пользователя
$('#buttonAddNewUser').click(function () {
    $('#usersTable').trigger('click');
    let userNew = {
        name: $(".formAddUser #nameNew").val(),
        age: $(".formAddUser #ageNew").val(),
        password: $(".formAddUser #passwordNew").val(),
        city: $(".formAddUser #cityNew").val(),
        roles: $(".formAddUser #rolesNew").val()
    };
    $.ajax({
        type: 'POST',
        url: '/rest/createUser',
        contentType: "application/json",
        dataType: 'text',
        data: JSON.stringify(userNew),
        success: function () {
            $('.formAddUser')[0].reset();
            alert("User was added in database");
            getAllUsers();
        },
        error:function () {
            alert("Fail");
        }
    })
});

//Для редактирования через модальное окно отображаем данные пользователя
$(document).on("click", "#updateButton", function () {
    let user = $(this).data('user');

    $('#idInput').val(user.id);
    $('#nameInput').val(user.name);
    $('#passwordInput').val(user.password);
    $('#ageInput').val(user.age);
    $('#cityInput').val(user.city);
    $('#roleInput').val(user.roles);

});

//Запрос на обновление
$(document).on("click", "#buttonInModalUpdate", function () {
    let formData = {
        id : $(".formEditUser #idInput").val(),
        name: $(".formEditUser #nameInput").val(),
        age: $(".formEditUser #ageInput").val(),
        password: $(".formEditUser #passwordInput").val(),
        city: $(".formEditUser #cityInput").val(),
        roles: $(".formEditUser #roleInput").val(),
    };
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '/rest/updateUser',
        dataType: 'text',
        data: JSON.stringify(formData),
        success: function () {
            getAllUsers();
        }
    });
    $('#buttonInModalUpdateClose').trigger('click');
});

//Для удаления пользователя через модальное окно
$(document).on("click", "#deleteButton", function () {
    let user = $(this).data('user');
        $.ajax({
            type: 'DELETE',
            url: '/rest/deleteUser/'+ user.id,
            success: function () {
                getAllUsers();
            }
        });
})


