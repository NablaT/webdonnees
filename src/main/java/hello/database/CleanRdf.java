package hello.database;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Remi on 06/02/2016.
 */
public class CleanRdf {

    private ArrayList<String> elementsFromRdf;
    private ArrayList<String> songElements;
    private ArrayList<String> bandElements;
    private ArrayList<Integer> listOfIndexes;

    private String pathSongs;
    private String pathArtists;

    public CleanRdf() {
        this.songElements=new ArrayList<>();
        this.bandElements=new ArrayList<>();
        this.elementsFromRdf = new ArrayList<>();
        this.listOfIndexes = new ArrayList<Integer>();
        this.pathArtists="./artists.txt";
        this.pathSongs="songs.txt";
        initialiseSongArtist();
    }

    public void getBackRdfContent(String pathFile, boolean firstTime) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFile));
            String line;
            while ((line = reader.readLine()) != null) {
                this.elementsFromRdf.add(line);
            }
            reader.close();


            for (int i = 0; i < 6; i++) {
                this.elementsFromRdf.remove(0);
            }

            //The breakPoint is the index where the separation between
            // songs and musicians appears

            int breakPoint = this.elementsFromRdf.indexOf("</rdf:RDF>");

            initialiseListOfIndexes();

            if(firstTime){
                getBackJustSong(this.listOfIndexes.get(0));
                getBackJustGroups(this.listOfIndexes.get(0));
            }
            else{

            }

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", pathFile);
            e.printStackTrace();
        }
    }

    public void getBackJustSong(int index) {
        String stringToSplit = "resource";
        String stringToSplit2 = "<";
        for (int i = 0; i < index; i++) {
            String finalResult = "";
            String[] tmpResult = this.elementsFromRdf.get(i).split(stringToSplit);
            if (tmpResult.length > 0) {
                try {
                    tmpResult = tmpResult[1].split(stringToSplit2);
                    finalResult = tmpResult[0];
                } catch (Exception e) {
                    //System.out.println("Too short array");
                }
            }
            finalResult = finalResult.replace("/", "");
            finalResult = finalResult.replace("_", " ");
            String stringToSplit3 = "\\(";
            String[] almostFinal = finalResult.split(stringToSplit3);
            if (almostFinal.length > 0) {
                finalResult = almostFinal[0];
            }
            if(!(finalResult.equals(""))){
                this.elementsFromRdf.set(i, finalResult);
                this.songElements.add(finalResult);
            }
        }
        removeSongsFromArrayRdf(index);
    }

    public void getBackJustGroups(int index) {
        for (int i = index; i < index + 6; i++) {
            this.elementsFromRdf.remove(0);
        }

        String stringToSplit = "resource";
        String stringToSplit2 = "<";
        // WE MAKE -2 to avoid last balise <rdf:> <rdfDescription>
        for (int i = 0; i < this.elementsFromRdf.size()-2; i++) {
            String finalResult = "";
            String[] tmpResult = this.elementsFromRdf.get(i).split(stringToSplit);
            if (tmpResult.length > 0) {
                try {
                    tmpResult = tmpResult[1].split(stringToSplit2);
                    finalResult = tmpResult[0];
                } catch (Exception e) {
                    //System.out.println("Too short array");
                }
            }
            finalResult = finalResult.replace("/", "");
            finalResult = finalResult.replace("_", " ");
            String stringToSplit3 = "\\(";
            String[] almostFinal = finalResult.split(stringToSplit3);
            if (almostFinal.length > 0) {
                finalResult = almostFinal[0];
            }
            if(!(finalResult.equals(""))){
                this.elementsFromRdf.set(i, finalResult);
                this.bandElements.add(finalResult);
            }
        }

    }

    public void deleteRecreateFile(String path){
        Path mypath= Paths.get(path);
        try{
            System.out.println("jerentre");
            Files.delete(mypath);
            //Files.write(mypath);
            PrintWriter writer = new PrintWriter(path);
            writer.close();
            System.out.println("jessort");
        }
        catch(Exception e){
            System.out.println("Error in deleting/creating file data.rdf");
        }
    }

    public void removeSongsFromArrayRdf(int index){
        for(int i=0;i<index; i++){
            this.elementsFromRdf.remove(0);
        }
    }

    public ArrayList<String> getElements() {
        return this.elementsFromRdf;
    }

    public void setElements(ArrayList<String> elements) {
        this.elementsFromRdf = elements;
    }

    public ArrayList<String> getSongElements() {
        return this.songElements;
    }

    public void setSongElements(ArrayList<String> elements) {
        this.songElements = elements;
    }

    public ArrayList<String> getBandElements() {
        return this.bandElements;
    }

    public void setBandElements(ArrayList<String> elements) {
        this.bandElements = elements;
    }

    public ArrayList<Integer> getListOfIndexes() {
        return this.listOfIndexes;
    }

    public void setListOfIndexes(ArrayList<Integer> indexes) {
        this.listOfIndexes = indexes;
    }

    public void cleanElements() {
        for (int i = 0; i < this.elementsFromRdf.size(); i++) {
            if (this.elementsFromRdf.get(i).equals("")) {
                this.elementsFromRdf.remove(i);
                i = i - 1;
            }
        }
    }

    public void initialiseListOfIndexes(){
        this.listOfIndexes=new ArrayList<>();
        for(int i=0; i<this.elementsFromRdf.size();i++){
            if(this.elementsFromRdf.get(i).indexOf("</rdf:RDF>")!=-1){
                this.listOfIndexes.add(i);
            }
        }
        System.out.println("list of indexes: "+ this.listOfIndexes);
    }

    /**
     * This function saves our current Artist or Song in order to remove it from the final
     * answer we have.
     */
    public void initialiseSongArtist(){
        try {
            URL url = getClass().getResource("/src/main/java/hello/database/songs.txt");
            File file = new File(url.getPath());
            InputStream input = getClass().getResourceAsStream("/src/main/java/hello/database/songs.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                this.elementsFromRdf.add(line);
            }
            reader.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
