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
        System.out.println(e.getClass());
        Result result;
        if(e.getMessage().equals("Bad credentials")){
            result=new Result(202,"用户名与密码不匹配");
        }
        else if(e.getMessage().equals("User is disabled")){
            result=new Result(203,"用户已登录");
        }
        else result=new Result(204,"用户已过期，请重新登录");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
