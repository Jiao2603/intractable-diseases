package SCA;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by jiao.xue on 2017/02/02.
 */
public class initialization {

    //個体の長さ
    public static int defaultGeneLength = 17;//24時間
    //今回は10000人と想定する
    public static final int NO_OF_PARAMETERS = 100000;


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


    //全ての個体が持つ行列の足し算、GAの種を作る
    //unit[NO_OF_PARAMETERS] unit[i]の範囲０〜pow(2,17)
    public static double[] Seed(int unit[]) {

        double[] Seed = new double[defaultGeneLength];
        // byte[] genes = new byte[defaultGeneLength];
        for (int i = 0; i < unit.length; i++) {
            byte[] genes = trans(unit[i]);
            for (int j = 0; j < genes.length; j++) {
                Seed[j] = (double) genes[j] / NO_OF_PARAMETERS + Seed[j];
            }
        }


        return Seed;
    }


    //０〜pow(2,17)の数値をbyteに変換

    public static byte[] trans(int num) {
        char[] chs = {'0', '1'};
        char[] arr = new char[defaultGeneLength];
        byte[] brr = new byte[defaultGeneLength];

        for (int x = 0; x < arr.length; x++) {
            arr[x] = ('0');
        }
        int pos = 0;
        while (num != 0) {
            int temp = num & 1;
            arr[pos++] = chs[temp];
            num = num >>> 1;
        }

        for (int x = 0; x < arr.length; x++) {
            brr[x] = (byte) Integer.parseInt(String.valueOf(arr[x]));
        }
        return brr;

    }



    public static void main(String[] args) {
       int[] genes = generateIndividual();
        double[] arr = Seed(genes);
        for (int x = 0; x < arr.length; x++) {
            System.out.println(arr[x]);

        }

    }
}
