import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GenerateRandoms {
    static final int NTASK = 4;
    public static void generateRandoms() throws InterruptedException, ExecutionException {
        File file = new File("data/randoms.txt");
        try {
            PrintWriter saveTo = new PrintWriter(file);
            ArrayList<FutureTask<String[]>> tasks = new ArrayList<>();
            for (int i = 0; i < NTASK; i++) {
                tasks.add(new FutureTask<>(new GenerateRandomsMultithread()));
            }
            for (int i = 0; i < NTASK; i++) {
                new Thread(tasks.get(i)).start();
            }
            /*Lepiej zapisywac pojedynczo*/
            for (int i = 0; i < NTASK; i++) {
                for (int j = 0; j < 25; j++) {
                    saveTo.println(tasks.get(i).get()[j]);
                }
            }
            saveTo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
