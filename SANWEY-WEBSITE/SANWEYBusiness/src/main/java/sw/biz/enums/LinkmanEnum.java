package sw.biz.enums;

/**
 * @author dwg
 * Date: 2023/12/26
 */
public enum LinkmanEnum {
    COMPLETED("已处理",1,"COMPLETED"),
    UNCOMPLETED("未处理",2,"UNCOMPLETED"),
            ;

    /** 名称 */
    private String sign;
    /** 值 */
    private int value;
    /** 编码 */
    private String code;


    LinkmanEnum(String sign, int value, String code) {
        this.sign = sign;
        this.value = value;
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
