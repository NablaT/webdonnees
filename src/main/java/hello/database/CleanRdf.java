package hello.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Remi on 06/02/2016.
 */
public class CleanRdf {

    private ArrayList<String> elementsFromRdf;

    public CleanRdf(){
        this.elementsFromRdf=new ArrayList<String>();
    }

    public void getBackRdfContent(String pathFile){
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(pathFile));
            String line;
            while ((line = reader.readLine()) != null)
            {
                this.elementsFromRdf.add(line);
            }
            reader.close();

            for(int i=0;i<6;i++){
                this.elementsFromRdf.remove(0);
            }

            String stringToSplit="resource";
            String stringToSplit2="<";
            for(int i=0; i<this.elementsFromRdf.size();i++){
                String finalResult="";
                String[] tmpResult=this.elementsFromRdf.get(i).split(stringToSplit);
                if(tmpResult.length>0){
                    try{
                        tmpResult=tmpResult[1].split(stringToSplit2);
                        finalResult=tmpResult[0];
                    }catch (Exception e){
                        System.out.println("Nothing");
                    }
                }
                finalResult=finalResult.replace("/","");
                this.elementsFromRdf.set(i,finalResult);
            }
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", pathFile);
            e.printStackTrace();
        }
    }

    public ArrayList<String> getElements(){
        return this.elementsFromRdf;
    }

    public void setElements(ArrayList<String> elements){
        this.elementsFromRdf=elements;
    }
}
