import exception.NoInternetConnectionException;

import java.net.InetAddress;

public class InternetConnection {
    public static void check() throws NoInternetConnectionException {
        boolean reachable = false;
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 www.google.com");
            int returnVal = p1.waitFor();
            reachable = (returnVal == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(reachable == false){
            throw new NoInternetConnectionException("NoInternetConnectionException");
        }
    }
}
