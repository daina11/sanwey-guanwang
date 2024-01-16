package sw.core.web;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 接口请求结果
 * @author wwh
 * @date 2020年12月29日
 */
public class Message implements Serializable {

    /**来自服务器响应的 HTTP 状态码*/
    private int status;

    /**来自服务器响应的 HTTP 信息*/
    private String msg;

    /**由服务器提供的响应数据*/
    private Object data;

    private Message() {
    }

    public Message(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Message(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**请求成功*/
    public static Message success(Object data){
        return new Message(HttpStatus.HTTP_OK, "请求成功", data);
    }

    public static Message success(String msg){
        return new Message(HttpStatus.HTTP_OK, msg);
    }

    /**请求警告*/
    public static Message warning(Object data){
        return new Message(HttpStatus.HTTP_BAD_REQUEST, "请求异常警告", data);
    }

    public static Message warning(String msg){
        return new Message(HttpStatus.HTTP_BAD_REQUEST, msg);
    }

    /**请求失败*/
    public static Message error(String msg){
        return new Message(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public static Message error(int status, String msg){
        return new Message(status, msg);
    }

    /**验证-返回是否请求成功*/
    @JsonIgnore
    public Boolean isSuccess(){
        return this.status == HttpStatus.HTTP_OK;
    }

    @JsonIgnore
    public Boolean isFail(){
        return this.status != HttpStatus.HTTP_OK;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
