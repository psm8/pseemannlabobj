import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CalculateMulti implements Callable<String[]> {
    private final double[] coeffs;
    private final ArrayList<double[]> arguments;
    private final int from;
    private final int to;


    public CalculateMulti(double[] _coeffs, ArrayList<double[]> _arguments, int _from, int _to) {
        this.coeffs = _coeffs;
        this.arguments = _arguments;
        this.from = _from;
        this.to = _to;


    }
    @Override
    public String[] call() {
        String[] results = new String[25];
        for (int i = from; i < to; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < 9999; j++) {
                double result = 0;
                for (int k = 0; k < coeffs.length; k++) {
                    result += coeffs[k] * Math.pow(arguments.get(i)[j],coeffs.length-k-1);
                }
                line.append(result+",");
            }
            double result = 0;
            for (int k = 0; k < coeffs.length; k++) {
                result += coeffs[k] * Math.pow(arguments.get(i)[arguments.get(i).length-1],coeffs.length-k-1);
            }
            line.append(result);
            results[i%25] = line.toString();
        }
        return results;
    }
}
