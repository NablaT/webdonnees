package hello;

import hello.database.CleanRdf;
import hello.jenaEngine.callDBPedia;
import hello.youtubeEngine.YoutubeQuerry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.jena.rdf.model.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);

        //On initialise la classe qui gere le contenu du rdf
        CleanRdf rdfContent=new CleanRdf();
        rdfContent.getBackRdfContent("data.rdf", true);
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
        System.out.println("couple: "+songBandList);

        /////////////// ICI TU AS LA LISTE DES ARTISTES //////////////////////////
        ArrayList<String> listOfArtists=new ArrayList<String>();
        listOfArtists=rdfContent.getBandElements();
        System.out.println("List of artists: "+ listOfArtists);

        //On met la list des chansons associées a leurs artistes ex: [beatles,yesterday]
        Map<String, Long> map = new YoutubeQuerry().closeSong(songBandList);

        for(String s : map.keySet()) {
            System.out.println("La chanson " + s + " a " + map.get(s) + " vues");
        }


        //rdfContent.initialiseListOfIndexes();
        /*List<String> s6 = new ArrayList<>();
        s6.add("beatles");
        s6.add("adele");
        s6.add("nirvana");
        s6.add("rihanna");
        s6.add("MAGIC!");
        Map<String, Long> map2 = new YoutubeQuerry().closeSongArtist(listOfArtists);

        for(String s : map2.keySet()) {
            System.out.println("L'artiste " + s + " a " + map2.get(s) + " vues");
        }*/

        rdfContent.deleteRecreateFile("data.rdf");
        String currentArtist="U2";
        try{
            Model m=  callDBPedia.run(currentArtist,"songOfArtist");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data.rdf",true)));
            m.write(pw,"RDF/XML");
        }catch (Exception e){
            System.out.println("plop");
        }

        System.out.println("START SECOND PART");
        //rdfContent.deleteRecreateFile("data.rdf");
        rdfContent.getBackRdfContent("data.rdf",false);
        ArrayList<ArrayList<String>> globalList=rdfContent.getListOfSongOfDifferentArtists();
        System.out.println("global list: "+ globalList);

    }
}
