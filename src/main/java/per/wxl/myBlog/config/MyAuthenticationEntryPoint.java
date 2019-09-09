package per.wxl.myBlog.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.model.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: wxl
 * @Date: 2019/9/5 19:26
 * @Description: 未登录的用户非法访问接口，触发的回调
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result result=new Result(300,"请登录");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
