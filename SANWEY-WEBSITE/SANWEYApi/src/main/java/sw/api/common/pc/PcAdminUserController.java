package sw.api.common.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sw.biz.srv.entity.AdminUser;
import sw.biz.srv.repository.IAdminUserRepository;
import sw.biz.srv.service.IAdminUserService;
import sw.core.jwt.JwtUtils;
import sw.core.web.Message;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dwg
 * Date: 2023/11/24
 */
@RestController
@CrossOrigin
@RequestMapping("/web/admin")
public class PcAdminUserController {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public PcAdminUserController(JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Resource
    private IAdminUserService iWareHouseService;
    @Resource
    private IAdminUserRepository iAdminUserRepository;

    @PostMapping("/register")
    public Message add(@RequestBody AdminUser user){
        if( iAdminUserRepository.findByUsername(user.getUsername())!=null){
            return Message.warning("用户名已存在");
        }

        user.setPassword( passwordEncoder.encode(user.getPassword()));
        iAdminUserRepository.save(user);
        return Message.success("注册成功");
    }
    @PostMapping("/login")
    public Message login(@RequestBody AdminUser user){
        AdminUser storedUser = iAdminUserRepository.findByUsername(user.getUsername());

        if (storedUser != null && passwordEncoder.matches(user.getPassword(), storedUser.getPassword() )) {
            String token = jwtUtils.generateToken(storedUser.getUsername());
            //创建一个map存一个key叫token
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);
            return Message.success(tokenMap);
        } else {
            return Message.error("用户名或密码不正确");
        }
    }
    @GetMapping("/getinfo")
    public Message getinfo(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7); // 去除 Bearer 前缀
        //验证token是否有效
        if (jwtUtils.validateToken(token)) {
            String username = jwtUtils.getUsernameFromToken(token);
            // 在这里根据用户名获取用户信息并返回
            Map<String, String> userMap = new HashMap<>();
            userMap.put("username", username);
            return Message.success(userMap);
        }
        // Token验证失败或拦截器处理失败，返回错误信息
        return Message.error("获取用户信息失败");
    }
    @GetMapping("/logout")
    public Message logout(){

        // Token验证失败或拦截器处理失败，返回错误信息
        return Message.success("123");
    }
    @GetMapping("/test")
    public Message test(){
        return Message.success("test");
    }
}
