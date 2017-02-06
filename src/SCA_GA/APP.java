package SCA_GA;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jiao.xue on 2017/02/03.
 */
public class APP {
    public static HashMap<Integer, HashMap<Integer, Double>> DB = new HashMap<>(); //難病DB
    public static int defaultGeneLength = 17;//17項目

    public static void main(String args[]) throws IOException {
        DB=FitnessCalc.make_DB();

        System.out.println("DB.size()"+DB.size());

        //int[] illness = new int[defaultGeneLength];//症状を入力1,1....
        int illness[]={1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        if(illness.length==defaultGeneLength) {
            int ills = trans(illness);//症状の番号に変る

            if (DB.containsKey(ills)) {
                System.out.println(DB.get(ills));
            } else System.out.println("未探索された　または　その症状はSCAではない　");
        }
        else System.out.println("入力間違い");
    }

    public static int trans(int[] ill){
    int ills=0;
        for(int i=0;i<ill.length;i++){
           ills+=ill[i]*Math.pow(2,i);
        }
        return ills;
    }




    }

