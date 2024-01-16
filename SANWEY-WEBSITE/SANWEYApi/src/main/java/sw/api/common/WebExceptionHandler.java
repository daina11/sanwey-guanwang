package sw.api.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sw.biz.web.MessageFroException;
import sw.core.web.Message;
import sw.core.web.MessageException;
import sw.core.web.SystemException;

/**
 * 异常捕捉控制
 * @author wwh
 * @date 2020年12月16日
 */
@Log4j2
@ControllerAdvice
public class WebExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {MessageException.class})
    public Message exception(MessageException e){
        return Message.warning(e.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = {SystemException.class})
    public Message exception(SystemException e){
        e.printStackTrace();
        return MessageFroException.error(e.getMsg(), e);
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public Message exception(Exception e){
        e.printStackTrace();
        return MessageFroException.error("服务器异常,请稍后再试", e);
    }
}
