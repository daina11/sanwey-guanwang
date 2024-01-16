package sw.biz.enums;

/**
 * @author dwg
 * Date: 2024/1/3
 */
public enum ContentDetailTypeEnum {
    PRODUCT("产品介绍",1,"PRODUCT"),
    CUSTOMER("客户案例",2,"CUSTOMER"),
    INDUSTRY("行业资讯",3,"INDUSTRY"),
    ;

    /** 名称 */
    private String sign;
    /** 值 */
    private int value;
    /** 编码 */
    private String code;


    ContentDetailTypeEnum(String sign, int value, String code) {
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
