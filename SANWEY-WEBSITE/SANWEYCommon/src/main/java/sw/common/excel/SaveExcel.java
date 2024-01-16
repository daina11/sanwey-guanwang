package sw.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2016/8/29.
 */
public class SaveExcel {

    public static SaveExcel execute(HSSFWorkbook book, String filePath, String fileName) {
        SaveExcel saveExcel=new SaveExcel();
        saveExcel.saveExcel(book,filePath,fileName);
        return saveExcel;
    }

    private void saveExcel(HSSFWorkbook book, String filePath, String fileName) {
        //检测保存路劲是否存在，不存在则新建
        checkFilePathIsExist(filePath);
        //将Excel保存至指定目录下
        fileName = getFileName(fileName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + "\\" + fileName + ".xls");
            book.write(out);
            System.out.println("——————————————————保存Excel文件成功，保存路径：" + filePath + "\\" + fileName + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("——————————————————保存Excel文件失败。exportExcelForListAndSave,message："+e.getMessage());
        }finally{
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkFilePathIsExist(String filePath) {
        File file = new File(filePath);

        if(!file.exists()){
            file.mkdirs();
        }
    }

    private String getFileName(String fileName) {
        if(fileName == null || "".equals(fileName)){
            //日期
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH24mmss");
            //随机数
            Random random = new Random();
            fileName = sdf.format(date) + String.valueOf(Math.abs(random.nextInt() * 1000000));
        }
        return fileName;
    }

}
