package exception;

public class FolderDoesntExistException extends Exception{
    public FolderDoesntExistException(String message){
        super(message);
    }
}
