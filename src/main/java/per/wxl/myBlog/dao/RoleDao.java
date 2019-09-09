package per.wxl.myBlog.dao;

import per.wxl.myBlog.model.Role;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/9 12:25
 * @Description:
 */
public interface RoleDao {
    public List<Role> getRolesByUserId(Integer userId);
}
