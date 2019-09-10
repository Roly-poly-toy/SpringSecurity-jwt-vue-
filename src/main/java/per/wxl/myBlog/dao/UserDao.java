package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.User;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 12:11
 * @Description:
 */
public interface UserDao {
    User getUserByUsername(String username);

    User getUserByEmail(String userEmail);

    void saveUser(User user);
}
