package hello.jenaEngine;

import org.apache.jena.rdf.model.Model;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Writer;

/**
 * Created by frouyer on 24/01/16.
 */
@RestController
@RequestMapping("/tweettranslator")
public class TwitterTranslatorController {

    @RequestMapping(method = RequestMethod.POST)
    public void request(Writer responseWriter, @RequestParam("input") String input) {
        JSONObject tweet = new JSONObject(input);
        Model m = TwitterTranslator.run(tweet);
        m.write(responseWriter, "RDF/XML");
    }
}
