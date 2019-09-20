package per.wxl.myBlog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import per.wxl.myBlog.config.JwtConfig;
import per.wxl.myBlog.model.MyUserDetails;

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
    RedisTemplate redisTemplate;
    public String generateToken(MyUserDetails myUserDetails) {
        Map<String,Object> claims=new HashMap<>();
        String username=myUserDetails.getUsername();
        claims.put("username",username);
        claims.put("time",new Date());
        List<String> roles=new ArrayList<>();
        for(GrantedAuthority authority:myUserDetails.getAuthorities())
            roles.add(authority.getAuthority());
        claims.put("roles",roles);
        String token=Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+ jwtConfig.getTime()*1000))
                .signWith(SignatureAlgorithm.HS384,jwtConfig.getSecret())
                .compact();
        redisTemplate.opsForValue().set(JwtConfig.REDIS_TOKEN_KEY_PREFIX+username
        ,jwtConfig.getPrefix()+token,jwtConfig.getTime(), TimeUnit.SECONDS);
        return token;
        
    }

    public String getUsernameFromToken(String authToken) {
        Claims claims=getClaimsFromToken(authToken);
        if(claims==null) return  null;
        else return (String) claims.get("username");

    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try {
            claims=Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims=null;
        }
        return claims;
    }

    public List<String> getRolesFromToken(String authToken) {
        Claims claims=getClaimsFromToken(authToken);
        return (List<String>) claims.get("roles");
    }
}
