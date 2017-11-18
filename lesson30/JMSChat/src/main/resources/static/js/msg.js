function getMessages(user) {
    $.ajax({
        method: "GET",
        url: "/msg/get",
        contentType: "application/json; charset=utf-8",
        data: {
            userName: user
        },

        success: function ( result ) {
            console.log("receive!")
            if (result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    $( ".container" ).append("<p>" + result[i] + "</p>");
                }
            }
        }
    });
}