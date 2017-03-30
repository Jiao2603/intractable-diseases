package Nan_disease;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jiao on 2017/03/29.
 */
public class FitnessCalc {
    //個体総数
    public static final int POPULATION_SIZE = 100;
    public static int main=8;//主症状、8種類
    public static int sub=9;//副症状：9種類
    public static int another=9;//検査所見：9種類
    public static int defaultGeneLength= main+sub+another;
    static double[] solution = new double[defaultGeneLength+1];
    //condition_pro P(症状１|病気);　病気になった時、症状が表す確率、統計値から計算できる
    static double[] condition_pro= new double[defaultGeneLength];
    //回す回数
    public static int times = 100;
    static File Dir = new File("/Users/jiao/Dropbox/難病/特定疾患");
    static String file_name = "010";
    static File list=new File(Dir.getPath()+"/"+file_name+"/data/list.csv");//読み用
    static File DB=new File(Dir.getPath()+"/"+file_name+"/data/DB.csv");//症状の可能な組み合わせ

//計算
    public static void calculation() throws IOException {
    //    public static void main(String args[]) throws IOException {
        condition_pro_value();

        // 初期化
        Population myPop = new Population(POPULATION_SIZE, true);
        Individual best=getBestIndividual(myPop);//////////

        // リサイクル

        int generationCount = 0;
        int t=0;
        while(generationCount<times ){//
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: "
                    + getBestIndividual(myPop).getFitness());
            myPop = Algorithm.evolvePopulation(myPop);
            ///////////////////
            Individual new_best=getBestIndividual(myPop);
            if(new_best==best){
                t++;
            }
            else {
                t=0;
                best=new_best;
            }
        }

        Individual result=getBestIndividual(myPop);
        System.out.println("Best Gene:");
        for(int i=0;i<result.size();i++){
            solution[i]=result.getGene(i);
            System.out.println(result.getGene(i));
        }

    }


    //病気になった時、症状が表す確率P(症状１|病気)、P(症状2|病気)...を統計値から計算
    public static void condition_pro_value() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(list));
            String str;
            int num=0;//ファイルの行数を記録
            while ((str = br.readLine()) != null) {
                num++;
                String[] pair = str.split(",");
               // if(pair.length==defaultGeneLength+1) {
                    for (int i = 0; i < pair.length-1; i++) {
                        if (pair[i + 1].equals("1")) {
                            condition_pro[i]++;

                        }
                    }
                //}
            }
            for(int i=0;i<condition_pro.length;i++){
                condition_pro[i]=condition_pro[i]/num;
                System.out.println(condition_pro[i]);//print
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }


    //geneから計算用の評価関数を作る
    public static double getFitness( Individual individual) {

        double evaluatevalue=0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(DB));
            String str;
            str = br.readLine();
            str = br.readLine();//最初の２行を飛ばす
            while ((str = br.readLine()) != null) {

                double record_value=1;
                double condition_value=1;
                String[] pair = str.split(",");
//1は症状あり、2は症状なし、不明と空欄のデータを除く
                for(int i=0;i<defaultGeneLength;i++) {
                    if (pair[i+1] == "1") {
                        condition_value*=condition_pro[i];
                        record_value *= individual.getGene(i);
                    }
                    //else{
                    else  if (pair[i+1] == "2") {
                        condition_value*=1-condition_pro[i];
                        record_value *= (1-individual.getGene(i));
                    }

                }
                double value=condition_value*individual.getGene(defaultGeneLength)-record_value;
                evaluatevalue+=value*value;

            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return evaluatevalue;
    }

    //populationを評価する
    //適応値が一番小さい方のfitnessを記録する
    static double getBestFitness(Population population) throws IOException {
        Individual individual;
        double BestFitness=999999;
        for(int i=0;i<population.size();i++){
            individual= population.getIndividual(i);
            if(getFitness(individual)<BestFitness){
                BestFitness=getFitness(individual);
            }
        }

        return BestFitness;
    }

    //populationを評価する
    //適応値が一番小さい方のfitnessを記録する
    static Individual getBestIndividual(Population population) throws IOException {
        Individual individual;
        Individual Bestindividual= population.getIndividual(0);
        double BestFitness=999999;
        for(int i=0;i<population.size();i++){
            individual= population.getIndividual(i);
            if(getFitness(individual)<BestFitness){
                BestFitness=getFitness(individual);
                Bestindividual=individual;
            }
        }

        return Bestindividual;
    }


}
