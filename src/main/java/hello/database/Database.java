package hello.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.apache.jena.rdf.model.Model;
import org.bson.Document;
import org.json.JSONObject;

import java.io.*;
import java.util.*;


public class Database {

    private File ficRDF = new File("data.rdf");

    private MongoClient client;
    private MongoDatabase db;

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    Database() {
        this.client = new MongoClient();
        this.db = client.getDatabase("kohaku");
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
