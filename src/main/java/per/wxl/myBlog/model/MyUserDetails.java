package per.wxl.myBlog.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 17:12
 * @Description:
 */
public class MyUserDetails extends User {
    private Integer userId;
    private List<String> roles;
    public MyUserDetails(per.wxl.myBlog.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUserName(), user.getUserPassword(), user.isUserStatus(), true, true
                , true, authorities);
        this.userId=user.getUserId();
    }

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public List<String> getRoles() {
        List<String> roles=new ArrayList<>();
        for(GrantedAuthority authority:this.getAuthorities())
            roles.add(authority.getAuthority());
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
