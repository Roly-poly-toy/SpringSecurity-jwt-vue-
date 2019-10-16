package per.wxl.myBlog.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import per.wxl.myBlog.config.JwtConfig;
import per.wxl.myBlog.config.MyAuthenticationFailureHandler;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.Result;
import per.wxl.myBlog.model.StatusCode;
import per.wxl.myBlog.service.UserService;
import per.wxl.myBlog.utils.JwtTokenUtil;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private List<RequestMatcher> requireAuthenticationRequestMatcher=new ArrayList<>();

    private Logger logger= LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);  //
    public JwtAuthenticationTokenFilter(){
        setRequestMatcher("/user/refreshToken","/blog/getAllBlog");
    }

    private void setRequestMatcher(String...patterns) {
        for(String pattern:patterns)
            requireAuthenticationRequestMatcher.add(new AntPathRequestMatcher(pattern));
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AccountExpiredException {
        boolean flag=false;
        for(int i=0;i<requireAuthenticationRequestMatcher.size()&&!flag;i++){
            if(requireAuthenticationRequestMatcher.get(i).matches(request)) flag=true;
        }
        if(flag)
            filterChain.doFilter(request,response);
        else{
            String token=request.getHeader(jwtConfig.getHeader());
            if(token!=null&&token.startsWith(jwtConfig.getPrefix())){
                token=token.substring(jwtConfig.getPrefix().length());
                UserDetails myUserDetails=null;
                try {
                     myUserDetails=userService.loadUserByToken(token);
                }catch (ExpiredJwtException e){
                   response.getWriter().write(JSON.toJSONString(new Result(StatusCode.EXPIRED,"expired",null)));
                   return;
                }catch (SignatureException e){
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(new Result(StatusCode.ERROR,"非法操作",null)));
                    return;
                }
                if(SecurityContextHolder.getContext().getAuthentication()==null){
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(myUserDetails,null,myUserDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }
    }
}
