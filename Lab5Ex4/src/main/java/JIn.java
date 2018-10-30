import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JIn {
    public static String getApiKey(){
        String text = null;
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);
            System.out.print("Wpisz ApiKey: ");
            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        return text;
    }
}