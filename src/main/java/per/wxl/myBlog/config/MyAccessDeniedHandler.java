package per.wxl.myBlog.config;

import com.alibaba.fastjson.JSON;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.model.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: wxl
 * @Date: 2019/9/5 19:19
 * @Description:登录认证成功后，访问权限不足后的异常回调
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Result result=new Result(403,"权限不足");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
