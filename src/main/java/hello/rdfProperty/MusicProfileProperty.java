package hello.rdfProperty;

import java.util.ArrayList;
import java.util.List;

public class MusicProfileProperty {
    private static MusicProfileProperty ourInstance = new MusicProfileProperty();

    public static MusicProfileProperty getInstance() {
        return ourInstance;
    }

    public List<String> getMusicProp() {
        return spProp;
    }

    private List<String> spProp;

    private MusicProfileProperty() {

        spProp = new ArrayList<>();

        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:genre");
        //spProp.add("http://dbpedia.org/ontology/dbpedia-owl:activeYearsStartYear");
        /*
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:birthDate");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:currentTeam");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:nationality");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:wikiPageExternalLink");
        spProp.add("http://dbpedia.org/ontology/dbpedia-owl:wikiPageWikiLink");
        spProp.add("http://fr.dbpedia.org/property/prop-fr:club");
        spProp.add("http://fr.dbpedia.org/property/prop-fr:dateDeNaissance");
        spProp.add("http://purl.org/dc/terms/subject/dcterms:subject");
        spProp.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#type/rdf:type");
        spProp.add("http://www.w3.org/2000/01/rdf-schema#comment/rdfs:comment");
        spProp.add("http://www.w3.org/2002/07/owl#sameAs/owl:sameAs");*/
    }
}
