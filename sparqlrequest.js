sparqlRequest("fr.dbpedia.org",
    "SELECT DISTINCT ?value ?year WHERE { <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:genre ?value . <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:activeYearsStartYear ?year}")

function sparqlRequest(siteDomain, query) {
    var url = "http://" + siteDomain + "/sparql?query=";
    url += encodeURIComponent(query);
    console.log("url: ", url);
    $.ajax({
        dataType: 'html',
        url: url,
        success: function (data) {
            alert('success: ' + data.results.bindings.length + ' results');
            console.log(data);
        }
    });
}