import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class GenerateRandomsMultithread implements Callable<String[]> {
    @Override
    public String[] call() {
        String[] randoms = new String[25];
        for (int i = 0; i < 25; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < 9999; j++) {
                line.append(Math.random()+",");
            }
            line.append(Math.random());
            randoms[i] = line.toString();
        }
        return randoms;
    }
}
