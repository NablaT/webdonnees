package hello;

import hello.jenaEngine.Ontologie;
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
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


        /*SpringApplication.run(Application.class, args);

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
        List<String> listOfArtists;
        listOfArtists = rdfContent.getBandElements();
        System.out.println("List of artists: "+ listOfArtists);

        //On met la list des chansons associées a leurs artistes ex: [beatles,yesterday]
        Map<String, Long> map = new YoutubeQuerry().closeSong(songBandList);

        for(String s : map.keySet()) {
            System.out.println("La chanson " + s + " a " + map.get(s) + " vues");
        }

        *//*List<String> s6 = new ArrayList<>();
        s6.add("beatles");
        s6.add("adele");
        s6.add("nirvana");
        s6.add("rihanna");
        s6.add("MAGIC!");*//*
        Map<String, Long> map2 = new YoutubeQuerry().closeSongArtist(listOfArtists);

        for(String s : map2.keySet()) {
            System.out.println("L'artiste " + s + " a " + map2.get(s) + " vues");
        }

        String currentArtist="U2";
        try{
            Model m=  callDBPedia.run(currentArtist,"songOfArtist");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data.rdf",true)));
            m.write(pw,"RDF/XML");
        }catch (Exception e){
            System.out.println("plop");
        }*/

        Ontologie OntologieClass = new Ontologie();
        OntModel ontologie = OntologieClass.load();

        OntClass song = ontologie.getOntClass("http://notreOnthologie#Song");
        System.out.println(song);
        ontologie.createIndividual("http://notreOnthologie#Hello", song);
        ontologie.createIndividual("http://notreOnthologie#Rude!", song);

        OntologieClass.write(ontologie);

    }
}
