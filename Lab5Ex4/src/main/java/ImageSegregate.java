import exception.FileIsNotPhotoException;
import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageSegregate{
    public static void segregateImage(Indico indico, File file, String dir) throws FileIsNotPhotoException, IndicoException{
        try {
            Boolean validImage = isImage(file);
            if (validImage) {
                IndicoResult single = indico.imageRecognition.predict(file);
                Map<String, Double> result = single.getImageRecognition();
                Map.Entry<String, Double> maxEntry = null;
                for (Map.Entry<String, Double> entry : result.entrySet()) {
                    if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                        maxEntry = entry;
                    }
                }
                String folderName = maxEntry.getKey();
                String[] parts = dir.split("/");
                new File(parts[0] + "/" + folderName).mkdir();
                file.renameTo(new File(parts[0] + "/" + folderName + "/" + file.getName()));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static Boolean isImage(File file) throws IOException,FileIsNotPhotoException{
        Image image = ImageIO.read(file);
        if (image == null){
            throw new FileIsNotPhotoException("FileIsNotPhotoException");
        }
        return true;
    }
}
