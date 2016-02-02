package hello.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import hello.jenaEngine.TwitterTranslator;
import hello.twitterEngine.TwitterClient;
import org.apache.jena.rdf.model.Model;
import org.bson.Document;
import org.json.JSONObject;

import java.io.*;
import java.util.*;


public class Database {

    private File ficRDF = new File("data.rdf");

    private MongoClient client;
    private MongoDatabase db;
    private Map<String, TwitterClient> tweetMap;

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    Database() {
        this.client = new MongoClient();
        this.db = client.getDatabase("kohaku");
        this.tweetMap = new HashMap<>();
    }

    public void persister(String t) {
        JSONObject tweet = new JSONObject(t);
        this.rdfWriter(TwitterTranslator.run(tweet));
        this.db.getCollection("Tweet").insertOne(new Document(Document.parse(t)));
    }

    public void clientSave(TwitterClient t) {
        this.tweetMap.put(t.getIdentifiant(), t);
    }

    public TwitterClient getById(String id) {
        if (tweetMap.containsKey(id)) {
            return tweetMap.get(id);
        }
        return null;
    }


    public void rdfWriter(Model m){
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ficRDF,true)));
            m.write(pw,"RDF/XML");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
