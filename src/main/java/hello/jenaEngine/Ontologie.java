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

    public OntModel load() {
        FileInputStream in;
        OntModel ontologie = ModelFactory.createOntologyModel();
        try
        {
            in=new FileInputStream("ontologie.xml");
            if(in.read() > 0) {
                System.out.println("Je passe dans le if");
                ontologie.read("ontologie.xml");
            } else {
                System.out.println("Je passe dans le else");
                ontologie = create();
            }
        }
        catch (Exception ex)
        {
            System.out.println("Je passe dans le catch");
            ontologie = create();
        }

        return ontologie;
    }

    public void write(OntModel ontologie) {
        FileOutputStream fichierSortie = null;

        try {
            fichierSortie = new FileOutputStream("ontologie.xml");
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
        String namespace = "http://notreOnthologie#";
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
}
