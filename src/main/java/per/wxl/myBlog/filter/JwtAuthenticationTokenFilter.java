package per.wxl.myBlog.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import per.wxl.myBlog.config.JwtConfig;
import per.wxl.myBlog.config.MyAuthenticationFailureHandler;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.service.UserService;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 17:59
 * @Description: 登录的用户访问接口时的过滤器 作用：取得header中的Authorization,值为包含用户信息的token,然后
 * 存入SpringSecurity的上下文中
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
   private JwtConfig jwtConfig;

    @Autowired
   private UserService userService;
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AccountExpiredException {
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
            }else{
                failureHandler.onAuthenticationFailure(request,response,new AccountExpiredException("token过期，请重新登陆"));
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
