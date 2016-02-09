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
public class ApplicationServer {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
        //On initialise la classe qui gere le contenu du rdf

    }
}
