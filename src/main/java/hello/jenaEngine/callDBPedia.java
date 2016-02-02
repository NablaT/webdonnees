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



public class callDBPedia {

    public static Model run(String r, String p) throws Exception {

        Model m = ModelFactory.createDefaultModel();
        Resource resource = m.createResource("http://fr.dbpedia.org/resource#" + r);
        Property property = m.createProperty("http://dbpedia.org/ontology/dbpedia-owl:genre");//m.createProperty(ParseTools.getURI(p));

        String service = "http://fr.dbpedia.org/sparql";

        /*String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                + "PREFIX dcterms: <http://purl.org/dc/terms/>"
                + "PREFIX prop-fr: <http://fr.dbpedia.org/property/>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "SELECT DISTINCT ?wikiValue WHERE { <http://fr.dbpedia.org/resource/" + r + "> " +
                ParseTools.getProperty(p) + " ?wikiValue }";*/

        String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                + "PREFIX dcterms: <http://purl.org/dc/terms/>"
                + "PREFIX prop-fr: <http://fr.dbpedia.org/property/>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "SELECT DISTINCT ?list WHERE{ {SELECT DISTINCT ?value ?year WHERE { <http://fr.dbpedia.org/resource/" + r + "> "
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:genre")
                + " ?value "
                + ". <http://fr.dbpedia.org/resource/" + r + ">"
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:activeYearsStartYear")
                + " ?year"
                + "}}"
                + "?value "
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:wikiPageWikiLink")
                + " ?list "
                + "}";


        /*

                + ". ?list"
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:activeYearsStartYear")
                + "?year2"

                + ". ?list"
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:activeYearsStartYear")
                + "?year2"

                + ". ?list"
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:activeYearsStartYear")
                + " ?year2"

        ?value dbpedia-owl:wikiPageWikiLink ?list

		. ?list dbpedia-owl:activeYearsStartYear ?year2
		.filter(exists{?list dbpedia-owl:bandMember ?members})
		.filter(?list!=?value)
		.filter(?year>?year2)

               /* + ". <http://fr.dbpedia.org/resource/" + r + ">"
                + ParseTools.getProperty("http://dbpedia.org/ontology/dbpedia-owl:activeYearsStartYear")
                + "?year}";*/
        //+ "SELECT DISTINCT ?value  WHERE {<http://fr.dbpedia.org/resource/" + r + "> dbpedia-owl:genre ?value }";


        System.out.println("query: "+ query);
        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        try {
            ResultSet results = qe.execSelect();
            /*QuerySolution sol = (QuerySolution) results.next();
            String answer = sol.get("?value").toString();
            resource.addProperty(property, answer, XSDDatatype.XSDstring);
            */

            for (; results.hasNext(); ) {
                QuerySolution sol = (QuerySolution) results.next();
                System.out.println("SOL: "+sol);
                String answer = sol.get("?list").toString();
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
