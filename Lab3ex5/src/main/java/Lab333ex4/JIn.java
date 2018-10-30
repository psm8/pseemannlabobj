package Lab333ex4;

import java.io.*;

public class JIn {
    public static String getString() {
        String text = null;
        System.out.println("Podaj haslo:");
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        return text;
    }
}