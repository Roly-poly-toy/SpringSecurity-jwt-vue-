package per.wxl.myBlog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.config.JwtConfig;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.User;
import per.wxl.myBlog.service.UserService;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 17:19
 * @Description:
 */
@Component
public class JwtTokenUtil {
    @Autowired
    JwtConfig jwtConfig;
    @Autowired
    UserService userService;
    private Logger logger= LoggerFactory.getLogger(JwtTokenUtil.class);
    public String generateToken(MyUserDetails myUserDetails) {
        Map<String,Object> claims=new HashMap<>();
        String username=myUserDetails.getUsername();
        claims.put("username",username);
        claims.put("time",new Date());
        List<String> roles=new ArrayList<>();
        roles=myUserDetails.getRoles();
        claims.put("roles",roles);
        String token=Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+ jwtConfig.getExpiredTime()*1000))
                .signWith(SignatureAlgorithm.HS384,jwtConfig.getSecret())
                .compact();
        return token;
    }

    public String generateToken(String username){
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",username);
        String token=Jwts.builder().
                setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+jwtConfig.getRefreshTime()*1000))
                .signWith(SignatureAlgorithm.HS384,jwtConfig.getSecret())
                .compact();
        return token;
    }

    public String getUsernameFromToken(String authToken) {
        Claims claims=getClaimsFromToken(authToken);
        if(claims==null) return  null;
        else return (String) claims.get("username");

    }

    private Claims getClaimsFromToken(String token){
        Claims  claims=Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        return claims;
    }

    public List<String> getRolesFromToken(String authToken) {
        Claims claims=getClaimsFromToken(authToken);
        return (List<String>) claims.get("roles");
    }

    public Map<String,String> refreshToken(String refreshToken){
        String username=getUsernameFromToken(refreshToken);
        MyUserDetails myUserDetails= (MyUserDetails) userService.loadUserByUsername(username);
        Map<String,String> newToken=new HashMap<>();
        newToken.put("token","Bearer "+generateToken(myUserDetails));   // 生成15分钟新的access_token
        newToken.put("refreshToken",generateToken(username));   //生成7天新的refresh_token
        logger.info("刷新token成功");
        return newToken;
    }
}
