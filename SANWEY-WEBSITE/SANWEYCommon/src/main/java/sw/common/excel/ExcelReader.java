package sw.common.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析Excel
 *
 */
public class ExcelReader {

    /**
     * 测试
     */
    @SuppressWarnings("rawtypes")
    public static List<Object> excelReadTest(String filePath, String[] properties, Class obj, int beginRow, int lastRow,int beginColumn) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("指定的文件不存在");
        }
        return excelRead(new FileInputStream(file), properties, obj, beginRow, lastRow,beginColumn);
    }

    /**
     * 支持解析EXCEL2007
     *
     * @param inputStream 文件流
     * @param properties  对象映射属性
     * @param obj         映射对象
     * @param beginRow    注入开始忽略行
     * @param lastRow     注入最后忽略行
     * @param beginColumn 注入开始忽略列
     * @return 结果集
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static List<Object> excelRead(InputStream inputStream, String[] properties, Class obj, int beginRow, int lastRow,int beginColumn) throws Exception {
        Workbook book = null;
//        try {
//            book = new XSSFWorkbook(inputStream);     //解析2007
//        } catch (Exception e) {
            book = new HSSFWorkbook(inputStream);      //解析2003
//        }
        return getExcelContent(book, properties, obj,beginRow,lastRow,beginColumn);
    }

    /**
     * 根据params、object解析Excel，并且构建list集合
     *
     * @param book       WorkBook对象，他代表了待将解析的Excel文件
     * @param properties 需要参考Object的属性
     *                   构建的Object对象，每一个row都相当于一个object对象
     * @return
     * @throws Exception
     * @autor:chenssy
     * @date:2014年8月9日
     */
    @SuppressWarnings("rawtypes")
    private static List<Object> getExcelContent(Workbook book, String[] properties, Class obj, int beginRow, int lastRow,int beginColumn) throws Exception {
        List<Object> resultList = new ArrayList<Object>();        //初始化结果解
        Map<String, Method> methodMap = getObjectSetterMethod(obj);
        Map<String, Field> fieldMap = getObjectField(obj);
        for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) {
            Sheet sheet = book.getSheetAt(numSheet);
            if (sheet == null) {   //谨防中间空一行
                continue;
            }
            for (int numRow = beginRow; numRow < sheet.getLastRowNum() + 1-lastRow; numRow++) {   //一个row就相当于一个Object
                Row row = sheet.getRow(numRow);
                if (row == null) {
                    continue;
                }
                resultList.add(getObject(row, properties, methodMap, fieldMap, obj,beginColumn));
            }
        }
        return resultList;
    }

    /**
     * 获取row的数据，利用反射机制构建Object对象
     *
     * @param row        对象
     * @param properties 参考的属性
     * @param methodMap  对象的setter方法映射
     * @param fieldMap   对象的属性映射
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private static Object getObject(Row row, String[] properties, Map<String, Method> methodMap, Map<String, Field> fieldMap, Class obj,int beginColumn) throws Exception {
        Object object = obj.newInstance();
        //最后一列
        int lastCellNum=row.getLastCellNum();
        if (properties.length > 0 && properties.length+beginColumn < lastCellNum){
            lastCellNum=properties.length+beginColumn;
        }
        for (int numCell = beginColumn; numCell < lastCellNum; numCell++) {
            Cell cell = row.getCell(numCell);
            if (cell == null) {
                continue;
            }
            String property = properties[numCell-beginColumn].toLowerCase();
            Field field = fieldMap.get(property);    //该property在object对象中对应的属性
            Method method = methodMap.get(property);  //该property在object对象中对应的setter方法
            String type = field.getType().getName();
            String cellValue = null;
            if ("java.util.Date".equals(type)) {
                cellValue = dateTransform(cell);
            } else {
                cellValue = getValue(cell);
            }
            setObjectPropertyValue(object, field, method, cellValue);
        }
        return object;
    }

    //日期转换
    private static String dateTransform(Cell cell) {
        //02-一月-1900
        String head = String.valueOf(cell).substring(0, 2);
        String body = String.valueOf(cell).substring(3, 5);
        String bottom = String.valueOf(cell).substring(5, 10);
        switch (body) {
            case "一月":
                body = "01";
                break;
            case "二月":
                body = "02";
                break;
            case "三月":
                body = "03";
                break;
            case "四月":
                body = "04";
                break;
            case "五月":
                body = "05";
                break;
            case "六月":
                body = "06";
                break;
            case "七月":
                body = "07";
                break;
            case "八月":
                body = "08";
                break;
            case "九月":
                body = "09";
                break;
            case "十月":
                body = "10";
                break;
            case "十一月":
                body = "11";
                break;
            case "十二月":
                body = "12";
                break;
        }
        return head + body + bottom;
    }

    /**
     * 根据指定属性的的setter方法给object对象设置值
     *
     * @param obj    对象
     * @param field  对象的属性
     * @param method 对象属性的相对应的方法
     * @param value  需要设置的值
     * @throws Exception
     */
    private static void setObjectPropertyValue(Object obj, Field field, Method method, String value) throws Exception {
        Object[] oo = new Object[1];
        String type = field.getType().getName();
        if ("java.lang.String".equals(type) || "String".equals(type)) {
            oo[0] = value;
        } else if ("java.lang.Integer".equals(type) || "java.lang.int".equals(type) || "Integer".equals(type) || "int".equals(type)) {
            if (value.length() > 0)
                oo[0] = Integer.valueOf(value);
        } else if ("java.lang.Float".equals(type) || "java.lang.float".equals(type) || "Float".equals(type) || "float".equals(type)) {
            if (value.length() > 0)
                oo[0] = Float.valueOf(value);
        } else if ("java.lang.Double".equals(type) || "java.lang.double".equals(type) || "Double".equals(type) || "double".equals(type)) {
            if (value.length() > 0)
                oo[0] = Double.valueOf(value);
        } else if ("java.math.BigDecimal".equals(type) || "BigDecimal".equals(type)) {
            if (value.length() > 0)
                oo[0] = new BigDecimal(value);
        } else if ("java.util.Date".equals(type) || "Date".equals(type)) {
            if (value.length() > 0) {//当长度为19(yyyy-MM-dd HH24:mm:ss)或者为14(yyyyMMddHH24mmss)时Date格式转换为yyyyMMddHH24mmss
                if (value.length() == 19 || value.length() == 14) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH24mmss");
                    oo[0] = simpleDateFormat.format(value);
                } else {     //其余全部转换为yyyyMMdd格式
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                    oo[0] = simpleDateFormat.format(value);
                }
            }
        } else if ("java.sql.Timestamp".equals(type)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH24mmss");
            if (value.length() > 0)
                oo[0] = simpleDateFormat.format(value);
        } else if ("java.lang.Boolean".equals(type) || "Boolean".equals(type)) {
            if (value.length() > 0)
                oo[0] = Boolean.valueOf(value);
        } else if ("java.lang.Long".equals(type) || "java.lang.long".equals(type) || "Long".equals(type) || "long".equals(type)) {
            if (value.length() > 0)
                oo[0] = Long.valueOf(value);
        }
        try {
            method.invoke(obj, oo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(Cell cell) {
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return NumberToTextConverter.toText(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 获取object对象所有属性的Setter方法，并构建map对象，结构为Map<'field','method'>
     *
     * @param object 对象
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Method> getObjectSetterMethod(Class object) {
        Field[] fields = object.getDeclaredFields();       //获取object对象的所有属性
        Method[] methods = object.getDeclaredMethods();    //获取object对象的所有方法
        Map<String, Method> methodMap = new HashMap<String, Method>();
        for (Field field : fields) {
            String attr = field.getName();
            for (Method method : methods) {
                String meth = method.getName();
                //匹配set方法
                if (meth != null && "set".equals(meth.substring(0, 3)) && Modifier.isPublic(method.getModifiers()) && ("set" + Character.toUpperCase(attr.charAt(0)) + attr.substring(1)).equals(meth)) {
                    //将匹配的setter方法加入map对象中
                    methodMap.put(attr.toLowerCase(), method);
                    break;
                }
            }
        }
        return methodMap;
    }

    /**
     * 获取object对象的所有属性，并构建map对象，对象结果为Map<'field','field'>
     *
     * @param object 对象
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, Field> getObjectField(Class object) {
        //获取object对象的所有属性
        Field[] fields = object.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fields) {
            String attr = field.getName();
            fieldMap.put(attr.toLowerCase(), field);
        }
        return fieldMap;
    }
}
