package sw.core.spring.user;

/**
 * 用户类型
 * @author wwh
 * @date 2020年12月17日
 */
public enum UserType {
    ADMIN("平台管理员", 1, "ADMIN", "平台管理员"),
    DEVOPS("运维管理员", 2, "DEVOPS", "运维管理员"),
    CUADMIN("超级管理员", 3, "CUADMIN", "企业超级管理员"),
    CUUSERUP("系统管理员", 4, "CUUSERUP", "企业管理员"),
    CUUSER("普通用户", 5, "CUUSER", "企业用户"),
    USER("平台用户", 6, "USER", "普通用户"),
    ENGINEER("工程师", 7, "ENGINEER", "工程师");

    private String sign;

    private int index;

    private String code;

    private String explain;

    UserType(String sign, int index, String code, String explain) {
        this.sign = sign;
        this.index = index;
        this.code = code;
        this.explain = explain;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
