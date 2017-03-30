package Nan_disease;

/**
 * Created by jiao on 2017/03/29.
 */
public class Individual {
    public static int main=8;//主症状、8種類
    public static int sub=9;//副症状：9種類
    public static int another=9;//検査所見：9種類
    public static int defaultGeneLength= main+sub+another;
    public static double[] genes= new double[defaultGeneLength+1];
    //condition_pro P(症状１|病気);　病気になった時、症状が表す確率、統計値から計算できる
    public static double[] condition_pro= new double[defaultGeneLength];
    // 個体の適応値
    private double fitness = 0;


    // 最初個体を作る
    //genes[defaultGeneLength]=P(病気)
    public void generateIndividual() {
        int i=0;
        while (i < defaultGeneLength+1){
            genes[i]=Math.random();
            i++;
           // System.out.println(genes[i]);
        }
        while(genes[defaultGeneLength]==0){//P(ill)は０じゃない
            genes[defaultGeneLength]=Math.random();
        }
    }

    /* Getters and setters */
    // 遺伝子の長さが違ったら、lengthの値にします
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }

    public double getGene(int index) {
        return genes[index];
    }
    public void setGene(int index, double value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    //サイズ測る
    public int size() {
        return genes.length;
    }

    //適応値

    public double getFitness() {
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

