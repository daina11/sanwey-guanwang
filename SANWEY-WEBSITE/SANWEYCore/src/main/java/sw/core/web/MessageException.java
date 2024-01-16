package sw.core.web;

import cn.hutool.http.HttpStatus;

/**
 * 异常信息提示
 * @author wwh
 * @date 2020年12月16日
 */
public class MessageException extends RuntimeException{

    /**异常编码*/
    private Integer code;
    /**异常信息*/
    private String msg;

    public MessageException(String msg){
        super(msg);
        this.code = HttpStatus.HTTP_BAD_REQUEST;
        this.msg = msg;
    }

    public MessageException(Integer code, String msg){
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
