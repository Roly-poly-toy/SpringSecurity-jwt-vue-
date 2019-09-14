package per.wxl.myBlog.dao;

import org.apache.ibatis.annotations.Param;
import per.wxl.myBlog.model.Blog;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 23:35
 * @Description:
 */
public interface BlogDao {
    void saveBlog(Blog blog);

    void saveBlogTag(@Param("blogId") Integer blogId,@Param("tagId") Integer tagId);
}
