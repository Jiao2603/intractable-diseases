package SCA;


import ga.core.IIndividual;
import ga.realcode.TRealNumberIndividual;
import ga.realcode.TUndxMgg;
import ga.realcode.TVector;

import java.io.*;
import java.util.List;

/**
 * Created by jiao.xue on 2017/02/02.
 * GAの評価関数を作る
 */
public class evaluate {
    //個体数
    public static final int POPULATION_SIZE = 100;
    //個体の長さ
    public static final int defaultGeneLength = 17;//24時間
    //今回は10000人と想定する
    public static final int NO_OF_PARAMETERS =100000;
    //GA用の種
    private static int[] Seed = new int[defaultGeneLength];

    public static final int NO_OF_CROSSOVERS = 100;//交叉回数

    public static final double MIN = 0;//閾値
    public static final double MAX = Math.pow(2,defaultGeneLength);//閾値

    public static double[] standard =new double[defaultGeneLength];



    private static double map(double x)//変数の定義域へ写像する。
    {
        return (MAX - MIN) * x + MIN;
    }


    //評価値を計算する
    //unit[NO_OF_PARAMETERS] unit[i]の範囲０〜pow(2,17)
    static double evaluateValue_cal(int[] unit)  {
        double[] seed=initialization.Seed(unit);
        double evaluationValue = 0.0;
        for(int i=0;i<defaultGeneLength;i++){
            evaluationValue+=Math.abs(seed[i]-standard[i]);
            //System.out.println(evaluationValue+"="+standard_sleep_time[i]+"X"+population[i]);

        }
        return evaluationValue;

    }


    //個体を評価
    private static void evaluateIndividual(TRealNumberIndividual ind) throws IOException {
        TVector v = ind.getVector();
        double evaluatonValue = 0.0;// v のdemension=10000
        int[] individual=new int [NO_OF_PARAMETERS];

        for (int i = 0; i < v.getDimension(); ++i) {
            int x =(int) map(v.getData(i));
            //System.out.println(x);

            if (x < MIN || x > MAX) {
                ind.setStatus(IIndividual.INVALID);
                return;
            }
            individual[i]=x;

        }
        evaluatonValue=evaluateValue_cal(individual);
        //System.out.println(evaluatonValue);
        ind.setEvaluationValue(evaluatonValue);
        ind.setStatus(IIndividual.VALID);
    }

    //populationを評価

    public static void evaluatePopulation(List<TRealNumberIndividual> pop) throws IOException {
        for (int i = 0; i < pop.size(); ++i) {
            evaluateIndividual(pop.get(i));
        }
    }



    //結果を書き出す

    public static void printIndividual(TRealNumberIndividual ind) {
        System.out.println("Evaluation value: " + ind.getEvaluationValue());
        TVector v = ind.getVector();
        for (int i = 0; i < v.getDimension(); ++i) {
            System.out.print((int)map(v.getData(i)) + " ");
        }
        System.out.println();
    }



    public static void print_Individual(TRealNumberIndividual ind, File outfile)  {

        try{
            PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outfile, false), "Shift_JIS"));//結合した結果を新しいファイル'out'に保存する

            System.out.println("Evaluation value: " + ind.getEvaluationValue());
            TVector v= ind.getVector();
            double x = 0;
            output.write("result");
            for(int i = 0; i<v.getDimension(); ++i) {
                x = map(v.getData(i));
                output.write("\n"+x);//四捨五入
                System.out.println("result"+i+" is "+x);

            }
            output.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }




    //public static void ga_calculation(File infile1, int sheet_num1, File infile2, int sheet_num2, File outfile) throws IOException {
        public static void main(String args[]) throws Exception {
            File file = new File("/Users/jiao.xue/Dropbox/SCA/SCA.xls");
            int type=6;
            standard =files.standard(file, type);

//GA
        TUndxMgg ga=  new TUndxMgg(true,NO_OF_PARAMETERS,POPULATION_SIZE,NO_OF_CROSSOVERS);
        //   do{
        ga = new TUndxMgg(true,NO_OF_PARAMETERS,POPULATION_SIZE,NO_OF_CROSSOVERS);

        List<TRealNumberIndividual> initialPopulation = ga.getInitialPopulation();
        evaluatePopulation(initialPopulation);
        System.out.println(  ga.getBestEvaluationValue());
        //   } while(ga.getBestEvaluationValue()>10);


      for(int i =0; i<1000; ++i)//500回を回す

        //for(int i =0; ga.getBestEvaluationValue()>1; ++i)//回す　until評価値は１より小さい
        {
            List<TRealNumberIndividual> family = ga.selectParentsAndMakeKids();
            evaluatePopulation(family);
            List<TRealNumberIndividual> nextPop = ga.doSelectionForSurvival();
            System.out.println( ga.getIteration() + " " + ga.getBestEvaluationValue() + " " + ga.getAverageOfEvaluationValues());
        }

        System.out.println();
        System.out.println("Best individual");
            printIndividual(ga.getBestIndividual());
       // print_best_unit(ga.getBestIndividual(),outfile);
    }



}
