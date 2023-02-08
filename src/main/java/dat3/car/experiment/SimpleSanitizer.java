package dat3.car.experiment;

//Never use anything in here for real
public class SimpleSanitizer {
    public static String simpleSanitize(String s){

     String str = s.replaceAll("\\<[^>]*>","");
        return str;
        //s="Hello World";
        //return s;
        //return "Hello World";
    }

}
