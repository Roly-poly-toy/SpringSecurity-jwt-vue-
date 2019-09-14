package per.wxl.myBlog.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.User;
import per.wxl.myBlog.service.UserService;
import per.wxl.myBlog.utils.JwtTokenUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: wxl
 * @Date: 2019/9/5 19:29
 * @Description: 登录认证成功后的处理类 认证成功后 1.给用户颁发一个token令牌 2.将用户状态置为false(da=0)防止重复登录
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Result result=new Result(200,"登陆成功");
        MyUserDetails myUserDetails= (MyUserDetails) authentication.getPrincipal();
        String username=myUserDetails.getUsername();
        userService.updateUserStatusByName(username,false);
        Map<String,Object> map=new HashMap<>(2);
        map.put("user",myUserDetails);
        map.put("token","Bearer "+jwtTokenUtil.generateToken(myUserDetails));
        result.setData(map);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
