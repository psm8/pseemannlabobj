import exceptions.WrongParamsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyLineChart {
    double[] coeffs;
    double rangeStart;
    double rangeEnd;
    int accurancy;
    double[] xData;
    double[] yData;

    public void setParams(String _coeffs, String _rangeStart, String _rangeEnd, String _accurancy) throws Exception{
        this.coeffs = convertStringArrayToIntArray(_coeffs.split(","));
        this.rangeStart = Double.parseDouble(_rangeStart);
        this.rangeEnd = Double.parseDouble(_rangeEnd);
        this.accurancy = Integer.parseInt(_accurancy);
        if(rangeStart>=rangeEnd){
            throw new WrongParamsException();
        }
        if(accurancy<=0){
            throw new WrongParamsException();
        }
    }

    public double[] getLineChartXData(){
        prepareXData();
        return this.xData;
    }

    public double[] getLineChartYData(){
        prepareYData();
        return this.yData;
    }

    private void prepareXData(){
        this.xData = new double[this.accurancy];
        for (int i = 0; i < accurancy; i++) {
            xData[i] = rangeStart + i * (rangeEnd-rangeStart)/(accurancy-1);
        }
    }

    private void prepareYData(){
        this.yData = new double[this.xData.length];
        for (int i = 0; i < xData.length; i++) {
            this.yData[i] = 0;
            for (int j = coeffs.length-1; j >= 0; j--) {
                this.yData[i] += coeffs[j]*Math.pow(xData[i],j);
            }
        }
    }

    private static double[] convertStringArrayToIntArray(String[] num){
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
