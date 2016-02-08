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
    private ArrayList<ArrayList<String>> listOfSongOfDifferentArtists;


    private String pathSongs;
    private String pathArtists;

    /**
     * Default constructor. Initializes all attributes of the class
     */
    public CleanRdf() {
        this.songElements = new ArrayList<>();
        this.bandElements = new ArrayList<>();
        this.elementsFromRdf = new ArrayList<>();
        this.listOfIndexes = new ArrayList<Integer>();
        this.listOfSongOfDifferentArtists = new ArrayList<ArrayList<String>>();
        this.pathArtists = "./artists.txt";
        this.pathSongs = "songs.txt";
        initialiseSongArtist();
    }

    /**
     * This function gets back data from rdf file and store them
     * in class attributes. We run it two times, one after requesting
     * dbpedia on club and music and another after merging data.
     * @param pathFile
     * @param firstTime
     */
    public void getBackRdfContent(String pathFile, boolean firstTime) {
        try {

            if (!firstTime) {
                this.elementsFromRdf = new ArrayList<>();
                //this.elementsFromRdf = new ArrayList<>();
            }
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

            if (firstTime) {
                getBackJustSong(this.listOfIndexes.get(0));
                getBackJustGroups(this.listOfIndexes.get(0));
            } else {
                for (int i = 0; i < this.listOfIndexes.size(); i = i + 2) {
                    int numberOfIteration = this.listOfIndexes.get(i + 1) - this.listOfIndexes.get(i);
                    getBackAllSongsFromDifferentArtists(this.listOfIndexes.get(i) + 2, numberOfIteration - 3);
                }
            }

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", pathFile);
            e.printStackTrace();
        }
    }


    /**
     * This function gets back all song of all our artists.
     * @param index
     * @param numberOfIteration
     */
    public void getBackAllSongsFromDifferentArtists(int index, int numberOfIteration) {

        String stringToSplit = "resource";
        String stringToSplit2 = "<";

        ArrayList<String> tmpArray = new ArrayList<>();
        // WE MAKE -2 to avoid last balise <rdf:> <rdfDescription>
        for (int i = index + 3; i < index + numberOfIteration; i++) {
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
            if (!(finalResult.equals(""))) {
                this.elementsFromRdf.set(i, finalResult);
                tmpArray.add(finalResult);
            }
        }
        this.listOfSongOfDifferentArtists.add(tmpArray);
    }

    /**
     * This function gets back all the song of the same albums
     * @param index
     */
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
            if (!(finalResult.equals(""))) {
                this.elementsFromRdf.set(i, finalResult);
                this.songElements.add(finalResult);
            }
        }
        removeSongsFromArrayRdf(index);
    }

    /**
     * THis function gets back all bands and artists presented
     * in our rdf.
     * @param index
     */
    public void getBackJustGroups(int index) {
        for (int i = index; i < index + 6; i++) {
            this.elementsFromRdf.remove(0);
        }

        String stringToSplit = "resource";
        String stringToSplit2 = "<";
        // WE MAKE -2 to avoid last balise <rdf:> <rdfDescription>
        for (int i = 0; i < this.elementsFromRdf.size() - 2; i++) {
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
            if (!(finalResult.equals(""))) {
                this.elementsFromRdf.set(i, finalResult);
                this.bandElements.add(finalResult);
            }
        }

    }

    /**
     * This function delete a file located at the path in parameter
     * and recreate it with the same name.
     * @param path
     */
    public void deleteRecreateFile(String path) {
        Path mypath = Paths.get(path);
        try {
            System.out.println("jerentre");
            Files.delete(mypath);
            //Files.write(mypath);
            PrintWriter writer = new PrintWriter(path);
            writer.close();
            System.out.println("jessort");
        } catch (Exception e) {
            System.out.println("Error in deleting/creating file data.rdf");
        }
    }

    /**
     * This function removes song from array
     * @param index
     */
    public void removeSongsFromArrayRdf(int index) {
        for (int i = 0; i < index; i++) {
            this.elementsFromRdf.remove(0);
        }
    }

    /**
     * THis function gets back all the element from rdf (attributes
     * of the class)
     * @return
     */
    public ArrayList<String> getElements() {
        return this.elementsFromRdf;
    }

    /**
     * This function sets the elementsFromRdf
     * @param elements
     */
    public void setElements(ArrayList<String> elements) {
        this.elementsFromRdf = elements;
    }

    /**
     * This function gets the list of song
     * @return
     */
    public ArrayList<String> getSongElements() {
        return this.songElements;
    }

    /**
     * This function sets the list of song
     * @param elements
     */
    public void setSongElements(ArrayList<String> elements) {
        this.songElements = elements;
    }

    /**
     * This function gets the list of bands.
     * @return
     */
    public ArrayList<String> getBandElements() {
        return this.bandElements;
    }

    /**
     * This function sets the list of bands
     * @param elements
     */
    public void setBandElements(ArrayList<String> elements) {
        this.bandElements = elements;
    }

    /**
     * THis function gets the list of indexes. This is the reference
     * of all </rdf> balises
     * @return
     */
    public ArrayList<Integer> getListOfIndexes() {
        return this.listOfIndexes;
    }

    /**
     * This function sets the list of indexes.
     * @param indexes
     */
    public void setListOfIndexes(ArrayList<Integer> indexes) {
        this.listOfIndexes = indexes;
    }

    /**
     * THis function gets the total list of song from different
     * artists
     * @return
     */
    public ArrayList<ArrayList<String>> getListOfSongOfDifferentArtists() {
        return this.listOfSongOfDifferentArtists;
    }

    /**
     * This function sets the total list of song from different
     * artists
     * @param list
     */
    public void setListOfSongOfDifferentArtists(ArrayList<ArrayList<String>> list) {
        this.listOfSongOfDifferentArtists = list;
    }

    /**
     * This function cleans the attributes elementsFromRdf when
     * an element is empty (equals to "").
     */
    public void cleanElements() {
        for (int i = 0; i < this.elementsFromRdf.size(); i++) {
            if (this.elementsFromRdf.get(i).equals("")) {
                this.elementsFromRdf.remove(i);
                i = i - 1;
            }
        }
    }

    /**
     * This function initialises the list of indexes.
     * The indexes corresponds to the line the following
     * balise: </rdf:RDF>
     */
    public void initialiseListOfIndexes() {
        this.listOfIndexes = new ArrayList<>();
        for (int i = 0; i < this.elementsFromRdf.size(); i++) {
            if (this.elementsFromRdf.get(i).indexOf("</rdf:RDF>") != -1) {
                this.listOfIndexes.add(i);
            }
        }

    }

    /**
     * This function saves our current Artist or Song in order to remove it from the final
     * answer we have.
     */
    public void initialiseSongArtist() {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Function refactorName. This function refactor the name
     * of the songs, it replaces spaces by "_" and removing
     * unused spaces at the end.
     * @param list
     * @return
     */
    public ArrayList<String> refactorName(ArrayList<String> list) {

        for (int i = 0; i < list.size(); i++) {
            boolean hasASpaceAtEnd = false;
            String currentWord = list.get(i);
            //We verify if there is no space at the end, if there is, we remove it
            if (list.get(i).charAt(list.get(i).length() - 1) == ' ') {
                currentWord = removeSpaceAtEnd(currentWord);
            }
            currentWord = currentWord.replace(" ", "_");
            list.set(i, currentWord);
        }
        return list;
    }

    /**
     * This function removes spaces at the end of a song
     * title.
     * @param word
     * @return
     */
    public String removeSpaceAtEnd(String word) {
        String res = "";
        for (int i = 0; i < word.length() - 1; i++) {
            res += word.charAt(i);
        }
        return res;
    }

    public ArrayList<String> removeUnderscore(ArrayList<String> artistList){

        for(int i=0; i<artistList.size();i++){

            artistList.set(i,artistList.get(i).replace("_", " "));
        }
        System.out.println("list of artist after"+artistList);
        return artistList;
    }
}
