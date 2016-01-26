

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;

/**
 * Created by
 *
 */
public class DBPediaEngine {

    private ArrayList<String> answerList;
    public DBPediaEngine(String terms){
        answerList = new ArrayList<>();
        String service = "http://fr.dbpedia.org/sparql";
        String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
                +"SELECT DISTINCT ?wikiValue WHERE { <http://fr.dbpedia.org/resource/"+terms+"> dbpedia-owl:wikiPageWikiLink ?wikiValue &format=json}";

        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        try {
            ResultSet results = qe.execSelect();
            for (; results.hasNext(); ) {
                QuerySolution sol = (QuerySolution) results.next();
                String answer = sol.get("?wikiValue").toString();
                answerList.add(answer);
                System.out.println(answer);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            qe.close();
        }
    }

    public ArrayList<String> getAnswerList() {
        return answerList;
    }

    public static void main(String[] args)throws Exception {
        new DBPediaEngine("France");
    }
}
