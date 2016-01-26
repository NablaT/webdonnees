/**
 * Created by jonathan on 19/01/16.
 */

var KEY_API = "AIzaSyDbwRQvCvTn8R2GBrwmMiWWoAb5hC_Zv9k";

youtubeRequest("yesterday", "beatles");


function youtubeSearch(title, artist, callback) {
    //Permet de récupérer les id à partir d'une chanson
    var query = "https://www.googleapis.com/youtube/v3/search?part=snippet&q="+title+"."+artist+"&type=video&order=viewCount&key="+KEY_API;
    console.log("url: ", query);
    id = null;
    $.ajax({
        dataType: "jsonp",
        url: query,
        success: function (data) {
            id = data.items[0].id.videoId;
            console.log(data.items[0].id.videoId);
            console.log("id :", id);
            return callback(id);
        }
    });
}

function youtubeRequest(title, artist) {
    youtubeSearch(title, artist, function(id) {
        var query2 = "https://www.googleapis.com/youtube/v3/videos?id="+id+"&key="+KEY_API+"&fields=items(id,snippet(channelId,title,categoryId),statistics)&part=snippet,statistics";
        console.log("url: ", query2);
        $.ajax({
            dataType: "jsonp",
            url: query2,
            success: function (data) {
                alert("Nombre de vue de "+title+" par "+artist+" : "+data.items[0].statistics.viewCount);
            }
        });
    });
}