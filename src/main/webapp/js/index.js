$(document).ready(function() {
    $("#tablediv").hide();
    //$("#showTable").click(function(event){
    $("#searchBox").keyup(function () {
        var title = document.getElementById("searchBox").value;
        var data = [title];
        $.post("IndexServlet", {json:data}, function(responseJson) {
            $("#movieTable").find("tr:gt(0)").remove();
            var table1 = $("#movieTable");
            var tbody = $("<tbody>");
            tbody.appendTo(table1);
            $.each(responseJson, function(key,value) {
                var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                var smallLink = value['smallPicture'];
                var bigLink = value['bigPicture'];
                //rowNew.children().eq(0).html("<img src=\"http://1.fwcdn.pl/po/25/73/712573/7756329.4.jpg\" alt=\"\">");
                //rowNew.children().eq(0).html("<img src=\"" + "http://1.fwcdn.pl/po/25/73/712573/7756329.4.jpg" + "\" alt=\"\">");
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['originalTitle']);
                rowNew.children().eq(2).text(value['polishTitle']);
                //rowNew.children().eq(3).text(value['url']);
                //rowNew.children().eq(3).html("<img src=\"" + String(link) + "\" alt=\"\">");
                rowNew.children().eq(3).html("<a class=\"thumb\" href=\"#\"><img src=\"" + String(smallLink) + "\" alt=\"\"><span><img src=\"" + String(bigLink) + "\" alt=\"\"></span></a>\n");
                rowNew.children().eq(4).text(value['productionCountry']);
                rowNew.children().eq(5).text(value['genre']);
                rowNew.children().eq(6).text(value['duration']);
                rowNew.children().eq(7).text(value['productionYear']);
                rowNew.appendTo(table1);
            });
            tbody.appendTo(table1);
        });
        $("#tablediv").show();
    });
});