package sw.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import sw.common.file.FileUtils;
import sw.common.util.DateFormatUtils;
import sw.common.util.SessionUtils;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * @author wwh
 * @date 2022/12/5-11:30
 * @email 644129971@qq.com
 */
public class ExcelExportUtils {

    public static void export(String[] header, String sheetTitle, List<Object> excelList) throws Exception {
        String exporter = "admin";
        if (SessionUtils. currentUser() != null) {
            exporter = SessionUtils. currentUser().getName();
        }
        ExcelExport excelExport = new ExcelExport(exporter);
        HSSFWorkbook sheets = excelExport.exportExcel(header, excelList, sheetTitle, null);
        OutputStream outputStream = FileUtils.downFileHelper(fileName(sheetTitle) + ".xls");
        sheets.write(outputStream);
        sheets.close();
        outputStream.close();
    }

    private static String fileName(String fileName) {
        StringBuffer stringBuffer = new StringBuffer(fileName);
        stringBuffer.append("-");
        stringBuffer.append(DateFormatUtils.format(new Date()));
        stringBuffer.append("-");
        stringBuffer.append(String.valueOf(System.currentTimeMillis()).substring(0, 5));
        return stringBuffer.toString();
    }
}
