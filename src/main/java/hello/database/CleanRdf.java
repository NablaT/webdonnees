package hello.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Remi on 06/02/2016.
 */
public class CleanRdf {

    private ArrayList<String> elementsFromRdf;
    private ArrayList<String> songElements;
    private ArrayList<String> bandElements;
    private String pathSongs;
    private String pathArtists;

    public CleanRdf() {
        this.songElements=new ArrayList<String>();
        this.bandElements=new ArrayList<String>();
        this.elementsFromRdf = new ArrayList<String>();
        this.pathArtists="artists.txt";
        this.pathSongs="songs.txt";
        initialiseSongArtist();
    }

    public void getBackRdfContent(String pathFile) {
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

            getBackJustSong(breakPoint);
            getBackJustGroups(breakPoint);

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
            //System.out.println("the character to split: "+stringToSplit3);
            String[] almostFinal = finalResult.split(stringToSplit3);
            if (almostFinal.length > 0) {
                finalResult = almostFinal[0];
            }
            if(!(finalResult.equals(""))){
                this.elementsFromRdf.set(i, finalResult);
                this.bandElements.add(finalResult);
            }
        }
        //System.out.println("GROUP LIST: "+this.bandElements);
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

    public void cleanElements() {
        for (int i = 0; i < this.elementsFromRdf.size(); i++) {
            if (this.elementsFromRdf.get(i).equals("")) {
                this.elementsFromRdf.remove(i);
                i = i - 1;
            }
        }
    }

    public void initialiseSongArtist(){
        /*BufferedReader reader = new BufferedReader(new FileReader(this.pathSongs));
        String line;
        while ((line = reader.readLine()) != null) {
            this.elementsFromRdf.add(line);
        }
        reader.close();*/
    }
}
