package sw.common.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import sw.common.util.DateFormatUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wwh on 2016/8/29.
 */
public class ExcelExport {
    /**
     * 时间格式：默认为yyyy-MM-dd
     */
    private String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 图片宽度，默认为：100
     */
    private int IMAGE_WIDTH = 30;
    /**
     * 图片高度，默认为：50
     */
    private int IMAGE_HEIGHT = 5;
    /**
     * 导出人
     */
    private String exporter;

    public ExcelExport(String exporter){
        this.exporter=exporter;
    }

    public HSSFWorkbook exportExcelAndSave(String[] header, List<Object> excelList, String sheetTitle, String filePath, String fileName, Object orderContent) {
        //生成Excel
        HSSFWorkbook book = exportExcel(header, excelList, sheetTitle, orderContent);
        //保存生成的Excel
        SaveExcel.execute(book, filePath, fileName);
        return book;
    }

    public HSSFWorkbook exportExcel(String[] header, List<Object> excelList, String sheetTitle, Object orderContent) {
        //生成一个Excel
        HSSFWorkbook book = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = book.createSheet(sheetTitle);
        //设置Excel里面数据
        setExcelContentData(book, sheet, header, excelList,orderContent);
        return book;
    }

    private void setExcelContentData(HSSFWorkbook book, HSSFSheet sheet, String[] header, List<Object> excelList, Object orderContent) {
        // 主体内容
        HSSFRow row = createDivHeader(sheet, setHeaderStyle(book, ICellStyle.index, ICellStyle.BOLDWEIGHT_BOLD), header, 0);;
        HSSFCellStyle hssfCellStyle=setBodyStyle(book, ICellStyle.ALIGN_CENTER, ICellStyle.index, ICellStyle.BOLDWEIGHT_NORMAL);
        // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        int index = 0;
        /* 避免在迭代过程中产生的新对象太多，这里讲循环内部变量全部移出来 */
        Object t = null;
        HSSFCell cell = null;
        Field field = null;
        String fieldName = null;
        String getMethodName = null;
        Class tCls = null;
        Method getMethod = null;
        Object value = null;
        BigDecimal bigDecimal=null;
        // 遍历集合数据，产生数据行
        Iterator<Object> it = excelList.iterator();
        int[] maxWidth = new int[header.length];   //初始化单元格宽度
        BigDecimal quantity = new BigDecimal(0);
        BigDecimal total= new BigDecimal(0);
        BigDecimal costing= new BigDecimal(0);
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            // 设置数据列
            t = it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(hssfCellStyle);
                field = fields[i];
                fieldName = field.getName();
                getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  //构建getter方法
                try {
                    tCls = t.getClass();
                    getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    value = (Object) getMethod.invoke(t, new Object[]{});
                    if ("quantity".equals(fieldName)) {
                        bigDecimal=new BigDecimal(String.valueOf(value));
                        quantity=quantity.add(bigDecimal);
                    }
                    if ("postage".equals(fieldName)) {
                        bigDecimal=new BigDecimal(String.valueOf(value));
                        total=total.add(bigDecimal);
                    }
                    if ("total".equals(fieldName)) {
                        bigDecimal=new BigDecimal(String.valueOf(value));
                        costing=costing.add(bigDecimal);
                    }
                    // 将value设置当单元格指定位置
                    setCellData(row, index, i, value, cell, sheet, patriarch, book, maxWidth);
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("——————————————————创建Excel数据列表时出错。method:setDataRow,message：" + e.getMessage());
                }
            }
        }
        /*String date =null;
        int lastRow=sheet.getLastRowNum()+1;

        date = "导出人："+this.exporter+"    日期："+ DateFormatUtils.format(new Date());
        sheet.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 0, 12));
        createexportManagerHeader(date,sheet, setHeaderStyle(book, ICellStyle.ALIGN_LEFT, ICellStyle.BOLDWEIGHT_BOLD), lastRow+1);*/
        //System.out.println("-------------------------填充Excel数据成功..........");
    }

    /**
     * 设置头部行样式
     */
    private HSSFCellStyle setHeaderStyle(HSSFWorkbook book, short color, short boldweight) {
        return getHssfCellStyle(book, color, boldweight);
    }

    /**
     * 设置主体内容样式
     */
    private HSSFCellStyle setBodyStyle(HSSFWorkbook book, short align, short color, short boldweight) {
        HSSFCellStyle hssfCellStyle=getHssfCellStyle(book, color, boldweight);
        hssfCellStyle.setBorderTop(BorderStyle.valueOf(ICellStyle.BORDER_MEDIUM));
        hssfCellStyle.setBorderLeft(BorderStyle.valueOf(ICellStyle.BORDER_MEDIUM));
        hssfCellStyle.setBorderRight(BorderStyle.valueOf(ICellStyle.BORDER_MEDIUM));
        hssfCellStyle.setBorderBottom(BorderStyle.valueOf(ICellStyle.BORDER_MEDIUM));
        hssfCellStyle.setTopBorderColor(ICellStyle.index);
        hssfCellStyle.setLeftBorderColor(ICellStyle.index);
        hssfCellStyle.setRightBorderColor(ICellStyle.index);
        hssfCellStyle.setBottomBorderColor(ICellStyle.index);
        return hssfCellStyle;
    }

    /**单元格样式*/
    private HSSFCellStyle getHssfCellStyle(HSSFWorkbook book, short color, short boldweight) {
        HSSFCellStyle headerStyle = book.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);   //水平居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);//内容位置
        //设置字体
        HSSFFont font = book.createFont();
        font.setFontHeightInPoints((short) 12);     //字号：12号
        font.setBold(true);
        font.setColor(color);   //黑色
        headerStyle.setFont(font);
        return headerStyle;
    }

    /**
     * 自定义行
     */
    private HSSFRow createDivHeader(HSSFSheet sheet, HSSFCellStyle headerStyle, String[] header, Integer index) {
        HSSFRow headRow = sheet.createRow(index);
        //sheet.setColumnWidth(1,3000);
        headRow.setHeightInPoints((short) (30));   //设置头部高度
        //添加数据
        HSSFCell cell = null;
        for (int i = 0; i < header.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headerStyle);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
        }
        return headRow;
    }

    /**
     * 创建日期行
     */
    private HSSFRow createDivDateHeader(HSSFSheet sheet, HSSFCellStyle headerStyle, Integer index) {
        HSSFRow headRow = sheet.createRow(index);
        headRow.setHeightInPoints((short) (20));   //设置头部高度
        //添加数据
        HSSFCell cell = headRow.createCell(0);
        cell.setCellStyle(headerStyle);
        String date = "出单日期：" + new SimpleDateFormat(DATE_PATTERN).format(new Date());
        HSSFRichTextString text = new HSSFRichTextString(date);
        cell.setCellValue(text);
        return headRow;
    }

    /**
     * 列表数据设定
     */
    private void setCellData(HSSFRow row, int index, int i, Object value, HSSFCell cell, HSSFSheet sheet, HSSFPatriarch patriarch, HSSFWorkbook book, int[] maxWidth) {
        String textValue = null;
        if (value instanceof Date) {    //为日期设置时间格式
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            textValue = sdf.format(date);
        }
        if (value instanceof byte[]) {   //byte为图片
            //设置图片单元格宽度、高度
            row.setHeightInPoints((short) (IMAGE_HEIGHT * 10));
            sheet.setColumnWidth(i, IMAGE_WIDTH * 256);
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) i, index, (short) i, index);
            anchor.setAnchorType(ClientAnchor.AnchorType.byId(3));
            //插入图片
            byte[] bsValue = (byte[]) value;
            patriarch.createPicture(anchor, book.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
        } else {   //其余全部当做字符处理
            if (value != null) {
                textValue = String.valueOf(value);
            } else {
                textValue = "";
            }
        }
        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
        if (textValue != null) {
            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher = p.matcher(textValue);

            //设置单元格宽度，是文字能够全部显示
            setCellMaxWidth(textValue, i, maxWidth);
            try {
                if (maxWidth[i] > 10000) {
                    sheet.setColumnWidth(i, 10000);    //设置单元格宽度
                }else {
                    sheet.setColumnWidth(i, maxWidth[i]);    //设置单元格宽度
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            row.setHeightInPoints((short) (20));   //设置单元格高度
            if (matcher.matches()) {
                // 是数字当作double处理
                cell.setCellValue(Double.parseDouble(textValue));
            } else {
                cell.setCellValue(textValue);
            }
        }
    }

    private void setCellMaxWidth(String textValue, int i, int[] maxWidth) {
        int size = textValue.length();
        int width = (size + 6) * 300;
        if (maxWidth[i] <= width) {
            maxWidth[i] = width;
        }
    }

    /**
     * 底栏
     */
    private HSSFRow createexportManagerHeader(String date, HSSFSheet sheet, HSSFCellStyle headerStyle, Integer index) {
        HSSFRow headRow = sheet.createRow(index);
        headRow.setHeightInPoints((short) (20));   //设置头部高度
        //添加数据
        HSSFCell cell = headRow.createCell(0);
        cell.setCellStyle(headerStyle);
        HSSFRichTextString text = new HSSFRichTextString(date);
        cell.setCellValue(text);
        return headRow;
    }
}
