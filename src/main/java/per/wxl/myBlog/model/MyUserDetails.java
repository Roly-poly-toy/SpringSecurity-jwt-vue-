package per.wxl.myBlog.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 17:12
 * @Description:
 */
public class MyUserDetails extends User {
    private String userPhoto;
    public MyUserDetails(per.wxl.myBlog.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUserName(), user.getUserPassword(), true, true, true
                , user.isUserStatus(), authorities);
        this.userPhoto=user.getUserPhoto();
    }
}
