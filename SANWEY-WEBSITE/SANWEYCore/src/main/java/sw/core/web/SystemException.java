package sw.core.web;

/**
 * 系统运行异常处理
 * @author wwh
 * @date 2020年12月16日
 */
public class SystemException extends RuntimeException{

    /**异常编码*/
    private Integer code;
    /**异常信息*/
    private String msg;

    public SystemException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
