package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.config.JwtConfig;
import per.wxl.myBlog.dao.RoleDao;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.Role;
import per.wxl.myBlog.model.User;
import per.wxl.myBlog.utils.JwtTokenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 12:10
 * @Description:
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userDao.getUserByUsername(s);
        if(user==null)
            throw new UsernameNotFoundException("不存在此用户！");
        List<Role> roles=roleDao.getRolesByUserId(user.getUserId());
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(Role role:roles) authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return new MyUserDetails(user,authorities);
    }

    public MyUserDetails loadUserByToken(String token) {
        String authToken=token.substring(jwtConfig.getPrefix().length());
        String username=jwtTokenUtil.getUsernameFromToken(authToken);
        if(username==null) return null;
        String redisToken= (String) redisTemplate.opsForValue().get(JwtConfig.REDIS_TOKEN_KEY_PREFIX+username);
        if(redisToken==null||!redisToken.equals(authToken)) return null;
        List<String> roles=jwtTokenUtil.getRolesFromToken(authToken);
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(String role:roles)
            authorities.add(new SimpleGrantedAuthority(role));
        return new MyUserDetails(username,null,authorities);
    }
}
