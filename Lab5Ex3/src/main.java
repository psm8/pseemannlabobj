import exception.FrameInBeforeFrameOutException;
import exception.FrameNumIsNotNumException;

import java.io.*;
import java.util.List;

public class main{
    public static void main(String[] args){
        File readFrom = new File(args[0]);
        File saveTo = new File(args[1]);
        int delay = Integer.parseInt(args[2]);
        int fps = Integer.parseInt(args[3]);
        try{
            PrintWriter output = new PrintWriter(saveTo);
            List<String> lines = DataUtils.ReadFromFile(readFrom);
            for(int i=0; i<lines.size();i++) {
                String line = lines.get(i);
                String[] words = line.split("}");
                String in = words[0].replace("{","");
                String out = words[1].replace("{","");
                try {
                    output.println(Subtitles.delay(in, out, delay, fps) + words[2]);
                }catch(FrameInBeforeFrameOutException e){
                    System.out.println((i+1)+" "+line+" "+e.getMessage());
                }catch(FrameNumIsNotNumException e){
                    System.out.println((i+1)+" "+line+" "+e.getMessage());
                }catch(Exception e){
                    System.out.println((i+1)+" "+line+" "+"UnknownException");
                }
            }
            output.close();
        } catch(Exception e){e.printStackTrace();}
    }
}
