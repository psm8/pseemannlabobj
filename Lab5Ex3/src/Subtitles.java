import exception.FrameInBeforeFrameOutException;
import exception.FrameNumIsNotNumException;

public class Subtitles {
    public static String delay(final String in, final String out,int delay, int fps) throws FrameInBeforeFrameOutException, FrameNumIsNotNumException {
        for(int i=0;i<in.length();i++){
            if(!Character.isDigit(in.charAt(i))){
                throw new FrameNumIsNotNumException("FrameNumIsNotNumException");
            }
        }
        if(Integer.parseInt(in)>Integer.parseInt(out)){
            throw new FrameInBeforeFrameOutException("FrameInBeforeFrameOutException");
        }
        int inDelayed = Integer.parseInt(in) + (delay*fps)/1000;
        int outDelayed = Integer.parseInt(out) + (delay*fps)/1000;
        return "{"+String.valueOf(inDelayed)+"}{"+String.valueOf(outDelayed)+"}";
    }
}
