package hello.jenaEngine;

import hello.database.Database;
import hello.database.ParseTools;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by kha on 21/01/16.
 */


public class callDBPedia {

    public static Model run(String r, String p) throws Exception {

        Model m = ModelFactory.createDefaultModel();
        Resource resource = m.createResource("http://fr.dbpedia.org/resource#" + r);
        Property property = m.createProperty(ParseTools.getURI(p));

        String service = "http://fr.dbpedia.org/sparql";

        String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                + "PREFIX dcterms: <http://purl.org/dc/terms/>"
                + "PREFIX prop-fr: <http://fr.dbpedia.org/property/>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "SELECT DISTINCT ?wikiValue WHERE { <http://fr.dbpedia.org/resource/" + r + "> " + ParseTools.getProperty(p) + " ?wikiValue }";

        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        try {
            ResultSet results = qe.execSelect();
            for (; results.hasNext(); ) {
                QuerySolution sol = (QuerySolution) results.next();
                String answer = sol.get("?wikiValue").toString();
                resource.addProperty(property, answer, XSDDatatype.XSDstring);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            qe.close();
        }

        Database.getInstance().rdfWriter(m);
        return m;
    }
}
