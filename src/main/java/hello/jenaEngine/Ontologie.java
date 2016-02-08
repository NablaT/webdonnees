package hello.jenaEngine;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.iri.impl.Main;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.shared.Command;
import org.apache.jena.shared.Lock;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.XSD;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jonathan on 07/02/16.
 */
public class Ontologie {
    String namespace = "http://notreOnthologie#";

    public OntModel load() {
        FileInputStream in;
        OntModel ontologie = ModelFactory.createOntologyModel();
        try
        {
            in=new FileInputStream("ontologie.owl");
            if(in.read() > 0) {
                ontologie.read("ontologie.owl");
            } else {
                ontologie = create();
            }
        }
        catch (Exception ex)
        {
            ontologie = create();
        }

        return ontologie;
    }

    public void write(OntModel ontologie) {
        FileOutputStream fichierSortie = null;

        try {
            fichierSortie = new FileOutputStream("ontologie.owl");
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger (Main.class.getName ()).log (Level.SEVERE, null, ex);
        }

        ontologie.write(fichierSortie);
    }

    public OntModel create() {

        // créer un modèle vide
        OntModel ontologie = ModelFactory.createOntologyModel();
        ontologie.createOntology (namespace);

        //Création des classes
        OntClass profil = ontologie.createClass (namespace + "Profil");
        OntClass song = ontologie.createClass (namespace + "Song");
        OntClass artist = ontologie.createClass (namespace + "Artist");

        //Création des propriétés d'objet
        ObjectProperty hasCloseSong = ontologie.createObjectProperty (namespace + "hasCloseSong");
        hasCloseSong.setDomain(song);
        hasCloseSong.setRange(song);
        ObjectProperty hasArtist = ontologie.createObjectProperty (namespace + "hasArtist");
        hasArtist.setDomain(song);
        hasArtist.setRange(artist);
        ObjectProperty hasCloseArtist = ontologie.createObjectProperty (namespace + "hasCloseArtist");
        hasCloseArtist.setDomain(song);
        hasCloseArtist.setRange(artist);
        ObjectProperty hasPopularSong = ontologie.createObjectProperty (namespace + "hasPopularSong");
        hasPopularSong.setDomain(artist);
        hasPopularSong.setRange(song);
        ObjectProperty hasListened = ontologie.createObjectProperty (namespace + "haslistened");
        hasListened.setDomain(profil);
        hasListened.setRange(song);
        ObjectProperty hasAlbumSong = ontologie.createObjectProperty (namespace + "hasAlbumSong");
        hasAlbumSong.setDomain(song);
        hasAlbumSong.setRange(song);




        //Création des propriétés de données
        DatatypeProperty hasBeenSeen = ontologie.createDatatypeProperty (namespace + "hasBeenSeen");
        hasBeenSeen.setDomain(song);
        hasBeenSeen.setRange(XSD.xint);
        DatatypeProperty hasYoutubeView = ontologie.createDatatypeProperty(namespace + "hasYoutubeView");
        hasYoutubeView.setDomain(song);
        hasYoutubeView.setRange(XSD.xlong);

        return ontologie;
    }

    public void add(OntModel ontologie, int id, String song, String artist, List<String> closeSongAlbum, List<String> closeArtist) {
        Individual profilInd = createProfil(ontologie, id);
        Individual songInd = createSong(ontologie, song);
        Individual artistInd = createArtist(ontologie, artist);
        addSongToProfil(ontologie, profilInd, songInd);
        addArtistToSong(ontologie, songInd, artistInd);
        addCloseSongAlbumToSong(ontologie, songInd, artistInd,closeSongAlbum);
        addCloseArtistToSong(ontologie, songInd, closeArtist);
    }

    private Individual createArtist(OntModel ontologie, String artist) {
        Individual individual = ontologie.getIndividual(namespace + artist);
        if(individual != null) {
            return individual;
        } else {
            OntClass artistClass = ontologie.getOntClass(namespace + "Artist");
            return ontologie.createIndividual(namespace + artist, artistClass);
        }
    }

    private Individual createSong(OntModel ontologie, String song) {
        Individual individual = ontologie.getIndividual(namespace + song);
        if(individual != null) {
            return individual;
        } else {
            OntClass artistClass = ontologie.getOntClass(namespace + "Song");
            return ontologie.createIndividual(namespace + song, artistClass);
        }
    }

    private Individual createProfil(OntModel ontologie, int id) {
        Individual individual = ontologie.getIndividual(namespace + "" + id);
        if(individual != null) {
            return individual;
        } else {
            OntClass artistClass = ontologie.getOntClass(namespace + "Profil");
            return ontologie.createIndividual(namespace + "" + id, artistClass);
        }
    }

    private void addCloseArtistToSong(OntModel ontologie, Individual songInd, List<String> closeArtist) {
        Property property = ontologie.getProperty(namespace + "hasCloseArtist");
        for(String s : closeArtist) {
            Individual artistInd = createArtist(ontologie, s);
            songInd.addProperty(property, artistInd);
        }
    }

    private void addCloseSongAlbumToSong(OntModel ontologie, Individual songInd, Individual artistInd, List<String> closeSongAlbum) {
        Property hasArtist = ontologie.getProperty(namespace + "hasArtist");
        Property hasAlbumSong = ontologie.getProperty(namespace + "hasAlbumSong");
        for(String s : closeSongAlbum) {
            Individual songAlbumInd = createSong(ontologie, s);
            songAlbumInd.addProperty(hasArtist, artistInd);
            songInd.addProperty(hasAlbumSong, songAlbumInd);
        }
    }

    private void addArtistToSong(OntModel ontologie, Individual songInd, Individual artistInd) {
        Property property = ontologie.getProperty(namespace + "hasArtist");
        songInd.addProperty(property, artistInd);
    }

    private void addSongToProfil(OntModel ontologie, Individual profilInd, Individual songInd) {
        Property property = ontologie.getProperty(namespace + "haslistened");
        profilInd.addProperty(property, songInd);
    }
}
