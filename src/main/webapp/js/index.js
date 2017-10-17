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
            myNode.removeChild(myNode.firstChild); // ---------- clear div with gallery
        }
        var title = document.getElementById("searchBox").value;
        var data = [title];
        $.post("IndexServlet", {json:data}, function(responseJson) {
            while (myNode.firstChild) {
                myNode.removeChild(myNode.firstChild); // ---------- clear div with gallery
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
                                            "<img id=\"" + String(id) + "\" src=\"" + String(picture) + "\" alt=\"" + String(polishTitle) + "\" onclick=\"addMovie(" + id + ", " + "'"+ String(polishTitle) + "'" + ", " + "'" + type + "'" + ")\">" +
                                        "</a>" +
                                    "</div>" +
                                "</div>");
                rowNew2.appendTo(galery);
            });
            clearfix.appendTo(galery); // ---------- new line for text, without this text will be next to the photo
        });
        $("#galleryDiv").show();
    });
});



// Get the modal
var modal = document.getElementById('myModal');
var modal2 = document.getElementById('myModal2');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function addMovie(id, title, movieOrSeries) {
    var myNode = document.getElementById("modalDiv");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild); // ---------- clear div with modal
    }
    var data = [id, title, movieOrSeries];
    $.post("PrepareModal", {json:data}, function(responseJson) {
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild); // ---------- clear div with modal
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

            var tableRow = $("<div class=\"modalTable\" style=\"overflow-x:auto\">" +
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
                                        "<td><b>Regał:</b></td>" +
                                        "<td>" + "<input autocomplete=\"off\" id=\"columnInput\" class=\"modalInput\" type=\"text\" name=\"column\" required />" + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Rząd:</b></td>" +
                                        "<td>" + "<input autocomplete=\"off\" id=\"rowInput\" class=\"modalInput\" type=\"text\" name=\"row\" required />" + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Gatunek:</b></td>" +
                                        "<td>" +
                                            "<select id=\"genreSelect\" class=\"modalSelect\">" +
                                                "<option>Komedia</option>" +
                                                "<option>Akcja</option>" +
                                                "<option>Sensacja</option>" +
                                            "</select>" +
                                        "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td><b>Kraj pochodzenia:</b></td>" +
                                        "<td>" +
                                        "<select id=\"countrySelect\" class=\"modalSelect\">" +
                                            "<option>Polska</option>" +
                                            "<option>Zagraniczny</option>" +
                                        "</select>" +
                                        "</td>" +
                                    "</tr>" +
                                "</table>" +
                                "<button class=\"button button2\" onclick=\"checkFormInModal()\">Dodaj</button>" +
                            "</div>");

            tableRow.appendTo(modal)
        });
    });
        setTimeout(function(){ loading() }, 1500);
    modal2.style.display = "block";
}

function loading() {
    modal2.style.display = "none";
    modal.style.display = "block";
}

function checkFormInModal() {
    var column = $('#columnInput').val();
    var row = $('#rowInput').val();
    var genre = $('#genreSelect').val();
    var country = $('#countrySelect').val();
    if (isNaN(column) || column < 1 || column > 10) {
        alert("Wartość w polu kolumna jest błędna")
    }
    else if (isNaN(row) || row < 1 || row > 10) {
        alert("Wartość w polu rząd jest błędna")
    }
    else{
        addToDatabase(column, row, genre, country)
    }
}

function addToDatabase(column, row, genre, country) {
    var data = [column, row, genre, country];
    modal.style.display = "none";
    modal2.style.display = "block";
    $.post("AddToDatabaseServlet", {json:data}, function(responseJson) {
        if(responseJson == "Serial dodano do bazy" || responseJson == "Film dodano do bazy"){
            modal2.style.display = "none";
            alert(responseJson);
        }
        else{
            modal2.style.display = "none";
            alert(responseJson);
            modal.style.display = "block";
        }
    });
}
