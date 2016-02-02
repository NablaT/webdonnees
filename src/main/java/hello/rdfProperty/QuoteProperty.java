package hello.rdfProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frouyer on 30/01/16.
 */
public class QuoteProperty {
    private static QuoteProperty ourInstance = new QuoteProperty();

    public static QuoteProperty getInstance() {
        return ourInstance;
    }

    private List<String> quProp;
    private QuoteProperty() {
        quProp = new ArrayList<>();

        quProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasEntity");
        quProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasName");
    }
}
