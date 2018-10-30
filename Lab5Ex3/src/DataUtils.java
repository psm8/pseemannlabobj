import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<String> ReadFromFile(File readFrom) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(readFrom));
        while (br.ready()) {
            lines.add(br.readLine());
        }
        return lines;
    }
}
