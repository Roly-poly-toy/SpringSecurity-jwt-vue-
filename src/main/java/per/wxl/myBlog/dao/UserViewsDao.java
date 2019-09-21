package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.UserViews;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/20 22:01
 * @Description:
 */
public interface UserViewsDao {
    UserViews getUserViewsById(Integer userId);
    void saveUserViews(List<UserViews> list);
}
