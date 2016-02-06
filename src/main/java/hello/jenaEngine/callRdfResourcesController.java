package hello.jenaEngine;

import org.apache.jena.rdf.model.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Remi on 03/02/2016.
 */

@RestController
@RequestMapping("/rdfResources")
public class CallRdfResourcesController {

    private List<String> rdfResult; // The string in which we store the results.

    @RequestMapping(method = RequestMethod.POST)
    public void request(Writer responseWriter, @RequestParam("resource") String resource, @RequestParam("property") String property) {
        try{
            rdfResult= (List<String>) CallRdfResources.request(resource);
            System.out.println("rdf result: "+rdfResult);
            responseWriter.write(rdfResult.toString());
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
