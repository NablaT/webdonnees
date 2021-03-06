package hello;

import hello.database.CleanRdf;
import hello.jenaEngine.Ontologie;
import hello.jenaEngine.callDBPedia;
import hello.youtubeEngine.YoutubeQuerry;
import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.iri.impl.Main;
import org.apache.jena.ontology.*;
import org.apache.jena.ontology.impl.OntModelImpl;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.shared.Command;
import org.apache.jena.shared.Lock;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
        //On initialise la classe qui gere le contenu du rdf
        CleanRdf rdfContent = new CleanRdf();
        rdfContent.getBackRdfContent("data.rdf", true);

        //System.out.println("groupeName: "+rdfContent.getSaveArtistWithQuoteGroup());
        rdfContent.cleanElements();

        //Initialisation de la liste des chansons
        ArrayList<String> songList = rdfContent.getSongElements();

        //On cree le couple [nom du groupe, chanson]
        List<List<String>> songBandList = new ArrayList<>();
        for (int i = 0; i < songList.size(); i++) {
            ArrayList<String> tmpArray = new ArrayList<String>();
            tmpArray.add("beatles");
            tmpArray.add(songList.get(i));
            songBandList.add(tmpArray);
        }

        /////////////// ICI TU AS LA LISTE DES ARTISTES //////////////////////////

        ArrayList<String> listOfArtists = new ArrayList<String>();
        listOfArtists = rdfContent.getBandElements();


        //System.out.println("song band list: "+songBandList);
        //On met la list des chansons associées a leurs artistes ex: [beatles,yesterday]
        Map<String, Long> map = new YoutubeQuerry().closeSong(songBandList);

        ArrayList<String> songAlbum = new ArrayList<>();
        for (String s : map.keySet()) {
            System.out.println("La chanson " + s + " a " + map.get(s) + " vues");
            songAlbum.add(s);
        }


        Map<String, Long> map2 = new YoutubeQuerry().closeSongArtist(listOfArtists);

        ArrayList<String> list = new ArrayList<>();
        for (String s : map2.keySet()) {
            System.out.println("L'artiste " + s + " a " + map2.get(s) + " vues");
            list.add(s);
        }

        //List is the artist list we get back
        list = rdfContent.refactorName(list);
        rdfContent.deleteRecreateFile("data.rdf");
        for (int i = 0; i < list.size(); i++) {
            try {
                Model m = callDBPedia.run(list.get(i), "songOfArtist");
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data.rdf", true)));
                m.write(pw, "RDF/XML");
            } catch (Exception e) {
                System.out.println("plop");
            }
        }


        //rdfContent.deleteRecreateFile("data.rdf");
        rdfContent.getBackRdfContent("data.rdf", false);
        ArrayList<ArrayList<String>> globalList = rdfContent.getListOfSongOfDifferentArtists();
        //System.out.println("global list: "+ globalList);

        //On cree le couple [nom du groupe, chanson]
        //On veut récuperer ces couples pour toutes les chansons des 5 artistes
        //System.out.println("list of artiste before: "+ list);
        list=rdfContent.removeUnderscore(list);
        List<List<List<String>>> globalSongBandList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<List<String>> songBandList2 = new ArrayList<>();
            for (int j = 0; j < globalList.get(i).size(); j++) {
                ArrayList<String> tmpArray = new ArrayList<String>();
                tmpArray.add(list.get(i));
                tmpArray.add(globalList.get(i).get(j));
                songBandList2.add(tmpArray);
            }
            globalSongBandList.add(songBandList2);
            //System.out.println("globalsongbandlist: " + globalSongBandList.get(i));
        }
        Map<String, Long> map3;

       // ArrayList<Map> allArtistAndSong=new ArrayList<>();
        Map<String,List<String>> allArtistAndSong= new HashMap<>();
        for (int i = 0; i < globalSongBandList.size(); i++) {
            //System.out.println("je rentre une seule fois");
            if(globalSongBandList.get(i).size()>0){
                //System.out.println("je te donne: "+ globalSongBandList.get(i));
                map3 = new YoutubeQuerry().closeSong(globalSongBandList.get(i));
                List<String> currentSong=new ArrayList<>();
                for (String s : map3.keySet()) {
                    System.out.println("La chanson " + s + " a " + map3.get(s) + " vues");
                    currentSong.add(s);
                }
                //Map<String,ArrayList<String>> currentMap= new HashMap<>();
                allArtistAndSong.put(list.get(i),currentSong);
                //allArtistAndSong.add(currentMap);
            }
            else{
                System.out.println("The array is empty");
            }

        }

        System.out.println("--------------------------------------------------");
        System.out.println("songAlbum: "+songAlbum);
        System.out.println("allArtistAndSong: "+allArtistAndSong);
        String song=rdfContent.getSong();
        System.out.println("song: "+song);
        String artist=rdfContent.getBand();
        System.out.println("artist: "+artist);
        int id=rdfContent.getIdUser();
        System.out.println("user id: "+id);

        Ontologie OntologieClass = new Ontologie();
        OntModel ontologie = OntologieClass.load();


        OntologieClass.add(ontologie, id, song, artist, songAlbum, allArtistAndSong);

        OntologieClass.write(ontologie);


    }
}
