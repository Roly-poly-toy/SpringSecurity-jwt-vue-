package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.User;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 12:11
 * @Description:
 */
public interface UserDao {
    public User getUserByUsername(String username);
}
