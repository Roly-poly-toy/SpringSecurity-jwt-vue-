package per.wxl.myBlog.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import per.wxl.myBlog.config.JwtConfig;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 17:59
 * @Description: 登录的用户访问接口时的过滤器 作用：取得header中的Authorization,值为包含用户信息的token
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader(jwtConfig.getHeader());
        if(token!=null&&token.startsWith(jwtConfig.getPrefix())){
            MyUserDetails myUserDetails=userService.loadUserByToken(token);
            if(myUserDetails!=null){
                if(SecurityContextHolder.getContext().getAuthentication()==null){
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(myUserDetails,null,myUserDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }else throw new AccountExpiredException("账户登录过期，请重新登陆！");
        }
        filterChain.doFilter(request,response);
    }
}
