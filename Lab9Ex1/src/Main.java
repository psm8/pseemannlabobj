import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        GenerateRandoms.generateRandoms();
        double[] coeffs = {1,0,1};
        MaxMultiThread.calculate(coeffs);
    }
}
