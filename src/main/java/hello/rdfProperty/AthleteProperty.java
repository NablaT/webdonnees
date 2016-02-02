package hello.rdfProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frouyer on 26/01/16.
 */
public class AthleteProperty {
    private static AthleteProperty ourInstance = new AthleteProperty();

    public static AthleteProperty getInstance() {
        return ourInstance;
    }

    public List<String> getSpProp() {
        return spProp;
    }

    private List<String> spProp;

    private AthleteProperty() {

        spProp = new ArrayList<>();

        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:abstract");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:birthDate");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:birthName");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:birthPlace");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:currentTeam");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:nationality");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:wikiPageExternalLink");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:wikiPageWikiLink");
        spProp.add("http://fr.dbpedia.org/property/prop-fr:club");
        spProp.add("http://fr.dbpedia.org/property/prop-fr:dateDeNaissance");
        spProp.add("http://purl.org/dc/terms/subject/dcterms:subject");
        spProp.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#type/rdf:type");
        spProp.add("http://www.w3.org/2000/01/rdf-schema#comment/rdfs:comment");
        spProp.add("http://www.w3.org/2002/07/owl#sameAs/owl:sameAs");
    }
}
