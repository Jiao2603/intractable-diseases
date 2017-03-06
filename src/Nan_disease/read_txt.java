package Nan_disease;


import java.io.*;

/**
 * Created by jiao.xue on 2017/02/23.
 * ファイルの中の難病主症状、副症状、検査所見の項目を抽出、結合する
 */
public class read_txt {
    //5３列から79列までの項目を使う、71列は使わない
    public static int start = 53;//
    public static int end = 79;//
    public static int inter = 71;//

    public static void main(String arg[]) throws FileNotFoundException {
        File Dir=new File("/Users/jiao/Dropbox/難病/特定疾患");
        String file_name="010";
        File file1 = new File(Dir.getPath()+"/"+file_name+"/data/"+file_name+"_2003新規（様式2）.txt");
        File file2 = new File(Dir.getPath()+"/"+file_name+"/data/"+file_name+"_2004新規（様式2）.txt");
        File file3 = new File(Dir.getPath()+"/"+file_name+"/data/"+file_name+"_2005新規（様式2）.txt");
        File file4 = new File(Dir.getPath()+"/"+file_name+"/data/"+file_name+"_2006新規（様式2）.txt");
        File file5 = new File(Dir.getPath()+"/"+file_name+"/data/"+file_name+"_2007新規（様式2）.txt");
        File file6 = new File(Dir.getPath()+"/"+file_name+"/data/"+file_name+"_2008新規（様式2）.txt");
        //File file = new File("/Users/jiao/Dropbox/難病/特定疾患/010/data/010_2003新規（様式2）.txt");

        File list=new File(Dir.getPath()+"/"+file_name+"/data/list.csv");
       make_file(file1,list);
        make_file(file2,list);
        make_file(file3,list);
        make_file(file4,list);
        make_file(file5,list);
        make_file(file6,list);

        BufferedReader br = new BufferedReader(new FileReader(list));

    }


    public static void make_file(File in, File list){
//5３列から79列までの項目を使う、71列は使わない
        try{
            BufferedReader br = new BufferedReader(new FileReader(in));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(list, true), "SHIFT_JIS"));


            String str = br.readLine();
            str = br.readLine();
            str = br.readLine();
            str = br.readLine();//第5行から

            while((str=br.readLine())!= null){
                String[] pair = str.split("\t");
                //String Id_now=pair[52];

                if(pair.length>=end) {
                    output.write(pair[0]+",");
                    for(int i=start-1;i<inter-1;i++) {
                        output.write(pair[i]+",");
                    }
                    for(int i=inter;i<end;i++) {

                        output.write(pair[i]+",");

                    }
                    //System.out.println(pair.length);
                }
                else if(pair.length>=start-1){
                    output.write(pair[0]+",");
                    if(pair.length<70) {
                        for (int i = start-1; i < pair.length; i++) {
                            output.write(pair[i] + ",");

                        }
                    }
                    else{
                        for(int i=start-1;i<inter-1;i++) {
                            output.write(pair[i]+",");
                        }
                        for(int i=inter;i<pair.length;i++) {

                            output.write(pair[i]+",");

                        }

                    }
                }
                else{
                    output.write(pair[0]+",");
                }
                output.write("\n");
            }

            br.close();
            output.close();
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
