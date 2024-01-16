package sw.api.common.pc;

import org.springframework.web.bind.annotation.*;
import sw.core.web.Message;

/**
 * @author dwg
 * Date: 2023/11/29
 */
@RestController
@CrossOrigin
@RequestMapping("/website")
public class testController {


    @GetMapping("/test")
    public Message add() {
       return Message.success("chengong ");
    }
}
