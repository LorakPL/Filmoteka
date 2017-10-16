function showAllMovies(servlet) {
    $("#tablediv").hide();
    $.post(servlet, function(responseJson) {
        if(responseJson == "error"){
            alert("Problem połączenia z bazą danych");
        }
        else{
            $("#movieTable").find("tr:gt(0)").remove();
            var table1 = $("#movieTable");
            var tablebody = $(
                "<thead>" +
                "<tr>" +
                "<th scope=\"col\" onclick=\"sortTable(0)\">Tytuł</th>" +
                "<th scope=\"col\">Zdjęcie</th>" +
                "<th scope=\"col\" onclick=\"sortTable(2)\">Rok</th>" +
                "<th scope=\"col\" onclick=\"sortTable(3)\">Czas trwania</th>" +
                "<th scope=\"col\">Obsada</th>" +
                "<th scope=\"col\" onclick=\"sortTable(5)\">Kolumna</th>" +
                "<th scope=\"col\" onclick=\"sortTable(6)\">Rząd</th>" +
                "<th scope=\"col\">Opis</th>" +
                "<th scope=\"col\" onclick=\"sortTable(8)\">Gatunek</th>" +
                "</tr>" +
                "</thead>"
            );
            tablebody.appendTo(table1);
            var tbody = $("<tbody>");
            tbody.appendTo(table1);
            $.each(responseJson, function(key,value) {
                alert(value['image_6']);
                var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                var polishTitle = value['polishTitle'];
                var bigImage = value['image_6'];
                rowNew.children().eq(0).text(value['polishTitle']);
                rowNew.children().eq(1).html("<img class=\"style_prevu_kit\" src=\"" + String(bigImage) + "\" alt=\"" + String(polishTitle) + "\">");
                rowNew.children().eq(2).text(value['year']);
                rowNew.children().eq(3).text(value['duration']);
                rowNew.children().eq(4).text(value['cast']);
                rowNew.children().eq(5).text(value['column']);
                rowNew.children().eq(6).text(value['row']);
                rowNew.children().eq(7).text(value['plot']);
                rowNew.children().eq(8).text(value['genre']);
                rowNew.appendTo(table1);
            });
            tbody.appendTo(table1);
        }
    });
    $("#tablediv").show();
}

function searchInTable() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("movieTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("movieTable");
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
    no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /*Loop through all table rows (except the
        first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
            one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
            based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch= true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch= true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
            and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount ++;
        } else {
            /*If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}