/*var resp=sparqlRequest("fr.dbpedia.org",
    "SELECT DISTINCT ?value ?year WHERE { <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:genre ?value . <http://fr.dbpedia.org/resource/Aerosmith> dbpedia-owl:activeYearsStartYear ?year}")
*/


var request=loadDoc("fr.dbpedia.org",
    "SELECT DISTINCT ?value ?album WHERE { <http://fr.dbpedia.org/resource/Yesterday> dbpedia-owl:wikiPageWikiLink ?value .filter(exists{?value dbpedia-owl:album ?album})}");
setTimeout(getList(request), 5000);


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

function getList(request){
    //console.log(document.getElementById("demo").innerHTML);

    console.log(document.getElementsByTagName("result"));

    var mypage=document.getElementsByTagName("result");
    console.log("mypage: ",mypage);
    console.log("mypage[0]: ",mypage.prototype);
    console.log("length: ", mypage.length);


    //for(var i=0;i)

    // var inputList = Array.prototype.slice.call(mypage);
    //var length=mypage[0].length;

    /*document.getElementById("demo").innerHTML;*/


}


function loadDoc(siteDomain, query) {
    console.log("debut loaddoc");
    var xhttp = new XMLHttpRequest();

    var request="test";
   // xhttp.onreadystatechange = function () {
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("demo").innerHTML = xhttp.responseText;
            request= xhttp.responseText;
            //var file=jQuery.parseJSON(xhttp.responseText);

        }
    };
    var url = "http://" + siteDomain + "/sparql?query=";
    url += encodeURIComponent(query) + "&format=text";
    xhttp.open("GET", url, true);
    xhttp.getAllResponseHeaders();
    xhttp.send();
    //getList();
    console.log("fin loaddoc");
    return request;
}

