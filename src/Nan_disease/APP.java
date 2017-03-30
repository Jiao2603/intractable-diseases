package Nan_disease;

import java.io.IOException;

import static Nan_disease.FitnessCalc.solution;
import static Nan_disease.FitnessCalc.condition_pro;

/**
 * Created by jiao on 2017/03/29.
 */
public class APP {
    public static int main=8;//主症状、8種類
    public static int sub=9;//副症状：9種類
    public static int another=9;//検査所見：9種類
    public static int defaultGeneLength= main+sub+another;

    public static void main(String args[]) throws IOException {
        FitnessCalc.calculation();
//1は症状あり、2は症状なし、不明と空欄のデータを除く
        int illness[]={1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        double pro=0;//症状から病気を判断できる確率
        if(illness.length!=defaultGeneLength){
            System.out.println("input error");
        }
        else{

            double a=1;
            double b=1;
            for(int i=0;i<defaultGeneLength;i++){

                if(illness[i]==1){
                    a*=solution[i];
                    b*=condition_pro[i];
                }
                else if(illness[i]==2){
                    a*=(1-solution[i]);
                    b*=(1-condition_pro[i]);
                }
            }
            pro=a*solution[defaultGeneLength]/b;
        }
        System.out.println("probability is"+pro);
    }
}
