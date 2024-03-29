package per.wxl.myBlog.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: wxl
 * @Date: 2019/9/5 20:29
 * @Description:
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    UserService userService;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Result result=new Result();
        if(authentication!=null){
            String username=((MyUserDetails)authentication.getPrincipal()).getUsername();
            userService.updateUserStatusByName(username,true);
            result.setCode(200);
            result.setMessage("成功退出");

        }else {
            result.setCode(201);
            result.setMessage("请先登录");
        }
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
