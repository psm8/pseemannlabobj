package Lab7EX3;

import Lab7EX3.exception.FileIsNotPhotoException;
import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ImageSegregate{
    public static Map<String, Double> segregateImage(Indico indico, File file) throws FileIsNotPhotoException, IndicoException{
        Map<String, Double> result = null;
        try {
            Boolean validImage = isImage(file);
            if (validImage) {
                IndicoResult single = indico.imageRecognition.predict(file);
                result = single.getImageRecognition();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;

    }
    public static Boolean isImage(File file) throws IOException,FileIsNotPhotoException{
        Image image = ImageIO.read(file);
        if (image == null){
            throw new FileIsNotPhotoException("FileIsNotPhotoException");
        }
        return true;
    }
}
