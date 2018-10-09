import java.io.*;
import java.util.Scanner;

public class JIn {
    public static String getString() {
        String text = null;
        try {
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            text = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    public static int getInt(){
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        return i;

    }
}