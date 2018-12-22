import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MaxMultiThread {
    final  static int NTASK =4;
    public static void calculate(double[] coeffs) throws InterruptedException, ExecutionException {
        ArrayList<double[]> arguments = readFromFile("data/randoms.txt");
        File file = new File("data/results.txt");
        try {
            PrintWriter saveTo = new PrintWriter(file);
            ArrayList<FutureTask<String[]>> tasks = new ArrayList<>();
            for( int i=0; i<NTASK; i++){
                tasks.add(new FutureTask<>(new CalculateMulti(coeffs,arguments,100*i/NTASK,100*(i+1)/NTASK)));
            }
            for( int i=0; i<NTASK; i++){
                new Thread(tasks.get(i)).start();
            }
            /*Lepiej zapisywac pojedynczo*/
            for (int i = 0; i < NTASK; i++) {
                for (int j = 0; j < 25; j++) {
                    saveTo.println(tasks.get(i).get()[j]);
                }
            }
            saveTo.close();
        } catch (FileNotFoundException e){ e.printStackTrace(); }
    }

    public static ArrayList<double[]> readFromFile(String fileURI){
        ArrayList<double[]> list = new ArrayList<>();


        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileURI), StandardCharsets.UTF_8);

            for(int i = 0; i<lines.size(); i++){
                double[] fArray = convertStringArrayToDoubleArray(lines.get(i).split(","));
                list.add(fArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static double[] convertStringArrayToDoubleArray(String[] num){
        if (num != null) {
            double fArray[] = new double[num.length];
            for (int i = 0; i <num.length; i++) {
                fArray[i] = Double.parseDouble(num[i]);
            }
            return fArray;
        }
        return null;
    }
}
