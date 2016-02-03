package hello.jenaEngine;

import hello.rdfProperty.MusicProfileProperty;
import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Writer;


@RestController
@RequestMapping("/dbpedia")
public class CallDBPediaController {

    @RequestMapping(method = RequestMethod.POST)
    public void request(Writer responseWriter, @RequestParam("resource") String resource, @RequestParam("property") String property) {
        try {
            for (String p : MusicProfileProperty.getInstance().getMusicProp()) {
                Model m = callDBPedia.run(resource, property);
                m.write(responseWriter, "RDF/XML");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
