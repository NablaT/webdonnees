package hello;

import hello.database.CleanRdf;
import hello.youtubeEngine.YoutubeQuerry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);

        CleanRdf rdfContent=new CleanRdf();
        rdfContent.getBackRdfContent("D:/Workspace/webdonnees/data.rdf");
       // System.out.println(rdfContent.getElements());

        rdfContent.cleanElements();

        ArrayList<String> songList=rdfContent.getSongElements();

        List<List<String>> songBandList=new ArrayList<>();
        for(int i=0;i<songList.size();i++){
            ArrayList<String> tmpArray=new ArrayList<String>();
            tmpArray.add("beatles");
            tmpArray.add(songList.get(i));
            songBandList.add(tmpArray);
        }


        //System.out.println("GETBACKJUSTGROUPS: "+ rdfContent.getBandElements());

        //List<String> fullSongList=rdfContent.getElements();
        //System.out.println(new YoutubeQuerry().youtubeRequest("beatles", "yesterday"));
        /*List<String> s1 = new ArrayList<>();
        s1.add("beatles");
        s1.add("yesterday");
        List<String> s2 = new ArrayList<>();
        s2.add("adele");
        s2.add("hello");
        List<String> s3 = new ArrayList<>();
        s3.add("nirvana");
        s3.add("lithium");
        List<String> s4 = new ArrayList<>();
        s4.add("rihanna");
        s4.add("diamonds");
        List<String> s5 = new ArrayList<>();
        s5.add("MAGIC!");
        s5.add("rude");
        List<List<String>> list = new ArrayList<>();
        list.add(s3);
        list.add(s2);
        list.add(s1);
        list.add(s4);
        list.add(s5);
        Map<String, Integer> map = new YoutubeQuerry().closeSong(list);*/
        Map<String, Integer> map = new YoutubeQuerry().closeSong(songBandList);

        for(String s : map.keySet()) {
            System.out.println("La chanson " + s + " a " + map.get(s) + " vues");
        }

    }
}
