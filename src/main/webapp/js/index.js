function navBarFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

$(document).ready(function() {
    $("#galleryDiv").hide();
    $("#searchBox").keyup(function () {
        var myNode = document.getElementById("galleryDiv");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild); // ---------- Czyszczenie diva zawierajacego galerie
        }
        var title = document.getElementById("searchBox").value;
        var data = [title];
        $.post("IndexServlet", {json:data}, function(responseJson) {
            while (myNode.firstChild) {
                myNode.removeChild(myNode.firstChild); // ---------- Czyszczenie diva zawierajacego galerie
            }
            var galery = $("#galleryDiv");
            var clearfix = $("<div class=\"clearfix\"></div>\n");
            $.each(responseJson, function(key,value) {
                var id = value['filmwebId'];
                var picture = value['image_6'];
                var polishTitle = value['polishTitle'];
                var type = value['type'];
                var rowNew2 = $("<div class=\"responsive\">" +
                                    "<div class=\"gallery\">" +
                                        "<a target=\"_blank\">" +
                                            "<img id=\"" + String(id) + "\" src=\"" + String(picture) + "\" alt=\"" + String(polishTitle) + "\" onclick=\"addMovie(" + id + ", " + "'"+ polishTitle + "'" + ", " + "'" + type + "'" + ")\">" +
                                        "</a>" +
                                    "</div>" +
                                "</div>");
                rowNew2.appendTo(galery);
            });
            clearfix.appendTo(galery); // ---------- Dzieki temu napisy przechodza do nowej lini, a nie powstaja kolo obrazow
        });
        $("#galleryDiv").show();
    });
});



// Get the modal
var modal = document.getElementById('myModal');
var modal2 = document.getElementById('myModal2');

/*
// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}
*/

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function addMovie(id, title, movieOrSeries) {
    var myNode = document.getElementById("modalDiv");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild); // ---------- Czyszczenie diva zawierajacego modal
    }
    var data = [id, title, movieOrSeries];
    $.post("PrepareModal", {json:data}, function(responseJson) {
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild); // ---------- Czyszczenie diva zawierajacego modal
        }
        var modal = $("#modalDiv");
        $.each(responseJson, function(key,value) {
            var id = value['filmwebId'];
            var cast = value['cast'];
            var polishTitle = value['polishTitle'];
            var year = value['year'];
            var image_6 = value['image_6'];
            var type = value['type'];
            var duration = value['duration'];
            var descriptionList = value['descriptionList'];
            var plot = value['plot'];


            var pictureRow = $("<div class=\"responsive\">" +
                                    "<div class=\"gallery\">" +
                                        "<a target=\"_blank\">" +
                                            "<img src=\"" + String(image_6) + "\" width=\"600\" height=\"600\" alt=\"" + String(polishTitle) + "\">" +
                                        "</a>" +
                                        "<div class=\"desc\">" + String(polishTitle) +
                                        "</div>" +
                                    "</div>" +
                                "</div>");

            pictureRow.appendTo(modal);

            var tableRow = $("<div class=\"modalTable\">" +
                                "<table>" +
                                    "<tr>" +
                                        "<td><b>Typ:</b></td>" +
                                        "<td>" + String(type) + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Rok:</b></td>" +
                                        "<td>" + String(year) + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Czas trwania:</b></td>" +
                                        "<td>" + String(duration) + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Obsada:</b></td>" +
                                        "<td>" + String(cast) + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Opis:</b></td>" +
                                        "<td>" + String(plot) + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Kolumna:</b></td>" +
                                        "<td>" + "<input class=\"modalInput\" type=\"text\" name=\"column\" required />" + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Rząd:</b></td>" +
                                        "<td>" + "<input class=\"modalInput\" type=\"text\" name=\"row\" required />" + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Gatunek:</b></td>" +
                                        "<td>" +
                                            "<select class=\"modalSelect\">" +
                                                "<option>Komedia</option>" +
                                                "<option>Akcja</option>" +
                                                "<option>Sensacja</option>" +
                                            "</select>" +
                                        "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Kraj pochodzenia:</b></td>" +
                                        "<td>" +
                                        "<select class=\"modalSelect\">" +
                                            "<option>Polski</option>" +
                                            "<option>Zagraniczny</option>" +
                                        "</select>" +
                                        "</td>" +
                                    "</tr>" +
                                "</table>" +
                                "<button class=\"button button2\">Dodaj</button>" +
                            "</div>");

            /*

            var formRow = $("<div class=\"modalForm\">" +
                                "<form id=\"modalForm\">" +
                                    "<div class=\"field-container\">" +
                                        "<input type=\"text\" class=\"field\" required placeholder=\"First name\"/>" +
                                        "<label class=\"floating-label\">First name</label>" +
                                    "</div>" +
                                    "<div class=\"field-container\">" +
                                        "<input type=\"text\" class=\"field\" required placeholder=\"Last name\"/>" +
                                        "<label class=\"floating-label\">Last name</label>" +
                                    "</div>" +
                                    "<div class=\"field-container\">" +
                                        "<select class=\"field\" placeholder=\"Options\">" +
                                            "<option></option>" +
                                            "<option>option 1</option>" +
                                            "<option>option 2</option>" +
                                        "</select>" +
                                        "<label class=\"floating-label\">Options</label>" +
                                    "</div>" +
                                "</form>" +
                            "</div>");

                            */

            tableRow.appendTo(modal);
            //formRow.appendTo(modal);
            //alert(id + " " + cast + " " + title + " " + polishTitle + " " + year + " " + bigPicture + " " + smallPicture + " " + type);
        });
    });
        setTimeout(function(){ loading() }, 1000);
        //alert("Wrocilo");
    modal2.style.display = "block";
    //modal.style.display = "block";
}

function loading() {
    modal2.style.display = "none";
    modal.style.display = "block";
}
