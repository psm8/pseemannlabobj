import java.io.*;

public class LiczbyPierwsze {
    private int num;
    public LiczbyPierwsze(int _num) {
        num = _num;
    }
    public boolean IsPrime(int _num){
        for (int i = 2; i*i<= _num; i++) {
            if (_num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public void Find(){
        for (int i = 2; i< num; i++) {
            if(IsPrime(i)){
                System.out.println(i);
            }
        }
    }
}
