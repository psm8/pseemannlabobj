import exception.FolderDoesntExistException;

import java.io.File;

public class ValidDirectoryCreator {
    public static File createDirectory(String fileName) throws FolderDoesntExistException{
        File dir = new File(fileName);
        if(!dir.isDirectory()){
            throw new FolderDoesntExistException("FolderDoesntExistException");
        }
        return dir;
    }
}
