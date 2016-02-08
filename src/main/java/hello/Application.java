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
        System.out.println("test");
        //On initialise la classe qui gere le contenu du rdf
        CleanRdf rdfContent = new CleanRdf();
        rdfContent.getBackRdfContent("data.rdf", true);

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


        System.out.println("song band list: "+songBandList);
        //On met la list des chansons associées a leurs artistes ex: [beatles,yesterday]
        Map<String, Long> map = new YoutubeQuerry().closeSong(songBandList);

        for (String s : map.keySet()) {
            System.out.println("La chanson " + s + " a " + map.get(s) + " vues");
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
        /*list.add(currentArtist);
        list.add("ACDC");
        list.add("Scorpions");
        list.add("Aerosmith");
        list.add("Nirvana");*/
        for (int i = 0; i < list.size(); i++) {
            try {
                Model m = callDBPedia.run(list.get(i), "songOfArtist");
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data.rdf", true)));
                m.write(pw, "RDF/XML");
            } catch (Exception e) {
                System.out.println("plop");
            }
        }


        Ontologie OntologieClass = new Ontologie();
        OntModel ontologie = OntologieClass.load();

        List<String> listSong = new ArrayList<>();
        listSong.add("Help!");
        listSong.add("I Need You");

        List<String> listSongArtist = new ArrayList<>();
        listSongArtist.add("Smell like teen's spirit");
        listSongArtist.add("Lithium");

        List<String> listSongArtist2 = new ArrayList<>();
        listSongArtist2.add("Hello");
        listSongArtist2.add("Rolling in the deep");

        Map<String, List<String>> mapArtist = new HashMap<>();
        mapArtist.put("Nirvana", listSongArtist);
        mapArtist.put("Adele", listSongArtist2);

        OntologieClass.add(ontologie, 1, "Yesterday", "Beatles", listSong, mapArtist);

        OntologieClass.write(ontologie);


        //rdfContent.deleteRecreateFile("data.rdf");
        rdfContent.getBackRdfContent("data.rdf", false);
        ArrayList<ArrayList<String>> globalList = rdfContent.getListOfSongOfDifferentArtists();
        //System.out.println("global list: "+ globalList);

        //On cree le couple [nom du groupe, chanson]
        //On veut récuperer ces couples pour toutes les chansons des 5 artistes
        System.out.println("list of artiste before: "+ list);
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
            System.out.println("globalsongbandlist: " + globalSongBandList.get(i));
        }
        Map<String, Long> map3;

        for (int i = 0; i < globalSongBandList.size(); i++) {
            System.out.println("je rentre une seule fois");
            if(globalSongBandList.get(i).size()>0){
                System.out.println("je te donne: "+ globalSongBandList.get(i));
                map3 = new YoutubeQuerry().closeSong(globalSongBandList.get(i));

                for (String s : map3.keySet()) {
                    System.out.println("La chanson " + s + " a " + map3.get(s) + " vues");
                }
            }
            else{
                System.out.println("The array is empty");
            }

        }


    }
}
