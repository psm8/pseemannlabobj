import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        File readFrom = new File(args[0]);
        File saveTo = new File(args[1]);
        System.out.println("Szyfruj - 1");
        System.out.println("Deszyfruj - 2");
        Scanner sc = new Scanner(System.in);
        int operationType = sc.nextInt();
        System.out.println("ROT11 - 1");
        System.out.println("Polibiusz - 2");
        int cipherType = sc.nextInt();
        if(cipherType == 1 && operationType == 1 ) {
            Cryptographer.cryptfile(readFrom, saveTo, new ROT11());
        }
        else if(cipherType == 2 && operationType == 1 ) {
            Cryptographer.cryptfile(readFrom, saveTo, new Polibiusz());
        }
        else if(cipherType == 1 && operationType == 2 ) {
            Cryptographer.decryptfile(readFrom, saveTo, new ROT11());
        }
        else{
            Cryptographer.decryptfile(readFrom, saveTo, new Polibiusz());
        }

    }
}
