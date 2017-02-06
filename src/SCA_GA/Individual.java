package SCA_GA;
import java.io.IOException;

/**
 * Created by jiao.xue on 2017/02/03.
 */
public class Individual {

    //遺伝子の長さ 今回は10000人と想定する
    static  int NO_OF_PARAMETERS =100000;

    public static final int defaultGeneLength = 17;

    //遺伝子行列 bitstring
    private int[] genes = new int[NO_OF_PARAMETERS];

    // 個体の適応値
    private double fitness = 0;

    public static final double MIN = 0;//閾値
    public static final double MAX = Math.pow(2,defaultGeneLength);//閾値

   public static double map(double x)//変数の定義域へ写像する。
    {
        return (MAX - MIN) * x + MIN;
    }

    ////////////////////////////
    // 最初遺伝子個体を作る


    //ランダム
    /*public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            int gene = (int) Math.floor(map(Math.random()));
            genes[i] = gene;
        }
    }
    */
    //

    // 最初個体を作る
    public static int[] generateIndividual() {
        int[] genes = new int[NO_OF_PARAMETERS];
        int i = 0;
        while (i < NO_OF_PARAMETERS) {
            genes[i] = (int) (Math.random() * Math.pow(2, defaultGeneLength));
            i++;
        }
        return genes;
    }

    ////////////////////////////

    /* Getters and setters */
    // 遺伝子の長さが違ったら、lengthの値にします
    public static void setDefaultGeneLength(int length) {
        NO_OF_PARAMETERS  = length;
    }

    public int getGene(int index) {
        return genes[index];
    }
    public void setGene(int index, int value) {
        genes[index] = value;
        fitness = 0;
    }


    /* Public methods */
    //サイズ測る
    public int size() {
        return genes.length;
    }

    //evaluatorの計算値
    public double getFitness() throws IOException {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }

}
