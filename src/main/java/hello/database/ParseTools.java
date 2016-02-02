package hello.database;


public class ParseTools {

    public static String getURI(String input){
        String tmp[] = input.split("/");
        String vocabulaire = tmp[tmp.length-1].split(":")[0]+":";
        return input.replace(vocabulaire,"");
    }

    public static String getProperty(String input){
        String tmp[] = input.split("/");
        String property = tmp[tmp.length-1];
        return property;
    }
}
