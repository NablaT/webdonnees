package hello.rdfProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frouyer on 26/01/16.
 */
public class ClubProperty {
    private static ClubProperty ourInstance = new ClubProperty();

    public static ClubProperty getInstance() {
        return ourInstance;
    }

    public List<String> getCbProp() {
        return cbProp;
    }

    private List<String> cbProp;

    private ClubProperty() {
        cbProp = new ArrayList<>();

        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:abstract");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:colour");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:colourName");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:formationDate");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:formationDate");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:formerName");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:ground");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:headquarter");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:league");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:wikiPageExternalLink");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:manager");
        cbProp.add("http://dbpedia.org/ontology/dbpedia-owl:wikiPageWikiLink");
        cbProp.add("http://www.w3.org/2002/07/owl#sameAs/owl:sameAs");
    }



}
