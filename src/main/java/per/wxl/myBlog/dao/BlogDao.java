package per.wxl.myBlog.dao;

import org.apache.ibatis.annotations.Param;
import per.wxl.myBlog.model.Blog;

import java.util.List;

/**
 * @Auther: wxl
 * @Date: 2019/9/14 23:35
 * @Description:
 */
public interface BlogDao {
    void saveBlog(Blog blog);

    void saveBlogTag(@Param("blogId") Integer blogId,@Param("tagId") Integer tagId);

    List<Blog> getAllBlog();

    Blog getBlogById(Integer blogId);
}
