var resp=sparqlRequest("fr.dbpedia.org",
    "SELECT DISTINCT ?value ?year WHERE { <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:genre ?value . <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:activeYearsStartYear ?year}")

loadDoc("fr.dbpedia.org",
    "SELECT DISTINCT ?value ?year WHERE { <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:genre ?value . <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:activeYearsStartYear ?year}")

function sparqlRequest(siteDomain, query) {
    var url = "http://" + siteDomain + "/sparql?query=";
    url += encodeURIComponent(query) + "&format=text";
    console.log("url: ", url);

    var outputs;
    $.get(url, function (data) {
        console.log(data);
    })

    var resp=$.ajax({
        dataType: 'text',
        url: url,
        type: 'GET',


        success: function (data) {
            console.log("Data: ",data);
            return data;
        }
    });


}

function getList(){
    alert(document.getElementById("demo").getAttributeNode());
    document.getElementById("demo").innerHTML;
}


function loadDoc(siteDomain, query) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("demo").innerHTML = xhttp.responseText;
            //var file=jQuery.parseJSON(xhttp.responseText);

        }
    };
    var url = "http://" + siteDomain + "/sparql?query=";
    url += encodeURIComponent(query) + "&format=text";
    xhttp.open("GET", url, true);
    xhttp.getAllResponseHeaders();
    xhttp.send();
}