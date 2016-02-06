package hello;

import hello.database.CleanRdf;
import hello.youtubeEngine.YoutubeQuerry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


 /*       SpringApplication.run(Application.class, args);

        //On initialise la classe qui gere le contenu du rdf
        CleanRdf rdfContent=new CleanRdf();
        rdfContent.getBackRdfContent("data.rdf");
       // System.out.println(rdfContent.getElements());

        rdfContent.cleanElements();

        //Initialisation de la liste des chansons
        ArrayList<String> songList=rdfContent.getSongElements();

        //On cree le couple [nom du groupe, chanson]
        List<List<String>> songBandList=new ArrayList<>();
        for(int i=0;i<songList.size();i++){
            ArrayList<String> tmpArray=new ArrayList<String>();
            tmpArray.add("beatles");
            tmpArray.add(songList.get(i));
            songBandList.add(tmpArray);
        }

        /////////////// ICI TU AS LA LISTE DES ARTISTES //////////////////////////
        ArrayList<String> listOfArtists=new ArrayList<String>();
        listOfArtists=rdfContent.getBandElements();
        System.out.println("List of artists: "+ listOfArtists);

        //On met la list des chansons associées a leurs artistes ex: [beatles,yesterday]
        Map<String, Long> map = new YoutubeQuerry().closeSong(songBandList);

        for(String s : map.keySet()) {
            System.out.println("La chanson " + s + " a " + map.get(s) + " vues");
        }*/

        List<String> s6 = new ArrayList<>();
        s6.add("beatles");
        s6.add("adele");
        s6.add("nirvana");
        s6.add("rihanna");
        s6.add("MAGIC!");
        Map<String, Long> map2 = new YoutubeQuerry().closeSongArtist(s6);

        for(String s : map2.keySet()) {
            System.out.println("L'artiste " + s + " a " + map2.get(s) + " vues");
        }

    }
}
