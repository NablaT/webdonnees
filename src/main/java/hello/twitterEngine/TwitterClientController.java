package hello.twitterEngine;

import com.google.common.collect.Lists;
import hello.database.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Writer;

/**
 * Created by frouyer on 19/01/16.
 */


@RestController
@RequestMapping("/twitterclient")
public class TwitterClientController{


    @RequestMapping(method = RequestMethod.POST)
    TwitterClient twitterclient(@RequestParam String terms) {
        TwitterClient client =  new TwitterClient(Lists.newArrayList(terms));
        Database.getInstance().clientSave(client);
        return client;
    }


    @RequestMapping("/{id}")
    ResponseEntity<?> run(Writer w, @PathVariable String id) {
        TwitterClient client = Database.getInstance().getById(id);
        if (client != null) {

            if (!client.isRun()) {
                client.run();
                return new ResponseEntity<>(null, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}