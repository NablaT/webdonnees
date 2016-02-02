package hello.rdfProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frouyer on 30/01/16.
 */
public class UserProperty {
    private static UserProperty ourInstance = new UserProperty();

    public static UserProperty getInstance() {
        return ourInstance;
    }

    private List<String> usProp;

    private UserProperty() {
        usProp = new ArrayList<>();

        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasQuote");
        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasCoordinates");
        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasGeolocation");
        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasId");
        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasLocation");
        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasName");
        usProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasTweet");

    }
}
