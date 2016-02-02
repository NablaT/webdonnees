package hello.rdfProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frouyer on 26/01/16.
 */
public class TweetProperty {
    private static TweetProperty ourInstance = new TweetProperty();

    public List<String> getTwProp() {
        return twProp;
    }

    private List<String> twProp;

    public static TweetProperty getInstance() {
        return ourInstance;
    }

    private TweetProperty() {
        twProp = new ArrayList<>();

        twProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasID");
        twProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasQuote");
        twProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasScreenName");
        twProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasCreationDate");
        twProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasReplyToScrrenName");
        twProp.add("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/j.0:hasStatus");




    }
}
