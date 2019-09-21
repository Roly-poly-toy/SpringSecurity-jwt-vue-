package per.wxl.myBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.wxl.myBlog.dao.UserViewsDao;
import per.wxl.myBlog.model.UserViews;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/20 22:09
 * @Description:
 */
@Service
@Transactional
public class UserViewService {
    @Autowired
    private UserViewsDao userViewsDao;

    public UserViews getUserViewsById(Integer userId){
        return userViewsDao.getUserViewsById(userId);
    }

    public void saveUserViews(List<UserViews> list){
        userViewsDao.saveUserViews(list);
    }
}
