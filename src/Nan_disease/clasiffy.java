package Nan_disease;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiao on 2017/02/24.
 */
public class clasiffy {
    public static int main=8;//主症状、8種類
    public static int sub=9;//副症状：9種類
    public static int another=9;//検査所見：9種類
    public static int defaultGeneLength= main+sub+another;
    public static HashMap<Double,Integer> DB_patient = new HashMap<>(); //症状がキー、人数が値

    public static void main(String arg[]) {


        File Dir = new File("/Users/jiao/Dropbox/難病/特定疾患");
        String file_name = "010";
        File list=new File(Dir.getPath()+"/"+file_name+"/data/list.csv");//読み用

        File DB=new File(Dir.getPath()+"/"+file_name+"/data/DB.csv");//症状の可能な組み合わせ
        try {
            PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(DB, false), "SHIFT_JIS"));
            //症状名の書き込み
            output.write("type,口腔粘膜の再発性アフタ性潰瘍,（ａ）結節性紅斑様皮疹,（ｂ）皮下の血栓性静脈炎,（ｃ）毛嚢炎様皮疹，ザ瘡様皮疹,（ａ）虹彩毛様体炎,（ｂ）網膜ぶどう膜炎（網脈絡膜炎),（ｃ）（ａ）（ｂ）を経過した症状,外陰部潰瘍,");
            output.write("変形や硬直を伴わない関節炎,副睾丸炎,腹痛," +
                    "潜血又は下血," +
                    "血管障害," +
                    "小血管障害," +
                    "頭痛," +
                    "麻痺," +
                    "精神症状,");
            output.write("（１）HLA-B51（B5）の陽性," +
                    "皮膚の針反応," +
                    "レンサ球菌ワクチン-プリックテスト," +
                    "赤沈値の亢進（＞30mm),血清CRPの陽性化（＞１．０),末梢血白血球数の増加（＞１０，０００）,補体価の上昇（＞40単位）," +
                    "リンパ球性血管炎と脂肪組織炎,壊死性血管炎," );
            output.write("人数");

            BufferedReader br = new BufferedReader(new FileReader(list));
            String str ;
            while((str=br.readLine())!= null) {
                int[] record= new int[main+sub+another];
                String[] pair = str.split(",");

                for(int i=0;i<pair.length-1;i++) {
                    if(pair[i+1].equals("1")||pair[i+1].equals("2")) {
                        record[i] = Integer.parseInt(pair[i + 1]);
                       // System.out.print(record[i]);
                    }
                    else{
                        record[i] =0;
                        //System.out.print(record[i]);
                    }
                }
                System.out.print(types(record));
                if (!DB_patient.containsKey(types(record))) {
                    DB_patient.put(types(record), 1);
                    System.out.println(","+ DB_patient.get(types(record)));
                }
                else{
                    int num=DB_patient.get(types(record));
                    DB_patient.put(types(record), num+1);
                    System.out.println(","+ DB_patient.get(types(record)));
                }
            }
            for(Map.Entry<Double,Integer> entry:DB_patient.entrySet()){
                output.write("\n"+entry.getKey()+",");
                int[] property= change(entry.getKey());

                for(int j=0;j<defaultGeneLength;j++){//変換
                    output.write(property[j]+",");
                }
                int a=entry.getValue();

                    output.write("," + a);
            }







            output.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }

    }

    public static double types(int[] record){
        double type=0.0;
        for(int i=0;i<record.length;i++){
            type+=(double)record[i]*Math.pow(3,i);
        }
        return type;
    }

    public static int[] change(double x){
        int[] property= new int[defaultGeneLength];
        for(int i=0;i<defaultGeneLength;i++){
            property[i]=0;

        }
        for(int i=0;x>=1.0;i++){
            property[i]=((int)x%3);
            x=(int)x/3;
        }
        return property;
    }
}
