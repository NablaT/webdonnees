package hello.jenaEngine;

import com.sun.org.apache.xpath.internal.operations.Mod;
import hello.database.Database;
import hello.database.ParseTools;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by kha on 21/01/16.
 */
public class TwitterTranslator {

    private static final String owlFile = "file:////home/frouyer/Workspace/Kohaku/TweetProject.owl";

    public static Model run(JSONObject tweet) {

//        Model ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
//        FileManager.get().readModel(ontology, owlFile);

        String uidTweet = UUID.randomUUID().toString();
        String uidQuote = UUID.randomUUID().toString();

        Model model = ModelFactory.createDefaultModel();

        Property hasID = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasID");
        Property hasCreationDate = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasCreationDate");
        Property hasReplyToScreenName = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasReplyToScrrenName");
        Property hasStatus = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasStatus");
        Property hasQuote = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasQuote");
        Property hasName = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasName");
        Property hasEntity = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasEntity");
        Property hasLanguage = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasLanguage");
        Property hasScreenName = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasScreenName");
        Property hasCoordinates = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasScreenName");
        Property hasGeolocation = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasScreenName");
        Property hasLocation = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasScreenName");
        Property hasTweet = model.createProperty("http://www.semanticweb.org/kha/ontologies/2016/0/tweetproject/hasScreenName");



        Resource tweetResource = model.createResource("http://fr.polytech.org/resource#tweet-"+ uidTweet);
        tweetResource.addProperty(hasID, tweet.get("id_str").toString(), XSDDatatype.XSDstring);
        tweetResource.addProperty(hasCreationDate, tweet.get("created_at").toString(), XSDDatatype.XSDdate);
        tweetResource.addProperty(hasReplyToScreenName, tweet.get("in_reply_to_screen_name").toString(), XSDDatatype.XSDstring);
        tweetResource.addProperty(hasStatus, tweet.get("text").toString(), XSDDatatype.XSDstring);
        tweetResource.addProperty(hasQuote,"http://fr.polytech.org/resource#quote-"+ uidQuote, XSDDatatype.XSDstring);


        Resource quoteResource = model.createResource("http://fr.polytech.org/resource#quote-"+ uidQuote);
        JSONArray mentions = tweet.getJSONObject("entities").getJSONArray("user_mentions");
        JSONArray hashtag = tweet.getJSONObject("entities").getJSONArray("hashtags");
        for(int i = 0; i < mentions.length(); i++){
            quoteResource.addProperty(hasName, mentions.getJSONObject(i).get("name").toString(), XSDDatatype.XSDstring);
        }
        for(int i = 0; i < hashtag.length(); i++){
            quoteResource.addProperty(hasEntity, hashtag.getJSONObject(i).get("text").toString(), XSDDatatype.XSDstring);
        }


        Resource userResource = model.createResource(
                "http://fr.polytech.org/resource#user-"+ tweet.getJSONObject("user")
                        .get("name").toString()
                        .replace(" ", "_"));


        userResource.addProperty(hasLanguage, tweet.getJSONObject("user").get("lang").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasScreenName, tweet.getJSONObject("user").get("screen_name").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasCoordinates, tweet.get("coordinates").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasGeolocation, tweet.get("geo").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasID, tweet.getJSONObject("user").get("id_str").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasLocation, tweet.getJSONObject("user").get("location").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasName, tweet.getJSONObject("user").get("name").toString(), XSDDatatype.XSDdate);
        userResource.addProperty(hasTweet, "http://fr.polytech.org/resource#tweet-"+ uidTweet, XSDDatatype.XSDdate);



        Database.getInstance().rdfWriter(model);


        return model;
    }

}
