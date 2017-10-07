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
                var id = value['id'];
                var picture = value['smallPicture'];
                var rowNew2 = $("<div class=\"responsive\">" +
                                    "<div class=\"gallery\">" +
                                        "<a target=\"_blank\">" +
                                            "<img id=\"" + String(id) + "\" src=\"" + String(picture) + "\" alt=\"" + String(id) + "\" onclick=\"addMovie(" + String(id) + ")\">" +
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

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function addMovie(id) {
    var myNode = document.getElementById("modalDiv");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild); // ---------- Czyszczenie diva zawierajacego modal
    }
    var data = [id];
    $.post("PrepareModal", {json:data}, function(responseJson) {
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild); // ---------- Czyszczenie diva zawierajacego modal
        }
        var modal = $("#modalDiv");
        $.each(responseJson, function(key,value) {
            var id = value['id'];
            var cast = value['cast'];
            var title = value['title'];
            var polishTitle = value['polishTitle'];
            var year = value['year'];
            var bigPicture = value['bigPicture'];
            var smallPicture = value['smallPicture'];
            var type = value['type'];



            var pictureRow = $("<div class=\"responsive\">" +
                                    "<div class=\"gallery\">" +
                                        "<a target=\"_blank\">" +
                                            "<img src=\"" + String(bigPicture) + "\" width=\"600\" height=\"600\" alt=\"" + String(polishTitle) + "\">" +
                                        "</a>" +
                                        "<div class=\"desc\">" + String(polishTitle) + "</div>" +
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
                                "</table>" +
                            "</div>");


            tableRow.appendTo(modal);
            //alert(id + " " + cast + " " + title + " " + polishTitle + " " + year + " " + bigPicture + " " + smallPicture + " " + type);
        });
    });
    modal.style.display = "block";
}