package hello.jenaEngine;

import hello.rdfProperty.AthleteProperty;
import hello.rdfProperty.ClubProperty;
import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Writer;

/**
 * Created by frouyer on 23/01/16.
 */
@RestController
@RequestMapping("/dbpedia")
public class CallDBPediaController {

    @RequestMapping(method = RequestMethod.POST)
    public void request(Writer responseWriter,@RequestParam("resource") String resource, @RequestParam("property") String property) {
        try {
            if(property.equals("athlete")){
                for(String p : AthleteProperty.getInstance().getSpProp()){
                    Model m = callDBPedia.run(resource,p);
                    m.write(responseWriter,"RDF/XML");
                }
            }else if(property.equals("club")){
                for(String p : ClubProperty.getInstance().getCbProp()){
                    Model m = callDBPedia.run(resource,p);
                    m.write(responseWriter,"RDF/XML");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
