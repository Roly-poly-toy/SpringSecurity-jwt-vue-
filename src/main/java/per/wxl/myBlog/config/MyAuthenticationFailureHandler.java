package per.wxl.myBlog.config;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.StatusCode;

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
        Result result;
        if(e.getClass()== UsernameNotFoundException.class)
            result=new Result(StatusCode.USERNOTFOUNT,"不存在此用户");
        else if(e.getClass()== BadCredentialsException.class)
            result=new Result(StatusCode.NOTMATCH,"用户名与密码不匹配");
        else if(e.getClass()== DisabledException.class)
            result=new Result(StatusCode.DISABLED,"用户已登录");
        else if(e.getClass()== AccountExpiredException.class)
            result=new Result(StatusCode.EXPIRED,"用户已过期，请重新登录");
        else result=new Result(StatusCode.WEBERROR,"服务器异常");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
