function showAllMovies() {
    $("#tablediv").hide();
    $.post("AllMoviesServlet", function(responseJson) {
        $("#movieTable").find("tr:gt(0)").remove();
        var table1 = $("#movieTable");
        var tablebody = $(
            "<thead>" +
                "<tr>" +
                    "<th scope=\"col\">Zdjęcie</th>" +
                    "<th scope=\"col\">Tytuł</th>" +
                    "<th scope=\"col\">Rok</th>" +
                    "<th scope=\"col\">Czas trwania</th>" +
                    "<th scope=\"col\">Obsada</th>" +
                "</tr>" +
            "</thead>"
        );
        tablebody.appendTo(table1);
        var tbody = $("<tbody>");
        tbody.appendTo(table1);
        $.each(responseJson, function(key,value) {
            var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
            var polishTitle = value['polishTitle'];
            var bigImage = value['image_6'];
            var smallImage = value['image_4'];
            /*
            var smallLink = value['smallPicture'];
            var bigLink = value['bigPicture'];
            rowNew.children().eq(0).text(value['id']);
            rowNew.children().eq(1).text(value['originalTitle']);
            rowNew.children().eq(2).text(value['polishTitle']);
            rowNew.children().eq(3).html("<a class=\"thumb\" href=\"#\"><img src=\"" + String(smallLink) + "\" alt=\"\"><span><img src=\"" + String(bigLink) + "\" alt=\"\"></span></a>\n");
            rowNew.children().eq(4).text(value['productionCountry']);
            rowNew.children().eq(5).text(value['genre']);
            rowNew.children().eq(6).text(value['duration']);
            rowNew.children().eq(7).text(value['productionYear']);
            */

            rowNew.children().eq(0).html("<a class=\"thumb\" href=\"#\"><img src=\"" + String(smallImage) + "\" alt=\"" + String(polishTitle) + "\"><span><img src=\"" + String(bigImage) + "\" alt=\"" + String(polishTitle) + "\"></span></a>");

            //rowNew.children().eq(0).text(value['image_4']);
            rowNew.children().eq(1).text(value['polishTitle']);
            rowNew.children().eq(2).text(value['year']);
            rowNew.children().eq(3).text(value['duration']);
            rowNew.children().eq(4).text(value['cast']);
            rowNew.appendTo(table1);
        });
        tbody.appendTo(table1);
    });
    $("#tablediv").show();
}