package hello.twitterEngine;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import hello.database.Database;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by frouyer on 13/01/16.
 */
@Component
@Scope("prototype")
public class TwitterClient {

    /**
     * Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream
     */
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
    // private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);
    private Client hosebirdClient;

    private String id;

    private Boolean run;


    public TwitterClient(List<String> listTerms) {

        System.out.println("creation tweet");

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();


        // Optional: set up some followings and track terms

        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        List<String> terms = Lists.newArrayList(listTerms);
        System.out.println(listTerms);

        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1("GudNiGFRRAKuluOiN8399AOFr", "vMxVHWoM30XFeLXXVhyp9k8B57LE4GtzfmLztgmuuXWy7UpK10",
                "161444812-gkb3TANwSapC2fQWYEEi078Aruh9UpmAom9TlAp8", "dgUqOCMJayzjULddkQaHWiWaKUEC5BJkkljpIuK8FHqvQ");

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(this.msgQueue));
        //    .eventMessageQueue(this.eventQueue);                          // optional: use this if you want to process client events

        this.hosebirdClient = builder.build();
        this.id = UUID.randomUUID().toString();
        this.run = false;
        this.hosebirdClient.connect();
    }

    public void run() {
        this.run = true;
        int i = 0;
        // on a different thread, or multiple different threads....
        while (!hosebirdClient.isDone()) {
            try {
                i++;
                String msg = this.msgQueue.take();
//                System.out.println(msg);
                Database.getInstance().persister(msg);
                if(i > 20)
                    hosebirdClient.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopTwitter() {
        this.hosebirdClient.stop();
    }

    public String getIdentifiant() {
        return id;
    }

    public Boolean isRun() {
        return run;
    }
}

