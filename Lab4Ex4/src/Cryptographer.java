import java.io.*;

public class Cryptographer {
    File readFrom;
    File saveTo;

    public static void cryptfile(File readFrom, File saveTo, Algorithm cipher){
        BufferedReader br = null;
        try{
            PrintWriter out = new PrintWriter(saveTo);
            br = new BufferedReader(new FileReader(readFrom));
            String line;
            while (br.ready()) {
                line = br.readLine();
                String[] words = line.split(" ");
                for(String tmp:words){
                    System.out.println(cipher.crypt(tmp));
                    out.print(cipher.crypt(tmp+" "));
                }
            }
            out.close();
        } catch(IOException e){}
    }

    public static void decryptfile(File readFrom, File saveTo, Algorithm cipher){
        BufferedReader br = null;
        try{
            PrintWriter out = new PrintWriter(saveTo);
            br = new BufferedReader(new FileReader(readFrom));
            String line;
            while (br.ready()) {
                line = br.readLine();
                String[] words = line.split(" ");
                for(String tmp:words){
                    System.out.println(cipher.decrypt(tmp));
                    out.print(cipher.decrypt(tmp+" "));
                }
            }
            out.close();
        } catch(IOException e){}
    }

}
