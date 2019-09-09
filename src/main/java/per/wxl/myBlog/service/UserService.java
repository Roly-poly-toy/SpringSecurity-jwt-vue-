package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.dao.RoleDao;
import per.wxl.myBlog.dao.UserDao;
import per.wxl.myBlog.model.MyUserDetails;
import per.wxl.myBlog.model.Role;
import per.wxl.myBlog.model.User;

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
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userDao.getUserByUsername(s);
        List<Role> roles=roleDao.getRolesByUserId(user.getUserId());
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(Role role:roles) authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return new MyUserDetails(user,authorities);
    }

    public MyUserDetails loadUserByToken(String token) {

    }
}
