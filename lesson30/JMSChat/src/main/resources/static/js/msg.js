function getMessages(user) {
    $.ajax({
        method: "GET",
        url: "/msg/get",
        contentType: "application/json; charset=utf-8",
        data: {
            'userName': user
        },

        success: function (result) {
            console.log("receive!");
            if (result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].startsWith(user)) {
                        $("#messages").append("<blockquote class=\"blockquote text-right\">" + result[i] + "</blockquote>");
                    } else {
                        $("#messages").append("<blockquote class=\"blockquote\">" + result[i] + "</blockquote>");
                    }
                }
            }
        }
    });
}

function sendMessage(user, msg) {
    console.log("sending " + user + " " + msg);
    $.ajax({
        method: "GET",
        url: "/msg/send",
        contentType: "application/json; charset=utf-8",
        data: {
            'userName': user,
            'message': msg
        },

        success: function (result) {
            //clear message input
            $("#message").val("");
        }
    });
}