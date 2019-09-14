package per.wxl.myBlog.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.model.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: wxl
 * @Date: 2019/9/5 19:28
 * @Description: 认证失败处理类 原因:1.用户名不存在  2.用户名与密码不匹配  3.用户因非法操作被锁定
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String str="";
        if(e.getMessage().equals("Bad credentials")) str="用户名与密码不匹配";
        else if(e.getMessage().equals("User is disabled")) str="用户已登录";
        Result result=new Result(400,str);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
