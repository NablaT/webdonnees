package hello.jenaEngine;


import hello.database.Database;
import hello.database.ParseTools;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remi on 03/02/2016.
 */
public class CallRdfResources {

    private static final String rdfFile = "file:////D:/Workspace/webdonnees/data.rdf";

    public static Model request(String r) throws Exception {

        /**
         * Model importing
         */
        Model data = ModelFactory.createDefaultModel();
        FileManager.get().readModel(data, rdfFile);
        System.out.println("step 1");
        String queryString = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                + "PREFIX dcterms: <http://purl.org/dc/terms/>"
                + "PREFIX prop-fr: <http://fr.dbpedia.org/property/>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "SELECT DISTINCT ?value WHERE {?p ?v ?value}";
        Query query = QueryFactory.create(queryString);

        System.out.println("step 2");

        try (QueryExecution qe = QueryExecutionFactory.create(query, data)){
            ResultSet results = qe.execSelect();
            for (; results.hasNext(); ) {
                QuerySolution sol =  results.nextSolution();
                String answer = sol.get("?value").toString();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
