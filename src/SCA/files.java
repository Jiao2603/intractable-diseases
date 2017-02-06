package SCA;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * Created by jiao.xue on 2017/02/02.
 *
 */
public class files {

    public static int defaultGeneLength = 17;//17項目


     //clinical manifestation of SCAのexcel情報を読み込む
    //file: file name
    //type: sporadic=1,autosomal dominat=2....の位置の番号
    public static double[] standard(File file, int type) throws IOException {

        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);//excelの中一つのシートしかない

        double[] percentage = new double[defaultGeneLength];

       // int rowstart = hssfSheet.getFirstRowNum();
        int rowstart=2;//第３行から
        int rowEnd = hssfSheet.getLastRowNum();
        String cellValue = "";
        for (int i = rowstart; i <= rowEnd; i++) {

            HSSFRow row = hssfSheet.getRow(i);//第i行
            if (null == row) continue;
            int cellStart = row.getFirstCellNum();
            int cellEnd = row.getLastCellNum();
            HSSFCell cell = row.getCell(type);        //i行第type列
            if (null == cell) {
                percentage[i - rowstart]=0;//空欄処理
            }
            else percentage[i - rowstart] = (double) cell.getNumericCellValue();//
            //System.out.println(percentage[i - rowstart]);

        }
        return percentage;

    }


 /*   public static void main(String args[]) throws Exception {
        File file = new File("/Users/jiao.xue/Dropbox/SCA/SCA.xls");
       int type=6;
        standard(file, type);

        double[] population= new double[defaultGeneLength];
        population=initialization.Seed();
        for (int i=0;i<population.length;i++){
            System.out.println(population[i]);
        }

    }
*/


    }
