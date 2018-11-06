package appl;

import excp.NieCalkowite;
import excp.TooBigNumberException;

import java.io.File;
import java.io.PrintWriter;

public class Calculator implements Savable {
    private double result;

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.Add(7);
        calc.Subtract(6);
        calc.Multiply(4);
        calc.Divide(1);
        try {
            calc.Factorial();
        }catch(TooBigNumberException | NieCalkowite e){System.out.println("Couldnt calculate factorial");}
        System.out.println(calc.getResult());
        calc.SaveToFile(calc.getResult(),"data/result.txt");
    }
    public Calculator(){
        result = 0;
    }
    public void Add(double x){
        this.result += x;
    }
    public void Subtract(double x){
        this.result -= x;
    }
    public void Multiply(double x){
        this.result *= x;
    }
    public void Divide(double x){
        this.result /= x;
    }
    public void Factorial() throws TooBigNumberException, NieCalkowite {
        if(this.result > 10){
            throw new TooBigNumberException();
        }
        if((this.result == Math.floor(this.result)) && !Double.isInfinite(this.result)){
            throw new NieCalkowite();
        }
        double tmp =1;
        for(int i=1;i<=this.result;i++){
            tmp *= i;
        }
        this.result = tmp;
    }
    public double getResult(){
        return this.result;
    }
    public void SaveToFile(double x, String filename){
        try{
            File saveTo = new File(filename);
            PrintWriter output = new PrintWriter(saveTo);
            output.print(x);
            output.close();

        }catch(Exception e){e.printStackTrace();}
    }


}
