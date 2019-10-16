package per.wxl.myBlog.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import per.wxl.myBlog.config.EmailConfig;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.StatusCode;
import per.wxl.myBlog.model.User;
import per.wxl.myBlog.service.UserService;
import per.wxl.myBlog.utils.DataCheckUtil;
import per.wxl.myBlog.utils.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wxl
 * @Date: 2019/9/7 17:57
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    EmailConfig emailConfig;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Blog!";
    }

    @PostMapping("/register")
    public Result register(User user,String mailCode,String inviteCode){
        if(!DataCheckUtil.checkStringNull(user.getUserName()
        ,user.getUserPassword()
        ,user.getUserEmail()
        ,mailCode,inviteCode))
        return new Result(201,"注册失败，字段不能为空！");
        else{
            int flag=userService.register(user,mailCode,inviteCode);
            if(flag!=0){
                Result result=new Result(201,null);
                switch (flag){
                    case 1:result.setMessage("验证码错误！");break;
                    case 2:result.setMessage("邮箱已被注册！");break;
                    case 3:result.setMessage("邀请码无效！");break;
                    case 4:result.setMessage("用户名已存在！");break;
                }
                return result;
            }else return new Result(200,"注册成功，请前往登录");
        }
    }

    @PostMapping("/sendEmail")
    public Result sendEmail(String email){
        if(!DataCheckUtil.checkStringNull(email)||!DataCheckUtil.checkEmail(email))
            return new Result(201,"邮箱格式错误！");
        String emailCode= (String) redisTemplate.opsForValue().get(emailConfig.getPrefix()+email);
        if(emailCode!=null)
            return new Result(201,emailConfig.getExpiredTime()+"分钟内不可重复发送验证码！");
        else{
            userService.sendEmail(email);
            return new Result(200,"发送成功！");
        }
    }

    @PostMapping("/refreshToken")
    public Result refreshToken(String refreshToken){
        Map<String,String> data=new HashMap<>();
        try {
            data=jwtTokenUtil.refreshToken(refreshToken);
        }catch (UsernameNotFoundException e){
            return new Result(StatusCode.USERNOTFOUNT,"账号可能已被删除",null);
        }catch (ExpiredJwtException e){
            return new Result(StatusCode.REFRESHTOKENEXPIRED,"账号过期，请重新登录",null);
        }
        return new Result(200,"刷新token成功",data);
    }
    /* todo */

}
