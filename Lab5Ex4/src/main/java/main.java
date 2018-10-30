import exception.FileIsNotPhotoException;
import exception.FolderDoesntExistException;
import exception.NoInternetConnectionException;
import io.indico.Indico;
import io.indico.api.utils.IndicoException;

import java.io.File;


public class main {
    public static void main(String[] args) {
        try {
            File dir = null;
            try {
                dir = ValidDirectoryCreator.createDirectory(args[0]);
            }catch(FolderDoesntExistException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
            try {
                InternetConnection.check();
            }catch(NoInternetConnectionException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
            Indico indico = null;
            boolean CorrectKey =false;
            do{
                try {
                    indico = new Indico(JIn.getApiKey());
                    indico.imageRecognition.predict("data/foto/test");
                }catch(IndicoException e){
                    if(e.getMessage().equals("imagerecognition failed with error Invalid API key")){
                        System.out.println("Invalid API key");
                    }
                    else{
                        CorrectKey =true;
                    }
                }
            }while(CorrectKey == false);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    System.out.println(child.getName());
                    try {
                        ImageSegregate.segregateImage(indico, child, args[0]);
                    }catch(FileIsNotPhotoException e ){
                        System.out.println(e.getMessage());
                    }catch(IndicoException e){
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
