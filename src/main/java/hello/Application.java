package hello;

import hello.youtubeEngine.YoutubeQuerry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        //SpringApplication.run(Application.class, args);
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new YoutubeQuerry().youtubeRequest("beatles", "yesterday"));
    }
}
