package sw.core.constant;

/**
 * 系统静态信息配置
 * @author wwh
 * @date 2020年12月14日
 */
public class SystemStaticConfig {

    /**字符编码*/
    public static final String ENCODING = "UTF-8";

    /**系统名称*/
    public static final String WEBSITE_NAME = "配件库管理";

    /**文件上传-存储路径*/
    public static final String FILE_UPLOAD_SAVE_PATH = "D:\\sanwey\\upload\\";;

    /** 日期格式配比 */
    public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

    /**邮件地址验证正则表达式*/
    public static final String EMAIL_REGEX = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

    /**默认排序字段，创建日期*/
    public static final String defaultOrderByColumn = "createDate";

    /**WebSocket传输识别码useId*/
    public static final String WEBSOCKET_USERID = "userId";

    /**服务订单-质保期小于3个月，不反写设备卡片的延保日期*/
    public static final int SERVICE_ORDER_QA = 3;
}
